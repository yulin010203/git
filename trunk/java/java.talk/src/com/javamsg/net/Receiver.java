package com.javamsg.net;

import static com.javamsg.CommandDefinition.IPMSG_ANSENTRY;
import static com.javamsg.CommandDefinition.IPMSG_BR_ABSENCE;
import static com.javamsg.CommandDefinition.IPMSG_BR_ENTRY;
import static com.javamsg.CommandDefinition.IPMSG_BR_EXIT;
import static com.javamsg.CommandDefinition.IPMSG_COMMASK;
import static com.javamsg.CommandDefinition.IPMSG_DELMSG;
import static com.javamsg.CommandDefinition.IPMSG_DIALUPOPT;
import static com.javamsg.CommandDefinition.IPMSG_FILEATTACHOPT;
import static com.javamsg.CommandDefinition.IPMSG_GETINFO;
import static com.javamsg.CommandDefinition.IPMSG_OPTMASK;
import static com.javamsg.CommandDefinition.IPMSG_READMSG;
import static com.javamsg.CommandDefinition.IPMSG_RECVMSG;
import static com.javamsg.CommandDefinition.IPMSG_SENDCHECKOPT;
import static com.javamsg.CommandDefinition.IPMSG_SENDMSG;

import static com.javamsg.CommonConstant.DEFAULT_BUFFER_SIZE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.PortUnreachableException;
import java.net.SocketTimeoutException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.javamsg.IEventReceiver;
import com.javamsg.beans.DialupUserList;
import com.javamsg.beans.FileBean;
import com.javamsg.beans.SystemData;
import com.javamsg.beans.UserBean;
import com.javamsg.beans.UserList;

/**
 * @author soyang
 *
 */
public class Receiver implements Runnable{
        private static final SystemData data = SystemData.instance();
        private static final Logger logger = Logger.getRootLogger(); 
        
        //private SystemData data;
        private UserList userList = UserList.instance();
        
        private DatagramSocket udpSocket;
        private Sender sender;
        
        private IEventReceiver eventReceiver;
        
        private boolean flag = true;            // indicates the status of this thread
        
        public Receiver(DatagramSocket udpSocket, Sender sender){
                this.udpSocket = udpSocket ;
                this.sender = sender ;
        }
        public void stop(){
                flag = false;
        }
        public void close(){
                udpSocket.close();
        }
        
        @Override
        public void run(){
                logger.debug("Start Receiving UDP Packet Thread ...");
                flag = true;
                while(flag){
                        this.receivePacket();
                }
                flag = false;
                logger.debug("Exit Receiving UDP Packet Thread ...");
        }
        public void setEventReceiver(IEventReceiver er){
                this.eventReceiver = er;
        }
        
        public void receivePacket(){
                // int buffLength = DEFAULT_BUFFER_SIZE;
                byte[] buff = new byte[DEFAULT_BUFFER_SIZE];
                DatagramPacket packet = new DatagramPacket(buff, DEFAULT_BUFFER_SIZE);
                try{
                        logger.debug("begin receive packet");
                        udpSocket.receive(packet);
                        // TODO check encoding issue, should this be encoded with friend's charset?
                        String message = new String(packet.getData(), 0, packet.getLength());
                        logger.debug("receive message : " + message);
                        if(message.indexOf(":") == -1){
                                return;
                        }
                        // TODO implement file receive
                        String[] fields = message.split(":");
                        String version = fields[0];
                        long packetNumber = Long.parseLong(fields[1]);
                        String ip = packet.getAddress().getHostAddress();
                        int port = packet.getPort();
                        UserBean user = new UserBean(version, fields[2], fields[3], ip, port);
                        // use sender name as default nick name
                        user.setNickName(fields[2]);
                        long commandField = Long.parseLong(fields[4]);
                        long opt = commandField & IPMSG_OPTMASK ;
                        long command = commandField & IPMSG_COMMASK;
                        
                        switch((int)command){
                        case (int)IPMSG_BR_ENTRY :
                                if(fields[5] != null){
                                        user.setNickName(fields[5]);
                                }else{
                                        user.setNickName(fields[2]);
                                }
                                this.receiveEntry(user, packetNumber, opt);
                                break;
                        case (int)IPMSG_BR_EXIT :
                                this.receiveExit(user);
                                break ;
                        case (int)IPMSG_ANSENTRY :
                                if(fields[5] != null){  
                                        user.setNickName(fields[5]);
                                }else{
                                        user.setNickName(fields[2]);
                                }
                                this.receiveAnswerEntry(user, opt);
                                break ;
                        case (int)IPMSG_BR_ABSENCE :
                                this.receiveAbsence(user, opt);
                                break;
                        case (int)IPMSG_SENDMSG :
                                UserBean tempUser = userList.getUserByIpAddress(user.getIpAddress());
                                if(tempUser != null){
                                        // find user in user list
                                        user = tempUser;
                                }
                                if((opt & IPMSG_SENDCHECKOPT) != 0){
                                        sender.sendReceiveMessage( user.getIpAddress(), packetNumber);
                                }
                                // user = UserList.instance().getUserByIpAddress(user.getIpAddress());
                                if((opt & IPMSG_FILEATTACHOPT) != 0){
                                        // with attachments
                                        this.receiveAttachments(packet.getData(), packet.getLength(), user);
                                }else{
                                        this.receiveMessage(user, fields[5], opt, packetNumber);
                                }
                                
                                break ;
                        case (int)IPMSG_RECVMSG :
                                this.receiveRecvMessage(packetNumber, ip);
                                break ;
                        case (int)IPMSG_READMSG :
                                this.receiveReadMessage();
                                break;
                        case (int)IPMSG_DELMSG :
                                this.receiveDeleteMessage();
                                break;
                        case (int)IPMSG_GETINFO :
                                this.receiveGetInfoMessage(user);
                                break;
                        default:
                                this.otherCommand();
                        }
                        
                }catch(SocketTimeoutException ste){
                        logger.error("socket timeout", ste);
                }catch(PortUnreachableException pue){
                        logger.error("port unreachable", pue);
                }catch(IllegalBlockingModeException ibme){
                        logger.error("illegal blocking mode", ibme);
                }catch(IOException ioe){
                        if(flag){
                                logger.error("I/O Error", ioe);
                        }
                }catch(Exception e){
                        logger.error("Receive Packet Error", e);
                }
        }
        private void receiveEntry(UserBean user, long packetNumber, long opt){
                // first add this user to user list
                UserList.instance().addUser(user);
                if((opt & IPMSG_DIALUPOPT) != 0){
                        DialupUserList.updateUser(user);
                }
                // second send IPMSG_ANSENTRY command to user
                sender.answerEnrty(data.getNickName(), user.getIpAddress(), ++packetNumber);
        }
        private void receiveExit(UserBean user){
                // remove this user from user list
                UserList.instance().removeUser(user);
                DialupUserList.removeUser(user);
        }
        private void receiveAnswerEntry(UserBean user, long opt){
                // first add this user to user list
                logger.debug("ip : " + user.getIpAddress() + " nickname : " + user.getNickName());
                UserList.instance().addUser(user);
                if((opt & IPMSG_DIALUPOPT) != 0){
                        DialupUserList.updateUser(user);
                }
                // TODO Send request to get public key
                
        }
        private void receiveAbsence(UserBean user, long opt){
                UserList.instance().updateUser(user);
                if((opt & IPMSG_DIALUPOPT) != 0){
                        DialupUserList.updateUser(user);
                }
        }
        private void receiveMessage(UserBean user, String extraInfo, long optCommand, long packetNumber){
                // TODO add this user into user list ?
                /*
                if((optCommand & IPMSG_SENDCHECKOPT) != 0){
                        sender.sendReceiveMessage( user.getIpAddress(), packetNumber);
                }
                */
                // TODO add absence function here
                // Notify GUI there is a message
                eventReceiver.receiveMessage(user, extraInfo);
                
        }
        private void receiveAttachments(byte[] message, long length, UserBean user){
                final String methodName = "Receiver::receiveAttachments";
                logger.info("Enter method : " + methodName);
                
                byte fileInfoSeparator = 7;             // character '\a'
                byte zero = 0;                                  // character '\0'
                byte fieldSeparater = 58 ;              // character ':'
                
                /******************* print message for test ************************/
                StringBuffer tempBuffer = new StringBuffer();
                String tempChar = "";
                for(int index = 0; index < length; index++){
                        if(message[index] == fileInfoSeparator){
                                tempChar = "*7*";
                        }else if(message[index] == zero){
                                tempChar = "*0*";
                        }else{
                                tempChar = new String(message, index, 1);
                        }
                        tempBuffer.append(tempChar);
                }
                logger.debug("Message is " + tempBuffer.toString());
                /******************* print message for test ************************/
                
                // parse the message
                int offset = 0;
                String[] fields = new String[6];
                for(int i = 0, index = 0 ; i < length && index < 6 ; i++){
                        if(message[i] == fieldSeparater){
                                // is ':'
                                fields[index] = new String(message, offset, i-offset);
                                index++;
                                offset = i+1;
                        }else if(message[i] == zero){
                                fields[index] = new String(message, offset, i-offset);
                                index++;
                                offset = i+1;
                                break;
                        }
                }
                // parse message
                String version = fields[0];
                long packetNumber = Long.parseLong(fields[1]);
                String sender = fields[2];
                String host = fields[3];
                long command = Long.parseLong(fields[4]);
                String extraMessage = fields[5];
                logger.info("extra message : " + extraMessage);
                user.setVersion(version);
                user.setSender(sender);
                logger.info("sender : " + sender);
                user.setHost(host);
                // parse extra file info, file info begin from offset
                ArrayList<String> fileInfoList = new ArrayList<String>();
                for(int j = offset; j < length; j++){
                        if(message[j] == fileInfoSeparator){
                                // one file info 
                                String temp = new String(message, offset, j-offset-1);  // sub a ':'
                                logger.info("File Info : " + temp);
                                fileInfoList.add(temp);
                                offset = j + 1 ;                // and a ':'
                        }
                }
                FileBean[] fileAttrList = new FileBean[fileInfoList.size()];
                for(int m = 0 ; m < fileInfoList.size(); m++){
                        // get file info string
                        String fileAttr = fileInfoList.get(m);
                        String[] tempFields = fileAttr.split(":");
                        if(tempFields.length < 5){
                                // this file info is broken, skip it
                                continue;
                        }
                        FileBean tempFileAttr = new FileBean();
                        tempFileAttr.setFileNo(Integer.parseInt(tempFields[0]));
                        logger.debug("File Number : " + tempFileAttr.getFileNo());
                        tempFileAttr.setFileName(tempFields[1]);
                        logger.debug("File Name : " + tempFileAttr.getFileName());
                        // tempFields[2] is in hex format, transform it
                        tempFileAttr.setFileSize(Long.parseLong(tempFields[2], 16));
                        logger.debug("File Size : " + tempFileAttr.getFileSize());
                        tempFileAttr.setLastModifyTime(Long.parseLong(tempFields[3], 16));
                        logger.debug("File Last Modify Time : " + tempFileAttr.getLastModifyTime());
                        tempFileAttr.setFileAttr(Long.parseLong(tempFields[4], 16));
                        logger.debug("File Attribute : " + tempFileAttr.getFileAttr());
                        tempFileAttr.setPacketNumber(packetNumber);
                        // TODO handling other extra file attribute
                        fileAttrList[m] = tempFileAttr;
                }
                // TODO start TCP receiver thread, begin file transfer
                eventReceiver.receiveAttachment(user, extraMessage, fileAttrList);
                logger.info("Exit method : " + methodName);
        }
        private void receiveRecvMessage(long packetNumber, String ipAddress){
                // this packet is the response for the last send packet
                logger.debug("Receive RECVMSG in Receiver Thread");
                UserBean user = UserList.instance().getUserByIpAddress(ipAddress);
                // TODO if packetNumber == user.getPacketNumber ?
                user.setPacketNumber(0);
        }
        private void receiveReadMessage()
        {
                // TODO Notify the system the other read the message
        }
        private void receiveDeleteMessage(){
                // TODO Notify the system the other delete the message
        }
        private void receiveGetInfoMessage(UserBean user){
                sender.sendInfo(user.getIpAddress());
        }
        
        private void otherCommand(){
                logger.debug("Receive Other command");
        }

        public boolean isFlag() {
                return flag;
        }

        public void setFlag(boolean flag) {
                this.flag = flag;
        }
}
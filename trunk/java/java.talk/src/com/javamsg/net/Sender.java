package com.javamsg.net;

import static com.javamsg.CommandDefinition.IPMSG_ABSENCEOPT;
import static com.javamsg.CommandDefinition.IPMSG_ANSENTRY;
import static com.javamsg.CommandDefinition.IPMSG_AUTORETOPT;
import static com.javamsg.CommandDefinition.IPMSG_BROADCASTOPT;
import static com.javamsg.CommandDefinition.IPMSG_BR_ABSENCE;
import static com.javamsg.CommandDefinition.IPMSG_BR_ENTRY;
import static com.javamsg.CommandDefinition.IPMSG_BR_EXIT;
import static com.javamsg.CommandDefinition.IPMSG_DELMSG;
import static com.javamsg.CommandDefinition.IPMSG_DIALUPOPT;
import static com.javamsg.CommandDefinition.IPMSG_FILEATTACHOPT;
import static com.javamsg.CommandDefinition.IPMSG_GETINFO;
import static com.javamsg.CommandDefinition.IPMSG_READMSG;
import static com.javamsg.CommandDefinition.IPMSG_RECVMSG;
import static com.javamsg.CommandDefinition.IPMSG_SENDCHECKOPT;
import static com.javamsg.CommandDefinition.IPMSG_SENDINFO;
import static com.javamsg.CommandDefinition.IPMSG_SENDMSG;
import static com.javamsg.CommonConstant.DEFAULT_BUFFER_SIZE;
import static com.javamsg.CommonConstant.MAX_ATTEMPT_TIME;
import static com.javamsg.CommonConstant.PORT;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.javamsg.beans.SystemData;
import com.javamsg.beans.UserBean;
import com.javamsg.beans.UserList;
import com.javamsg.util.MyTools;
/**
 * @author soyang
 * use for send message
 */
public class Sender {
        private static final SystemData data = SystemData.instance();
        private static final Logger logger = Logger.getRootLogger(); 
        // private SystemData data;
        private DatagramSocket socket;
        private DatagramPacket packet;
        private InetAddress localAddress;
        // other side has receive the message
        private boolean isConfirmed;
        
        public Sender(DatagramSocket socket, InetAddress localAddress){
                this.socket = socket;
                this.localAddress = localAddress;
        }
        /**
         * Broadcast
         * Tell all nodes "I am coming!"
         */
        public void entry(){
                this.broadcast(IPMSG_BR_ENTRY | IPMSG_ABSENCEOPT, data.getNickName());
        }
        /**
         * Broadcast
         * Tell all nodes "I am going away..."
         */
        public void exit(){
                this.broadcast(IPMSG_BR_EXIT , "");
        }
        /**
         * Broadcast
         * My information changed.
         */
        public void absenceModeChange(){
                this.broadcast(IPMSG_BR_ABSENCE, data.getNickName());
        }
        /**
         * Broadcast 
         * send message to all nodes
         * @param extraInfo
         */
        public void sendMessageToAll(String extraInfo){
                this.broadcast(IPMSG_SENDMSG | IPMSG_BROADCASTOPT, extraInfo);
        }
        /**
         * Broadcast
         */
        public void sendGetInfoMessageToAll(){
                this.broadcast(IPMSG_GETINFO| IPMSG_BROADCASTOPT, "");
        }
        /**
         * broadcast the command
         * @param command
         * @param extraInfo
         */
        private void broadcast(long command, String extraInfo){
                byte[] buff = buildMessage(command, extraInfo, System.currentTimeMillis());
                packet = new DatagramPacket(buff, buff.length, this.getBroadcastAddress(), PORT);
                try{
                        socket.send(packet);
                }catch(IOException ioe){
                        logger.error("Broadcast Error!", ioe);
                }
        }
        /**
         * tool method, get broadcast address for broadcast methods
         * @return
         */
        private InetAddress getBroadcastAddress(){
                final String methodName = "Sender::getBroadcastAddress";
                logger.info("Enter method : " + methodName);
                InetAddress broadcastAddress = null ;
                try{
                        logger.info("Local Address : " + localAddress.getHostAddress());
                        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localAddress);
                        logger.info("Network Interface of " + localAddress.getHostAddress() + " is " + networkInterface.getDisplayName());
                        List<InterfaceAddress> addresses = networkInterface.getInterfaceAddresses();
                        for(InterfaceAddress address : addresses){
                                logger.info("Address of " + networkInterface.getDisplayName() + " " + address.getAddress().getHostAddress() );
                                if (address.getBroadcast() != null){
                                        broadcastAddress = address.getBroadcast();
                                        break;
                                }
                        }
                        if(broadcastAddress == null){
                                // TODO show a dialog, describe Error message
                                logger.error("Get broadcast address error! Broadcast Address is null in method getBroadcastAddress");
                                System.exit(1);
                        }
                }catch(SocketException se){
                        logger.error("Get broadcast address error!", se);
                }
                logger.info("Exit method : " + methodName);
                return broadcastAddress;
        }
        /**
         * New Message
         * Tell one special node "I am coming!"
         * Send IPMSG_ENTRY command to one node
         * @param extraInfo
         * @param ipAddress
         */
        public void dialUp(String extraInfo, String ipAddress){
                this.sendPacket( IPMSG_BR_ENTRY |IPMSG_DIALUPOPT | IPMSG_ABSENCEOPT , extraInfo, ipAddress);
        }
        public void sendDetectPacket(String ipAddress){
                this.sendPacket( IPMSG_BR_ENTRY |IPMSG_DIALUPOPT | IPMSG_ABSENCEOPT , data.getNickName(), ipAddress);
        }
        /**
         * Response
         * send answer entry command to one node
         * @param extraInfo
         * @param ipAddress
         */
        public void answerEnrty(String extraInfo, String ipAddress, long packetNumber){
                this.sendResponsePacket(IPMSG_ABSENCEOPT | IPMSG_ANSENTRY, extraInfo, ipAddress, packetNumber);
        }
        /**
         * New Message
         * Send Exit command to one Node
         * @param ipAddress
         */
        public void sendExit(String ipAddress){
                this.sendPacket(IPMSG_DIALUPOPT | IPMSG_BR_EXIT, "", ipAddress);
        }
        /**
         * New Message
         * Send Absence Command to one Node, tell Node "My Information changed."
         * @param extraInfo
         * @param ipAddress
         */
        public void sendAbsence(String extraInfo, String ipAddress){
                this.sendPacket(IPMSG_ABSENCEOPT | IPMSG_BR_ABSENCE, extraInfo, ipAddress);
        }
        /**
         * New Message
         * This Message need Response.
         * send message to one node
         * @param extraInfo
         * @param ipAddress
         * @return true if this message has been received by node, false if this message delivering failed.
         */
        public boolean sendMessage(String extraInfo, String ipAddress){
                // TODO check response message and retry to send this message.  
                UserBean user = UserList.instance().getUserByIpAddress(ipAddress);
                long packetNumber = System.currentTimeMillis();
                byte[] buff = buildMessage(IPMSG_SENDMSG | IPMSG_SENDCHECKOPT, extraInfo, packetNumber) ;
                // long packetNumber = this.sendPacket(IPMSG_SENDMSG | IPMSG_SENDCHECKOPT, extraInfo, ipAddress);
                user.setPacketNumber(packetNumber);
                logger.debug("Send packet number is " + packetNumber);
                int i = 0;
                do{
                        sendBytePacket( buff, ipAddress);
                        try {
                                Thread.sleep(100);
                        } catch (InterruptedException e) {
                                // continue;
                        }finally{
                                i++;
                        }
                }while(i < MAX_ATTEMPT_TIME && user.getPacketNumber() == packetNumber);
                if(user.getPacketNumber() == packetNumber){
                        // doesn't receive RECVMSG
                        logger.info("Your friend isn't online, Message delivering failed!");
                        return false;
                }
                // receive RECVMSG
                return true;
        }
        /**
         * Send message to a group
         * @param extraInfo
         * @param ipAddress
         */
        public void sendGroupMessage(String extraInfo, String ipAddress){
                this.sendPacket(IPMSG_SENDMSG | IPMSG_BROADCASTOPT, extraInfo, ipAddress);
        }
        /**
         * send FileInfo
         * @param extraInfo contains message and file extra info
         * @param ipAddress
         * @return
         */
        public boolean sendFileInfo(byte[] buffer, String ipAddress){
                final String methodName = "Sender::sendFileInfo";
                logger.info("Enter Method : " + methodName);
                
                UserBean user = UserList.instance().getUserByIpAddress(ipAddress);
                
                long packetNumber = System.currentTimeMillis();
                user.setPacketNumber(packetNumber);
                String message = data.getVersion() + ":" + packetNumber + ":" + data.getSender() +":" + data.getHost();
                message = message + ":" + (IPMSG_SENDMSG | IPMSG_FILEATTACHOPT  | IPMSG_SENDCHECKOPT) + ":";
                byte[] buff = new byte[DEFAULT_BUFFER_SIZE];
                ByteBuffer tempBuffer = ByteBuffer.wrap(buff);
                // ByteBuffer tempBuffer = ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
                tempBuffer.put(MyTools.getBytes(message, data.getEncoding())).put(buffer);
                // byte[] buff = new byte[DEFAULT_BUFFER_SIZE];
                // byte[] buff = new byte[tempBuffer.limit()];
                // tempBuffer.get(buff);
                byte[] realBytes = getRealBytes(buff, DEFAULT_BUFFER_SIZE);
                /******************* print message for test ************************/
                byte fileInfoSeparator = 7;             // character '\a'
                byte zero = 0;                                  // character '\0'
                byte fieldSeparater = 58 ;              // character ':'
                StringBuffer tempStringBuffer = new StringBuffer();
                String tempChar = "";
                for(int index = 0; index < realBytes.length; index++){
                        if(realBytes[index] == fileInfoSeparator){
                                tempChar = "*7*";
                        }else if(realBytes[index] == zero){
                                tempChar = "*0*";
                        }else{
                                tempChar = new String(buff, index, 1);
                        }
                        tempStringBuffer.append(tempChar);
                }
                logger.debug("Message is " + tempStringBuffer.toString());
                /******************* print message for test ************************/
                
                
                
                int i = 0;
                do{
                        this.sendBytePacket( buff, ipAddress);
                        try {
                                Thread.sleep(100);
                        } catch (InterruptedException e) {
                                // continue;
                        }finally{
                                i++;
                        }
                }while(i < MAX_ATTEMPT_TIME && user.getPacketNumber() == packetNumber);
                if(user.getPacketNumber() == packetNumber){
                        // doesn't receive RECVMSG
                        logger.info("Your friend isn't online, Message delivering failed!");
                        return false;
                }
                // receive RECVMSG
                return true;
                
        }
        /**
         * Response message
         * @param extraInfo
         * @param ipAddress
         * @param packetNumber
         */
        public void sendReadMessage(String extraInfo, String ipAddress, long packetNumber){
                this.sendResponsePacket(IPMSG_READMSG | IPMSG_AUTORETOPT, extraInfo, ipAddress, packetNumber);
        }
        /**
         * Response Message
         * @param extraInfo
         * @param ipAddress
         * @param packetNumber
         */
        public void sendDeleteMessage(String extraInfo, String ipAddress, long packetNumber){
                this.sendResponsePacket(IPMSG_DELMSG | IPMSG_AUTORETOPT, extraInfo, ipAddress, packetNumber);
        }
        /**
         * Response
         * send RECV command to one Node, notify friend "I receive your message"
         * @param ipAddress
         * @param packetNumber
         */
        public void sendReceiveMessage(String ipAddress, long packetNumber){
                this.sendResponsePacket(IPMSG_SENDCHECKOPT | IPMSG_RECVMSG, String.valueOf(packetNumber), ipAddress, packetNumber);
        }
        public void sendInfo(String ipAddress){
                String extraInfo = ""
                 + data.getVersion() + " ("
                 + System.getProperty("java.vendor") + " ver."
                 + System.getProperty("java.version") + "/"
                 + System.getProperty("os.name") + " "
                 + System.getProperty("os.version") + ")";
                this.sendPacket(IPMSG_SENDINFO, extraInfo, ipAddress);
        }
        /**
         * This method for "New Message"
         * Send packet to one Node
         * @param command
         * @param extraInfo
         * @param ipAddress
         */
        private long sendPacket(long command, String extraInfo, String ipAddress){
                long packetNumber = System.currentTimeMillis();
                byte[] buff = buildMessage(command, extraInfo, packetNumber) ;
                String testString = new String(buff, 0, buff.length);
                logger.debug(testString);
                sendBytePacket( buff, ipAddress);
                return packetNumber;
        }
        private void sendBytePacket(byte[] buffer, String ipAddress){
                try{
                        InetAddress address = InetAddress.getByName(ipAddress);
                        packet = new DatagramPacket(buffer, buffer.length, address, PORT);
                        socket.send(packet);
                }catch(UnknownHostException ue){
                        logger.error("UnknownHostException", ue);
                }catch(IOException ioe){
                        logger.error("I/O Error", ioe);
                }
        }
        /**
         * This method for "Response".
         * @param command
         * @param extraInfo
         * @param ipAddress
         * @param packetNumber
         */
        private void sendResponsePacket(long command, String extraInfo, String ipAddress, long packetNumber){
                byte[] buff ;
                if (packetNumber != -1){
                        buff = buildResponseMessage(command, extraInfo, packetNumber);
                }else{
                        buff = buildMessage(command, extraInfo, System.currentTimeMillis());
                }
                try{
                        InetAddress address = InetAddress.getByName(ipAddress);
                        packet = new DatagramPacket(buff, buff.length, address, PORT);
                        socket.send(packet);
                }catch(UnknownHostException ue){
                        logger.error("UnknownHostException", ue);
                }catch(IOException ioe){
                        logger.error("I/O Error", ioe);
                }
        }
        /**
         * build message : ver:NO:sender:host:command:extraInfo
         * @param command
         * @param extraInfo
         * @return
         */
        private byte[] buildMessage(long command, String extraInfo, long packetNumber){
                return buildResponseMessage(command, extraInfo, packetNumber);
        }
        /**
         * build response message, packet number is receive packet number+1
         * IMPORTANT INFORMATION: This method must be Thread Safe, OR just keep this object in one thread!!
         * @param command
         * @param extraInfo
         * @param packetNumber
         * @return
         */
        synchronized private byte[] buildResponseMessage(long command, String extraInfo, long packetNumber){
                long number = packetNumber + 1;
                // keep current packet number in System Data for check the response message
                // is for this packet or not!
                // Sender must be keep in one Thread, Or this will get wrong action!!!
                // data.setCurrentPacketNumber(number);
                String message = data.getVersion() + ":" + number + ":" + data.getSender() +":" + data.getHost();
                message = message + ":" + command + ":" + extraInfo ;
                // return message.getBytes();
                return MyTools.getBytes(message, data.getEncoding());
        }
        private byte[] getRealBytes(byte[] buff, int length){
                byte zero = 0;                                  // character '\0'
                int endPosition = 0;
                for(int i =0 ; i < DEFAULT_BUFFER_SIZE -1; i++){
                        if(buff[i] == zero && buff[i+1] == zero){
                                endPosition = i;
                                break;
                        }
                }
                return Arrays.copyOfRange(buff, 0, endPosition);
                
        }

        public boolean isConfirmed() {
                return isConfirmed;
        }

        public void setConfirmed(boolean isConfirmed) {
                this.isConfirmed = isConfirmed;
        }
}
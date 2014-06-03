package com.javamsg;

import static com.javamsg.CommonConstant.*;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Enumeration;

import org.apache.log4j.Logger;

import com.javamsg.beans.FileBean;
import com.javamsg.beans.SystemData;
import com.javamsg.net.Receiver;
import com.javamsg.net.Sender;
import com.javamsg.util.MyTools;

/**
 * @author soyang
 *
 */
public class Client {
        private static final Logger logger = Logger.getRootLogger();
        private static final SystemData data = SystemData.instance();
        
        private Sender sender;
        private Receiver receiver;
        
        private DatagramSocket udpSocket;
        private InetAddress localAddress ;
        
        
        public Client(){
                init();
                sender = new Sender(udpSocket, localAddress);
                receiver = new Receiver(udpSocket, sender);
                logger.debug("sender is null ? " + sender == null);
                logger.debug("receiver is null ? " + receiver == null);
        }
        public void close(){
                receiver.stop();
                receiver.close();
        }
        /**
         * when system startup, call this method
         */
        public void entry(){
                sender.entry();
        }
        
        public void startListeningUdpCommand(){
                Thread receiverThread = new Thread(receiver);
                receiverThread.start();
        }
        
        public boolean sendMessage(String extraInfo, String ipAddress){
                return sender.sendMessage(extraInfo, ipAddress);
        }
        public void sendDetectPacket(String ipAddress){
                sender.sendDetectPacket(ipAddress);
        }
        public boolean sendMessageWithAttachments(String message, FileBean[] fileAttrList, int count, String ipAddress){
                byte fileInfoSeparator = 7;             // character '\a'
                byte zero = 0;                                  // character '\0'
                byte fieldSeparater = 58 ;              // character ':'
                byte[] buff = new byte[DEFAULT_BUFFER_SIZE];
                ByteBuffer buffer = ByteBuffer.wrap(buff);
                // build send message
                buffer.put(getBytes(message)).put(zero);
                // build extra file info
                String fileExtraInfo = "";
                for(int i = 0 ; i < count; i++){
                        FileBean fileAttr = fileAttrList[i];
                        fileExtraInfo = fileAttr.getFileNo() + ":" + fileAttr.getFileName() + ":"
                                + Long.toHexString(fileAttr.getFileSize()) + ":" + Long.toHexString(fileAttr.getLastModifyTime()) + ":"
                                + Long.toHexString(fileAttr.getFileAttr()) ;
                        buffer.put(getBytes(fileExtraInfo)).put(fieldSeparater).put(fileInfoSeparator);
                }
                // add a '\0' in the end
                buffer.put(zero);
                byte[] realBytes = getRealBytes(buff, DEFAULT_BUFFER_SIZE);
                
                /******************* print message for test ************************/
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
                
                
                
                
                
                // buffer is message\0file1:\a:file2:\a:
                return sender.sendFileInfo(realBytes, ipAddress);
                //return true ;
        }
        
        public void setEventReceiver(IEventReceiver er){
                receiver.setEventReceiver(er);
        }
        
        public void stopReceiving(){
                receiver.setFlag(false);
        }
        
        
        /**
         * initialize the localAddress and udpSocket
         */
        private void init(){
                try{
                        if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1){
                                localAddress = getLocalAddressForWindows();
                        }else{
                                localAddress = getLocalAddressForLinux();
                        }
                        udpSocket = new DatagramSocket(PORT);
                        udpSocket.setBroadcast(true);
                }catch(SecurityException se){
                        logger.error("Client init failed!", se);
                }catch(SocketException se){
                        logger.error("Client init failed!", se);
                }
        }
        
        private InetAddress getLocalAddressForWindows(){
                InetAddress inetAddress = null ;
                try{
                        inetAddress = InetAddress.getLocalHost();
                        if(inetAddress.isLoopbackAddress()){
                                logger.error("Get local Address for windows error. local address is " + inetAddress.getHostAddress());
                        }
                }catch(UnknownHostException ue){
                        logger.error("getLocalAddressForWindows", ue);
                }
                return inetAddress;
        }
        private InetAddress getLocalAddressForLinux(){
                try{
                        NetworkInterface networkInterface = NetworkInterface.getByName("eth0");
                        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                        while(inetAddresses.hasMoreElements()){
                                InetAddress address = inetAddresses.nextElement();
                                if(!address.isLinkLocalAddress()){      // just get IPv4 address
                                        return address;
                                }
                        }
                }catch(SocketException se){
                        logger.error("getLocalAddressForWindows", se);
                }
                logger.error("Get local Address for linux error.");
                return null;
        }
        private byte[] getBytes(String s){
                return MyTools.getBytes(s, data.getEncoding());
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
        /**
         * just for test
         * @param args
         */
        public static void main(String[] args){
                Client c = new Client();
                c.entry();
                c.startListeningUdpCommand();
                // c.sendMessage("Go back?", "192.168.1.234");
        }
}
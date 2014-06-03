package com.javamsg.net;

import static com.javamsg.CommonConstant.*;
import static com.javamsg.CommandDefinition.*;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.javamsg.beans.FileBean;
import com.javamsg.beans.SystemData;
import com.javamsg.util.MyTools;

public class TcpSender implements Runnable {

        private static final Logger logger = Logger.getRootLogger();
        private static final SystemData data = SystemData.instance();
        
        private ServerSocket tcpSocket;
        private Socket clientSocket;
        
        private FileBean[] fileList;            // file ready to send
        
        private boolean flag = true;
        
        public TcpSender(FileBean[] files){
                fileList = files;
                
                try {
                        tcpSocket = new ServerSocket(PORT);
                } catch (IOException e) {
                        logger.error("Error occur in TcpSender Thread" , e);
                }
                
        }
        // stop this thread, must interrupt the blocking receive method
        public void stop(){
                flag = false;
        }
        public void close(){
                try{
                        if(clientSocket != null){
                                clientSocket.close();
                        }
                        if(tcpSocket != null){
                                tcpSocket.close();
                        }
                }catch(IOException ioe){
                        if(flag){
                                logger.warn("Socket close error ", ioe);
                        }
                }
        }
        public boolean isWorking(){
                return flag;
        }
        
        @Override
        public void run() {
                logger.debug("Thread TcpSender starting...");
                
                flag = true;
                
                String extraInfo;
                int count = 0;
                while(flag && count < fileList.length){
                        // accept connection from file receiver
                        accept();
                        if("".equals(extraInfo = receiveDataRequest().trim())){
                                break;
                        }
                        logger.debug("extraInfo is " + extraInfo);
                        String[] fields = extraInfo.split(":");
                        long packetNo = Long.parseLong(fields[0], 16);
                        // TODO check this packet number
                        long fileNo = Long.parseLong(fields[1], 16);
                        // TODO implement this
                        // long offset = Long.parseLong(fields[2], 16);
                        FileBean fileAttr = findFile(fileNo);
                        if(fileAttr == null){
                                // doesn't find this file
                                continue;
                        }
                        if(fileAttr.getFileAttr() == IPMSG_FILE_REGULAR){
                                // is regular file
                                sendFileData(fileAttr);
                        }else if(fileAttr.getFileAttr() == IPMSG_FILE_DIR){
                                // is directory
                                sendDirData(fileAttr);
                        }
                        if(clientSocket != null && clientSocket.isConnected()){
                                try {
                                        clientSocket.close();
                                } catch (IOException e) {
                                        logger.error("Error when close client socket in loop");
                                }
                        }
                        count++;
                }
                // thread will exit
                flag = false;
                try{
                        if(clientSocket != null && clientSocket.isConnected()){
                                clientSocket.close();
                        }
                        if(tcpSocket != null && clientSocket.isConnected()){
                                tcpSocket.close();
                        }
                }catch(IOException ioe){
                        if(flag){
                                logger.warn("Socket close error ", ioe);
                        }
                }
                
                logger.debug("Thread TcpSender exit...");
        }
        
        public void accept(){
                final String methodName = "TcpSender::accept";
                logger.debug("Enter method : " + methodName);
                try{
                        // tcpSocket = new ServerSocket(PORT);
                        // one sender socket can only accept one client socket
                        clientSocket = tcpSocket.accept();
                }catch(IOException ioe){
                        // TODO if exception occurred, file transfer must be stop
                        logger.error("Error occur in method " + methodName);
                        flag = false;
                }
                logger.debug("Exit method : " + methodName);
        }
        /**
         * send this file to receiver
         * @param fileAttr
         */
        public void sendFileData(FileBean fileAttr){
                final String methodName = "TcpSender::sendFileData";
                logger.info("Enter method : " + methodName);
                
                byte[] dataBuffer = new byte[data.getBufferSize()];
                long fileSize = fileAttr.getFileSize();
                long finishSize = 0;
                int readSize = 0;
                logger.info("Ready to send file : " + fileAttr.getPathName());
                File fromFile = createFile(fileAttr.getPathName());
                try{
                        FileInputStream input = new FileInputStream(fromFile);
                        OutputStream output = clientSocket.getOutputStream();
                        OutputStream out = new DataOutputStream(new BufferedOutputStream(output));
                        // read data from local file
                        readSize = input.read(dataBuffer);
                        while(readSize > 0 && finishSize < fileSize){
                                out.write(dataBuffer, 0, readSize);
                                finishSize = finishSize + readSize ;
                                readSize = input.read(dataBuffer);
                        }
                        input.close();
                        out.flush();
                        // out.close();
                        logger.info("Send File " + fileAttr.getFileName() + " successfully!");
                }catch(FileNotFoundException fnfe){
                        // TODO
                        logger.error("Error occur in Method : " + methodName, fnfe);
                }catch(IOException ioe){
                        logger.error("Error occur in Method : " + methodName, ioe);
                }
                logger.info("Exit method : " + methodName);
        }
        public void sendDirData(FileBean fileAttr){
                // TODO implement this method
                final String methodName = "TcpSender::sendDirData(FileBean)";
                logger.debug("Enter method : " + methodName);
                
                File sendDir = new File(fileAttr.getPathName());
                logger.info("Ready to send Directory " + sendDir.getName());
                sendDirData(sendDir);
                
                logger.debug("Exit method : " + methodName);
        }
        private void sendDirData(File sendFile){
                final String methodName = "TcpSender::sendDirData(File)";
                
                logger.info("Ready to send file " + sendFile.getName() + " in method " + methodName);
                
                long fileSize = calculateFileSize(sendFile) ;
                
                if(sendFile.isDirectory()){
                        // Directory
                        String extraInfo = ":" + sendFile.getName() + ":0:2:" ;
                        // TODO check this
                        extraInfo = addHeaderSize(extraInfo);
                        byte[] buff = getBytes(extraInfo);
                        try {
                                OutputStream output = clientSocket.getOutputStream();
                                // send this directory info to client
                                output.write(buff);
                        } catch (IOException e) {
                                logger.error("Send file data " + sendFile.getName() + " error in method " + methodName);
                                return;
                        }
                        // send all children file of this directory
                        for(File childFile : sendFile.listFiles()){
                                sendDirData(childFile);
                        }
                        // all file in current directory sending finished, goto parent directory
                        extraInfo = ":.:0:3:";
                        extraInfo = addHeaderSize(extraInfo);
                        buff = getBytes(extraInfo);
                        try {
                                OutputStream output = clientSocket.getOutputStream();
                                // send this dir info to client
                                output.write(buff);
                                // output.flush();
                        } catch (IOException e) {
                                logger.error("Send RETPARENT message error in method " + methodName);
                                return;
                        }
                }else if(sendFile.isFile()){
                        // Regular File
                        String extraInfo = ":" + sendFile.getName() + ":" + Long.toHexString(fileSize) + ":"
                                                + Long.toHexString(IPMSG_FILE_REGULAR) + ":" ;
                        // TODO check this
                        extraInfo = addHeaderSize(extraInfo) ;
                        byte[] buff = getBytes(extraInfo);
                        try {
                                OutputStream output = clientSocket.getOutputStream();
                                // send this dir info to client
                                output.write(buff);
                                // output.flush();
                        } catch (IOException e) {
                                logger.error("Send file data " + sendFile.getName() + " error in method " + methodName);
                                return;
                        }
                        // just use these two fields
                        FileBean tempFileAttr = new FileBean();
                        tempFileAttr.setPathName(sendFile.getAbsolutePath());
                        tempFileAttr.setFileSize(fileSize);
                        sendFileData(tempFileAttr);
                        return ;
                }
                logger.info("Send Directory " + sendFile.getName() + " successfully!");
        }
        /**
         * receive data request from receiver
         * @return packetNo:fileNo:offset
         */
        private String receiveDataRequest(){
                final String methodName = "TcpSender::receiveDataRequest";
                logger.debug("Enter method : " + methodName);
                
                int buffLength = DEFAULT_BUFFER_SIZE;
                byte[] buff = new byte[buffLength];
                int readSize = 0 ;
                String extraInfo = "";
                try{
                        InputStream input = clientSocket.getInputStream();
                        readSize = input.read(buff);
                        if(readSize <= 0){
                                // there is no data request
                                logger.debug("Receive Empty Request Data in method : " + methodName);
                                return "";
                        }else{
                                String message = new String(buff, 0, readSize);
                                logger.debug("Receive Request Data " + message + " in method : " + methodName);
                                String[] fileds = message.split(":");
                                
                                if(fileds.length >= 7){
                                        // packetNo:fileNo:
                                        extraInfo = fileds[5] + ":" + fileds[6] + ":";
                                }
                        }
                }catch(IOException ioe){
                        // TODO 
                        logger.error("Error occur in method " + methodName, ioe);
                        extraInfo = "";
                }
                return extraInfo;
        }
        private FileBean findFile(long fileNo){
                for(int i = 0 ; i < fileList.length; i++){
                        if(fileNo == fileList[i].getFileNo()){
                                return fileList[i];
                        }
                }
                return null;
        }
        
        private File createFile(String pathName){
                File file = new File(pathName);
                return file;
        }
        private String addHeaderSize(String extraInfo){
                int headerSize = 0 ;
                String zeroPlaceHolder = "00000";
                headerSize = getBytes(zeroPlaceHolder + extraInfo).length ;
                String tempHeaderSize = Long.toHexString(headerSize);
                int zeroCount = zeroPlaceHolder.length() - tempHeaderSize.length() ;
                // add "0" in the front
                for(int i = 0 ; i < zeroCount; i++){
                        tempHeaderSize = "0" + tempHeaderSize;
                }
                extraInfo = tempHeaderSize + extraInfo ;
                return extraInfo;
        }
        private byte[] getBytes(String s){
                return MyTools.getBytes(s, data.getEncoding());
        }
        /**
         * calculate file or directory size
         * @param file
         * @return
         */
        private long calculateFileSize(File file){
                long size = 0;
                if(file.isFile()){
                        size = file.length();
                }else if(file.isDirectory()){
                        for(File childFile : file.listFiles()){
                                size = size + calculateFileSize(childFile);
                        }
                }
                return size;
        }
}
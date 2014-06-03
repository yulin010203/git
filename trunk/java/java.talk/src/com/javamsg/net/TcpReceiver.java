package com.javamsg.net;

import static com.javamsg.CommandDefinition.IPMSG_FILEATTACHOPT;
import static com.javamsg.CommandDefinition.IPMSG_FILE_DIR;
import static com.javamsg.CommandDefinition.IPMSG_FILE_REGULAR;
import static com.javamsg.CommandDefinition.IPMSG_FILE_RETPARENT;
import static com.javamsg.CommandDefinition.IPMSG_GETDIRFILES;
import static com.javamsg.CommandDefinition.IPMSG_GETFILEDATA;
import static com.javamsg.CommonConstant.DEFAULT_BUFFER_SIZE;
import static com.javamsg.CommonConstant.PORT;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.javamsg.beans.FileBean;
import com.javamsg.beans.SystemData;
import com.javamsg.util.MyTools;

public class TcpReceiver implements Runnable{
        private static final Logger logger = Logger.getRootLogger();
        private static final SystemData data = SystemData.instance();

        private Socket tcpSocket;
        private InputStream  input;
        private OutputStream output;
        SocketAddress socketAddress;
        
        private String ipAddress;               // server ip address
        private FileBean[] fileList;            // file to receive
        // this stack use for contain directory
        private Stack<File> dirStack = new Stack<File>();
        
        private boolean flag = true;
        
        public TcpReceiver(String ip, FileBean[] files){
                this.ipAddress = ip;
                this.fileList = files;
        
                socketAddress = new InetSocketAddress(ipAddress, PORT);
        }
        /**
         * stop this thread, must interrupt blocking method
         */
        public void stop(){
                flag = false;
        }
        public void close(){
                if(tcpSocket != null){
                        try{
                                tcpSocket.close();
                        }catch(IOException ioe){
                                if(flag){
                                        logger.warn("Socket close error ", ioe);
                                }
                        }
                }
        }
        public boolean isWorking(){
                return flag;
        }
        
        @Override
        public void run() {
                flag = true;
                // connect();           ------------ can't connect once for all files, must be one connection for one file!!!
                for(int i = 0 ; i < fileList.length && flag ; i++){
                        // connect to the server
                        connect();
                        if(fileList[i].getFileAttr() == IPMSG_FILE_REGULAR){
                                // is regular file
                                // send a file request to file sender, I'm ready to receive file data
                                if(!requestFileData(fileList[i], 0)){
                                        // request file data error
                                        disconnect();
                                        continue;
                                }
                                // file will be saved in this path
                                String pathName = fileList[i].getPathName();
                                // create a file
                                File toFile = createFile(pathName);
                                logger.info("File " + fileList[i].getFileName() + " will be save to " + pathName);
                                // prepare to receive file data
                                receiveFileData(fileList[i], toFile);
                                
                        }else if(fileList[i].getFileAttr() == IPMSG_FILE_DIR){
                                // is directory
                                // send a directory data request to dir sender, I'm ready to receive directory data
                                if(!requestDirData(fileList[i], 0)){
                                        // send data request error
                                        disconnect();
                                        continue;
                                }
                                // directory will be saved to this directory
                                String pathName = fileList[i].getPathName();
                                File toDir = new File(pathName);
                                toDir = toDir.getParentFile();
                                /*
                                if(!toDir.mkdirs()){
                                        // create directory failed!
                                        logger.error("Create Directorys failed!");
                                        disconnect();
                                        continue;
                                }
                                */
                                // add this Dir into stack
                                dirStack.push(toDir);
                                logger.info("Dir " + fileList[i].getFileName() + " will be save to " + pathName);
                                // prepare to receive directory data
                                receiveDirData(toDir);
                        }
                        disconnect();
                }
                // file transfer finish
                if(tcpSocket != null && tcpSocket.isConnected()){
                        try{
                                tcpSocket.close();
                        }catch(IOException ioe){
                                logger.error("Error occur when close tcp receiver socket " + ioe);
                        }
                }
                flag = false;
                logger.info("File Transfer successfully, Thread TcpReceiver Exit!");
                
        }
        
        public void receiveFileData(FileBean fileAttr, File toFile){
                final String methodName = "TcpReceiver::receiverFileData";
                logger.info("Enter Method : " + methodName);
                // receive file data from ServerSocket
                // write file into local disk
                byte[] dataBuffer = new byte[data.getBufferSize()];
                long finishSize = 0;
                long fileSize = fileAttr.getFileSize();
                int readSize = 0;
                // long offset = 0;
                InputStream in ;
                try{
                        RandomAccessFile raFile = new RandomAccessFile(toFile, "rw");
                        
                        // InputStream input = tcpSocket.getInputStream();
                        in = new DataInputStream(new BufferedInputStream(input));
                        
                        do{
                                // logger.debug("Ready to read file data in method : " + methodName);
                                readSize = in.read(dataBuffer);
                                logger.debug("Read data size " + readSize + " in method : " + methodName);
                                // write data to local file
                                raFile.write(dataBuffer, 0, readSize);
                                raFile.skipBytes(readSize);
                                finishSize = finishSize + readSize;
                                // TODO update finish size of transfer progress bar
                        }while(readSize > 0 && finishSize < fileSize );
                        raFile.close();
                        logger.info("Receive file " + fileAttr.getFileName() + " successfully!");
                }catch(IOException ioe){
                        logger.error("Error occur in method : " + methodName, ioe);
                }finally{
                        
                }
                logger.info("Exit Method : " + methodName);
        }
        public void receiveDirData(File toDir){
                final String methodName = "TcpReceiver::receiveDirData";
                logger.debug("Enter method : " + methodName);
                
                byte fieldSeparater = 58 ;              // character ':'
                
                int buffLength = DEFAULT_BUFFER_SIZE;
                byte[] buff = new byte[buffLength];
                
                int headerSize = 0;
                String fileName = "";
                int fileSize = 0;
                long attr = 0;
                int contentLength = 0;
                int offset = 0;
                boolean enough = false;         // has enough data?
                try{    
                        while(flag){
                                // read as many as possible
                                enough = false;
                                while(offset <= buff.length){
                                        for(int i = 0; i < offset; i++){
                                                if(buff[i] == fieldSeparater){
                                                        // is ':'
                                                        headerSize = Integer.parseInt(new String(buff, 0, i), 16);
                                                        if(headerSize <= offset){
                                                                enough = true;
                                                        }
                                                        break;
                                                }
                                        }
                                        if(enough){
                                                break;
                                        }
                                        offset = offset + input.read(buff, offset, buff.length - offset);
                                }
                                
                                if(!enough){
                                        // header size is beyond buff size
                                        logger.error("Header size is beyond buff size!");
                                        return;
                                }

                                // buff = header + content
                                ///***************************************************/
                                //String testString = new String(buff, 0, (int)buffSize);
                                //logger.debug("***********************  test begin *******************************");
                                //logger.debug(testString);
                                //logger.debug("***********************  test end *******************************");
        
                                // header-size:filename:file-size:fileattr
                                String header = new String(buff, 0, (int)headerSize);
                                logger.debug("Receive File Data : Header is " + header + " in method : " + methodName);
                                String[] headerFields = header.split(":");
                                // file name
                                fileName = headerFields[1];
                                // file size
                                fileSize = Integer.parseInt(headerFields[2], 16);
                                // file attribute
                                attr = Long.parseLong(headerFields[3], 16);

                                contentLength = offset - headerSize;
                                
                                if(attr == IPMSG_FILE_RETPARENT){
                                        // goto parent dir
                                        logger.debug("last in");
                                        try{
                                                logger.debug("name : " + dirStack.peek().getName());
                                                logger.debug("parent name : " + dirStack.peek().getParentFile().getName());
                                                dirStack.pop() ;
                                                if(dirStack.size() == 1){
                                                        // return to the toDir, receiving finish
                                                        break;
                                                }
                                        }catch(EmptyStackException ese){
                                                // receiving finished! Exit this loop
                                                break;
                                        }
                                        if(contentLength > 0){
                                                // there are some other attachments
                                                for(int j = 0 ; j < contentLength; j++){
                                                        buff[j] = buff[headerSize + j];
                                                }
                                                offset = contentLength;
                                                logger.debug("rest attachment size is " + offset + " In (attr==RET)**********" + new String(buff, 0, contentLength));
                                                continue;
                                        }
                                }else if(attr == IPMSG_FILE_DIR){
                                        // create a new directory
                                        File dirFile = new File(dirStack.peek(), fileName);
                                        dirFile.mkdir();
                                        dirStack.push(dirFile);
                                        if(contentLength > 0){
                                                // there are some other attachments
                                                for(int j = 0 ; j < contentLength; j++){
                                                        buff[j] = buff[headerSize + j];
                                                }
                                                offset = contentLength;
                                                logger.debug("rest attachment size is " + offset + " In (attr==DIR)");
                                                continue;
                                        }
                                }else if(attr == IPMSG_FILE_REGULAR){
                                        // receive a regular file
                                        // create this regular file in current path
                                        File newFile = new File(dirStack.peek(), fileName);
                                        if(!newFile.createNewFile()){
                                                // create new file failed!
                                                logger.error("Create new file failed!");
                                                continue;
                                        }
                                        InputStream in ;
                                        try{
                                                RandomAccessFile raFile = new RandomAccessFile(newFile, "rw");
                                                
                                                if(contentLength >= fileSize){
                                                        raFile.write(buff, (int)headerSize, (int)fileSize);
                                                        // receiving finished
                                                        raFile.close();
                                                        contentLength = contentLength - fileSize;
                                                        if(contentLength > 0){
                                                                // there are some other attachments
                                                                for(int j = 0 ; j < contentLength; j++){
                                                                        buff[j] = buff[(int)(headerSize + fileSize + j)];
                                                                }
                                                                offset = contentLength;
                                                                continue;
                                                        }
                                                }else{ 
                                                        raFile.write(buff, headerSize, contentLength);
                                                        raFile.skipBytes(contentLength);
                                                        in = new DataInputStream(new BufferedInputStream(input));
                                                        byte[] dataBuffer = new byte[DEFAULT_BUFFER_SIZE];
                                                        int finishSize = contentLength;
                                                        contentLength = 0;
                                                        int readFileSize = 0 ;
                                                        int restFileSize = 0;
                                                        do{
                                                                restFileSize = fileSize - finishSize;
                                                                readFileSize = in.read(dataBuffer, 0, dataBuffer.length);
                                                                
                                                                logger.debug("Read data size " + readFileSize + " in method : " + methodName);
                                                                // write data to local file
                                                                int writeSize = readFileSize < restFileSize ? readFileSize : restFileSize ;
                                                                raFile.write(dataBuffer, 0, writeSize);
                                                                raFile.skipBytes(writeSize);
                                                                finishSize = finishSize + writeSize;
                                                                // TODO update finish size of transfer progress bar
                                                        }while(finishSize < fileSize );
                                                        raFile.close();
                                                        contentLength = readFileSize - restFileSize;
                                                        if(contentLength > 0){
                                                                // there are some other attachments
                                                                for(int j = 0 ; j < contentLength; j++){
                                                                        buff[j] = dataBuffer[readFileSize - contentLength + j];
                                                                }
                                                                offset = contentLength;
                                                                continue;
                                                        }
                                                }
                                        }catch(IOException ioe){
                                                
                                        }
                                }else{
                                        logger.info("receive other file attribute " + attr);
                                }
                                offset = 0;
                        } //~ while
                }catch(IOException ioe){
                        logger.error("Error occur in method : " + methodName, ioe);
                }
                logger.info("Receive file " + toDir.getName() + " successfully!");
                logger.debug("Exit method : " + methodName);
        }
        
        public boolean requestFileData(FileBean fileAttr, long offset){
                return requestData(IPMSG_GETFILEDATA, fileAttr, offset);
        }
        public boolean requestDirData(FileBean fileAttr, long offset){
                return requestData(IPMSG_GETDIRFILES, fileAttr, offset);
        }
        /**
         * 
         * @param ipAddress
         * @param dataType IPMSG_GETFILEDATA or IPMSG_GETDIRFILES
         * @param attr file attribute
         */
        private boolean requestData(long dataType, FileBean fileAttr, long offset){
                final String methodName = "TcpSender::requestData";
                logger.debug("Enter method " + methodName);
                try{
                        String attr = Long.toHexString(fileAttr.getPacketNumber()) + ":" + Long.toHexString(fileAttr.getFileNo()) + ":" + Long.toHexString(offset);
                        long command = IPMSG_FILEATTACHOPT | dataType ;
                        byte[] buffer = buildResponseMessage(command, attr);
                        String testString = new String(buffer, 0, buffer.length);
                        logger.debug("Request data string is " + testString);
                        // OutputStream output = tcpSocket.getOutputStream();
                        output.write(buffer);
                        output.flush();
                }catch(IOException ioe){
                        logger.error("connect Error in " + methodName, ioe);
                        return false;
                }
                logger.debug("Exit method " + methodName);
                return true;
        }
        private void connect(){
                final String methodName = "TcpSender::connect";
                logger.debug("Enter method " + methodName);
                try{
                        tcpSocket = new Socket();
                        // SocketAddress socketAddress = new InetSocketAddress(ipAddress, PORT);
                        tcpSocket.connect(socketAddress);
                        input = tcpSocket.getInputStream();
                        output = tcpSocket.getOutputStream();
                }catch(SecurityException se){
                        logger.error("new tcp Socket error in " + methodName, se);
                }catch(IOException ioe){
                        logger.error("connect Error in " + methodName, ioe);
                }
                logger.debug("Exit method " + methodName);
        }
        private void disconnect(){
                final String methodName = "TcpSender::disconnect";
                logger.debug("Enter method " + methodName);
                try{
                        if(tcpSocket != null && tcpSocket.isConnected()){
                                tcpSocket.close();
                        }
                }catch(IOException ioe){
                        logger.error("Close tcp socket error in method : " + methodName);
                }
                logger.debug("Exit method " + methodName);
        }
        /**
         * build a message packet
         * @param command
         * @param extraInfo
         * @return
         */
        private byte[] buildResponseMessage(long command, String extraInfo){
                long packetNumber = System.currentTimeMillis();
                String message = data.getVersion() + ":" + packetNumber + ":" + data.getSender() +":" + data.getHost();
                message = message + ":" + command + ":" + extraInfo ;
                return MyTools.getBytes(message, data.getEncoding());
        }
        private File createFile(String pathName){
                // TODO handle file exist situation
                File file = new File(pathName);
                try{
                        // create a new file
                        file.createNewFile();
                }catch(IOException ioe){
                        
                }
                return file;
        }
}
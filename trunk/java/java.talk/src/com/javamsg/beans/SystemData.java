package com.javamsg.beans;

import static com.javamsg.CommonConstant.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

public class SystemData {
        
        private static final Logger logger = Logger.getRootLogger(); 
        private static SystemData data = new SystemData();
        
        private String version ;
        private String sender ;
        private String host ;
        private String nickName ;
        
        private String operatingSystemVersion ;
        private String encoding ;
        // if currentPacketNumber is setted to -1, means packet has been confirmed
        //private long currentPacketNumber;
        
        private int bufferSize ;
        
        private SystemData(String nickname){
                this();
                this.nickName   = nickname;
        }
        private SystemData(){
                init();
                this.nickName = sender;
        }
        public static SystemData instance(){
                return data;
        }
        /**
         * init system data
         */
        private void init(){
                logger.debug("Enter class SystemData method init : Initialize System Data.");
                try{
                        this.version    = VERSION;
                        this.sender             = System.getProperty("user.name");
                        this.host               = InetAddress.getLocalHost().getHostName();
                        this.operatingSystemVersion     = System.getProperty("os.name") + "#" + System.getProperty("os.version");
                        this.encoding   = Charset.defaultCharset().toString();
                        this.bufferSize = DEFAULT_BUFFER_SIZE;
                }catch(SecurityException se){
                        logger.error("init system data failed", se);
                }catch(UnknownHostException ue){
                        logger.error("init system data failed", ue);
                }
                logger.debug("sender : " + sender);
                logger.debug("host : " + host);
                logger.debug("osVersion : " + operatingSystemVersion);
                logger.debug("encoding : " + encoding);
                logger.debug("Exit class SystemData method init : Initialize System Data.");
        }
        public String getVersion() {
                return version;
        }
        public void setVersion(String version) {
                this.version = version;
        }
        public String getSender() {
                return sender;
        }
        public void setSender(String sender) {
                this.sender = sender;
        }
        public String getHost() {
                return host;
        }
        public void setHost(String host) {
                this.host = host;
        }
        public String getNickName() {
                return nickName;
        }
        public void setNickName(String nickName) {
                this.nickName = nickName;
        }
        public String getOperatingSystemVersion() {
                return operatingSystemVersion;
        }
        public void setOperatingSystemVersion(String operatingSystemVersion) {
                this.operatingSystemVersion = operatingSystemVersion;
        }
        public String getEncoding() {
                return encoding;
        }
        public void setEncoding(String encoding) {
                this.encoding = encoding;
        }
        /*
        public long getCurrentPacketNumber() {
                return currentPacketNumber;
        }
        public void setCurrentPacketNumber(long currentPacketNumber) {
                this.currentPacketNumber = currentPacketNumber;
        }
        */
        public int getBufferSize() {
                return bufferSize;
        }
        public void setBufferSize(int bufferSize) {
                this.bufferSize = bufferSize;
        }
}
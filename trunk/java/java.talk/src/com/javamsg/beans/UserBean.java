package com.javamsg.beans;

import static com.javamsg.CommonConstant.*;
/**
 * @author soyang
 *
 */
public class UserBean {
        private String version = VERSION;
        private String sender ;
        private String host ;
        private String nickName ;
        
        private String operatingSystemVersion = "Windows" ;
        private String encoding = "utf-8";
        
        private String ipAddress ;
        private int port;
        
        private long packetNumber = 0;
        
        public UserBean(String ver, String sender, String host, String ipAddress, int port){
                this.version = ver;
                this.sender = sender;
                this.host = host;
                this.ipAddress = ipAddress;
                this.port = port;
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

        public String getIpAddress() {
                return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
                this.ipAddress = ipAddress;
        }

        public int getPort() {
                return port;
        }

        public void setPort(int port) {
                this.port = port;
        }

        public long getPacketNumber() {
                return packetNumber;
        }

        public void setPacketNumber(long packetNumber) {
                this.packetNumber = packetNumber;
        }
}
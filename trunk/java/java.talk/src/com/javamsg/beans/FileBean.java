package com.javamsg.beans;

public class FileBean {
        private int fileNo = 0;                 
        private String fileName = "";
        private long fileSize = 0;              // DEC
        private long lastModifyTime;    // DEC
        private long fileAttr;                  // DEC
        
        private long packetNumber = 0;  // use for file transfer
        
        private String pathName = "";           // file store in this path, use for receiver
        
        public FileBean(){
                
        }
        public FileBean(int no, String name, long size, long time, long attr){
                fileNo = no;
                fileName = name;
                fileSize = size;
                lastModifyTime = time;
                fileAttr = attr;
        }
        
        /**
         * get a file attribute string
         * format  fileNo:fileName:fileSize:lastModifyTime:fileAttr
         * @return
         */
        /*
        public String getAttribute(){
                // TODO implement this method
                StringBuffer buffer = new StringBuffer();
                buffer.append(fileNo).append(":").append(fileName).append(":").append(fileSize)
                        .append(":").append(lastModifyTime).append(":").append(fileAttr);
                return buffer.toString();
        }
        */
        public int getFileNo() {
                return fileNo;
        }
        public void setFileNo(int fileNo) {
                this.fileNo = fileNo;
        }
        public String getFileName() {
                return fileName;
        }
        public void setFileName(String fileName) {
                this.fileName = fileName;
        }
        public long getFileSize() {
                return fileSize;
        }
        public void setFileSize(long fileSize) {
                this.fileSize = fileSize;
        }
        public long getLastModifyTime() {
                return lastModifyTime;
        }
        public void setLastModifyTime(long lastModifyTime) {
                this.lastModifyTime = lastModifyTime;
        }
        public long getFileAttr() {
                return fileAttr;
        }
        public void setFileAttr(long fileAttr) {
                this.fileAttr = fileAttr;
        }
        public String getPathName() {
                return pathName;
        }
        public void setPathName(String pathName) {
                this.pathName = pathName;
        }
        public long getPacketNumber() {
                return packetNumber;
        }
        public void setPacketNumber(long packetNumber) {
                this.packetNumber = packetNumber;
        }
        
}
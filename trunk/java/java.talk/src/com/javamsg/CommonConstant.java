package com.javamsg;

/**
 * @author soyang
 *
 */
public interface CommonConstant {
        public static final String VERSION = "1";
        public static final int PORT    = 2400;
        public static final int MAX_ATTEMPT_TIME = 4;
        public static final int DEFAULT_BUFFER_SIZE = 8192;             // from ipmsg
        public static final int MAX_ATTACHMENTS_LENGTH = 3 ;
        
        // file type
        public static final String REGULAR_FILE = "file";
        public static final String DIRECTORY = "dir";
        
        // icons
        public static final String SYSTEM_ICON = "resource/icons/qq.jpg";
}

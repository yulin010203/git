package com.javamsg;

/**
 * @author soyang
 * ipmsg command constant variable define
 */
public interface CommandDefinition {
        /*========== Constant Value ==========*/
        public static final long IPMSG_COMMASK          = 0x000000ffL;
        public static final long IPMSG_OPTMASK          = 0xffffff00L;
        
        public static final long IPMSG_NOOPERATION      = 0x00000000L;
        
        public static final long IPMSG_BR_ENTRY         = 0x00000001L;
        public static final long IPMSG_BR_EXIT          = 0x00000002L;
        public static final long IPMSG_ANSENTRY         = 0x00000003L;
        public static final long IPMSG_BR_ABSENCE       = 0x00000004L;
        
        public static final long IPMSG_BR_ISGETLIST = 0x00000018L;
        public static final long IPMSG_OKGETLIST        = 0x00000015L;
        public static final long IPMSG_GETLIST          = 0x00000016L;
        public static final long IPMSG_ANSLIST          = 0x00000017L;
        
        public static final long IPMSG_SENDMSG          = 0x00000020L;
        public static final long IPMSG_RECVMSG          = 0x00000021L;
        
        public static final long IPMSG_READMSG          = 0x00000030L;
        public static final long IPMSG_DELMSG           = 0x00000031L;
        
        public static final long IPMSG_GETINFO          = 0x00000040L;
        public static final long IPMSG_SENDINFO         = 0x00000041L;
        
        public static final long IPMSG_GETPUBKEY        = 0x00000072L;
        public static final long IPMSG_ANSPUBKEY        = 0x00000073L;
        
        // other opt
        public static final long IPMSG_ABSENCEOPT               = 0x00000100L;
        public static final long IPMSG_SERVEROPT                = 0x00000200L;
        public static final long IPMSG_DIALUPOPT                = 0x00010000L;
        public static final long IPMSG_ENCRYPTOPT               = 0x00400000L;
        public static final long IPMSG_ENCRYPTOPTOLD    = 0x00800000L;
        
        // send opt
        public static final long IPMSG_SENDCHECKOPT = 0x00000100L;
        public static final long IPMSG_SECRETOPT        = 0x00000200L;
        public static final long IPMSG_BROADCASTOPT = 0x00000400L;
        public static final long IPMSG_MULTICASTOPT = 0x00000800L;
        public static final long IPMSG_NOPOPUPOPT       = 0x00001000L;
        public static final long IPMSG_AUTORETOPT       = 0x00002000L;
        public static final long IPMSG_RETRYOPT         = 0x00004000L;
        public static final long IPMSG_PASSWORDOPT      = 0x00008000L;
        public static final long IPMSG_NOLOGOPT         = 0x00020000L;
        public static final long IPMSG_NEWMUTIOPT       = 0x00040000L;

        // encrypt opt
        public static final long IPMSG_RSA_512          = 0x00000001L;
        public static final long IPMSG_RSA_1024         = 0x00000002L;
        public static final long IPMSG_RSA_2048         = 0x00000004L;
        public static final long IPMSG_RC2_40           = 0x00001000L;
        public static final long IPMSG_RC2_128          = 0x00004000L;
        public static final long IPMSG_RC2_256          = 0x00008000L;
        public static final long IPMSG_BLOWFISH_128 = 0x00020000L;
        public static final long IPMSG_BLOWFISH_256 = 0x00040000L;
        
        // file 
        public static final long IPMSG_FILE_MTIME               = 0x00000014L;
        public static final long IPMSG_FILE_CREATETIME  = 0x00000016L;
        public static final long IPMSG_FILE_REGULAR     = 0x00000001L;
        public static final long IPMSG_FILE_DIR                 = 0x00000002L;
        public static final long IPMSG_FILE_RETPARENT   = 0x00000003L; // return parent
        public static final long IPMSG_FILEATTACHOPT    = 0x00200000L;
        public static final long IPMSG_GETFILEDATA              = 0x00000060L;
        public static final long IPMSG_RELEASEFILES     = 0x00000061L;
        public static final long IPMSG_GETDIRFILES              = 0x00000062L;
}
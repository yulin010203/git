package com.javamsg.util;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.javamsg.beans.SystemData;

public class MyTools {
        private static final SystemData data = SystemData.instance();
        /**
         * encoding String s with charset in data
         * @param s String will be encoding
         * @return
         */
        public static byte[] getBytes(String s, String charsetName){
                byte[] result;
                
                try{
                        result = s.getBytes(charsetName);
                }catch(UnsupportedEncodingException e){
                        result = s.getBytes(); 
                }
                result = s.getBytes();
                return result;
        }
        /**
         * translate byte[] into String using this charset
         * @param buff
         * @param offset
         * @param length
         * @param charsetName
         * @return
         */
        public static String getString(byte[] buff, int offset, int length, String charsetName){
                String result;
                try{
                        result = new String(buff, offset, length, charsetName);
                }catch(UnsupportedEncodingException e){
                        result = new String(buff, offset, length);
                }
                return result;
        }
        public static String getString(byte[] buff, String charsetName){
                return getString(buff, 0, buff.length, charsetName);
        }
        /**
         * calculate directory length recursively, if file is regular file ,just return file length
         * @param file
         * @return file length
         */
        public static long calculateFileLength(File file){
                long size = 0;
                if(file.isFile()){
                        size = file.length();
                }else if(file.isDirectory()){
                        for(File childFile : file.listFiles()){
                                size = size + calculateFileLength(childFile);
                        }
                }
                return size;
        }
}
package com.javamsg.beans;

import java.util.ArrayList;
import java.util.List;

public class DialupUserList {
        // this class can not be instanced
        private DialupUserList(){}
        // the system just need one userList
        private static List<UserBean> userList = new ArrayList<UserBean>();
        public static int addUser(UserBean user){
                // Look up the user list to decide whether this user is in the list or not
                if(!contain(user)){
                        userList.add(user);
                }
                // TODO Notify GUI to add this user
                return getUserCount();
        }
        public static boolean removeUser(UserBean user){
                if(user == null){
                        return true;
                }
                for(int i = 0 ; i < userList.size(); i++){
                        if(user.getIpAddress().equals(userList.get(i).getIpAddress())){
                                userList.remove(i);
                                return true;
                        }
                }
                return false;
        }
        public static UserBean getUserByIndex(int index){
                if(index < userList.size()){
                        return userList.get(index);
                }
                return null;
        }
        public static void updateUser(UserBean user){
                if (user == null ){
                        return ;
                }
                // find this user, use user's ip address as a specification
                for(int i = 0; i < userList.size(); i++){
                        if(userList.get(i).getIpAddress().equals(user.getIpAddress())){
                                // update this user
                                userList.set(i, user);
                                return ;
                        }
                }
                // if this user not in the list, add it
                userList.add(user);
        }
        public static int getUserCount(){
                return userList.size();
        }
        private static boolean contain(UserBean user){
                if(user == null){
                        return false;
                }
                for(int i = 0 ; i < userList.size(); i++){
                        if(user.getIpAddress().equals(userList.get(i).getIpAddress())){
                                return true;
                        }
                }
                return false;
        }
}
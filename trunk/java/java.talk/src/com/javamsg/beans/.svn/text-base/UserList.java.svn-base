package com.javamsg.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.javamsg.Observer;
import com.javamsg.Subject;

/**
 * @author soyang
 *
 */
public class UserList implements Subject{
        private static final Logger logger = Logger.getRootLogger(); 
        
        // singleton
        private UserList(){}
        
        private static UserList list = new UserList();
        
        public static UserList instance(){
                return list;
        }
        // the system just need one userList
        private List<UserBean> userList = new ArrayList<UserBean>();
        
        private ArrayList<Observer> observerList = new ArrayList<Observer>();
        
        @Override
        public void registerObserver(Observer observer){
                observerList.add(observer);
        }
        @Override
        public void removeObserver(Observer observer){
                observerList.remove(observer);
        }
        
        public void notifyObservers(){
                // TODO modify this function
                for( Observer o : observerList ){
                    o.update();
                }               

        }
        
        public int addUser(UserBean user){
                // Look up the user list to decide whether this user is in the list or not
                if(!contain(user)){
                        userList.add(user);
                        // Notify GUI to add this user
                        notifyObservers();
                }
                return getUserCount();
        }
        public boolean removeUser(UserBean user){
                if(user == null){
                        return true;
                }
                for(int i = 0 ; i < userList.size(); i++){
                        if(user.getIpAddress().equals(userList.get(i).getIpAddress())){
                                userList.remove(i);
                                notifyObservers();
                                return true;
                        }
                }
                return false;
        }
        public UserBean getUserByIndex(int index){
                if(index < userList.size()){
                        return userList.get(index);
                }
                return null;
        }
        public UserBean getUserByIpAddress(String ip){
                if (ip == null){
                        return null;
                }
                for(int i = 0; i < userList.size(); i++){
                        if(ip.equals(userList.get(i).getIpAddress())){
                                return userList.get(i);
                        }
                }
                return null;
        }
        public void updateUser(UserBean user){
                if (user == null ){
                        return ;
                }
                // find this user, use user's ip address as a specification
                for(int i = 0; i < userList.size(); i++){
                        if(userList.get(i).getIpAddress().equals(user.getIpAddress())){
                                // update this user
                                userList.set(i, user);
                                notifyObservers();
                                return ;
                        }
                }
                // if this user not in the list, add it
                userList.add(user);
        }
        public int getUserCount(){
                return userList.size();
        }
        
        private boolean contain(UserBean user){
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
package com.ipmsg.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.PortUnreachableException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.channels.IllegalBlockingModeException;
import java.util.Enumeration;
import java.util.List;

import com.javamsg.util.MyTools;

public class BroadcastTest {
        private int version = 1 ;
        public static final int PORT = 2425;
        
        public static final long IPMSG_BR_ENTRY = 0x00000001L;
        public static final long IPMSG_BR_EXIT = 0x00000002L; 
        public static final long IPMSG_ANSENTRY = 0x00000003L; 
        public static final long IPMSG_ABSENCEOPT = 0x00000100L;
        public static final long IPMSG_SENDMSG = 0x00000020L; 
        
        private InetAddress localAddress;
        private InetAddress broadcastAddress;
        
        private DatagramSocket socket;
        private DatagramPacket packet;
        
        private String sender = "mario";
        private String host = "Microsoft";
        public void init(){
                try{
                        localAddress = InetAddress.getLocalHost();
                        if(localAddress.isLoopbackAddress()){
                                System.out.println("get local ip error!");
                                return;
                        }
                        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localAddress);
                        List<InterfaceAddress> addresses = networkInterface.getInterfaceAddresses();
                        for(InterfaceAddress address : addresses){
                        	if (address.getAddress() instanceof Inet6Address){
                        		continue;
                        	}
                                if (address.getBroadcast() != null){
                                        broadcastAddress = address.getBroadcast();
                                        break;
                                }else{
                                        System.out.println("get broadcast address error!");
                                }
                        }
                        System.out.println("local ip address is " + localAddress.getHostAddress());
                        System.out.println("broadcast address is " + broadcastAddress.getHostAddress());
                        
                        socket = new DatagramSocket();
                        socket.setBroadcast(true);
                        
                }catch(UnknownHostException ue){
                        ue.printStackTrace();
                }catch(SocketException se){
                        se.printStackTrace();
                }
                
        }
        // on linux system
        public void init2(){
                try{
                        NetworkInterface networkInterface = NetworkInterface.getByName("eth0");
                        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                        while(inetAddresses.hasMoreElements()){
                                InetAddress address = inetAddresses.nextElement();
                                if(!address.isLinkLocalAddress()){      // just get IPv4 address
                                        localAddress = address;
                                }
                        }
                        // get broadcast address
                        List<InterfaceAddress> addresses = networkInterface.getInterfaceAddresses();
                        for(InterfaceAddress address : addresses){
                                if (address.getBroadcast() != null){
                                        broadcastAddress = address.getBroadcast();
                                        break;
                                }
                        }
                        if(broadcastAddress == null){
                                System.out.println("get broadcast address error!");
                        }
                        System.out.println("local ip address is " + localAddress.getHostAddress());
                        System.out.println("broadcast address is " + broadcastAddress.getHostAddress());
                        
                        socket = new DatagramSocket();
                        socket.setBroadcast(true);
                }catch(SocketException se){
                        se.printStackTrace();
                }
                
                
        }
        public void entry(){
                this.broadcast(IPMSG_BR_ENTRY | IPMSG_ABSENCEOPT, "supermario");
        }
        public void exit(){
                this.broadcast(IPMSG_BR_EXIT , "");
        }
        
        private void broadcast(long command, String extraInfo){
                byte[] buff = buildMessage(command, extraInfo);
                packet = new DatagramPacket(buff, buff.length, broadcastAddress, PORT);
                try{
                        socket.send(packet);
                }catch(IOException ioe){
                        ioe.printStackTrace();
                }
        }
        public void answerEnrty(){
                this.sendPacket(IPMSG_ANSENTRY, "Newer");
        }
        public void sendMessage(){
                this.sendPacket(IPMSG_SENDMSG, "Hello,Mario");
        }
        private void sendPacket(long command, String extraInfo){
                byte[] buff = buildMessage(command, extraInfo);
                try{
                        InetAddress address = InetAddress.getByName("172.16.16.153");
                        packet = new DatagramPacket(buff, buff.length, address, 2425);
                        socket.send(packet);
                }catch(UnknownHostException ue){
                        ue.printStackTrace();
                }catch(IOException ioe){
                        ioe.printStackTrace();
                }
        }
        private byte[] buildMessage(long command, String extraInfo){
                String message = version + ":" + System.currentTimeMillis() + ":" + sender +":" + host;
                message = message + ":" + command + ":" + extraInfo ;
                return MyTools.getBytes(message, "UTF-8");
        }
        
        public void receivePacket(){
                int buffLength = 256;
                byte[] buff = new byte[buffLength];
                DatagramPacket packet = new DatagramPacket(buff, buffLength);
                try{
                        socket.receive(packet);
                        String message = new String(packet.getData(), 0, packet.getLength()-2);
                        System.out.println("receive message : " + message);
                        if(message.indexOf(":") == -1){
                                return;
                        }
                        String[] fields = message.split(":");
                        long commandField = Long.parseLong(fields[4]);
                        if((commandField & IPMSG_ANSENTRY) != 0){
                                this.receiveAnswerEntry(fields, packet);
                        }else if((commandField & IPMSG_SENDMSG) !=0){
                                this.receiveMessage(fields, packet);
                        }
                }catch(SocketTimeoutException ste){
                        ste.printStackTrace();
                }catch(PortUnreachableException pue){
                        pue.printStackTrace();
                }catch(IllegalBlockingModeException ibme){
                        ibme.printStackTrace();
                }catch(IOException ioe){
                        ioe.printStackTrace();
                }
        }
        private void receiveAnswerEntry(String[] fields, DatagramPacket packet){
                System.out.println("Receive answer entry command");
                System.out.println("version : " + fields[0]);
                System.out.println("packet number : " + fields[1]);
                System.out.println("sender : " + fields[2]);
                System.out.println("host : " + fields[3]);
                System.out.println("extraInfo : " + fields[5]);
                System.out.println("sender address : " + packet.getAddress().getHostAddress() + ":" + packet.getPort());
        }
        private void receiveMessage(String[] fields, DatagramPacket packet){
                System.out.println("Receive send message command");
                System.out.println("version : " + fields[0]);
                System.out.println("packet number : " + fields[1]);
                System.out.println("sender : " + fields[2]);
                System.out.println("host : " + fields[3]);
                System.out.println("extraInfo : " + fields[5]);
                System.out.println("sender address : " + packet.getAddress().getHostAddress() + ":" + packet.getPort());
        }
        public static void main(String[] args) throws Exception{
                String ip = InetAddress.getLocalHost().getHostAddress();
                System.out.println("local host ip is " + ip);
                BroadcastTest b = new BroadcastTest();
                b.init();
                // b.init2();
                
                // b.entry();
                
                // b.exit();
                b.sendMessage();
                b.receivePacket();
                //for(int i=0 ; i < 3; i++){
                //      b.receivePacket();
                //}
                // b.answerEnrty();
        }
}
package sample;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;
import jpcap.PacketReceiver;
import jpcap.packet.ARPPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 陈霖 2014年6月25日
 */
public class JpcapTip implements PacketReceiver {

	private static final Log log = LogFactory.getLog(JpcapTip.class);

	public void receivePacket(Packet packet) {
		System.out.println("********************************************");
		/* IP数据报报文头 */
		byte[] l = packet.header;
		/*
		 * for (int t=0;t<21;t++){ System.out.print(l[t]+" *** "); }
		 */
		String str = "";
		System.out.print("报文头 : ");
		for (int i = 0; i < l.length; i++) {
			// str=str+l;
			int m = 0;
			m = l[i];
			m = m << 24;
			m = m >>> 24;
			str = str + Integer.toHexString(m);
			// System.out.print("     ***     "+l[i]);
		}
		System.out.println(str);
		int d = l.length;
		System.out.println("首部长度 ：" + (d * 8) + "bit");

		/* 分析源IP地址和目的IP地址 */
		/* 分析协议类型 */
		/**
		 * if(packet.getClass().equals(IPPacket.class)) { IPPacket ipPacket=(IPPacket)packet; byte[] iph=ipPacket.option; String iphstr=new String(iph);
		 * System.out.println(iphstr); }
		 */
		if (packet.getClass().equals(ARPPacket.class)) {
			System.out.println("协议类型 ：ARP协议");
			try {
				ARPPacket arpPacket = (ARPPacket) packet;
				System.out.println("源网卡MAC地址为 ：" + arpPacket.getSenderHardwareAddress());
				System.out.println("源IP地址为 ：" + arpPacket.getSenderProtocolAddress());
				System.out.println("目的网卡MAC地址为 ：" + arpPacket.getTargetHardwareAddress());
				System.out.println("目的IP地址为 ：" + arpPacket.getTargetProtocolAddress());

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (packet.getClass().equals(UDPPacket.class)) {
			System.out.println("协议类型 ：UDP协议");
			try {
				UDPPacket udpPacket = (UDPPacket) packet;
				System.out.println("源IP地址为 ：" + udpPacket.src_ip);
				int tport = udpPacket.src_port;
				System.out.println("源端口为：" + tport);
				System.out.println("目的IP地址为 ：" + udpPacket.dst_ip);
				int lport = udpPacket.dst_port;
				System.out.println("目的端口为：" + lport);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (packet.getClass().equals(TCPPacket.class)) {
			System.out.println("协议类型 ：TCP协议");
			try {
				TCPPacket tcpPacket = (TCPPacket) packet;
				int tport = tcpPacket.src_port;
				System.out.println("源IP地址为 ：" + tcpPacket.src_ip);
				System.out.println("源端口为：" + tport);
				System.out.println("目的IP地址为 ：" + tcpPacket.dst_ip);
				int lport = tcpPacket.dst_port;
				System.out.println("目的端口为：" + lport);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (packet.getClass().equals(IPPacket.class)) {
			
		} else if (packet.getClass().equals(ICMPPacket.class))
			System.out.println("协议类型 ：ICMP协议");
		else
			System.out.println("协议类型 ：GGP、EGP、JGP协议或OSPF协议或ISO的第4类运输协议TP4");

		/* IP数据报文数据 */
		byte[] k = packet.data;
		String str1 = "";
		System.out.print("数据 : ");
		for (int i = 0; i < k.length; i++) {
			// int m=0;
			// m=k[i];
			// m=m<<24;
			// m=m>>>24;
			// str1=str+Integer.toHexString(m);
			str1 = new String(k);
			// str1=str1+k[i];
			// System.out.print("     ***     "+k[i]);
		}
		System.out.println(str1);
		System.out.println("数据报类型 : " + packet.getClass());

		System.out.println("********************************************");
	}

	public static void main(String[] args) throws Exception {

		NetworkInterface[] devices = JpcapCaptor.getDeviceList(); // .getDeviceList();.
		// for (int i =0; i<devices.length;i++) {
		int a = 0;
		// try {
		/* 本地网络信息 */
		byte[] b = devices[0].mac_address; // 网卡物理地址
		// }
		// catch() {}
		System.out.print("网卡MAC : 00");
		for (int j = 0; j < b.length; j++) {
			// a=a<<8;
			a = b[j];
			a = a << 24;
			a = a >>> 24;
			System.out.print(Integer.toHexString(a));
		}
		System.out.println();
		NetworkInterfaceAddress[] k = devices[0].addresses;

		// System.out.println("网卡MAC : "+Integer.toHexString(a));
		for (int n = 0; n < k.length; n++) {
			System.out.println("本机IP地址 : " + k[n].address); // 本机IP地址
			System.out.println("子网掩码   : " + k[n].subnet); // 子网掩码
		}
		System.out.println("网络连接类型 : " + devices[0].datalink_description);
		// }
		NetworkInterface deviceName = devices[0];
		/* 将网卡设为混杂模式下用网络设备deviceName */
		JpcapCaptor jpcap = JpcapCaptor.openDevice(deviceName, 2000, false, 1); // openDevice(deviceName,1028,false,1);
		jpcap.loopPacket(-1, new JpcapTip());
	}

}
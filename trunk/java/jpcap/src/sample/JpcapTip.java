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
		byte[] l = packet.header;
		String str = "";
		for (int i = 0; i < l.length; i++) {
			int m = 0;
			m = l[i];
			m = m << 24;
			m = m >>> 24;
			str = str + Integer.toHexString(m);
		}
		log.info("header:" + str);
		if (packet.getClass().equals(ARPPacket.class)) {
			log.info("ARP");
			try {
				ARPPacket arpPacket = (ARPPacket) packet;
				log.info("sender mac: " + arpPacket.getSenderHardwareAddress());
				log.info("sender ip: " + arpPacket.getSenderProtocolAddress());
				log.info("target mac: " + arpPacket.getTargetHardwareAddress());
				log.info("target ip: " + arpPacket.getTargetProtocolAddress());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (packet.getClass().equals(UDPPacket.class)) {
			log.info("UDP");
			try {
				UDPPacket udpPacket = (UDPPacket) packet;
				log.info("sender ip: " + udpPacket.src_ip + ":" + udpPacket.src_port);
				log.info("target ip: " + udpPacket.dst_ip + ":" + udpPacket.dst_port);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (packet.getClass().equals(TCPPacket.class)) {
			log.info("TCP");
			try {
				TCPPacket tcpPacket = (TCPPacket) packet;
				log.info("sender ip: " + tcpPacket.src_ip + ":" + tcpPacket.src_port);
				log.info("target ip: " + tcpPacket.dst_ip + ":" + tcpPacket.dst_port);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (packet.getClass().equals(IPPacket.class)) {

		} else if (packet.getClass().equals(ICMPPacket.class)) {
			ICMPPacket icmpPacket = (ICMPPacket) packet;
			log.info("sender ip: " + icmpPacket.src_ip);
			log.info("target ip: " + icmpPacket.dst_ip);
		} else {
			log.info("GGP/EGP/JGP/OSPF/ISO/TP4: " + new String(packet.data));
			return;
		}

		// packet data
		byte[] k = packet.data;
		String data = new String(k);
		log.info(data);
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		int a = 0;
		byte[] b = devices[0].mac_address;
		String mac = "00";
		for (int j = 0; j < b.length; j++) {
			a = b[j];
			a = a << 24;
			a = a >>> 24;
			mac += Integer.toHexString(a);
		}
		log.info("MAC: " + mac);

		NetworkInterfaceAddress[] k = devices[0].addresses;
		for (int n = 0; n < k.length; n++) {
			log.info("IP: " + k[n].address);
			log.info("netmask: " + k[n].subnet);
		}
		log.info("TYPE: " + devices[0].datalink_description);
		JpcapCaptor jpcap = JpcapCaptor.openDevice(devices[0], 2000, false, 1);
		jpcap.loopPacket(-1, new JpcapTip());
	}

}
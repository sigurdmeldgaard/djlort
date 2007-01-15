package djlort;

import java.io.IOException;
import java.net.*;

public class UdpProtocol implements Protocol {

	private DatagramSocket socket;
	private String host;
	private int port;
	
	public UdpProtocol(String host, int port){
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.host = host;
		this.port = port;
		
	}
	
	public void send(String s) {
		// TODO Auto-generated method stub
		byte[] buf= s.getBytes();
		try {
			DatagramPacket p = new DatagramPacket(buf, buf.length,
				InetAddress.getByName(host), port);
			socket.send(p);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public String toString(){
		return host+":"+port;
	}


}

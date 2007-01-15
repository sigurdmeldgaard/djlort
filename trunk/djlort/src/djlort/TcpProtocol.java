package djlort;

import java.io.*;
import java.net.*;

public class TcpProtocol implements Protocol {

	private Socket socket;
	private DataOutputStream output;
	
	public TcpProtocol(String host, int port){
		try {
			this.socket = new Socket(host, port);
			//this.output = new PrintStream(socket.getOutputStream());
			this.output = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(String s) {
		try {
			output.writeBytes(s+";"); // PD req. a ; at the end
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			byte[] buf= s.getBytes();
//			try {
//				DatagramPacket p = new DatagramPacket(buf, buf.length,
//					InetAddress.getByName(host), port);
//				socket.send(p);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

		
		
	}
	
	public String toString(){
		return socket.toString();
	}

}

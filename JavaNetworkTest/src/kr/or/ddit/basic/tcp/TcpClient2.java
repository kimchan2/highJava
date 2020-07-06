package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient2 {
	public static void main(String[] args) throws IOException {
		//Socket socket = new Socket("localHost", 7777);
		Socket socket = new Socket("192.168.201.35", 7777);
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();
	}
}

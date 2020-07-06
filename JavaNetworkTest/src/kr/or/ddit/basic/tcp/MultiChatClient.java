package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiChatClient {
	Scanner scan = new Scanner(System.in);
	private String nickName; // 대화명
	
	// 비지니스 로직 시작...
	public void clientStart() {
		// 대화명 입력받기
		System.out.println("대화명 : ");
		nickName = scan.next();
		
		Socket socket = null;
		try {
//			String serverIp = "localhost";
			String serverIp = "192.168.201.45";
			socket = new Socket(serverIp, 7777);
			
			System.out.println("서버에 연결되었습니다.");
			
			// 송신용 쓰레드 생성
			ClientSender sender = new ClientSender(socket, nickName);

			// 수신용 쓰레드 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			// 쓰레드 실행
			sender.start();
			receiver.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 메시지를 전송하기 위한 Thread클래스
	class ClientSender extends Thread{
		Socket socket;
		DataOutputStream dos;
		String name; // 대화명
		Scanner scan = new Scanner(System.in);
		
		public ClientSender(Socket socket, String name) {
			this.name = name;
			this.socket = socket;
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		@Override
		public void run() {
			try {
				// 시작하자마자 자신의 대화명을 서버로 전송
				if(dos != null ) {
					dos.writeUTF(name);
				}
				
				while(dos != null) {
					// 키보드로 입력받은 메시지를 서버로 전송
					dos.writeUTF("[" + name + "]" + scan.nextLine());
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	} // 송신용 쓰레드 끝...
	
	public static void main(String[] args) {
		new MultiChatClient().clientStart();
	}
	
	// 수신용 Thread 클래스 정의
	class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream dis;
		
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			while(dis != null) {
				try {
					// 서버로부터 수신한 메시지 출력하기
					System.out.println(dis.readUTF());
				}catch (IOException e) {}
			}
		}
	}
}

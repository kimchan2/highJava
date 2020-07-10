package kr.or.ddit.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import kr.or.ddit.rmi.inf.ChatClient;
import kr.or.ddit.rmi.inf.ChatServer;

@SuppressWarnings("serial")
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient{

   private ChatServer server; // 원격 RMI 채팅 서비스 
   
   private ChatController controller; // 단체채팅화면 제어용 컨트롤러

   private PrivateChatController pcController; // 개인채팅화면 제어용 컨트롤러
   
   private Stage primaryStage;

   protected static String name;


	public ChatClientImpl(String name, ChatController controller, Stage primaryStage) throws RemoteException, Exception {

		ChatClientImpl.name = name;
		
		this.controller = controller;
		
		controller.setChatClientImpl(this);
		
		this.setPrimaryStage(primaryStage);
		
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	/**
	 * 서버에 접속하기
	 * @throws Exception
	 */
	public void connect() throws Exception {
		
		Registry reg = LocateRegistry.getRegistry("192.168.201.45", 8888);
		//System.setProperty("java.rmi.server.hostname","192.168.201.45");

		server = (ChatServer) reg.lookup("RMIChatServer");

		server.addClient(this, name); // 현재 객체를 RMI Chat 서버에 등록
		
		this.controller.setNickName(name); // 닉네임(대화명) 설정하기
		
		this.controller.setServer(server); // RMI 서버 설정
		
	}
	
	/**
	 * RMI서버에서 호출될 메서드(전체 채팅창에 메시지 출력)
	 */
	public void printMessage(String msg) throws RemoteException {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.showMsg(msg); // 컨트롤러객체를 이용하여 대화창에 메시지 출력하기
			}
		});
	}
	
	
	
	/**
	 * RMI서버에서 호출될 메서드(전체 채팅창에 메시지 출력)
	 */
	public void printPrivateMessage(String msg) throws RemoteException {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pcController.showMsg(msg); // 컨트롤러객체를 이용하여 대화창에 메시지 출력하기
			}
		});
	}
	
	/**
	 * RMI서버에서 호출될 메서드(사용자 목록)
	 */
	@Override
	public void setUserList(List<String> userList) throws RemoteException {
		
		Platform.runLater(()->{
			controller.showUserList(userList);
		});
	}

	/**
	 * RMI서버에서 호출될 메서드(1:1 대화 요청 창)
	 */
	@Override
	public void showReqPrivateChatDialog(String fromNickName) throws RemoteException {
		
		Platform.runLater(()->{
			
			Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
			
			alertConfirm.setTitle("1:1 대화 요청");
			alertConfirm.setContentText(fromNickName + "로부터 1:1대화 요청이 왔습니다.\n수락하시겠습니까?");
			
			// Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
			ButtonType confirmResult = alertConfirm.showAndWait().get();
			
			if (confirmResult == ButtonType.OK) {
				try {
					// 요청에 대한 응답하기 (from, to 순서가 바뀜에 주의!!!)
					server.replyPrivateChat(name, fromNickName, true); 
					
					// 1:1 창 띄우기
					FXMLLoader loader = new FXMLLoader(getClass().getResource("PrivateChat.fxml"));
					Parent root = loader.load();
					
					pcController = loader.getController();
					pcController.setServer(server); // 원격객체 설정
					// 메시지 보낼때는 from, to 가 뒤바뀜!!!
					pcController.setFromNickName(name);
					pcController.setToNickName(fromNickName);
					
					Label label = (Label)root.lookup("#lbTitle");
					label.setText("1:1 대화창(With: " + fromNickName + ")");
					
					Scene scene = new Scene(root);
					
					Stage privateChatStage = new Stage();
					privateChatStage.setTitle("1:1 대화창");
					privateChatStage.setScene(scene);
					privateChatStage.show();
					
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (confirmResult == ButtonType.CANCEL) {
					try {
						// 요청에 대한 응답하기 (from, to 순서가 바뀜에 주의!!!)
						server.replyPrivateChat(name, fromNickName, false);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				System.out.println("취소 버튼을 눌렀습니다.");
			}
		});
		
	}
	
	/**
	 * RMI서버에서 호출될 메서드(1:1 대화요청에 대한 응답창)
	 */
	@Override
	public void showReplyPrivateChat(String fromNickName, boolean isAccecpted) throws RemoteException {

		if (isAccecpted) { // 요청이 승인된 경우
			Platform.runLater(() -> {

				showInfoDialog("1:1 대화 요청 등답", fromNickName + "으로부터 1:1 대화 요청이 승인되었습니다.");
				
				try {
					// 1:1 창 띄우기
					FXMLLoader loader = new FXMLLoader(getClass().getResource("PrivateChat.fxml"));
					Parent root = loader.load();
					
					pcController = loader.getController();
					pcController.setServer(server); // 원격객체 설정
					
					// 메시지 보낼때는 from, to 가 뒤바뀜!!!
					pcController.setFromNickName(name);
					pcController.setToNickName(fromNickName);
					
					Label label = (Label)root.lookup("#lbTitle");
					label.setText("1:1 대화창(With: " + fromNickName + ")");
					
					Scene scene = new Scene(root);
					
					Stage privateChatStage = new Stage();
					privateChatStage.setTitle("1:1 대화창");
					privateChatStage.setScene(scene);
					privateChatStage.show();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			});
		} else {
			Platform.runLater(() -> {

				showInfoDialog("1:1 대화 요청 등답", fromNickName + "로부터 1:1대화 요청이 거절되었습니다.");
			});
		}
	}
	
	/**
	 * 알림창 띄우기
	 * @param title
	 * @param contentText
	 */
	private void showInfoDialog(String title, String contentText) {
		
		Alert alertConfirm = new Alert(AlertType.INFORMATION);
		
		alertConfirm.setTitle(title);
		alertConfirm.setContentText(contentText);
		
		// Alert창을 보여주고 사용자가 누른때까지 기다리기
		alertConfirm.showAndWait().get();
	}

}

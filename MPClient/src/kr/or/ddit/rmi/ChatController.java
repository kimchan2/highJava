package kr.or.ddit.rmi;

import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.rmi.inf.ChatServer;

public class ChatController implements Initializable{

	@FXML private TextArea taChatList;
	@FXML private TextField tfChat;
	@FXML private ListView<String> lvUserList;
	
	private String nickName; // 대화명
	
	private ChatServer server; // 원격 RMI 채팅 서비스 
	private ChatClientImpl chatClientImpl; // 원격객체
	
	
	public void setChatClientImpl(ChatClientImpl chatClientImpl) {
		this.chatClientImpl = chatClientImpl;
	}

	/**
	 * 초기화 메서드
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tfChat.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.ENTER) { // 엔터키를 누른 경우..
				try {
					sendMessage(null);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 닉네임(대화명) 설정하기
	 * @param nickName
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * RMI 서버 설정하기
	 * @param server
	 */
	public void setServer(ChatServer server) {
		this.server = server;
	}

	/**
	 * 메시지 전송시 호출되는 메서드
	 * @param event
	 * @throws RemoteException
	 */
	@FXML
	public void sendMessage(ActionEvent event) throws RemoteException{
		String message = tfChat.getText();
		server.sayToAll(message, nickName);
		tfChat.setText("");
	}
	
	/**
	 * 텍스트 박스에 메시지 출력하기
	 * @param message
	 */
	public void showMsg(String message) {
		taChatList.appendText(message+ "\n");
	}

	/**
	 * 리스트뷰에 사용자 목록 출력하기
	 * @param message
	 */
	public void showUserList(List<String> userList) {
		
		lvUserList.setItems(FXCollections.observableArrayList(userList));
	}
	
	/**
	 * RMI 서버 접속 해제
	 * @param event
	 * @throws RemoteException
	 */
	@FXML 
	public void disconnect(ActionEvent event) throws RemoteException {
		
		server.disconnect(chatClientImpl, nickName);
		
		chatClientImpl.getPrimaryStage().close(); // 창 닫기
		
	}
	
	
	/**
	 * 1:1대화 신청하기
	 * @throws RemoteException 
	 */
	@FXML 
	public void requestPrivateChat() throws RemoteException {
		
		if(lvUserList.getSelectionModel().getSelectedItems() == null) {
			System.out.println("사용자 선택해 주세요.");
		}else {
			String toNickName = (String) lvUserList.getSelectionModel().getSelectedItem();
			server.requestPrivateChat(nickName, toNickName);
			
		}
		
	}

	/**
	 * 파일 전송하기
	 */
	@FXML 
	public void sendFile() {
		
		if(lvUserList.getSelectionModel().getSelectedItems() == null) {
			System.out.println("사용자 선택해 주세요.");
		}else {
			// 파일 열기 창
			Button btnFileOpen = new Button("Open FileChooser 실행");
			btnFileOpen.setOnAction(event -> {
				FileChooser filechooser = new FileChooser();
				
				// 확장별로 파일 구분하는 필터 등록하기
				filechooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
						new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
						new ExtensionFilter("Audio Files", "*.wav", "*.mp3"), 
						new ExtensionFilter("All Files", "*.*"));
				
				// Dialog창에서 '열기'버튼을 누르면 선택한 파일 정보가 반환되고
				// '최소'버튼을 누르면 null이 반환된다.
				File selectFile = filechooser.showOpenDialog(lvUserList.getScene().getWindow());
				if (selectFile != null) {
					// 이 영역에서 파일내용을 읽어오는 작업을 수행한다.
					System.out.println("OPEN : " + selectFile.getPath());
				}
			});
			
			// 파일 전송 시작
			String toNickName = (String) lvUserList.getSelectionModel().getSelectedItem();
			//server.requestPrivateChat(nickName, toNickName);
		}
		
	}
	
}

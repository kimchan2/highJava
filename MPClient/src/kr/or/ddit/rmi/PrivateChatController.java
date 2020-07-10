package kr.or.ddit.rmi;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import kr.or.ddit.rmi.inf.ChatServer;

public class PrivateChatController implements Initializable {

	@FXML TextField tfChat;
	@FXML TextArea taChatList;
	@FXML Label lbTitle;
	private String fromNickName, toNickName;
	
	public void setFromNickName(String fromNickName) {
		this.fromNickName = fromNickName;
	}

	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}

	private ChatServer server;

	public void setServer(ChatServer server) {
		this.server = server;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tfChat.setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.ENTER) { // 엔터키를 누른 경우..
				try {
					sendMessage();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 텍스트 박스에 메시지 출력하기
	 * @param message
	 */
	public void showMsg(String message) {
		taChatList.appendText(message+ "\n");
	}
	
	
	/**
	 * 특정인에게 메시지 보내기
	 * @throws RemoteException 
	 */
	@FXML public void sendMessage() throws RemoteException {
		server.sayToSomeone(tfChat.getText(), fromNickName, toNickName);
		tfChat.setText("");
	}
	
	/**
	 * 창 닫기
	 */
	@FXML public void close() {
		Stage stage = (Stage)tfChat.getScene().getWindow();
		stage.close();
	}

}

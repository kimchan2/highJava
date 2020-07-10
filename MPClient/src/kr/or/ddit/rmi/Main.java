package kr.or.ddit.rmi;
	
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("chat.fxml"));
			BorderPane root = (BorderPane)loader.load();
			ChatController controller = loader.getController();
			
			String nickName = "홍길동";
			TextInputDialog dialog = new TextInputDialog("대화명");
			Optional<String> opl = dialog.showAndWait();
			if(opl.isPresent()) {
				nickName = opl.get();
			}
			ChatClientImpl chatClientImpl = new ChatClientImpl(nickName, controller, primaryStage);
			chatClientImpl.connect(); // RMI 서버에 접속하기
			
			Scene scene = new Scene(root,400,500);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

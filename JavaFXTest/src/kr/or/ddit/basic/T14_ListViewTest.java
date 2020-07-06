package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class T14_ListViewTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		ListView<String> list = new ListView<>();
		
		Label label = new Label();
		label.setFont(new Font(20));
		
		// ListView에 들어갈 데이터 구성하기
		ObservableList<String> data =
				FXCollections.observableArrayList(
					"green", "gold", "red", "blue", "black",	
					"brown", "blueviolet", "pink", "yellow", "chocolate"	
				);
		
		list.setItems(data);
		
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				label.setText(newValue);
				label.setTextFill(Color.web(newValue)); // 글자색 변경
			}
		});
		
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(list, label);
		
		Scene scene = new Scene(vbox);
		primaryStage.setTitle("ListView 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

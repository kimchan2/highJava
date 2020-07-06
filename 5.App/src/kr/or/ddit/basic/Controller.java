package kr.or.ddit.basic;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TableColumn;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class Controller implements Initializable{
	
	private Stage primaryStage;
	@FXML TableView<Student> tableView;
	@FXML TableColumn<Student, String> name;
	@FXML TableColumn<Student, String> kor;
	@FXML TableColumn<Student, String> math;
	@FXML TableColumn<Student, String> eng;
	@FXML Button addBtn;
	@FXML Button infoBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		kor.setCellValueFactory(new PropertyValueFactory<>("kor"));
		math.setCellValueFactory(new PropertyValueFactory<>("math"));
		eng.setCellValueFactory(new PropertyValueFactory<>("eng"));
		
		addBtn.setOnAction(e->{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AppMain.fxml"));
			try {
				Parent root = loader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Controller controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
			
			// 새창 띄우기
			// 1. Stage 객체 생성
			Stage dialog = new Stage(StageStyle.UTILITY);
			
			// 2. 모달창 여부 설정
			// 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
			dialog.initModality(Modality.APPLICATION_MODAL);
			
			// 3. 부모창 지정
			dialog.initOwner(primaryStage);
			
			dialog.setTitle("사용자 정의 창");
			
			// 4. 자식창에 나타날 컨테이너 객체 생성
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("추가.fxml"));
			}catch (IOException ex) {
				ex.printStackTrace();
			}
			
			// 부모창에서 fxml로 만든 자식창의 컨트롤 객체얻기
			TextField nameT = (TextField) parent.lookup("#nameT");
			TextField korT = (TextField) parent.lookup("#korT");
			TextField mathT = (TextField) parent.lookup("#mathT");
			TextField engT = (TextField) parent.lookup("#engT");
			Button saveBtn = (Button) parent.lookup("#saveBtn");
			Button cancelBtn = (Button) parent.lookup("#cancelBtn");
			
			saveBtn.setOnAction(e1->{
				Student stu = new Student(
					nameT.getText(),
					korT.getText(),
					mathT.getText(),
					engT.getText()
				);
				tableView.getItems().add(stu);
				dialog.close();
			});
			
			cancelBtn.setOnAction(e1->{
				dialog.close();
			});
			// 5. Scene객체 생성해서 컨테이너 객체 추가
			Scene scene = new Scene(parent);
			
			// 6. Stage객체에 Scene
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
			
		});
		
		infoBtn.setOnAction(e->{
			// 파이차트----------------------------------------------------
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initOwner(primaryStage);
			dialog.setTitle("사용자 정의 창");
			
			Student stu = tableView.getItems().get(
					tableView.getSelectionModel().getSelectedIndex());
			
			PieChart pieChart = new PieChart();
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
					new PieChart.Data("영어", new Integer(stu.getEng())), 
					new PieChart.Data("수학", new Integer(stu.getMath())), 
					new PieChart.Data("국어", new Integer(stu.getKor())) 
				);
			
			pieChart.setLabelLineLength(50);
			pieChart.setLegendSide(Side.BOTTOM); // 범례가 나타날 위치 지정
			pieChart.setData(pieChartData);
			
			Scene scene = new Scene(pieChart, 500, 500);
			dialog.setTitle("파이 그래프");
			dialog.setScene(scene);
			dialog.show();
			// 막대차트----------------------------------------------------
			Stage dialog2 = new Stage(StageStyle.UTILITY);
			dialog2.initOwner(primaryStage);
			dialog2.setTitle("사용자 정의 창");
			
			// 축의 값이 주로 문자열 일때
			CategoryAxis xAxis = new CategoryAxis();
			
			// 축의 값이 숫자일 때 사용하는 객체
			NumberAxis yAxis = new NumberAxis();
			
			// 위에서 만든 축 정보를 이용한 BarChart 객체 생성
			BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
			
			// BarChart에 나타날 데이터 구성하기
			Integer len = tableView.getItems().size();
			
			List<XYChart.Series<String, Number>> serList = new ArrayList<>(); 
			
			// 테이블 Row 수만큼 시리즈를 만들어준다
			for(int i = 0; i < len; i++) {
				serList.add(new XYChart.Series<>());
				serList.get(i).setName(
						tableView.getItems().get(i).getName());
				serList.get(i).getData().addAll(
						new XYChart.Data<>("영어", new Integer(tableView.getItems().get(i).getEng())),
						new XYChart.Data<>("국어", new Integer(tableView.getItems().get(i).getKor())),
						new XYChart.Data<>("수학", new Integer(tableView.getItems().get(i).getMath()))
				);
				bc.getData().add(serList.get(i));
			}
			
			Scene scene2 = new Scene(bc, 800, 600);
			dialog2.setTitle("막대 그래프");
			dialog2.setScene(scene2);
			dialog2.show();
		});
	}
	
	public void setPrimaryStage(Stage primaryStage) {

		this.primaryStage = primaryStage;

	}
}
package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Controller implements Initializable{

	@FXML TextField id;
	@FXML TextField name;
	@FXML TextField tel;
	@FXML TextField addr;
	@FXML Button addBtn;
	@FXML Button crtBtn;
	@FXML Button delBtn;
	@FXML Button conBtn;
	@FXML Button cnBtn;
	@FXML TableColumn<MemVO, String> idCol;
	@FXML TableColumn<MemVO, String> nameCol;
	@FXML TableColumn<MemVO, String> telCol;
	@FXML TableColumn<MemVO, String> addrCol;
	@FXML TableView<MemVO> tableView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
		addrCol.setCellValueFactory(new PropertyValueFactory<>("addr"));
		
		conBtn.setDisable(true);
		cnBtn.setDisable(true);
		
		addBtn.setOnAction(e->{
			// 3가지 버튼 비활성화
			addBtn.setDisable(true);
			crtBtn.setDisable(true);
			delBtn.setDisable(true);
			
			if(id.getText().isEmpty() 
		               || name.getText().isEmpty()
		               || tel.getText().isEmpty()
		               || addr.getText().isEmpty()) {
				errMsg("작업 오류", "빈 항목이 있습니다.");
		        return;
			}
			MemVO mv = new MemVO(id.getText(), name.getText(), tel.getText(), addr.getText());
			
			conBtn.setDisable(false);
			cnBtn.setDisable(false);
			
			cnBtn.setOnAction(e1->{
				id.clear();
				name.clear();
				tel.clear();
				addr.clear();
				conBtn.setDisable(true);
				cnBtn.setDisable(true);
				// 3가지 버튼 활성화
				addBtn.setDisable(false);
				crtBtn.setDisable(false);
				delBtn.setDisable(false);
				
				return;
			});
			conBtn.setOnAction(e2->{
				tableView.getItems().add(mv);
				infoMsg("작업 결과", name.getText() + "님 정보를 추가하는데 성공 했습니다!");
				id.clear();
				name.clear();
				tel.clear();
				addr.clear();
				
				conBtn.setDisable(true);
				cnBtn.setDisable(true);
				addBtn.setDisable(false);
				crtBtn.setDisable(false);
				delBtn.setDisable(false);
			});
		
		});
		
	    crtBtn.setOnAction(e->{
	    	
	    	addBtn.setDisable(true);
			crtBtn.setDisable(true);
			delBtn.setDisable(true);
			
	    	if(id.getText().isEmpty() 
		               || name.getText().isEmpty()
		               || tel.getText().isEmpty()
		               || addr.getText().isEmpty()) {
		            errMsg("작업 오류", "빈 항목이 있습니다.");
		            return;
		         }
	    	conBtn.setDisable(false);
			cnBtn.setDisable(false);
	    	
	    	cnBtn.setOnAction(e1->{
				id.clear();
				name.clear();
				tel.clear();
				addr.clear();
				conBtn.setDisable(true);
				cnBtn.setDisable(true);
				addBtn.setDisable(false);
				crtBtn.setDisable(false);
				delBtn.setDisable(false);
				return;
			});
			conBtn.setOnAction(e2->{
				
				tableView.getItems().set(tableView.getSelectionModel().getSelectedIndex(), 
						new MemVO(id.getText(), name.getText(), tel.getText(), addr.getText()));
				
				infoMsg("작업 결과", name.getText() + "님 정보를 수정했습니다.");
				id.clear();
				name.clear();
				tel.clear();
				addr.clear();
				conBtn.setDisable(true);
				cnBtn.setDisable(true);
				addBtn.setDisable(false);
				crtBtn.setDisable(false);
				delBtn.setDisable(false);
			});
	    	
	    	
	    });
	    
	    delBtn.setOnAction(e->{
	    	addBtn.setDisable(true);
			crtBtn.setDisable(true);
			delBtn.setDisable(true);
			
			conBtn.setDisable(false);
			cnBtn.setDisable(false);
			
	    	cnBtn.setOnAction(e1->{
				id.clear();
				name.clear();
				tel.clear();
				addr.clear();
				conBtn.setDisable(true);
				cnBtn.setDisable(true);
				addBtn.setDisable(false);
				crtBtn.setDisable(false);
				delBtn.setDisable(false);
				return;
			});
	    	
	    	conBtn.setOnAction(e2->{
	    		tableView.getItems().remove(tableView.getSelectionModel().getSelectedIndex());
	    		infoMsg("작업 결과", name.getText() + "님 정보를 삭제했습니다.");
	    		id.clear();
	    		name.clear();
	    		tel.clear();
	    		addr.clear();
	    		conBtn.setDisable(true);
				cnBtn.setDisable(true);
				addBtn.setDisable(false);
				crtBtn.setDisable(false);
				delBtn.setDisable(false);
	    	});
	    });
	    
	    // TableView를 클릭햇을 때 처리
	    tableView.setOnMouseClicked(e->{
	    	if(tableView.getSelectionModel().getSelectedItem() != null) {
	    		
	    		// TableView에서 선택한 줄의 데이터를 가져오기
	    		MemVO mv2 = tableView.getSelectionModel().getSelectedItem();
	    		
	    		id.setText(mv2.getId());
	    		name.setText(mv2.getName());
	    		tel.setText(mv2.getTel());
	    		addr.setText(mv2.getAddr());
	    	}
	    });
	}
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
   
	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("오류");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	public class MemVO{
		private String id;
		private String name;
		private String tel;
		private String addr;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		public MemVO(String id, String name, String tel, String addr) {
			super();
			this.id = id;
			this.name = name;
			this.tel = tel;
			this.addr = addr;
		}
		@Override
		public String toString() {
			return "memVO [id=" + id + ", name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
		}
	}
}

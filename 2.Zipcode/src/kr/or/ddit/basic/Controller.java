package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable{

	@FXML ComboBox<String> option;
	@FXML TextField search;
	@FXML Button searchBtn;
	@FXML TableView<Info> tableView;
	@FXML TableColumn<Info, String> ZIPCODE;
	@FXML TableColumn<Info, String> SIDO;
	@FXML TableColumn<Info, String> GUGUN;
	@FXML TableColumn<Info, String> DONG;
	@FXML TableColumn<Info, String> BUNJI;
	
	private SqlMapClient smc;
	private Reader rd;
	private String selectedOption;
	private ObservableList<Info> iList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ZIPCODE.setCellValueFactory(new PropertyValueFactory<>("ZIPCODE"));
		SIDO.setCellValueFactory(new PropertyValueFactory<>("SIDO"));
		GUGUN.setCellValueFactory(new PropertyValueFactory<>("GUGUN"));
		DONG.setCellValueFactory(new PropertyValueFactory<>("DONG"));
		BUNJI.setCellValueFactory(new PropertyValueFactory<>("BUNJI"));
		
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		
		ObservableList<String> optionList = FXCollections.observableArrayList("동이름", "우편번호");
		option.setItems(optionList);
		
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패");
			e.printStackTrace();
		}
		
		option.setOnAction(e->{
			
			selectedOption = option.getSelectionModel().getSelectedItem();
			
			if(selectedOption.equals("동이름")) {
				if(search.getText() != null) {
					
					searchBtn.setOnAction(e1->{
						try {
							List<Info> list = new ArrayList<>();
							list = smc.queryForList("info.getInfoUsingDong", search.getText());
							iList = FXCollections.observableArrayList(list);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						tableView.setItems(iList);
					});
				}
			}
			
			else if(selectedOption.equals("우편번호")) {
				if(search.getText() != null) {
					
					searchBtn.setOnAction(e1->{
						try {
							List<Info> list = new ArrayList<>();
							list = smc.queryForList("info.getInfoUsingZIPCODE", search.getText());
							iList = FXCollections.observableArrayList(list);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						tableView.setItems(iList);
					});
					
				}
			}
			
		});
		
	}
	
}

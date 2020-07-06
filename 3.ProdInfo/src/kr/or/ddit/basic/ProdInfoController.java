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

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ProdInfoController implements Initializable{

	@FXML ComboBox<String> LPROD;
	@FXML ComboBox<String> PROD;
	@FXML TableView<prodVO> table;
	@FXML TableColumn<prodVO, String> PROD_ID;
	@FXML TableColumn<prodVO, String> PROD_NAME;
	@FXML TableColumn<prodVO, String> PROD_BUYER;
	@FXML TableColumn<prodVO, String> PROD_COST;
	@FXML TableColumn<prodVO, String> PROD_PRICE;
	@FXML TableColumn<prodVO, String> PROD_SALE;
	@FXML TableColumn<prodVO, String> PROD_OUTLINE;
	@FXML TableColumn<prodVO, String> PROD_DETAIL;

	private Reader rd;
	private SqlMapClient smc;
	String LPROD_GU;
	List<String> PROD_NAME_list = new ArrayList<>();
	prodVO pv = new prodVO();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		PROD_ID.setCellValueFactory(new PropertyValueFactory<>("PROD_ID"));
		PROD_NAME.setCellValueFactory(new PropertyValueFactory<>("PROD_NAME"));
		PROD_BUYER.setCellValueFactory(new PropertyValueFactory<>("PROD_BUYER"));
		PROD_COST.setCellValueFactory(new PropertyValueFactory<>("PROD_COST"));
		PROD_PRICE.setCellValueFactory(new PropertyValueFactory<>("PROD_PRICE"));
		PROD_SALE.setCellValueFactory(new PropertyValueFactory<>("PROD_SALE"));
		PROD_OUTLINE.setCellValueFactory(new PropertyValueFactory<>("PROD_OUTLINE"));
		PROD_DETAIL.setCellValueFactory(new PropertyValueFactory<>("PROD_DETAIL"));
		
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패");
			e.printStackTrace();
		}
		
		ObservableList<String> LPROD_list = FXCollections.observableArrayList(
				"컴퓨터제품", "여성캐주얼", "남성캐주얼", "피혁잡화", "화장품", "음반/CD", "도서", "문구류"
		);
		LPROD.setItems(LPROD_list);
		
		
		LPROD.setOnAction(e -> {
			
			String choice = LPROD.getSelectionModel().getSelectedItem();
			try {
				LPROD_GU = (String)smc.queryForObject("prod.getLPROD_GU", choice);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			try {
				PROD_NAME_list = smc.queryForList("prod.getPROD_NAME", LPROD_GU);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ObservableList<String> O_PROD_NAME_list = FXCollections.observableArrayList(PROD_NAME_list);
			PROD.setItems(O_PROD_NAME_list);
			
		});
		
		PROD.setOnAction(e->{
			String vPROD_NAME = PROD.getSelectionModel().getSelectedItem();
			
			try {
				pv = (prodVO)smc.queryForObject("prod.getInfo", vPROD_NAME);
				System.out.println(pv);
			} catch (SQLException e1) {
				System.out.println("getInfo 실패");
				e1.printStackTrace();
				
			}
			
			ObservableList<prodVO> data = FXCollections.observableArrayList(pv);
			
			table.setItems(data);
			
		});
		
	}

}


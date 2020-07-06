package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;

public class PagingController implements Initializable{

	@FXML Pagination pagination;
	@FXML TableView<MemberVO> tableView;
	@FXML TableColumn<MemberVO, String> id;
	@FXML TableColumn<MemberVO, String> name;
	@FXML TableColumn<MemberVO, String> addr;

	private int from, to, countForPage;
	
	private ObservableList<MemberVO> allTableData, currentPageData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		addr.setCellValueFactory(new PropertyValueFactory<>("addr"));

		allTableData = FXCollections.observableArrayList(
				new MemberVO("1", "홍길동1", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("2", "홍길동12", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("3", "홍길동13", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("4", "홍길동14", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("5", "홍길동15", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("6", "홍길동16", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("7", "홍길동17", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("8", "홍길동18", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("9", "홍길동19", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("10", "홍길동20", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("11", "홍길동21", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("12", "홍길동22", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("13", "홍길동23", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("14", "홍길동24", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("15", "홍길동25", "대전시 중구 대흥동 대덕인재개발원1"),
				new MemberVO("16", "홍길동26", "대전시 중구 대흥동 대덕인재개발원1")
		);
		
		tableView.setItems(allTableData);
		
		int totalCnt = allTableData.size(); // 전체 레코드 수
		countForPage = 5;
		int totalPageCnt = ((totalCnt - 1) / countForPage) + 1; // 전체 페이지 수
		
		pagination.setPageCount(totalPageCnt);
		pagination.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * countForPage;
				to = from + countForPage - 1;
				tableView.setItems(getTableViewData(from, to));
				
				return tableView;
			}

		});
		
	}
	
	private ObservableList<MemberVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int totCnt = allTableData.size();
		for(int i = from; i <= to && i < totCnt; i++) {
			currentPageData.add(allTableData.get(i));
		}
		
		return currentPageData;
	}

}

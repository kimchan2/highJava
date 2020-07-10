package kr.or.ddit.member;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.vo.MemberVO;

public class MemberController implements Initializable {
	
	@FXML private TableView<MemberVO> tableView;
	@FXML private TableColumn<MemberVO, String> id;
	@FXML private TableColumn<MemberVO, String> name;
	@FXML private TableColumn<MemberVO, String> tel;
	@FXML private TableColumn<MemberVO, String> address;
	
	@FXML private Pagination pagination;
	
	private int from, to, itemsForPage;
	
	private ObservableList<MemberVO> AllTableData, currentPageData;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후
		//    사용할 객체를 불러온다.
		Registry reg;
		try {
			reg = LocateRegistry.getRegistry("192.168.201.45", 8888);
			IMemberService clientInf = (IMemberService) reg.lookup("memberService");
			// 이제부터는 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			memList = clientInf.getAllMemberList();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
				
		id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));		
		name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));		
		tel.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));		
		address.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));	
		
		// 전체 테이블 데이터 설정
		AllTableData = FXCollections.observableArrayList(memList);
		
		
		itemsForPage = 5; // 한페이지 보여줄 항목 수 설정
		int totPageCount = AllTableData.size()%itemsForPage == 0 ? AllTableData.size()/itemsForPage : AllTableData.size()/itemsForPage + 1;
		pagination.setPageCount(totPageCount); // 전체 페이지 수 설정
		
		//방법1(Callback타입의 익명객체 생성)
		/*pagination.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				tableView.setItems(getTableViewData(from, to));
				
				return tableView;
			}
		});
		*/
		
		//방법2(람다식)
		/*pagination.setPageFactory((Integer pageIndex)->{
			
			from = pageIndex * itemsForPage;
			to = from + itemsForPage - 1;
			tableView.setItems(getTableViewData(from, to));
		
			return tableView;
		}); // 페이징처리를 위한 팩토리 메서드 설정
		*/
		
		
		//방법3(메서드 참조) 
		// =>하나의 메서드만 호출하는 람다식은
		//   '클래스이름::메서드이름' 또는 '참조변수::메서드이름' 으로 바꿀 수 있다.
		pagination.setPageFactory(this::createPage); // 메서드 참조 
		
	}
	
	/**
	 * 페이징 처리를 위한 팩토리 메서드(메서드 참조용)
	 * @param pageIndex
	 * @return
	 */
	private Node createPage(int pageIndex){
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;
		tableView.setItems(getTableViewData(from, to));
		
		return tableView;
	}
	
	/**
	 * TableView에 채워줄 데이터를 가져오는 함수
	 * @param from
	 * @param to
	 * @return
	 */
	private ObservableList<MemberVO> getTableViewData(int from, int to){
		
		currentPageData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = AllTableData.size();
		for(int i = from; i <= to && i <totSize; i++){
		
			currentPageData.add(AllTableData.get(i));
		}
		
		return currentPageData;
	}
}

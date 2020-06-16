package kr.or.ddit.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

public class BoardTest {
	
	private Scanner sc = new Scanner(System.in);
	private IBoardService service;
	
	public BoardTest() {
		service = new BoardServiceImpl();
	}
	
	public static void main(String[] args) {
		BoardTest bt = new BoardTest();
		bt.start();
	}
	
	/**
	 * 메뉴 출력 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체 게시판 출력");
		System.out.println("  2. 게시글 작성");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 게시글 삭제");
		System.out.println("  5. 게시글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작 메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu();
			choice = sc.nextInt(); // 메뉴번호 입력받기
			sc.nextLine();
			switch (choice) {
			case 1: // 전체 게시판 출력
				selectAllBoardList();
				break;
			case 2: // 게시글 작성
				insertBoard();
				break;
			case 3: // 게시글 수정
				updateBoard();
				break;
			case 4: // 게시글 삭제
				deleteBoard();
				break;
			case 5: // 게시글 검색
				selectBoardList();
				break;
			case 6: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		 }while(choice!=6);
	}
	
	/**
	 * 데이터베이스의 모든 정보를 반환하는 메서드
	 */
	public void selectAllBoardList() {
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		boardList = service.selectAllBoardList();
		
		System.out.println();
		System.out.println("========================================================================");
		System.out.println("게시글번호\t제목\t작성자\t작성일\t내용");
		System.out.println("========================================================================");
		
		for(BoardVO bv : boardList) {
			System.out.println(bv.getBoard_no() + "\t" + bv.getBoard_title() + "\t" + bv.getBoard_writer()
							 + "\t" + bv.getBoard_date() + "\t" + bv.getBoard_content());
		}
		System.out.println("========================================================================");
		System.out.println("출력완료");
	}
	
	/**
	 * 데이터베이스에 게시글을 INSERT하는 메서드
	 */
	public void insertBoard() {
		BoardVO bv = new BoardVO();
		System.out.print("제목을 입력하세요 >> ");
		String board_title = sc.next();
		
		System.out.print("작성자를 입력하세요 >> ");
		String board_writer = sc.next();
		
		sc.nextLine();
		
		System.out.println("내용을 입력하세요 >> ");
		String board_content = sc.nextLine();
		
		bv.setBoard_title(board_title);
		bv.setBoard_writer(board_writer);
		bv.setBoard_content(board_content);
		
		int cnt = service.insertBoard(bv);
		if( cnt > 0 ) {
			System.out.println("게시글 작성 성공");
		}else {
			System.out.println("게시글 작성 실패");
		}
	}
	
	/**
	 * 데이터베이스에 게시글을 UPDATE하는 메서드
	 */
	public void updateBoard() {
		BoardVO bv = new BoardVO();
		
		System.out.print("수정할 게시글 번호를 입력하세요 >> ");
		int board_no = sc.nextInt();
		sc.nextLine();
		
		System.out.print("수정할 게시글 제목을 입력하세요 >> ");
		String board_title = sc.next();
		
		System.out.print("수정할 게시글 작성자를 입력하세요 >> ");
		String board_writer = sc.next();

		System.out.print("수정할 게시글 내용을 입력하세요 >> ");
		String board_content = sc.next();
		
		bv.setBoard_no(board_no);
		bv.setBoard_title(board_title);
		bv.setBoard_writer(board_writer);
		bv.setBoard_content(board_content);
		
		int cnt = service.updateBoard(bv);
		if( cnt > 0 ) {
			System.out.println("게시글 수정 성공");
		}else {
			System.out.println("게시글 수정 실패");
		}
	}
	
	/**
	 * 데이터베이스에 게시글을 DELETE하는 메서드
	 */
	public void deleteBoard() {
		System.out.print("삭제할 게시글 번호를 입력하세요 >> ");
		int board_no = sc.nextInt();
		
		int cnt = service.deleteBoard(board_no);
		
		if( cnt > 0) {
			System.out.println("게시글 삭제 성공");
		}else {
			System.out.println("게시글 삭제 실패");
		}
	}
	
	/**
	 * BoardVO에 담긴 데이터를 이용하여 게시글을 검색하는 메서드
	 */
	public void selectBoardList() {
		List<BoardVO> boardList = new ArrayList<>();
		BoardVO bv = new BoardVO();
		
		System.out.print("검색할 게시글 번호를 입력하세요 >> ");
		int board_no = sc.nextInt();
		
		sc.nextLine();
		System.out.print("검색할 게시글 제목을 입력하세요 >> ");
		String board_title = sc.nextLine();
		
		System.out.print("검색할 게시글  작성자를 입력하세요 >> ");
		String board_writer = sc.nextLine();
		
		System.out.print("검색할 게시글 날짜를 입력하세요 >> ");
		String board_date = sc.nextLine();
		
		System.out.print("검색할 게시글 내용을 입력하세요 >> ");
		String board_content = sc.nextLine();

		bv.setBoard_no(board_no);
		bv.setBoard_title(board_title);
		bv.setBoard_writer(board_writer);
		bv.setBoard_date(board_date);
		bv.setBoard_content(board_content);
		boardList = service.selectBoardList(bv);
		
		if(boardList.size() != 0) {
			System.out.println();
			System.out.println("========================================================================");
			System.out.println("게시글번호\t제목\t작성자\t작성일\t내용");
			System.out.println("========================================================================");
			
			for(BoardVO bv2 : boardList) {
				System.out.println(bv2.getBoard_no() + "\t" + bv2.getBoard_title() + "\t" + bv2.getBoard_writer()
								 + "\t" + bv2.getBoard_date() + "\t" + bv2.getBoard_content());
			}
			System.out.println("========================================================================");
			System.out.println("출력완료");
		} else {
			System.out.println("검색 결과가 없습니다");
		}
	}
}
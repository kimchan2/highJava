package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal
*/
public class BoardImpl implements IBoard{
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		BoardImpl board = new BoardImpl();
		board.start();
	}

	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = sc.nextInt(); // 메뉴번호 입력받기
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

	
	@Override
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

	@Override
	public void selectAllBoardList() {
		
		System.out.println();
	    System.out.println("--------------------------------------------------");
	    System.out.println(" BOARD_NO\t\tBOARD_TITLE\t\tBOARD_WRITER\t\tBOARD_DATE\t\tBOARD_CONTENT");
	    System.out.println("--------------------------------------------------");
		
		try{
		
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM jdbc_board ORDER BY board_no";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Integer board_no = rs.getInt("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				String board_date = rs.getString("board_date");
				String board_content = rs.getString("board_content");
				System.out.println(board_no + "\t\t" + board_title + "\t\t" + board_writer + "\t\t"
								   + board_date + "\t\t" + board_content);
			}
			System.out.println("-----------------------출력 끝-----------------------");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnet();
		}
	}

	@Override
	public void insertBoard() {
		
		System.out.print("게시글 제목을 입력하세요 >> ");
		String board_title = sc.next();
		
		sc.nextLine();
		
		System.out.print("게시글 작성자를 입력하세요 >> ");
		String board_writer = sc.next();
		
		sc.nextLine();
		
		System.out.print("게시글 내용을 입력하세요 >> ");
		String board_content = sc.next();
		
		
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO jdbc_board VALUES (board_seq.nextVal, ?, ?, SYSDATE, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_writer);
			pstmt.setString(3, board_content);
			
			int cnt = pstmt.executeUpdate();
			
			if( cnt > 0) {
				System.out.println("게시글 등록 완료");
			}else {
				System.out.println("게시글 등록 실패");
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnet();
		}
	}

	@Override
	public void updateBoard() {
		int chk = 0;
		
		do {
			System.out.print("수정할 게시글 번호를 입력하세요 >> ");
			int board_no = Integer.parseInt(sc.next());
			
			try {
				conn = DBUtil.getConnection();
				String sql = "SELECT COUNT(board_no) FROM jdbc_board WHERE board_no = " + board_no;
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					chk = rs.getInt("COUNT(board_no)");
				}
				
				if(chk > 0) {
					System.out.print("수정할 게시글 내용을 입력해주세요 >> ");
					String board_content = sc.next();
					String sql2 = "UPDATE jdbc_board SET board_content = ? " +
								  "WHERE board_no = " + board_no;
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, board_content);
					
					int cnt = pstmt.executeUpdate();
					
					if(cnt > 0) {
						System.out.println("게시글 수정 완료");
					}else {
						System.out.println("게시글 수정 실패");
					}
					
				}else{
					System.out.println("등록되지 않은 게시글입니다.");
					System.out.println("다시 입력해주세요.");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}while(chk == 0);
	}

	@Override
	public void deleteBoard() {
		int chk = 0;
		
		do {
			System.out.print("삭제 게시글 번호를 입력하세요 >> ");
			int board_no = Integer.parseInt(sc.next());
			
			try {
				conn = DBUtil.getConnection();
				String sql = "SELECT COUNT(board_no) FROM jdbc_board WHERE board_no = " + board_no;
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					chk = rs.getInt("COUNT(board_no)");
				}
				
				if(chk > 0) {
					String sql2 = "DELETE jdbc_board WHERE board_no = " + board_no;
					pstmt = conn.prepareStatement(sql2);
					
					int cnt = pstmt.executeUpdate();
					
					if(cnt > 0) {
						System.out.println("게시글 삭제 완료");
					}else {
						System.out.println("게시글 삭제 실패");
					}
					
				}else{
					System.out.println("등록되지 않은 게시글입니다.");
					System.out.println("다시 입력해주세요.");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}while(chk == 0);
	}

	@Override
	public void selectBoardList() {
		int chk = 0;
		
		do {
			sc.nextLine();
			System.out.print("게시글 작성자를 입력하세요 >> ");
			String input_board_writer = sc.next();
			
			try {
				conn = DBUtil.getConnection();
				String sql = "SELECT * FROM jdbc_board WHERE board_writer = " + input_board_writer;
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					chk = rs.getInt("board_no");
				}
				
				if(chk > 0) {
					conn = DBUtil.getConnection();
					String sql2 = "SELECT * FROM jdbc_board WHERE board_writer = " + input_board_writer;
					rs = stmt.executeQuery(sql2);
					
					while(rs.next()) {
						Integer board_no = rs.getInt("board_no");
						String board_title = rs.getString("board_title");
						String board_writer = rs.getString("board_writer");
						String board_date = rs.getString("board_date");
						String board_content = rs.getString("board_content");
						System.out.println(board_no + "\t\t" + board_title + "\t\t" + board_writer + "\t\t"
										   + board_date + "\t\t" + board_content);
					}
					System.out.println("-----------------------출력 끝-----------------------");
					
				}else{
					System.out.println("게시글을 입력하지 않은 사용자 입니다.");
					System.out.println("다시 입력해주세요.");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}while(chk == 0);
	}
	
	private void disConnet() {
	      //  사용했던 자원 반납
	      if(rs!=null)try{ rs.close(); }catch(SQLException e){}
	      if(stmt!=null)try{ stmt.close(); }catch(SQLException e){}
	      if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
	      if(conn!=null)try{ conn.close(); }catch(SQLException e){}
	   }
	
}















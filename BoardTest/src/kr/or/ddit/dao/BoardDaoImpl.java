package kr.or.ddit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.BoardVO;

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
public class BoardDaoImpl implements IBoardDao{
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner sc = new Scanner(System.in);
	
	@Override
	public boolean getBoard(Integer board_no) {
		
		boolean check = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM jdbc_board " +
						 " WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0) {
				check = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnet();
		}
		return check;
	}
	
	@Override
	public List<BoardVO> selectAllBoardList() {

		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try{
		
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM jdbc_board";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoard_no(rs.getInt("board_no"));
				bv.setBoard_title(rs.getString("board_title"));
				bv.setBoard_writer(rs.getString("board_writer"));
				bv.setBoard_date(rs.getString("board_date"));
				bv.setBoard_content(rs.getString("board_content"));
				
				boardList.add(bv);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnet();
		}
		return boardList;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO jdbc_board VALUES (board_seq.nextVal, ?, ?, SYSDATE, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnet();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;

		try {
			String sql = "UPDATE jdbc_board SET board_title = ?, board_writer = ?, board_content = ? "
					   + "WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			pstmt.setInt(4, bv.getBoard_no());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnet();
		}
		return cnt;
	}


	@Override
	public int deleteBoard(Integer board_no) {
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM jdbc_board WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> selectBoardList(BoardVO bv) {
		
		List<BoardVO> boardList = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM jdbc_board where 1=1 ";
			if (bv.getBoard_no() != null && !bv.getBoard_no().equals("")) {
				sql += " AND board_no = ? ";
			}
			if (bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
				sql += " AND board_title = ? ";
			}
			if (bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
				sql += " AND board_writer = ? ";
			}
			if (bv.getBoard_date() != null && !bv.getBoard_date().equals("")) {
				sql += " AND board_date = ? ";
			}
			if (bv.getBoard_content() != null && !bv.getBoard_content().equals("")) {
				sql += " AND board_content LIKE '%' || ? || '%' ";
			}

			pstmt = conn.prepareStatement(sql);
			int index = 1;

			if (bv.getBoard_no() != null && !bv.getBoard_no().equals("")) {
				pstmt.setInt(index++, bv.getBoard_no());
			}
			if (bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
				pstmt.setString(index++, bv.getBoard_title());
			}
			if (bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
				pstmt.setString(index++, bv.getBoard_writer());;
			}
			if (bv.getBoard_date() != null && !bv.getBoard_date().equals("")) {
				pstmt.setString(index++, bv.getBoard_date());
			}
			if (bv.getBoard_content() != null && !bv.getBoard_content().equals("")) {
				pstmt.setString(index++, bv.getBoard_content());
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bv2 = new BoardVO();
				bv2.setBoard_no(rs.getInt("board_no"));
				bv2.setBoard_title(rs.getString("board_title"));
				bv2.setBoard_writer(rs.getString("board_writer"));
				bv2.setBoard_date(rs.getString("board_date"));
				bv2.setBoard_content(rs.getString("board_content"));
				
				boardList.add(bv2);
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return boardList;
	}
	
	private void disConnet() {
	      //  사용했던 자원 반납
	      if(rs!=null)try{ rs.close(); }catch(SQLException e){}
	      if(stmt!=null)try{ stmt.close(); }catch(SQLException e){}
	      if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
	      if(conn!=null)try{ conn.close(); }catch(SQLException e){}
	   }
}















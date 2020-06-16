package kr.or.ddit.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

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
	
	private Scanner sc = new Scanner(System.in);
	private SqlMapClient smc;
	private static IBoardDao dao;
	
	private BoardDaoImpl() {
		Reader rd;
		
		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static IBoardDao getInstance() {
		if( dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}
	
	@Override
	public boolean getBoard(int board_no) {
		
		boolean check = false;
		try {
			
			BoardVO bv = (BoardVO) smc.queryForObject("boardTest", board_no);
			if(bv != null) {
				check = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	@Override
	public List<BoardVO> selectAllBoardList() {

		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try{
			
			boardList = smc.queryForList("boardTest.selectAllBoardList");
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		
		int cnt = 0;
		Object obj = new Object();
		
		try {
			
			obj = smc.insert("boardTest.insertBoard", bv);
			if(obj == null) {
				cnt = 1;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;

		try {
			
			cnt = smc.update("boardTest.updateBoard", bv);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public int deleteBoard(int board_no) {
		int cnt = 0;

		try {

			cnt = smc.delete("boardTest.deleteBoard", board_no);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> selectBoardList(BoardVO bv) {
		
		List<BoardVO> boardList = new ArrayList<>();
		try {
			
			boardList = smc.queryForList("boardTest.selectBoardList", bv);
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return boardList;
	}
}















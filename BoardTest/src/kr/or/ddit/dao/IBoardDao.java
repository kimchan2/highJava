package kr.or.ddit.dao;

import java.util.List;

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

public interface IBoardDao {
	
	/**
	 * 입력받은 작성자가 작성한 게시글이 존재하는지 확인하는 메서드
	 * @param board_no 확인할 게시글 번호
	 * @return 존재하면 true, 없으면 false
	 */
	public boolean getBoard(Integer board_no);
	
	/**
	 * 데이터베이스의 모든 정보를 반환하는 메서드
	 * @return 모든 게시글 List
	 */
	public List<BoardVO> selectAllBoardList();
	
	/**
	 * 데이터베이스에 게시글을 INSERT하는 메서드
	 * @param bv INSERT할 정보가 담긴 BoardVO 객체
	 * @return INSERT 성공시 1, 실패시 0
	 */
	public int insertBoard(BoardVO bv);
	
	/**
	 * 데이터베이스에 게시글을 UPDATE하는 메서드
	 * @param bv UPDATE할 정보가 담긴 BoardVO 객체
	 * @return UPDATE 성공시 1, 실패시 0
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 데이터베이스에 게시글을 DELETE하는 메서드
	 * @param board_no 삭제할 게시글 번호
	 * @return DELETE 성공시 1, 실패시 0
	 */
	public int deleteBoard(Integer board_no);
	
	/**
	 * BoardVO에 담긴 데이터를 이용하여 게시글을 검색하는 메서드
	 * @param bv 검색할 자료가 들어있는 BoardVO 객체
	 * @return 검색 결과를 담은 List
	 */
	public List<BoardVO> selectBoardList(BoardVO bv);
}












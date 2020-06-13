package kr.or.ddit.basic;

import java.util.List;

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

public interface IBoard {
	
	/**
	 * 메뉴를 보여주는 메서드
	 */
	public void displayMenu();
	
	/**
	 * 게시판 전체 출력 메서드
	 */
	public void selectAllBoardList();
	
	/**
	 * T08_BoardVO 객체에 담겨진 자료를 jdbc_board DB에 INSERT하는 메서드
	 */
	public void insertBoard();
	
	/**
	 * T08_BoardVO 객체에 담겨진 자료를 jdbc_board DB에 해당하는 board_no를 찾아서 UPDATE하는 메서드
	 */
	public void updateBoard();
	
	/**
	 * board_no에 해당하는 게시글을 jdbc_board DB에서 찾아서 게시글을 DELETE하는 메서드
	 */
	public void deleteBoard();
	
	/**
	 * board_writer에 해당하는 게시글을 jdbc_board DB에서 찾아서 게시글을 SELECT하는 메서드
	 */
	public void selectBoardList();
}












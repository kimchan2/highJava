package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IBoardService {
	
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

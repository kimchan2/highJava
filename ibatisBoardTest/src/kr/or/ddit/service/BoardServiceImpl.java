package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.dao.BoardDaoImpl;
import kr.or.ddit.dao.IBoardDao;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
	IBoardDao board = BoardDaoImpl.getInstance();

	@Override
	public List<BoardVO> selectAllBoardList() {
		return board.selectAllBoardList();
	}

	@Override
	public int insertBoard(BoardVO bv) {
		return board.insertBoard(bv);
	}

	@Override
	public int updateBoard(BoardVO bv) {
		return board.updateBoard(bv);
	}

	@Override
	public int deleteBoard(int board_no) {
		return board.deleteBoard(board_no);
	}

	@Override
	public List<BoardVO> selectBoardList(BoardVO bv) {
		return board.selectBoardList(bv);
	}

}

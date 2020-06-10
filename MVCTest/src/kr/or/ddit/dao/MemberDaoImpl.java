package kr.or.ddit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements iMemberDao{
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	
	@Override
	public int insertMember(MemberVO mv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getMember(String memId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(MemberVO mv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}

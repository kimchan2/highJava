package kr.or.ddit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static IMemberDao dao;
	
	private MemberDaoImpl() {}
	
	public static IMemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	private void disConnet() {
	      //  사용했던 자원 반납
	      if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
	      if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
	      if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
	      if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
	         conn = DBUtil.getConnection();
	         
	         String sql = "INSERT INTO MYMEMBER(mem_id, mem_name, mem_tel, mem_addr) "
	                  + " VALUES (?, ?, ?, ?)";
	         
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, mv.getMem_id());
	         pstmt.setString(2, mv.getMem_name());
	         pstmt.setString(3, mv.getMem_tel());
	         pstmt.setString(4, mv.getMem_addr());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnet();
		}
		return cnt;
	}

	@Override
	public boolean getMember(String memId) {
		boolean check = false;
		try{
	         conn = DBUtil.getConnection();
	         String sql = "SELECT COUNT(*) as cnt FROM mymember"
	                  + " WHERE mem_id = ?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, memId);
	         
	         rs = pstmt.executeQuery();
	         
	         int cnt = 0;
	         if(rs.next()) {
	            cnt = rs.getInt("cnt");
	         }
	         if(cnt > 0) {
	            check = true;
	         }
	         
	      }catch(SQLException e){
	         e.printStackTrace();
	      }finally {
	         disConnet(); // 자원반납
	      }
		return check;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
	         conn = DBUtil.getConnection();
	    	 //conn = DBUtil3.getConnection();
	         String sql = "SELECT * FROM mymember";
	         
	         stmt = conn.createStatement();
	         
	         rs = stmt.executeQuery(sql);
	         
	         while(rs.next()) {
	            String memId = rs.getString("mem_id");
	            String memName = rs.getString("mem_name");
	            String memTel = rs.getString("mem_tel");
	            String memAddr = rs.getString("mem_addr");
	            
	            MemberVO mv = new MemberVO();
	            mv.setMem_id(memId);
	            mv.setMem_name(memName);
	            mv.setMem_tel(memTel);
	            mv.setMem_addr(memAddr);
	            
	            memList.add(mv);
	         }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnet();
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
		      conn = DBUtil.getConnection();
		      String sql = "UPDATE mymember" + " SET mem_name = ?" 
		                   + " ,mem_tel = ?"+ " ,mem_addr = ?"
		                   + " where mem_id = ?";
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1,mv.getMem_name());
		      pstmt.setString(2,mv.getMem_tel());
		      pstmt.setString(3,mv.getMem_addr());
		      pstmt.setString(4,mv.getMem_id());
		      
			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnet();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM mymember WHERE mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disConnet();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT * FROM mymember where 1=1 ";
			if (mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				sql += " AND mem_id = ? ";
			}
			if (mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				sql += " AND mem_name = ? ";
			}
			if (mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				sql += " AND mem_tel = ? ";
			}
			if (mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				sql += " AND mem_addr LIKE '%' || ? || '%' ";
			}

			pstmt = conn.prepareStatement(sql);
			int index = 1;

			if (mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				pstmt.setString(index++, mv.getMem_id());
			}
			if (mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				pstmt.setString(index++, mv.getMem_name());
			}
			if (mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				pstmt.setString(index++, mv.getMem_tel());
			}
			if (mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				pstmt.setString(index++, mv.getMem_addr());
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mv2 = new MemberVO();
				mv2.setMem_id(rs.getString("mem_id"));
				mv2.setMem_name(rs.getString("mem_name"));
				mv2.setMem_tel(rs.getString("mem_tel"));
				mv2.setMem_addr(rs.getString("mem_addr"));
				
				memList.add(mv2);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnet();
		}
		return memList;
	}
	
	
}

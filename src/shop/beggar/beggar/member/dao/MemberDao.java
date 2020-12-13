package shop.beggar.beggar.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shop.beggar.beggar.vo.MemberVo;

import static shop.beggar.common.JdbcUtil.*;

/**
 * @PackageName		: shop.beggar.beggar.member.dao
 * @FileName		: MemberDao.java
 * @Since			: 2020. 12. 6.
 * @Author			: HJLee
 * @Description		: MemberDao 설정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 6.		HJLee				최초 작성
 *
 */
public class MemberDao {
	private Connection con;
	private MemberDao() {}
	
	public static class LazyHolder {
		private static final MemberDao INSTANCE = new MemberDao();
	}
	
	public static MemberDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int registerMember(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement("insert into inf_mber_tb (id, pwd) values (?,?)");
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			count = pstmt.executeUpdate();
			
			if (count > 0) {
				pstmt = con.prepareStatement("insert into inf_mber_privcy_tb (mber_sq, name, phone, email, email_fl, sms_fl) values ((select mber_sq from inf_mber_tb where id=?),?,?,?,?,?)");
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getPhone());
				pstmt.setString(4, vo.getEmail());
				pstmt.setBoolean(5, vo.isEmail_fl());
				pstmt.setBoolean(6, vo.isSms_fl());
				count = pstmt.executeUpdate();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		
		return count;
	}
	
	public MemberVo getMemberLoginInfo(MemberVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select id, pwd, mber_sq from inf_mber_tb where id=?");
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setMber_sq(rs.getInt("mber_sq"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return vo;
	}
	
	public String searchId(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		
		try {
			pstmt = con.prepareStatement("select id from inf_mber_tb a INNER JOIN inf_mber_privcy_tb b where a.mber_sq = b.mber_sq" + query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				id = rs.getString("id");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return id;
	}
	
	public int changeRandomPwd(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement("update inf_mber_tb set pwd=? where id=?");
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getId());
			count = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		
		return count;
	}
	
	public int changeNewPwd(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement("update inf_mber_tb set pwd=? where mber_sq=?");
			pstmt.setString(1, vo.getPwd());
			pstmt.setInt(2, vo.getMber_sq());
			count = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		
		return count;
	}
	
	public int loginHisRegister(MemberVo vo, String ipAddr) {
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement("insert into inf_mber_lgn_hist_tb (mber_sq, ipaddr) values (?,?)");
			pstmt.setInt(1, vo.getMber_sq());
			pstmt.setString(2, ipAddr);
			count = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		
		return count;
	}
	
}

﻿package shop.beggar.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Pagenation;
import static shop.beggar.common.JdbcUtil.close;

/**
 * @PackageName : shop.beggar.admin.dao
 * @FileName : AdmindDao.java
 * @Since : 2020. 12. 7.
 * @Author : KB
 * @Description :
 *              =====================================================================================
 *              Modification History
 *              =====================================================================================
 *              Date Author Note
 *              -------------------------------------------------------------------------------------
 *              2020. 12. 7. KB 최초 작성
 *
 */
public class AdminDao {
	private Connection con;

	private AdminDao() {
	}

	private static class LazyHolder {
		private static final AdminDao INSTANCE = new AdminDao();
	}

	public static AdminDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<MemberVo> getArticleList(Pagenation pagenation, String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberVo> list = new ArrayList<>();
		try {
//			pstmt = con.prepareStatement("select * from"
//                    + " (select p.*, a.id, row_number()"
//                    + " over(order by mber_sq desc) as rnum"
//                    + " from inf_mber_tb p"
//                    + " inner join inf_privcy_tb a"
//                    + " on p.mber_sq=a.mber_sq"
//                    + " where 1=1" + query +")"
//                    + " where rnum between ? and ?");
			pstmt = con.prepareStatement("select * from(inf_mber_tb a inner JOIN inf_mber_privcy_tb b on a.mber_sq=b.mber_sq) where 1=1"+query+" LIMIT ?,?");
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			pstmt.setInt(2, pagenation.getEndArticleNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVo vo = new MemberVo();
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setId(rs.getString("id"));
				vo.setDttm(rs.getString("dttm"));
				vo.setName(rs.getString("name"));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	public int getArticleCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mber_tb");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}

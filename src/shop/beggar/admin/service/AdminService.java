package shop.beggar.admin.service;

import java.sql.Connection;
import java.util.ArrayList;

import shop.beggar.admin.dao.AdminDao;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Pagenation;

import static shop.beggar.common.JdbcUtil.*;
/**
 * @PackageName		: shop.beggar.admin.service
 * @FileName		: AdminService.java
 * @Since			: 2020. 12. 6.
 * @Author			: HJLee
 * @Description		: AdminService 설정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 6.		HJLee				최초 작성
 *
 */


public class AdminService {
	public ArrayList<MemberVo> getArticleList(Pagenation pagenation,String query) {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<MemberVo> list = dao.getArticleList(pagenation,query);
		close(con);
		return list;
	}
	
	public int getArticleCount() {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getArticleCount();
		close(con);
		return count;
	}
}

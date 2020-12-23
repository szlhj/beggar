package shop.beggar.admin.service;

import java.sql.Connection;
import java.util.ArrayList;

import shop.beggar.admin.dao.AdminDao;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.member.dao.MemberDao;
import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.beggar.vo.ItemVo;
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
	
	public ArrayList<ItemVo> getItemArticleList(Pagenation pagenation,String query) {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<ItemVo> list = dao.getItemArticleList(pagenation,query);
		close(con);
		return list;
	}
	public ArrayList<BoardVo> getBoardArticleList(Pagenation pagenation,String query) {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<BoardVo> list = dao.getBoardArticleList(pagenation, query);
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
	
	public int getItemArticleCount() {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getItemArticleCount();
		close(con);
		return count;
	}
	public int getBoardArticleCount() {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getBoardArticleCount();
		close(con);
		return count;
	}

	public boolean itemAdd(ItemVo vo, int admin_sq) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.itemAdd(vo, admin_sq);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	public boolean boardAdd(BoardVo vo, int admin_sq) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.boardAdd(vo, admin_sq);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}

	public boolean registerAdmin(AdminVo vo) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.registerAdmin(vo);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public int supperAdminInfo() {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.supperAdminInfo();
		close(con);
		return count;
	}
	
	public AdminVo adminLoginInfo(AdminVo vo) {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AdminVo infoVo = dao.adminLoginInfo(vo);
		close(con);
		return infoVo;
	}

	public boolean adminHistory(AdminVo vo,String ipAddr) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.adminHistory(vo, ipAddr);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public AdminVo adminInfoAll(AdminVo vo) {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		AdminVo infoVo = dao.adminInfoAll(vo);
		close(con);
		return infoVo;
	}

	public boolean adminModify(AdminVo vo) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.adminModify(vo);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	
	public ItemVo getItemDetail(ItemVo vo) {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ItemVo infoVo = dao.getItemDetail(vo);
		close(con);
		return infoVo;
	}

	public boolean itemModify(ItemVo vo, int admin_sq) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.itemModify(vo, admin_sq);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}

	public boolean itemShow(ItemVo vo, int admin_sq) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.itemShow(vo, admin_sq);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}

	public boolean itemDel(ItemVo vo, int admin_sq) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.itemDel(vo, admin_sq);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
}

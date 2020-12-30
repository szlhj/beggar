package shop.beggar.beggar.item.service;

import static shop.beggar.common.JdbcUtil.*;


import java.sql.Connection;
import java.util.ArrayList;

import shop.beggar.admin.dao.AdminDao;
import shop.beggar.beggar.item.dao.ItemDao;
import shop.beggar.beggar.member.dao.MemberDao;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Pagenation;



/**
 * @PackageName		: shop.beggar.beggar.item.service
 * @FileName		: ItemService.java
 * @Since			: 2020. 12. 6.
 * @Author			: HJLee
 * @Description		: ItemService 설정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 6.		HJLee				최초 작성
 *
 */
public class ItemService {
	public ArrayList<ItemVo> getItemPageInfo(Pagenation pagenation , String category) {
		ItemDao dao= ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<ItemVo> list = dao.getItemPageInfo(pagenation , category);
		close(con);
		return list;
	}
	
	public int getArticleCount(String category) {
		ItemDao dao= ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getArticleCount(category);
		close(con);
		return count;
	}
	
	public boolean dummyRegisterItemInfo(ItemVo vo) {
		ItemDao dao = ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.dummyRegisterItemInfo(vo);
		boolean isSuccess = true;
		if(count > 0 ) {
			commit(con); //0보다 크면 커밋
		} else {
			rollback(con);  //작으면 롤백함
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}
	
	public ItemVo getItemDetailInfo(ItemVo vo) {
		ItemDao dao = ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ItemVo voInfo = dao.getItemDetailInfo(vo);
		
		close(con);
		
		return voInfo;
	}
	
	public boolean registerCart(ItemVo vo) {
		ItemDao dao = ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.registerCart(vo);
		boolean isSuccess = true;
		if(count > 0) {
			commit(con);//0보다 크면 커밋
		} else {
			rollback(con);  //작으면 롤백함
			isSuccess = false;
		}
		close(con);
		return isSuccess;
	}
	
}

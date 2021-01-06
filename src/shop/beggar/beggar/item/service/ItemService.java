package shop.beggar.beggar.item.service;

import static shop.beggar.common.JdbcUtil.close;
import static shop.beggar.common.JdbcUtil.commit;
import static shop.beggar.common.JdbcUtil.getConnection;
import static shop.beggar.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.beggar.admin.dao.AdminDao;
import shop.beggar.beggar.item.dao.ItemDao;
import shop.beggar.beggar.member.dao.MemberDao;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.beggar.vo.OrderVo;
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
	
//---------------------------------------------------------------------------
//	inf_orser_tb 관련 시작	
	public OrderVo orderMberInfo(int mber_sq) {
		ItemDao dao = ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		OrderVo voInfo = dao.orderMberInfo(mber_sq);
		
		close(con);
		
		return voInfo;
	}
	
	public ArrayList<OrderVo> orderItemList(int mber_sq) {
		ItemDao dao= ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<OrderVo> list = dao.orderItemList(mber_sq);
		close(con);
		return list;
	}
	
	public boolean orderPayment(OrderVo vo) {
		ItemDao dao = ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.orderPayment(vo);
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
	
	public ArrayList<OrderVo> orderItemListnonmber(String nonmber) {
		ItemDao dao= ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<OrderVo> list = dao.orderItemListnonmber(nonmber);
		close(con);
		return list;
	}
	
	public boolean orderPaymentNonmber(OrderVo vo) {
		ItemDao dao = ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.orderPaymentNonmber(vo);
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
	
	public ArrayList<OrderVo> orderPaymentList(OrderVo vo) {
		ItemDao dao= ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<OrderVo> list = dao.orderPaymentList(vo);
		close(con);
		return list;
	}
	
	public OrderVo orderPaymentListAddr(OrderVo vo) {
		ItemDao dao = ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		OrderVo voInfo = dao.orderPaymentListAddr(vo);
		
		close(con);
		
		return voInfo;
	}
	
	public boolean orderDelete(OrderVo vo) {
		ItemDao dao = ItemDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.orderDelete(vo);
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

//	inf_orser_tb 관련 종료
//---------------------------------------------------------------------------
	
}

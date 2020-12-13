package shop.beggar.beggar.item.dao;

import java.sql.Connection;

/**
 * @PackageName		: shop.beggar.beggar.item.dao
 * @FileName		: ItemDao.java
 * @Since			: 2020. 12. 6.
 * @Author			: HJLee
 * @Description		: ItemDao 설정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 6.		HJLee				최초 작성
 *
 */
public class ItemDao {
	private Connection con;
	private ItemDao() {}
	
	private static class LazyHolder {
		private static final ItemDao INSTANCE = new ItemDao();
	}
	
	public static ItemDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
}

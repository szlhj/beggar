package shop.beggar.admin.dao;

import java.sql.Connection;

/**
 * @PackageName		: shop.beggar.admin.dao
 * @FileName		: AdmindDao.java
 * @Since			: 2020. 12. 7.
 * @Author			: KB
 * @Description		: 
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 7.		KB				최초 작성
 *
 */
public class AdmindDao {
	private Connection con;
	private AdmindDao() {}
	
	private static class LazyHolder {
		private static final AdmindDao INSTANCE = new AdmindDao();
	}
	
	public static AdmindDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
}

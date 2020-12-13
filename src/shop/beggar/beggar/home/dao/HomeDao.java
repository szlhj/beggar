package shop.beggar.beggar.home.dao;

import java.sql.Connection;

/**
 * @PackageName		: shop.beggar.beggar.home.dao
 * @FileName		: HomeDao.java
 * @Since			: 2020. 12. 6.
 * @Author			: HJLee
 * @Description		: HomeDao 설정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 6.		HJLee				최초 작성
 *
 */
public class HomeDao {
	private Connection con;
	private HomeDao() {}
	
	private static class LazyHolder {
		private static final HomeDao INSTANCE = new HomeDao();
	}
	
	public static HomeDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
}

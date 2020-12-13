package shop.beggar.beggar.board.dao;

import java.sql.Connection;

/**
 * @PackageName		: shop.beggar.beggar.board.dao
 * @FileName		: BoardDao.java
 * @Since			: 2020. 12. 6.
 * @Author			: HJLee
 * @Description		: BoardDao설정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 6.		HJLee				최초 작성
 *
 */
public class BoardDao {
	private Connection con;
	private BoardDao() {}
	
	private static class LazyHolder {
		private static final BoardDao INSTANCE = new BoardDao();
	}
	
	public static BoardDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
}

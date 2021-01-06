package shop.beggar.beggar.board.service;

import static shop.beggar.common.JdbcUtil.close;
import static shop.beggar.common.JdbcUtil.commit;
import static shop.beggar.common.JdbcUtil.getConnection;
import static shop.beggar.common.JdbcUtil.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import shop.beggar.admin.dao.AdminDao;
import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.common.Pagenation;

/**
 * @PackageName		: shop.beggar.beggar.board.service
 * @FileName		: BoardService.java
 * @Since			: 2020. 12. 6.
 * @Author			: HJLee
 * @Description		: BordService 설정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 6.		HJLee				최초 작성
 *
 */
public class BoardService {
	public boolean boardAdd(BoardVo vo) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.boardAdd(vo);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	public ArrayList<BoardVo> getBoardArticleList(Pagenation pagenation,String query) {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		ArrayList<BoardVo> list = dao.getBoardArticleList(pagenation, query);
		close(con);
		return list;
	}
	public int getBoardArticleCount() {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int count = dao.getBoardArticleCount();
		close(con);
		return count;
	}
	public boolean increaseCount(BoardVo vo) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.increaseCount(vo);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	public BoardVo getBoardDetail(BoardVo vo) {
		AdminDao dao= AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		BoardVo infoVo = dao.getBoardDetail(vo);
		close(con);
		return infoVo;
	}
	public boolean boardModify(BoardVo vo) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.boardModify(vo);
		
		if (count > 0) {
			commit(con);
		} else {
			rollback(con);
			isSuccess = false;
		}
		
		close(con);
		
		return isSuccess;
	}
	public boolean boardDel(BoardVo vo) {
		boolean isSuccess = true;
		
		AdminDao dao = AdminDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int count = dao.boardDel(vo);
		
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

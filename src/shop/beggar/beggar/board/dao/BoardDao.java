package shop.beggar.beggar.board.dao;

import static shop.beggar.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.common.Pagenation;
import shop.beggar.common.Parser;

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
	private BoardDao() {
	}
	
	private static class LazyHolder {
		private static final BoardDao INSTANCE = new BoardDao();
	}
	
	public static BoardDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	public int boardAdd(BoardVo vo) {	
		PreparedStatement pstmt = null;
		int count = 0;
		try {
				pstmt = con.prepareStatement("insert into inf_board_tb (board_number, goods_info, title, content, count, mber_sq) values (?,?,?,?,0,?)");
				pstmt.setInt(1, vo.getBoard_number());
				pstmt.setString(2, vo.getGoods_info());
				pstmt.setString(3, vo.getTitle());
				pstmt.setString(4, vo.getContent());
				pstmt.setInt(5, vo.getMber_sq());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	public ArrayList<BoardVo> getBoardArticleList(Pagenation pagenation, String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select * from(select a.board_sq, a.mber_sq, a.board_number, a.count, a.del_fl, date_format(a.dttm,'%Y-%m-%d') as dttm, a.goods_info, a.title, a.content, a.comment, b.id from inf_board_tb a, inf_mber_tb b where a.mber_sq = b.mber_sq UNION ALL select a.board_sq, a.mber_sq, a.board_number, a.count, a.del_fl, date_format(a.dttm,'%Y-%m-%d') as dttm, a.goods_info, a.title, a.content, a.comment, '' as id from inf_board_tb a) as board where 1=1 "+query+" group by board_sq LIMIT ?,?");
			//select * from (inf_board_tb a, inf_mber_tb b a.mber_sq=b.mber_sq) 
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			pstmt.setInt(2, pagenation.getARTICLE_COUNT_PER_PAGE());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setBoard_sq(rs.getInt("board_sq"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setMber_id(rs.getString("id"));
				vo.setBoard_number(rs.getInt("board_number"));
				vo.setCount(rs.getInt("count"));
				vo.setDel_fl(rs.getInt("del_fl"));
				vo.setDttm(rs.getString("dttm"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	public int getBoardArticleCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_board_tb");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}
	public BoardVo getBoardDetail(BoardVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select * from(select a.*, b.id from inf_board_tb a, inf_mber_tb b where a.mber_sq = b.mber_sq UNION ALL select *, '' as id from inf_board_tb) as board where board_sq=?");
			pstmt.setInt(1, vo.getBoard_sq());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
			//	vo.setItem_sq(rs.getInt("item_sq"));
				vo.setBoard_sq(rs.getInt("board_sq"));
				vo.setMber_id(rs.getString("id"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setBoard_number(rs.getInt("board_number"));
				vo.setGoods_info(rs.getString("goods_info"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString(Parser.chgToHtml("content")));
				vo.setDttm(rs.getString("dttm"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return vo;
	}
	public int increaseCount(BoardVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			
			pstmt = con.prepareStatement("update inf_board_tb set count=count+1 where board_sq=?");
			pstmt.setInt(1, vo.getBoard_sq());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	public int boardDel(BoardVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			
			pstmt = con.prepareStatement("update inf_board_tb set del_fl=?, dttm=now() where board_sq=?");
			pstmt.setBoolean(1, vo.isDel_fl());
			pstmt.setInt(2, vo.getBoard_sq());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	public int boardModify(BoardVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			
			pstmt = con.prepareStatement("update inf_board_tb set board_number=?, goods_info=?, title=?, content=?, count=? where board_sq=?");
			pstmt.setInt(1, vo.getBoard_number());
			pstmt.setString(2, vo.getGoods_info());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setInt(5, vo.getCount());
			pstmt.setInt(6, vo.getBoard_sq());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}

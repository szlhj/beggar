package shop.beggar.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Pagenation;
import shop.beggar.common.Parser;

import static shop.beggar.common.JdbcUtil.close;

/**
 * @PackageName : shop.beggar.admin.dao
 * @FileName : AdmindDao.java
 * @Since : 2020. 12. 7.
 * @Author : KB
 * @Description :
 *              =====================================================================================
 *              Modification History
 *              =====================================================================================
 *              Date Author Note
 *              -------------------------------------------------------------------------------------
 *              2020. 12. 7. KB 최초 작성
 *
 */
public class AdminDao {
	private Connection con;

	private AdminDao() {
	}

	private static class LazyHolder {
		private static final AdminDao INSTANCE = new AdminDao();
	}

	public static AdminDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<MemberVo> getArticleList(Pagenation pagenation, String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select * from inf_mber_tb a inner JOIN inf_mber_privcy_tb b where a.mber_sq=b.mber_sq and 1=1"+query+" LIMIT ?,?");
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			pstmt.setInt(2, pagenation.getARTICLE_COUNT_PER_PAGE());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVo vo = new MemberVo();
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setId(rs.getString("id"));
				vo.setDttm(rs.getString("dttm"));
				vo.setName(rs.getString("name"));
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<ItemVo> getItemArticleList(Pagenation pagenation, String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select item_sq, a.admin_sq, (select admin_name from inf_admin_tb b where a.admin_sq = b.admin_sq) as admin_name, del_fl, show_fl, price, discount, stok, date_format(dttm,'%Y-%c-%d') as dttm, category, code, color, item_name, item_number, item_rating, size, explanation, preview, filepath"
					+ " from inf_goods_tb a where 1=1"+query+" order by dttm desc LIMIT ?,?");
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			pstmt.setInt(2, pagenation.getARTICLE_COUNT_PER_PAGE());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemVo vo = new ItemVo();
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setShow_fl(rs.getBoolean("show_fl"));
				vo.setPrice(rs.getInt("price"));
				vo.setDiscount(rs.getInt("discount"));
				vo.setStok(rs.getInt("stok"));
				vo.setDttm(rs.getString("dttm"));
				vo.setCategory(rs.getNString("category"));
				vo.setCode(rs.getString("code"));
				vo.setColor(rs.getString("color"));
				vo.setItem_name(rs.getString("item_name"));
				vo.setItem_number(rs.getString("item_number"));
				vo.setItem_rating(rs.getString("item_rating"));
				vo.setSize(rs.getString("size"));
				vo.setExplanation(rs.getString("explanation"));
				vo.setPreview(rs.getString("preview"));
				vo.setFilepath(rs.getString("filepath"));
				vo.setAdmin_name(rs.getString("admin_name"));
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
	public ArrayList<BoardVo> getBoardArticleList(Pagenation pagenation, String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select * from inf_board_tb where 1=1"+query+" LIMIT ?,?");
			pstmt.setInt(1, pagenation.getStartArticleNumber());
			pstmt.setInt(2, pagenation.getARTICLE_COUNT_PER_PAGE());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setBoard_sq(rs.getInt("board_sq"));
				vo.setPerson_sq(rs.getInt("admin_sq"));
				vo.setBoard_number(rs.getString("board_number"));
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
	
	

	public int getArticleCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mber_tb");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

	public int getItemArticleCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_goods_tb");
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
	
	
	public int itemAdd(ItemVo vo, int admin_sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_goods_tb (item_name, category, code, price, discount, stok, color, item_number, item_rating, size, explanation, admin_sq) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, vo.getItem_name());
			pstmt.setString(2, vo.getCategory());
			pstmt.setString(3, vo.getCode());
			pstmt.setInt(4, vo.getPrice());
			pstmt.setInt(5, vo.getDiscount());
			pstmt.setInt(6, vo.getStok());
			pstmt.setString(7, vo.getColor());
			pstmt.setString(8, vo.getItem_number());
			pstmt.setString(9, vo.getItem_rating());
			pstmt.setString(10, vo.getSize());
			pstmt.setString(11, vo.getExplanation());
			pstmt.setInt(12, admin_sq);
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	public int boardAdd(BoardVo vo, int admin_sq) {	
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_board_tb (board_number, goods_info, title, content, admin_sq, count) values (?,?,?,?,?,0)");
			pstmt.setString(1, vo.getBoard_number());
			pstmt.setString(2, vo.getGoods_info());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setInt(5, vo.getAdmin_sq());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int registerAdmin(AdminVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_admin_tb ( admin_id, admin_pwd, admin_name, admin_phone, admin_email, admin_memo, admin_supper) "
					+ "values(?,?,?,?,?,?,?)");
			pstmt.setString(1, vo.getAdminId());
			pstmt.setString(2, vo.getAdminPwd());
			pstmt.setString(3, vo.getAdminName());
			pstmt.setString(4, vo.getAdminPhone());
			pstmt.setString(5, vo.getAdminEmail());
			pstmt.setString(6, vo.getAdminMemo());
			pstmt.setBoolean(7, vo.isAdmin_supper());
			
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public int supperAdminInfo() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) as cnt from inf_admin_tb where admin_supper = true");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}

	public AdminVo adminLoginInfo(AdminVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select admin_sq, admin_id, admin_pwd, admin_supper from inf_admin_tb where admin_id=?");
			pstmt.setString(1, vo.getAdminId());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo.setAdminId(rs.getString("admin_id"));
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setAdminPwd(rs.getString("admin_pwd"));
				vo.setAdmin_supper(rs.getBoolean("admin_pwd"));
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
	
	public int adminHistory(AdminVo vo, String ipAddr) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into inf_admin_lgn_hist_tb (admin_sq, admin_hist_ip) values(?,?)");
			pstmt.setInt(1, vo.getAdmin_sq());
			pstmt.setString(2, ipAddr);
			
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public AdminVo adminInfoAll(AdminVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		admin_sq, admin_supper, admin_del_fl, admin_dttm, admin_id, admin_pwd, admin_name, admin_email, admin_phone, admin_memo
//		admin_dttm, admin_name, admin_email, admin_phone, admin_memo
		
		try {
			pstmt = con.prepareStatement("select * from inf_admin_tb where admin_sq=? order by dttm desc");
			pstmt.setInt(1, vo.getAdmin_sq());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo.setAdminId(rs.getString("admin_id"));
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setAdminPwd(rs.getString("admin_pwd"));
				vo.setAdmin_supper(rs.getBoolean("admin_supper"));
				vo.setAdmin_del_fl(rs.getBoolean("admin_del_fl"));
				vo.setDttm(rs.getString("admin_dttm"));
				vo.setAdminName(rs.getString("admin_name"));
				vo.setAdminEmail(rs.getString("admin_email"));
				vo.setAdminPhone(rs.getString("admin_phone"));
				vo.setAdminMemo(rs.getString("admin_memo"));
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
	
	public int adminModify(AdminVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			if (vo.getAdminPwd() == null || vo.getAdminPwd().equals("")) {
				pstmt = con.prepareStatement("update inf_admin_tb set admin_email =?, admin_memo=?,admin_phone=? where admin_id=?");
				pstmt.setString(1, vo.getAdminEmail());
				pstmt.setString(2, vo.getAdminMemo());
				pstmt.setString(3, vo.getAdminPhone());
				pstmt.setString(4, vo.getAdminId());
			} else {
				pstmt = con.prepareStatement("update inf_admin_tb set admin_email =?, admin_memo=?,admin_phone=?, admin_pwd=? where admin_id=?");
				pstmt.setString(1, vo.getAdminEmail());
				pstmt.setString(2, vo.getAdminMemo());
				pstmt.setString(3, vo.getAdminPhone());
				pstmt.setString(4, vo.getAdminPwd());
				pstmt.setString(5, vo.getAdminId());
			}
			
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public ItemVo getItemDetail(ItemVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select item_sq, a.admin_sq, (select admin_name from inf_admin_tb b where a.admin_sq = b.admin_sq) as admin_name, del_fl, show_fl, price, discount, stok, date_format(dttm,'%Y-%c-%d') as dttm, category, code, color, item_name, item_number, item_rating, size, explanation, preview, filepath"
					+ " from inf_goods_tb a where item_sq=?");
			pstmt.setInt(1, vo.getItem_sq());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setDel_fl(rs.getBoolean("del_fl"));
				vo.setShow_fl(rs.getBoolean("show_fl"));
				vo.setPrice(rs.getInt("price"));
				vo.setDiscount(rs.getInt("discount"));
				vo.setStok(rs.getInt("stok"));
				vo.setDttm(rs.getString("dttm"));
				vo.setCategory(rs.getNString("category"));
				vo.setCode(rs.getString("code"));
				vo.setColor(rs.getString("color"));
				vo.setItem_name(rs.getString("item_name"));
				vo.setItem_number(rs.getString("item_number"));
				vo.setItem_rating(rs.getString("item_rating"));
				vo.setSize(rs.getString("size"));
				vo.setExplanation(rs.getString(Parser.chgToHtml("explanation")));
				vo.setPreview(rs.getString("preview"));
				vo.setFilepath(rs.getString("filepath"));
				vo.setAdmin_name(rs.getString("admin_name"));
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
	
	public int itemModify(ItemVo vo, int admin_sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			if (vo.getExplanation() == null) {
				pstmt = con.prepareStatement("update inf_goods_tb set item_name=?, category=?, code=?, price=?, discount=?, stok=?, color=?, item_number=?, item_rating=?, size=?, admin_sq=?, dttm=now() where item_sq=?");
				pstmt.setString(1, vo.getItem_name());
				pstmt.setString(2, vo.getCategory());
				pstmt.setString(3, vo.getCode());
				pstmt.setInt(4, vo.getPrice());
				pstmt.setInt(5, vo.getDiscount());
				pstmt.setInt(6, vo.getStok());
				pstmt.setString(7, vo.getColor());
				pstmt.setString(8, vo.getItem_number());
				pstmt.setString(9, vo.getItem_rating());
				pstmt.setString(10, vo.getSize());
				pstmt.setInt(11, admin_sq);
				pstmt.setInt(12, vo.getItem_sq());
				
				count = pstmt.executeUpdate();
			} else {
				pstmt = con.prepareStatement("update inf_goods_tb set item_name=?, category=?, code=?, price=?, discount=?, stok=?, color=?, item_number=?, item_rating=?, size=?, explanatio=?, admin_sq=?, dttm=now() where item_sq=?");
				pstmt.setString(1, vo.getItem_name());
				pstmt.setString(2, vo.getCategory());
				pstmt.setString(3, vo.getCode());
				pstmt.setInt(4, vo.getPrice());
				pstmt.setInt(5, vo.getDiscount());
				pstmt.setInt(6, vo.getStok());
				pstmt.setString(7, vo.getColor());
				pstmt.setString(8, vo.getItem_number());
				pstmt.setString(9, vo.getItem_rating());
				pstmt.setString(10, vo.getSize());
				pstmt.setString(11, vo.getExplanation());
				pstmt.setInt(12, admin_sq);
				pstmt.setInt(13, vo.getItem_sq());
				
				count = pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int itemShow(ItemVo vo, int admin_sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			
			pstmt = con.prepareStatement("update inf_goods_tb set show_fl=?, admin_sq=?, dttm=now() where item_sq=?");
			pstmt.setBoolean(1, vo.isShow_fl());
			pstmt.setInt(2, admin_sq);
			pstmt.setInt(3, vo.getItem_sq());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int itemDel(ItemVo vo, int admin_sq) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			
			pstmt = con.prepareStatement("update inf_goods_tb set del_fl=?, admin_sq=?, dttm=now() where item_sq=?");
			pstmt.setBoolean(1, vo.isDel_fl());
			pstmt.setInt(2, admin_sq);
			pstmt.setInt(3, vo.getItem_sq());
			
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

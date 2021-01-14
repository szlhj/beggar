package shop.beggar.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.beggar.admin.vo.AdminVo;
import shop.beggar.admin.vo.FileVo;
import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.beggar.vo.OrderVo;
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
			pstmt = con.prepareStatement("select * from (inf_mber_tb a inner JOIN inf_mber_privcy_tb b on a.mber_sq=b.mber_sq) where 1=1"+query+" LIMIT ?,?");
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
			pstmt = con.prepareStatement("select * from(select a.*, b.id from inf_board_tb a, inf_mber_tb b where a.mber_sq = b.mber_sq UNION ALL select *, '' as id from inf_board_tb) as board where 1=1 "+query+" group by board_sq order by board_number, dttm desc LIMIT ?,?");
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
				vo.setComment(rs.getString("comment"));
				
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
	
	

	public int getArticleCount(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_mber_tb where 1=1 "+ query);
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
	
	public int getBoardArticleCount(String query) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_board_tb where 1=1 "+ query);
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
			pstmt = con.prepareStatement("insert into inf_goods_tb (item_name, category, code, price, discount, stok, color, item_number, item_rating, size, explanation, admin_sq, filepath) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
			pstmt.setString(13, vo.getFilepath());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int searchItemSq(ItemVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select item_sq from inf_goods_tb where code=? and item_name=? and item_number=? and item_rating=?");
			pstmt.setString(1, vo.getCode());
			pstmt.setString(2, vo.getItem_name());
			pstmt.setString(3, vo.getItem_number());
			pstmt.setString(4, vo.getItem_rating());
			
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
				vo.setAdmin_supper(rs.getBoolean("admin_supper"));
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
			pstmt = con.prepareStatement("select * from inf_admin_tb where admin_sq=? order by admin_dttm desc");
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
				vo.setComment(rs.getString("comment"));
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
				pstmt = con.prepareStatement("update inf_goods_tb set item_name=?, category=?, code=?, price=?, discount=?, stok=?, color=?, item_number=?, item_rating=?, size=?, admin_sq=?, dttm=now(), filepath=? where item_sq=?");
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
				pstmt.setString(12, vo.getFilepath());
				pstmt.setInt(13, vo.getItem_sq());
				
				count = pstmt.executeUpdate();
			} else {
				pstmt = con.prepareStatement("update inf_goods_tb set item_name=?, category=?, code=?, price=?, discount=?, stok=?, color=?, item_number=?, item_rating=?, size=?, explanation=?, admin_sq=?, dttm=now(), filepath=? where item_sq=?");
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
				pstmt.setString(13, vo.getFilepath());
				pstmt.setInt(14, vo.getItem_sq());
				
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
	
//------------------------------------------------------------------------------
//	 file upload관련 Dao 시작
	public int fileUpload(FileVo vo, String path) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			
			pstmt = con.prepareStatement("insert into inf_file_tb (fileName, fileRealName, item_sq) values (?, ?, ?)");
			pstmt.setString(1, vo.getFileName());
			pstmt.setString(2, vo.getFileRealName());
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
	
	
	//다운로드 수 증가
	public int fileHit(String fileRealName) {
		try {
			PreparedStatement pstmt = con.prepareStatement("update inf_file_tb set downloadCount = (select max(downloadCount) + 1 from file where fileRealName=?) where fileRealName=?");
			pstmt.setString(1, fileRealName);
			pstmt.setString(2, fileRealName);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//file table의 내용 가져오기
	public ArrayList<FileVo> getList() {
		ArrayList<FileVo> list = new ArrayList<FileVo>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from inf_file_tb");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FileVo file = new FileVo();
				file.setFileName(rs.getString("fileName"));
				file.setFileRealName(rs.getString("FileRealName"));
				list.add(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
//	 file upload관련 Dao 종료
//------------------------------------------------------------------------------
	
	public int fileItemSq(int item_sq, String newFileRealName) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			
			pstmt = con.prepareStatement("update inf_file_tb set item_sq=? where fileRealName=?");
			pstmt.setInt(1, item_sq);
			pstmt.setString(2, newFileRealName);
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

	public ArrayList<AdminVo> getListAdminVo() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AdminVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(" select admin_sq, admin_supper, admin_del_fl, date_format(admin_dttm,'%Y-%c-%d') as admin_dttm, admin_id, admin_pwd, admin_name, admin_email, admin_phone, admin_memo from inf_admin_tb");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AdminVo vo = new AdminVo();
				vo.setAdmin_del_fl(rs.getBoolean("admin_del_fl"));
				vo.setAdmin_sq(rs.getInt("admin_sq"));
				vo.setDttm(rs.getString("admin_dttm"));
				vo.setAdminEmail(rs.getString("admin_email"));
				vo.setAdminId(rs.getString("admin_id"));
				vo.setAdminMemo(rs.getString("admin_memo"));
				vo.setAdminName(rs.getString("admin_name"));
				vo.setAdminPhone(rs.getString("admin_phone"));
				vo.setAdminPwd(rs.getString("admin_pwd"));
				vo.setAdmin_supper(rs.getBoolean("admin_supper"));
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

	public int getAdminListCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_admin_tb");
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

	public AdminVo getAdminDetail(AdminVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select admin_sq, admin_supper, admin_del_fl, date_format(admin_dttm,'%Y-%c-%d') as admin_dttm, admin_id, admin_pwd, admin_name, admin_email, admin_phone, admin_memo from inf_admin_tb where admin_sq=?");
			pstmt.setInt(1, vo.getAdmin_sq());
//			admin_del_fl, date_format(admin_dttm,'%Y-%c-%d') as admin_dttm, admin_name, admin_email, admin_phone, admin_memo
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
	
	public int boardAnswer(BoardVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			
			pstmt = con.prepareStatement("update inf_board_tb set comment=? where board_sq=?");
			pstmt.setString(1, vo.getComment());
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

	public ArrayList<OrderVo> orderList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select a.order_sq, a.mber_sq, a.item_sq, a.item_stok, a.price, a.shipping, a.order_payment_plan, a.nonmber, a.record_item, a.addr_form, a.addr_to, ifnull(a.name_form,c.name) as name_form, a.name_to, ifnull(a.name_form_phone,c.phone) as name_form_phone, a.name_to_phone, date_format(a.order_dttm,'%Y-%c-%d') as order_dttm, b.item_name, b.filepath as item_img from inf_order_tb a inner join inf_goods_tb b on a.item_sq = b.item_sq left join inf_mber_privcy_tb c on a.mber_sq = c.mber_sq where shipping not in (6)");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setAddr_form(rs.getString("addr_form"));
				vo.setAddr_to(rs.getString("addr_to"));
				vo.setItem_img(rs.getString("item_img"));
				vo.setItem_name(rs.getString("item_name"));
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setItem_stok(rs.getInt("item_stok"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setName_form(rs.getString("name_form"));
				vo.setName_form_phone(rs.getString("name_form_phone"));
				vo.setName_to(rs.getString("name_to"));
				vo.setName_to_phone(rs.getString("name_to_phone"));
				vo.setNonmber(rs.getString("nonmber"));
				vo.setOrder_dttm(rs.getString("order_dttm"));
				vo.setOrder_payment_plan(rs.getInt("order_payment_plan"));
				vo.setOrder_sq(rs.getInt("order_sq"));
				vo.setPrice(rs.getInt("price"));
				vo.setRecord_item(rs.getString("record_item"));
				vo.setShipping(rs.getInt("shipping"));
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

	public ArrayList<OrderVo> itemDeleteList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select a.order_sq, a.mber_sq, a.item_sq, a.item_stok, a.price, a.shipping, a.order_payment_plan, a.nonmber, a.record_item, a.addr_form, a.addr_to, ifnull(a.name_form,c.name) as name_form, a.name_to, ifnull(a.name_form_phone,c.phone) as name_form_phone, a.name_to_phone, date_format(a.order_dttm,'%Y-%c-%d') as order_dttm, b.item_name, b.filepath as item_img from inf_order_tb a inner join inf_goods_tb b on a.item_sq = b.item_sq left join inf_mber_privcy_tb c on a.mber_sq = c.mber_sq where a.shipping=6");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setAddr_form(rs.getString("addr_form"));
				vo.setAddr_to(rs.getString("addr_to"));
				vo.setItem_img(rs.getString("item_img"));
				vo.setItem_name(rs.getString("item_name"));
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setItem_stok(rs.getInt("item_stok"));
				vo.setMber_sq(rs.getInt("mber_sq"));
				vo.setName_form(rs.getString("name_form"));
				vo.setName_form_phone(rs.getString("name_form_phone"));
				vo.setName_to(rs.getString("name_to"));
				vo.setName_to_phone(rs.getString("name_to_phone"));
				vo.setNonmber(rs.getString("nonmber"));
				vo.setOrder_dttm(rs.getString("order_dttm"));
				vo.setOrder_payment_plan(rs.getInt("order_payment_plan"));
				vo.setOrder_sq(rs.getInt("order_sq"));
				vo.setPrice(rs.getInt("price"));
				vo.setRecord_item(rs.getString("record_item"));
				vo.setShipping(rs.getInt("shipping"));
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
	
	public int orderShipping(OrderVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			
			pstmt = con.prepareStatement("update inf_order_tb set shipping=? where order_sq=?");
			pstmt.setInt(1, vo.getShipping());
			pstmt.setInt(2, vo.getOrder_sq());
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int orderShippingDelete(OrderVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			
			pstmt = con.prepareStatement("update inf_order_tb set shipping=7 where order_sq=?");
			pstmt.setInt(1, vo.getOrder_sq());
			
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

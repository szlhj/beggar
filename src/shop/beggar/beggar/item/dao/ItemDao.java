package shop.beggar.beggar.item.dao;

import static shop.beggar.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Pagenation;

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
	
	public ArrayList<ItemVo> getItemPageInfo(Pagenation pagenation , String category) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemVo> list = new ArrayList<>();
		try {
			if(category.equals("0")) {
				pstmt = con.prepareStatement("select filepath,preview,item_name,price,discount,item_sq,category from inf_goods_tb where del_fl=0 and show_fl=1 LIMIT ?,?");
		//			pstmt.setInt(1, pagenation.getStartArticleNumber());
				pstmt.setInt(1, pagenation.getStartArticleNumber());
				pstmt.setInt(2, pagenation.getARTICLE_COUNT_PER_PAGE());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ItemVo vo = new ItemVo();
					vo.setFilepath(rs.getString("filepath"));
					vo.setPreview(rs.getString("preview"));
					vo.setItem_name(rs.getString("item_name"));
					vo.setPrice(rs.getInt("price"));
					vo.setDiscount(rs.getInt("discount"));
					vo.setItem_sq(rs.getInt("item_sq"));
					vo.setCategory(rs.getString("category"));
//					vo.setDisprice(rs.getInt("price") - (rs.getInt("price") * rs.getInt("discount")  / 100)  );
					list.add(vo);
					}
			} else {
				pstmt = con.prepareStatement("select filepath,preview,item_name,price,discount,category,item_sq from inf_goods_tb a where del_fl=0 and show_fl=1 and category='" + category + "' LIMIT ?,?");
				pstmt.setInt(1, pagenation.getStartArticleNumber());
				pstmt.setInt(2, pagenation.getARTICLE_COUNT_PER_PAGE());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ItemVo vo = new ItemVo();
					vo.setFilepath(rs.getString("filepath"));
					vo.setPreview(rs.getString("preview"));
					vo.setItem_name(rs.getString("item_name"));
					vo.setPrice(rs.getInt("price"));
					vo.setDiscount(rs.getInt("discount"));
					vo.setItem_sq(rs.getInt("item_sq"));
//					vo.setDisprice(rs.getInt("price") - (rs.getInt("price") * rs.getInt("discount")  / 100)  );
					vo.setCategory(rs.getString("category"));
					list.add(vo);
					}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public int getArticleCount(String category) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			if(category.equals("0")) {
			pstmt = con.prepareStatement("select count(*) from inf_goods_tb where del_fl=0 and show_fl=1");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			} else {
				pstmt = con.prepareStatement("select count(*) from inf_goods_tb where del_fl=0 and show_fl=1 and category="+ category);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int dummyRegisterItemInfo(ItemVo vo) {  //글쓰기로 db에 게시글 데이터를 입력함
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement    //bbs.nextval 은 bbs라는 시퀀스를 따로 만들어 둔 상태에서 ex)게시판 번호 같이 순서대로 올라가는 번호를 입력할때 따로 만들어두고 사용 (순서대로 1씩오름)
					("insert into inf_goods_tb(price,discount,stok,del_fl,show_fl,category,code,color,item_name,item_number,item_rating,size,explanation,preview,filepath) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, vo.getPrice());		//mber_sq에 id가 들어감
			pstmt.setInt(2, vo.getDiscount());
			pstmt.setInt(3, vo.getStok());
			pstmt.setBoolean(4, vo.isDel_fl());
			pstmt.setBoolean(5, vo.isShow_fl());
			pstmt.setString(6, vo.getCategory());
			pstmt.setString(7, vo.getCode());
			pstmt.setString(8, vo.getColor());
			pstmt.setString(9, vo.getItem_name());
			pstmt.setString(10, vo.getItem_number());
			pstmt.setString(11, vo.getItem_rating());
			pstmt.setString(12, vo.getSize());
			pstmt.setString(13, vo.getExplanation());
			pstmt.setString(14, vo.getPreview());
			pstmt.setString(15, vo.getFilepath());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public ItemVo getItemDetailInfo(ItemVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement("select item_sq , price, discount , stok , dttm , category , code , color, item_name, item_number, item_rating, size , explanation , preview, filepath from inf_goods_tb where del_fl=0 and show_fl=1 and item_sq=?");
			pstmt.setInt(1, vo.getItem_sq());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo.setItem_sq(rs.getInt("item_sq"));
				vo.setPrice(rs.getInt("price"));
				vo.setDiscount(rs.getInt("discount"));
				vo.setStok(rs.getInt("stok"));
				vo.setCategory(rs.getString("category"));
				vo.setCode(rs.getString("code"));
				vo.setColor(rs.getString("color"));
				vo.setItem_name(rs.getString("item_name"));
				vo.setItem_number(rs.getString("item_number"));
				vo.setItem_rating(rs.getString("item_rating"));
				vo.setSize(rs.getString("size"));
				vo.setExplanation(rs.getString("explanation"));
				vo.setPreview(rs.getString("preview"));
				vo.setFilepath(rs.getString("filepath"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return vo;
	}
	
	public int registerCart(ItemVo vo) {  //장바구니 정보를 db 테이블에 저장
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement    
					("insert into inf_cart_tb(item_sq,mber_sq) values(?,?)");
			pstmt.setInt(1, vo.getItem_sq());		//mber_sq에 id가 들어감
			pstmt.setInt(2, vo.getMber_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
}

package shop.beggar.beggar.item.dao;

import static shop.beggar.common.JdbcUtil.close;
import static shop.beggar.common.JdbcUtil.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.beggar.vo.OrderVo;
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
	
	public int checkCartItem(ItemVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_cart_tb where mber_sq=? and item_sq=?");
			pstmt.setInt(1, vo.getMber_sq());
			pstmt.setInt(2, vo.getItem_sq());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public ItemVo cartItemInfo(int item_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVo vo = null;
		try {
			pstmt = con.prepareStatement("select item_sq , price, discount , stok , dttm , category , code , color, item_name, item_number, item_rating, size , explanation , preview, filepath from inf_goods_tb where del_fl=0 and show_fl=1 and item_sq=?");
			pstmt.setInt(1,item_sq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new ItemVo();
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
	
	public int getArticleItem(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_cart_tb where mber_sq=?");
			pstmt.setInt(1,mber_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public ArrayList<ItemVo> getCartPageInfo(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ItemVo> list = new ArrayList<>();
		try {
				pstmt = con.prepareStatement("select b.filepath,b.item_name,b.price,b.discount,b.stok,a.item_stok,a.item_sq from inf_cart_tb a INNER JOIN inf_goods_tb b where a.item_sq = b.item_sq and a.mber_sq=?");
				pstmt.setInt(1, mber_sq);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ItemVo vo = new ItemVo();
					vo.setFilepath(rs.getString("filepath"));
					vo.setItem_name(rs.getString("item_name"));
					vo.setPrice(rs.getInt("price"));
					vo.setDiscount(rs.getInt("discount"));
					vo.setStok(rs.getInt("stok"));
					vo.setItem_stok(rs.getInt("item_stok"));
					vo.setItem_sq(rs.getInt("item_sq"));
					list.add(vo);
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
	
	public ItemVo getPurchasePage(int mber_sq,int item_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVo vo = null;
		try {
			pstmt = con.prepareStatement("select b.filepath,b.item_name,b.price,b.discount,b.stok,a.item_stok,a.item_sq from inf_cart_tb a INNER JOIN inf_goods_tb b where a.item_sq = b.item_sq and a.mber_sq=? and b.item_sq=?");
			pstmt.setInt(1, mber_sq);
			pstmt.setInt(2, item_sq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new ItemVo();
				vo.setFilepath(rs.getString("filepath"));
				vo.setItem_name(rs.getString("item_name"));
				vo.setPrice(rs.getInt("price"));
				vo.setDiscount(rs.getInt("discount"));
				vo.setStok(rs.getInt("stok"));
				vo.setItem_stok(rs.getInt("item_stok"));
				vo.setItem_sq(rs.getInt("item_sq"));
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
	
	public int changeCartStok(ItemVo ivo) {  //장바구니 정보를 db 테이블에 저장
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement    
					("update inf_cart_tb set item_stok=? where item_sq=? and mber_sq =?");
			pstmt.setInt(1, ivo.getItem_stok());
			pstmt.setInt(2, ivo.getItem_sq());	
			pstmt.setInt(3, ivo.getMber_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public int countOrderInfo(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("select count(*) from inf_order_tb where mber_sq=?");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public OrderVo purchaseAddrInfo(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVo ovo = null;
		try {
				pstmt = con.prepareStatement("select addr_form,addr_to,name_form,name_to,name_form_phone,name_to_phone,record_item from inf_order_tb where mber_sq=? order by order_dttm desc limit 1");
				pstmt.setInt(1, mber_sq);
				rs = pstmt.executeQuery();
			while(rs.next()) {
				ovo = new OrderVo();
				ovo.setAddr_form(rs.getString("addr_form"));
				ovo.setAddr_to(rs.getString("addr_to"));
				ovo.setName_form(rs.getString("name_form"));
				ovo.setName_to(rs.getString("name_to"));
				ovo.setName_form_phone(rs.getString("name_form_phone"));
				ovo.setName_to_phone(rs.getString("name_to_phone"));
				ovo.setRecord_item(rs.getString("record_item"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		
		return ovo;
	}
	
	public int registerOrderInfo(OrderVo vo) {  //주문 정보를 db 테이블에 저장
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement    
					("insert into inf_order_tb(mber_sq,item_sq,item_stok,price,order_payment_plan,nonmber,record_item,addr_form,addr_to,name_form,name_to,name_form_phone,name_to_phone) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, vo.getMber_sq());
			pstmt.setInt(2, vo.getItem_sq());
			pstmt.setInt(3, vo.getItem_stok());
			pstmt.setInt(4, vo.getPrice());
			pstmt.setInt(5, vo.getOrder_payment_plan());
			pstmt.setString(6, vo.getNonmber());
			pstmt.setString(7, vo.getRecord_item());
			pstmt.setString(8, vo.getAddr_form());
			pstmt.setString(9, vo.getAddr_to());
			pstmt.setString(10, vo.getName_form());
			pstmt.setString(11, vo.getName_to());
			pstmt.setString(12, vo.getName_form_phone());
			pstmt.setString(13, vo.getName_to_phone());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
//---------------------------------------------------------------------------
//		inf_orser_tb 관련 시작	
	
	public OrderVo orderMberInfo(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVo vo = new OrderVo();
		
		try {
			pstmt = con.prepareStatement("select ifnull(a.name,b.name_form) as mber_name, b.addr_form, b.addr_to, ifnull(a.phone, b.name_form_phone) as mber_phone, b.record_item as record, b.name_to, b.name_to_phone from inf_order_tb b left join inf_mber_privcy_tb a on a.mber_sq = b.mber_sq where a.mber_sq=? order by b.order_dttm desc limit 1");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo.setName_form(rs.getString("mber_name"));
				vo.setAddr_form(rs.getString("addr_form"));
				vo.setName_form_phone(rs.getString("mber_phone"));
				vo.setRecord_item(rs.getString("record"));
				vo.setName_to(rs.getString("name_to"));
				vo.setAddr_to(rs.getString("addr_to"));
				vo.setName_to_phone(rs.getString("name_to_phone"));
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
	
	public ArrayList<OrderVo> orderItemList(int mber_sq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select c.item_name as item_name, c.filepath as item_img, c.price, a.item_stok as stok from inf_order_tb a, inf_mber_tb b, inf_goods_tb c where a.mber_sq = b.mber_sq and a.item_sq = c.item_sq and a.mber_sq=? and a.shipping=0");
			pstmt.setInt(1, mber_sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setItem_name(rs.getString("item_name"));
				vo.setItem_img(rs.getString("item_img"));
				vo.setItem_stok(rs.getInt("stok"));
				vo.setPrice(rs.getInt("price"));
				list.add(vo);
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
	
	public int orderPayment(OrderVo vo) {  //회원 결재
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_order_tb set shipping=1, order_payment_plan=?, record_item=?, addr_form=?, name_form=?, name_form_phone=?, addr_to=?, name_to=?, name_to_phone=? where mber_sq=? and shipping=1");
			pstmt.setInt(1, vo.getOrder_payment_plan());
			pstmt.setString(2, vo.getRecord_item());
			pstmt.setString(3, vo.getAddr_form());
			pstmt.setString(4, vo.getName_form());
			pstmt.setString(5, vo.getName_form_phone());
			pstmt.setString(6, vo.getAddr_to());
			pstmt.setString(7, vo.getName_to());
			pstmt.setString(8, vo.getName_to_phone());
			pstmt.setInt(9, vo.getMber_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public ArrayList<OrderVo> orderItemListnonmber(String nonmber) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderVo> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement("select c.item_name as item_name, c.filepath as item_img, c.price, a.item_stok as stok, date_format(a.order_dttm,'%Y-%c-%d') as order_dttm from inf_order_tb a left join inf_mber_tb b on a.mber_sq=b.mber_sq inner join inf_goods_tb c on a.item_sq = c.item_sq where a.shipping=1 and nonmber=?");
			pstmt.setString(1, nonmber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderVo vo = new OrderVo();
				vo.setItem_name(rs.getString("item_name"));
				vo.setItem_img(rs.getString("item_img"));
				vo.setItem_stok(rs.getInt("stok"));
				vo.setPrice(rs.getInt("price"));
				list.add(vo);
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
	
	public int orderPaymentNonmber(OrderVo vo) {  //비회원 결재
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("update inf_order_tb set shipping=1, order_payment_plan=?, record_item=?, addr_form=?, name_form=?, name_form_phone=?, addr_to=?, name_to=?, name_to_phone=? where nonmber=? and shipping=1");
			pstmt.setInt(1, vo.getOrder_payment_plan());
			pstmt.setString(2, vo.getRecord_item());
			pstmt.setString(3, vo.getAddr_form());
			pstmt.setString(4, vo.getName_form());
			pstmt.setString(5, vo.getName_form_phone());
			pstmt.setString(6, vo.getAddr_to());
			pstmt.setString(7, vo.getName_to());
			pstmt.setString(8, vo.getName_to_phone());
			pstmt.setString(9, vo.getNonmber());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public ArrayList<OrderVo> orderPaymentList(OrderVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<OrderVo> list = new ArrayList<>();
		try {
			if (vo.getMber_sq() == 0) {
				pstmt = con.prepareStatement("select c.item_name as item_name, c.filepath as item_img, c.price, a.item_stok as stok, date_format(a.order_dttm,'%Y-%c-%d') as order_dttm, a.shipping from inf_order_tb a left join inf_mber_tb b on a.mber_sq=b.mber_sq inner join inf_goods_tb c on a.item_sq = c.item_sq where a.nonmber=?");
				pstmt.setString(1, vo.getNonmber());
			} else {
				pstmt = con.prepareStatement("select c.item_name as item_name, c.filepath as item_img, c.price, a.item_stok as stok, date_format(a.order_dttm,'%Y-%c-%d') as order_dttm, a.shipping from inf_order_tb a left join inf_mber_tb b on a.mber_sq=b.mber_sq inner join inf_goods_tb c on a.item_sq = c.item_sq where a.mber_sq=?");
				pstmt.setInt(1, vo.getMber_sq());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new OrderVo();
				vo.setItem_name(rs.getString("item_name"));
				vo.setItem_img(rs.getString("item_img"));
				vo.setItem_stok(rs.getInt("stok"));
				vo.setPrice(rs.getInt("price"));
				vo.setOrder_dttm(rs.getString("order_dttm"));
				vo.setShipping(rs.getInt("shipping"));
				list.add(vo);
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
	
	public OrderVo orderPaymentListAddr(OrderVo vo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			if (vo.getMber_sq() == 0) {
				pstmt = con.prepareStatement("select ifnull(a.name,b.name_form) as mber_name, b.addr_form, b.addr_to, ifnull(a.phone,b.name_form_phone) as mber_phone, b.record_item as record, b.name_to, b.name_to_phone, date_format(b.order_dttm,'%Y-%c-%d') as order_dttm from inf_order_tb b left join inf_mber_privcy_tb a on a.mber_sq = b.mber_sq where b.nonmber=? order by b.order_dttm desc limit 1");
				pstmt.setString(1, vo.getNonmber());
			} else {
				pstmt = con.prepareStatement("select ifnull(a.name,b.name_form) as mber_name, b.addr_form, b.addr_to, ifnull(a.phone,b.name_form_phone) as mber_phone, b.record_item as record, b.name_to, b.name_to_phone, date_format(b.order_dttm,'%Y-%c-%d') as order_dttm from inf_order_tb b left join inf_mber_privcy_tb a on a.mber_sq = b.mber_sq where b.mber_sq=? order by b.order_dttm desc limit 1");
				pstmt.setInt(1, vo.getMber_sq());
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo.setName_form(rs.getString("mber_name"));
				vo.setAddr_form(rs.getString("addr_form"));
				vo.setName_form_phone(rs.getString("mber_phone"));
				vo.setRecord_item(rs.getString("record"));
				vo.setName_to(rs.getString("name_to"));
				vo.setAddr_to(rs.getString("addr_to"));
				vo.setName_to_phone(rs.getString("name_to_phone"));
				vo.setOrder_dttm(rs.getString("order_dttm"));
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
	
	public int orderDelete(OrderVo vo) {  //회원 결재
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			if (vo.getMber_sq() == 0) {
				pstmt = con.prepareStatement("update inf_order_tb set shipping=6 where nonmber=? and shipping=1");
				pstmt.setString(1, vo.getNonmber());
			} else {
				pstmt = con.prepareStatement("update inf_order_tb set shipping=6 where mber_sq=? and shipping=1");
				pstmt.setInt(1, vo.getMber_sq());
			}
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}

//		inf_orser_tb 관련 종료
//---------------------------------------------------------------------------
	
		
}

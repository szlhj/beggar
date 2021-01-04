package shop.beggar.beggar.vo;

/**
 * @PackageName		: shop.beggar.beggar.vo
 * @FileName		: CartVo.java
 * @Since			: 2020. 12. 31.
 * @Author			: HJLee
 * @Description		: cartVo
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 31.		HJLee				최초 작성
 *
 */
public class CartVo {
	private int cart_sq;
	private int mber_sq;
	private int item_sq;
	private int item_stok;
	private String cart_dttm;
	
	/**
	 * @return the cart_sq
	 */
	public int getCart_sq() {
		return cart_sq;
	}
	/**
	 * @param cart_sq the cart_sq to set
	 */
	public void setCart_sq(int cart_sq) {
		this.cart_sq = cart_sq;
	}
	/**
	 * @return the mber_sq
	 */
	public int getMber_sq() {
		return mber_sq;
	}
	/**
	 * @param mber_sq the mber_sq to set
	 */
	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}
	/**
	 * @return the item_sq
	 */
	public int getItem_sq() {
		return item_sq;
	}
	/**
	 * @param item_sq the item_sq to set
	 */
	public void setItem_sq(int item_sq) {
		this.item_sq = item_sq;
	}
	/**
	 * @return the item_stok
	 */
	public int getItem_stok() {
		return item_stok;
	}
	/**
	 * @param item_stok the item_stok to set
	 */
	public void setItem_stok(int item_stok) {
		this.item_stok = item_stok;
	}
	/**
	 * @return the cart_dttm
	 */
	public String getCart_dttm() {
		return cart_dttm;
	}
	/**
	 * @param cart_dttm the cart_dttm to set
	 */
	public void setCart_dttm(String cart_dttm) {
		this.cart_dttm = cart_dttm;
	}
	
	
}

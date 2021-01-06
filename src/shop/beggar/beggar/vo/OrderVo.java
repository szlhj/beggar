package shop.beggar.beggar.vo;

/**
 * @PackageName		: shop.beggar.beggar.vo
 * @FileName		: OrderVo.java
 * @Since			: 2020. 12. 31.
 * @Author			: HJLee
 * @Description		: orderVo
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 31.		HJLee				최초 작성
 *
 */
public class OrderVo {
	private int order_sq;
	private int mber_sq;
	private int item_sq;
	private int item_stok;
	private int price;
	private int shipping;
	private String record_item;
	private String addr_form;
	private String addr_to;
	private String order_dttm;
	private String name_form;
	private String name_form_phone;
	private String item_name;
	private String item_img;
	private String name_to;
	private String name_to_phone;
	private int order_payment_plan;
	private String nonmber;
	
	/**
	 * @return the nonmber
	 */
	public String getNonmber() {
		return nonmber;
	}
	/**
	 * @param nonmber the nonmber to set
	 */
	public void setNonmber(String nonmber) {
		this.nonmber = nonmber;
	}
	/**
	 * @return the order_payment_plan
	 */
	public int getOrder_payment_plan() {
		return order_payment_plan;
	}
	/**
	 * @param order_payment_plan the order_payment_plan to set
	 */
	public void setOrder_payment_plan(int order_payment_plan) {
		this.order_payment_plan = order_payment_plan;
	}
	/**
	 * @return the name_to_phone
	 */
	public String getName_to_phone() {
		return name_to_phone;
	}
	/**
	 * @param name_to_phone the name_to_phone to set
	 */
	public void setName_to_phone(String name_to_phone) {
		this.name_to_phone = name_to_phone;
	}
	/**
	 * @return the order_sq
	 */
	public int getOrder_sq() {
		return order_sq;
	}
	/**
	 * @param order_sq the order_sq to set
	 */
	public void setOrder_sq(int order_sq) {
		this.order_sq = order_sq;
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
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the shipping
	 */
	public int getShipping() {
		return shipping;
	}
	/**
	 * @param shipping the shipping to set
	 */
	public void setShipping(int shipping) {
		this.shipping = shipping;
	}
	/**
	 * @return the record_item
	 */
	public String getRecord_item() {
		return record_item;
	}
	/**
	 * @param record_item the record_item to set
	 */
	public void setRecord_item(String record_item) {
		this.record_item = record_item;
	}
	/**
	 * @return the addr_form
	 */
	public String getAddr_form() {
		return addr_form;
	}
	/**
	 * @param addr_form the addr_form to set
	 */
	public void setAddr_form(String addr_form) {
		this.addr_form = addr_form;
	}
	/**
	 * @return the addr_to
	 */
	public String getAddr_to() {
		return addr_to;
	}
	/**
	 * @param addr_to the addr_to to set
	 */
	public void setAddr_to(String addr_to) {
		this.addr_to = addr_to;
	}
	/**
	 * @return the order_dttm
	 */
	public String getOrder_dttm() {
		return order_dttm;
	}
	/**
	 * @param order_dttm the order_dttm to set
	 */
	public void setOrder_dttm(String order_dttm) {
		this.order_dttm = order_dttm;
	}
	/**
	 * @return the name_form
	 */
	public String getName_form() {
		return name_form;
	}
	/**
	 * @param name_form the name_form to set
	 */
	public void setName_form(String name_form) {
		this.name_form = name_form;
	}
	/**
	 * @return the name_form_phone
	 */
	public String getName_form_phone() {
		return name_form_phone;
	}
	/**
	 * @param name_form_phone the name_form_phone to set
	 */
	public void setName_form_phone(String name_form_phone) {
		this.name_form_phone = name_form_phone;
	}
	/**
	 * @return the item_name
	 */
	public String getItem_name() {
		return item_name;
	}
	/**
	 * @param item_name the item_name to set
	 */
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	/**
	 * @return the item_img
	 */
	public String getItem_img() {
		return item_img;
	}
	/**
	 * @param item_img the item_img to set
	 */
	public void setItem_img(String item_img) {
		this.item_img = item_img;
	}
	/**
	 * @return the name_to
	 */
	public String getName_to() {
		return name_to;
	}
	/**
	 * @param name_to the name_to to set
	 */
	public void setName_to(String name_to) {
		this.name_to = name_to;
	}
	
}

package shop.beggar.beggar.vo;

public class ItemVo {
		
	
	private int item_sq;
	private int price;
	private int discount;
	private int stok; //goods 테이블에 상품 총 재고
	private boolean del_fl;
	private boolean show_fl;
	private String dttm;
	private String category;
	private String code;
	private String color;
	private String item_name;
	private String item_number;
	private String item_rating;
	private String size;
	private String explanation;
	private String preview;
	private String filepath;
	private int admin_sq;
	private String admin_name;
	private int mber_sq;
	private int item_stok; //cart테이블에 장바구니에 상품 수량

	
	
	public int getItem_stok() {
		return item_stok;
	}
	public void setItem_stok(int item_stok) {
		this.item_stok = item_stok;
	}
	public int getMber_sq() {
		return mber_sq;
	}
	public void setMber_sq(int mber_sq) {
		this.mber_sq = mber_sq;
	}
	/**
	 * @return the admin_sq
	 */
	public int getAdmin_sq() {
		return admin_sq;
	}
	/**
	 * @param admin_sq the admin_sq to set
	 */
	public void setAdmin_sq(int admin_sq) {
		this.admin_sq = admin_sq;
	}
	/**
	 * @return the admin_name
	 */
	public String getAdmin_name() {
		return admin_name;
	}
	/**
	 * @param admin_name the admin_name to set
	 */
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	/**
	 * @return the item_sq
	 */
	public int getItem_sq() {
		return item_sq;
	}
	/**
	 * @return the show_fl
	 */
	public boolean isShow_fl() {
		return show_fl;
	}
	/**
	 * @param show_fl the show_fl to set
	 */
	public void setShow_fl(boolean show_fl) {
		this.show_fl = show_fl;
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
	 * @return the item_number
	 */
	public String getItem_number() {
		return item_number;
	}
	/**
	 * @param item_number the item_number to set
	 */
	public void setItem_number(String item_number) {
		this.item_number = item_number;
	}
	/**
	 * @return the item_rating
	 */
	public String getItem_rating() {
		return item_rating;
	}
	/**
	 * @param item_rating the item_rating to set
	 */
	public void setItem_rating(String item_rating) {
		this.item_rating = item_rating;
	}
	/**
	 * @return the preview
	 */
	public String getPreview() {
		return preview;
	}
	/**
	 * @param preview the preview to set
	 */
	public void setPreview(String preview) {
		this.preview = preview;
	}
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @param item_sq the item_sq to set
	 */
	public void setItem_sq(int item_sq) {
		this.item_sq = item_sq;
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
	 * @return the discount
	 */
	public int getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	/**
	 * @return the stok
	 */
	public int getStok() {
		return stok;
	}
	/**
	 * @param stok the stok to set
	 */
	public void setStok(int stok) {
		this.stok = stok;
	}
	/**
	 * @return the del_fl
	 */
	public boolean isDel_fl() {
		return del_fl;
	}
	/**
	 * @param del_fl the del_fl to set
	 */
	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}
	/**
	 * @return the dttm
	 */
	public String getDttm() {
		return dttm;
	}
	/**
	 * @param dttm the dttm to set
	 */
	public void setDttm(String dttm) {
		this.dttm = dttm;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the explain
	 */
	public String getExplanation() {
		return explanation;
	}
	/**
	 * @param explain the explain to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
}

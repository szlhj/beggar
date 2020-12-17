package shop.beggar.beggar.vo;

public class ItemVo {
	private int item_sq;// 상품일련번호
	private int price;// 상품가격
	private int discount;// 상품할인율
	private int stok;// 재고
	private boolean del_fl;// 상품삭제여부
	private boolean dttm;// 상품생성일자
	private String catagore;// 상품카테고리번호
	private String code;// 상품코드
	private String color;// 상품컬러
	private String item_name;// 상품명
	private String item_number;// 상품 넘버링
	private String item_rating;// 상품 등급
	private String size;// 상품사이즈
	private String imgaddress;// 이미지 주소
	private String explanation;// 설명
	
	public int getItem_sq() {
		return item_sq;
	}
	public void setItem_sq(int item_sq) {
		this.item_sq = item_sq;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getStok() {
		return stok;
	}
	public void setStok(int stok) {
		this.stok = stok;
	}
	public boolean isDel_fl() {
		return del_fl;
	}
	public void setDel_fl(boolean del_fl) {
		this.del_fl = del_fl;
	}
	public boolean isDttm() {
		return dttm;
	}
	public void setDttm(boolean dttm) {
		this.dttm = dttm;
	}
	public String getCatagore() {
		return catagore;
	}
	public void setCatagore(String catagore) {
		this.catagore = catagore;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_number() {
		return item_number;
	}
	public void setItem_number(String item_number) {
		this.item_number = item_number;
	}
	public String getItem_rating() {
		return item_rating;
	}
	public void setItem_rating(String item_rating) {
		this.item_rating = item_rating;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getImgaddress() {
		return imgaddress;
	}
	public void setImgaddress(String imgaddress) {
		this.imgaddress = imgaddress;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	
	
}

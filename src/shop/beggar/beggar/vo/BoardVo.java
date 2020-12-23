package shop.beggar.beggar.vo;

public class BoardVo {
		
	
	private int board_sq;// 게시판 넘버
	private int person_sq;// 사람 번호
	private String board_number;// 게시판 구분
	private int count; // 게시판 조회수
	private int del_fl; // 삭제 유무
	private String dttm; // 게시판 작성 시간
	private String goods_info; // 상품번호
	private String title; //제목
	private String content; // 내용
	private int admin_sq;// 관리자 번호
	
	public int getAdmin_sq() {
		return admin_sq;
	}
	public void setAdmin_sq(int admin_sq) {
		this.admin_sq = admin_sq;
	}
	public int getBoard_sq() {
		return board_sq;
	}
	public void setBoard_sq(int board_sq) {
		this.board_sq = board_sq;
	}
	public int getPerson_sq() {
		return person_sq;
	}
	public void setPerson_sq(int person_sq) {
		this.person_sq = person_sq;
	}
	public String getBoard_number() {
		return board_number;
	}
	public void setBoard_number(String board_number) {
		this.board_number = board_number;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getDel_fl() {
		return del_fl;
	}
	public void setDel_fl(int del_fl) {
		this.del_fl = del_fl;
	}
	public String getDttm() {
		return dttm;
	}
	public void setDttm(String dttm) {
		this.dttm = dttm;
	}
	public String getGoods_info() {
		return goods_info;
	}
	public void setGoods_info(String goods_info) {
		this.goods_info = goods_info;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}

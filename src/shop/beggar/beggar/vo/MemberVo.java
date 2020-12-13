package shop.beggar.beggar.vo;

/**
 * @PackageName		: shop.beggar.beggar.vo
 * @FileName		: MemberVo.java
 * @Since			: 2020. 12. 6.
 * @Author			: HJLee
 * @Description		: member 테이블의 내용을 class형태로 변환
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 6.		HJLee				최초 작성
 *
 */
public class MemberVo {
	private int mber_sq;
	private String id;
	private String pwd;
	private boolean del_fl;
	private String name;
	private String phone;
	private String email;
	private String dttm;
	private boolean email_fl;
	private boolean sms_fl;
	
	/**
	 * @return the email_fl
	 */
	public boolean isEmail_fl() {
		return email_fl;
	}
	/**
	 * @param email_fl the email_fl to set
	 */
	public void setEmail_fl(boolean email_fl) {
		this.email_fl = email_fl;
	}
	/**
	 * @return the sms_fl
	 */
	public boolean isSms_fl() {
		return sms_fl;
	}
	/**
	 * @param sms_fl the sms_fl to set
	 */
	public void setSms_fl(boolean sms_fl) {
		this.sms_fl = sms_fl;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
}

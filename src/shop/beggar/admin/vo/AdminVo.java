package shop.beggar.admin.vo;

/**
 * @PackageName		: shop.beggar.admin.vo
 * @FileName		: AdminVo.java
 * @Since			: 2020. 12. 18.
 * @Author			: HJLee
 * @Description		: 
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 18.		HJLee				최초 작성
 *
 */
public class AdminVo {
	private int admin_sq;
	private boolean admin_supper;
	private boolean admin_del_fl;
	private String dttm;
	private String adminId;
	private String adminPwd;
	private String adminName;
	/**
	 * @return the adminName
	 */
	public String getAdminName() {
		return adminName;
	}
	/**
	 * @param adminName the adminName to set
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	private String adminEmail;
	private String adminPhone;
	private String adminMemo;
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
	 * @return the admin_supper
	 */
	public boolean isAdmin_supper() {
		return admin_supper;
	}
	/**
	 * @param admin_supper the admin_supper to set
	 */
	public void setAdmin_supper(boolean admin_supper) {
		this.admin_supper = admin_supper;
	}
	/**
	 * @return the admin_del_fl
	 */
	public boolean isAdmin_del_fl() {
		return admin_del_fl;
	}
	/**
	 * @param admin_del_fl the admin_del_fl to set
	 */
	public void setAdmin_del_fl(boolean admin_del_fl) {
		this.admin_del_fl = admin_del_fl;
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
	 * @return the adminId
	 */
	public String getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	/**
	 * @return the adminPwd
	 */
	public String getAdminPwd() {
		return adminPwd;
	}
	/**
	 * @param adminPwd the adminPwd to set
	 */
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	/**
	 * @return the adminEmail
	 */
	public String getAdminEmail() {
		return adminEmail;
	}
	/**
	 * @param adminEmail the adminEmail to set
	 */
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	/**
	 * @return the adminPhone
	 */
	public String getAdminPhone() {
		return adminPhone;
	}
	/**
	 * @param adminPhone the adminPhone to set
	 */
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	/**
	 * @return the adminMemo
	 */
	public String getAdminMemo() {
		return adminMemo;
	}
	/**
	 * @param adminMemo the adminMemo to set
	 */
	public void setAdminMemo(String adminMemo) {
		this.adminMemo = adminMemo;
	}
	
	
}

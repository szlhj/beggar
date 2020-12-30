package shop.beggar.admin.vo;

/**
 * @PackageName		: shop.beggar.admin.vo
 * @FileName		: FileVo.java
 * @Since			: 2020. 12. 24.
 * @Author			: HJLee
 * @Description		: 
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 24.		HJLee				최초 작성
 *
 */
public class FileVo {
	private int file_sq;
	private String fileName;
	private String fileRealName;
	private int item_sq;
	private String dttm;

	/**
	 * @param file_sq
	 * @param fileName
	 * @param fileRealName
	 * @param item_sq
	 * @param dttm
	 */
	public FileVo() {}
	
	public FileVo(int file_sq, String fileName, String fileRealName, int item_sq, String dttm) {
		super();
		this.file_sq = file_sq;
		this.fileName = fileName;
		this.fileRealName = fileRealName;
		this.item_sq = item_sq;
		this.dttm = dttm;
	}

	/**
	 * @return the file_sq
	 */
	public int getFile_sq() {
		return file_sq;
	}

	/**
	 * @param file_sq the file_sq to set
	 */
	public void setFile_sq(int file_sq) {
		this.file_sq = file_sq;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileRealName
	 */
	public String getFileRealName() {
		return fileRealName;
	}

	/**
	 * @param fileRealName the fileRealName to set
	 */
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
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

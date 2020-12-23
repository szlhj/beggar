package shop.beggar.file;

/**
 * @PackageName		: shop.beggar.common.file
 * @FileName		: FileDTO.java
 * @Since			: 2020. 12. 21.
 * @Author			: HJLee
 * @Description		: 파일 업로드 및 다운로드에 사용될 클래스
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 21.		HJLee				최초 작성
 *
 */
public class FileDTO {
	String fileName;
	String fileRealName;
	int downloadCount;
	
	/**
	 * @return the downloadCount
	 */
	public int getDownloadCount() {
		return downloadCount;
	}
	/**
	 * @param downloadCount the downloadCount to set
	 */
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
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
	 * @param fileName
	 * @param fileRealName
	 * @param downloadCount
	 */
	public FileDTO(String fileName, String fileRealName, int downloadCount) {
		super();
		this.fileName = fileName;
		this.fileRealName = fileRealName;
		this.downloadCount = downloadCount;
	}
	public FileDTO() {}
	
}

package shop.beggar.file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.beggar.admin.vo.FileVo;

/**
 * @PackageName		: shop.beggar.common.file
 * @FileName		: FileDAO.java
 * @Since			: 2020. 12. 21.
 * @Author			: HJLee
 * @Description		: 
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 21.		HJLee				최초 작성
 *
 */
public class FileDAO {
	
	private Connection con;

//	private static class LazyHolder {
//		private static final FileDAO INSTANCE = new FileDAO();
//	}
//
//	public static FileDAO getInstance() {
//		return LazyHolder.INSTANCE;
//	}
//
//	public void setConnection(Connection con) {
//		this.con = con;
//	}
	
	public FileDAO() {
		try {
			String dbURL = "jdbc:mariadb://bzu.co.kr:3306/beggar?serverTimezon=UTC";
			String dbID = "beggar";
			String dbPassword = "1111";
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int fileUpload(String fileName, String fileRealName) {
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into file (fileName, fileRealName) values (?, ?)");
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int fileHit(String fileRealName) {
		try {
			PreparedStatement pstmt = con.prepareStatement("update file set downloadCount = (select max(downloadCount) + 1 from file where fileRealName=?) where fileRealName=?");
			pstmt.setString(1, fileRealName);
			pstmt.setString(2, fileRealName);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<FileVo> getList() {
		ArrayList<FileVo> list = new ArrayList<FileVo>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from file");
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FileVo file = new FileVo();
				file.setFileName(rs.getString("fileName"));
				file.setFileRealName(rs.getString("FileRealName"));
				list.add(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

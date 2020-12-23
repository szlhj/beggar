package shop.beggar.file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	public int upload(String fileName, String fileRealName) {
		String sql = "insert into file (fileName, fileRealName) values (?, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fileName);
			pstmt.setString(2, fileRealName);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int hit(String fileRealName) {
		String sql = "update file set downloadCount = (select max(downloadCount) + 1 from file where fileRealName=?) where fileRealName=?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fileRealName);
			pstmt.setString(2, fileRealName);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<FileDTO> getList() {
		String sql = "select * from file";
		ArrayList<FileDTO> list = new ArrayList<FileDTO>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				FileDTO file = new FileDTO();
				file.setFileName(rs.getString("fileName"));
				file.setFileRealName(rs.getString("FileRealName"));
				file.setDownloadCount(rs.getInt("downloadCount"));
				list.add(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

package shop.beggar.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/downloadAction")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("file");
		
		String directory = this.getServletContext().getRealPath("/uploadFile/");
		
		File file = new File(directory + "/" + fileName);
		
		String mineType = getServletContext().getMimeType(file.toString());
		if (mineType == null) {
			response.setContentType("application/octet-stream");
		}
		
		String downloadName = null;
//		만약 현재 사용자의 접속 브라우져가 인터넷 익스플로러가 아니면
		if (request.getHeader("user-agent").indexOf("MSIE") == -1) {
			downloadName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
		} else {
			downloadName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
		}
		
		response.setHeader("Content-Disposition", "attachment;filename=\"" + downloadName + "\";");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		ServletOutputStream servletOutPutStream = response.getOutputStream();
		
//		파일을 1024 byte 만큼 쪼개서 다운로드 함
		byte b[] = new byte[1024];
		int data = 0;
		
		while((data = (fileInputStream.read(b, 0, b.length))) != -1) {
			servletOutPutStream.write(b, 0, data);
		}
		
		//파일 다운로드가 끝나고 파일카운트에 1 증가
		new FileDAO().fileHit(fileName);
		
		servletOutPutStream.flush();
		servletOutPutStream.close();
		fileInputStream.close();
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

}

package shop.beggar.admin.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.FileVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: FileUploadAction.java
 * @Since			: 2020. 12. 24.
 * @Author			: HJLee
 * @Description		: 파일 업로드 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 24.		HJLee				최초 작성
 *
 */
public class FileUploadAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String domain = request.getRequestURL().toString().replace(request.getRequestURI(),"");
		String pathFolder = "/uploadFile/";
		
		String urlDirectory = request.getRequestURI().toString().replace(request.getServletPath(), "");
		
		String directory = request.getSession().getServletContext().getRealPath(pathFolder);
		String now = new SimpleDateFormat("yyyyMMddHmsS").format(new Date()); //현재시간 설정
		int maxSize = 1024 * 1024 * 100; //100M이하만 사용가능
		String encoding = "UTF-8";
		
		//파일 폴더가 없을시 자동생성
		File file = new File(directory);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		//multipartRequest 객체를 통해 request에 선택된 파일을 upload폴더에 maxSize만큼 encoding의 형식으로 업로드를 하겟다는 의미
		MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
		
		
		
		
//// 		Enumeration 는 for문과 같은 의도로 만들어진 객체 여러개의 파일을 하나씩 확인하는 목적으로 사용
		Enumeration fileNames = multipartRequest.getFileNames();

//		while(fileNames.hasMoreElements()) {
			String parameter = (String) fileNames.nextElement();
			String fileName = multipartRequest.getOriginalFileName(parameter); //파일의 실제 이름
			String fileRealName = multipartRequest.getFilesystemName(parameter); //파일의 시스템 이름
			
			//파일이름에 null값이 존재하면 다시 업로드를 하지 않도록 처리
//			if (fileName == null) continue;
			
			int i = -1;
			i = fileRealName.lastIndexOf(".");  //파일 실제이름의 확장자 위치 가져오기
			String newFileRealName = now + fileRealName.substring(i,fileRealName.length()); //파일 이름과 현재시간 합치기
			
			File oldFile = new File(directory + fileRealName);  //기존의 파일 가져오기
			File newFile = new File(directory + newFileRealName);  //현재시간과 결합한 파일명 
			
			oldFile.renameTo(newFile); //파일명 변경
			
			
			//확장자를 확인해서 이외의확장자가 들어오면 메세지를 뿌려주고 그 파일을 삭제
			if (!fileName.endsWith(".png") && !fileName.endsWith(".gif") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
				File files = new File(directory + newFileRealName);
				files.delete();
				
				response.setContentType("text/html;charset=UTF-8;");
				PrintWriter out = response.getWriter();
				out.write("<script>alert('업로드할 수 없는 확장자입니다.');history.back()</script>");
				out.close();
				return null;
			} else{
				AdminService svc = new AdminService();
				FileVo vo = new FileVo();
				vo.setFileName(fileName);
				vo.setFileRealName(newFileRealName);
				
				if (!svc.fileUpload(vo, domain + pathFolder + newFileRealName)){
					response.setContentType("text/html;charset=UTF-8;");
					PrintWriter out = response.getWriter();
					out.write("<script>alert('업로드에 실패하였습니다.');history.back()</script>");
					out.close();
					return null;
				}
//				out.write("파일명 : " + fileName + "<br>");
//				out.write("실제파일명 : " + fileRealName + "<br>");
			}
//		}
		
		HttpSession session = request.getSession();
		session.setAttribute("fileImgNamePathDirectory", directory);
		session.setAttribute("fileImgNameDomain", domain);
		session.setAttribute("fileImgNamePathFoldery", pathFolder);
		session.setAttribute("fileImgNamePathRealName", newFileRealName);
		
//		ActionForward forward = new ActionForward();
//		forward.setPath("/admin/itemList");
		return null;
	}
}

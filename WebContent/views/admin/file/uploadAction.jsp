<%@page import="java.util.Enumeration"%>
<%@ page import="shop.beggar.file.FileDAO" %>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP file up/down</title>
</head>
<body>
	<button onclick="location.href='/admin/'">관리자 홈으로</button><br>
	
	<%
		String directory = application.getRealPath("/uploadFile/");
		int maxSize = 1024 * 1024 * 100; //100M이하만 사용가능
		String encoding = "UTF-8";
		
		//파일 폴더가 없을시 자동생성
		File file = new File(directory);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		//multipartRequest 객체를 통해 request에 선택된 파일을 upload폴더에 maxSize만큼 encoding의 형식으로 업로드를 하겟다는 의미
		MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
		
// 		Enumeration 는 for문과 같은 의도로 만들어진 객체 여러개의 파일을 하나씩 확인하는 목적으로 사용
		Enumeration fileNames = multipartRequest.getFileNames();

		while(fileNames.hasMoreElements()) {
			String parameter = (String) fileNames.nextElement();
			String fileName = multipartRequest.getOriginalFileName(parameter); //파일의 실제 이름
			String fileRealName = multipartRequest.getFilesystemName(parameter); //파일의 시스템 이름
			
			//파일이름에 null값티 존재하면 다시 업로드를 하지 않도록 처리
			if (fileName == null) continue;
			
			//확장자를 확인해서 이외의확장자가 들어오면 메세지를 뿌려주고 그 파일을 삭제
			if (!fileName.endsWith(".png") && !fileName.endsWith(".gif") && !fileName.endsWith(".jpg")) {
				File files = new File(directory + fileRealName);
				files.delete();
				out.write("업로드할 수 없는 확장자입니다.");
			} else{
// 				new FileDAO().upload(fileName, fileRealName);
				out.write("파일명 : " + fileName + "<br>");
				out.write("실제파일명 : " + fileRealName + "<br>");
			}
		}
	%>
</body>
</html>
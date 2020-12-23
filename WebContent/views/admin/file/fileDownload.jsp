<%@page import="shop.beggar.file.FileDTO"%>
<%@page import="shop.beggar.file.FileDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP file up/down</title>
</head>
<body>
	<button onclick="location.href='/admin/'">관리자 홈으로</button>
	
	<%
// 		String directory = application.getRealPath("/uploadFile/");
// 		String files[] = new File(directory).list();
		
// 		for(String file : files){
// 			out.write("<a href=\"" + request.getContextPath() + "/downloadAction?file=" + URLEncoder.encode(file, "UTF-8") + "\">" + file + "</a><br>");
// 		}

		ArrayList<FileDTO> fileList = new FileDAO().getList();
		
		for (FileDTO file : fileList) {
			out.write("<a href=\"" + request.getContextPath() + "/downloadAction?file=" + 
				java.net.URLEncoder.encode(file.getFileRealName(), "UTF-8") + "\">" + 
					file.getFileName() + "(다운로드 횟수 : " + file.getDownloadCount() + ")</a><br>");
		}
	%>
</body>
</html>
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
	
	<form action="/views/admin/file/uploadAction.jsp" method="post" enctype="multipart/form-data">
		파일1 : <input type="file" name="file1"><br>
		파일2 : <input type="file" name="file2"><br>
		파일3 : <input type="file" name="file3"><br>
		<input type="submit" value="업로드">
	</form>
	<a href="/views/admin/file/fileDownload.jsp">파일다운로드</a>
</body>
</html>
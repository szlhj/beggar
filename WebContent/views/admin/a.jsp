<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script src="https://cdn.ckeditor.com/ckeditor5/24.0.0/classic/ckeditor.js"></script> -->
<script src="/ckeditor/ckeditor.js"></script>
<!-- <script src="https://cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script> -->
        </head>
<body>
	<button onclick="location.href='/admin/'">관리자 홈으로</button>
	<button onclick="location.href='/'">홈으로</button><br>
	<textarea name="editor"></textarea>
	<script>
		CKEDITOR.replace( 'editor' );
	</script>
</body>
</body>
</html>
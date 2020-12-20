<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 메인</title>

<!-- <link rel="stylesheet" href="/views/css/board.css" type="text/css"> -->
<!-- <link rel="stylesheet" href="/views/css/ress.css" type="text/css"> -->
<!-- <link rel="stylesheet" href="/views/css/navbar.css" type="text/css"> -->

</head>
<body>
<!-- 	<div> -->
<%-- 		<jsp:include page="/views/navbar.jsp"></jsp:include> --%>
<!-- 	</div> -->
	<div>
		<ul class="board_sideMenu">
			<li>자주하는 질문</li>
			<li>제품관련 문의</li>
			<li>1:1문의</li>
			<li>내가쓴글 보기</li>
		</ul>
	</div>
	<div>
		<table>
			
		</table>
	</div>
	<button onclick="location.href='/board/writeBoard'">작성</button>
	<button onclick="location.href='/'">취소</button>
</body>
</html>
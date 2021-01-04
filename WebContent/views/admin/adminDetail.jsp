<%@page import="shop.beggar.admin.vo.AdminVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	AdminVo vo = (AdminVo) request.getAttribute("adminVo");
    	
    	String del_fl = null;
    	String supper = null;
    	
    	if (vo.isAdmin_del_fl() == true){
    		del_fl = "삭제";
    	} else {
    		del_fl = "사용중";
    	}
    	if (vo.isAdmin_supper() == true){
    		supper = "슈퍼관리자";
    	} else {
    		supper = "관리자";
    	}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 상세보기</title>

<script type="text/javascript">
	function modify() {
		location.href="/admin/modifyAdmin";
	}
	function cancle() {
		location.href="/admin";
	}
</script>

</head>
<body>
	<button onclick="location.href='/admin'">관리자 홈으로</button>
	<button onclick="location.href='/'">홈으로</button><br>
	
	관리자 일련번호 : <%=vo.getAdmin_sq() %><br>
	관리자 아이디 : <%=vo.getAdminId() %><br>
	관리자 이름 : <%=vo.getAdminName() %><br>
	관리자 이메일 : <%=vo.getAdminEmail() %><br>
	관리자 전화번호 : <%=vo.getAdminPhone() %><br>
	관리자 삭제 여부 : <%=del_fl %><br>
	슈퍼관리자 여부 : <%=supper %><br>
	관리자 메모 : <%=vo.getAdminMemo() %><br>
	관리자 생성일자 : <%=vo.getDttm() %><br>
	
	<button onclick="modify()">수정</button>
	<button onclick="cancle()">취소</button>
	
</body>
</html>
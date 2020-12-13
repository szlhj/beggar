<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@page import="shop.beggar.common.LoginManager"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	LoginManager lm = LoginManager.getInstance();
    	String id = lm.getMemberId(session);
    	
    	MemberVo vo = (MemberVo) session.getAttribute("vo");
    	
    	int mber_sq = 0;
    	if (vo != null) {
    		mber_sq = vo.getMber_sq();
    	}
    	
//     	String ls_validate =
// 		"현재 페이지는 물론 이전 페이지에서 설정된 세션 값들도 나타납니다.";
// 		session.setAttribute("Validate", ls_validate);
		
// 		String ls_name = "";
// 		String ls_value = "";
		
// 		Enumeration enum_app = session.getAttributeNames();
// 		int i=0;
// 		while(enum_app.hasMoreElements()){
// 		i++;
// 		ls_name=enum_app.nextElement().toString() ;
// 		ls_value= session.getAttribute(ls_name).toString( );
		
// 		out.println("<br>얻어온 세션 이름 [ " + i +" ] : " + ls_name+"<br>");
// 		out.println("<br>얻어온 세션 값 [ " + i +" ] : " + ls_value+"<hr>");
// 		out.println(vo.getMber_sq()+"<hr>");
// 		}
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BEGGAR.SHOP</title>

<script type="text/javascript">
	function join() {
		location.href="/member/registerAction.do";
	}
	function login() {
		location.href="/member/loginAction.do";
	}
	function modify() {
		location.href="/member/modifyAction.do";
	}
	function logout() {
		location.href="/member/logoutAction.do";
	}
</script>

</head>
<body>
	<div>
		<%if (mber_sq == 0) { %>
			<button type="button" onclick="join()">회원가입</button>
			<button type="button" onclick="login()">로그인</button>
		<%} else { %>
			<button type="button" onclick="modify()">회원수정</button>
			<button type="button" onclick="logout()">로그아웃</button>
		<%} %>
	</div>
	<div>
		<jsp:include page="/views/item/itemPage.jsp" />
	</div>
</body>
</html>
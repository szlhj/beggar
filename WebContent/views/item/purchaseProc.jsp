<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String nonmber = (String) request.getAttribute("nonmber");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문완료 화면</title>
</head>
<body>

<%if(nonmber == null) {%>
	<h3>주문이 완료 되었습니다.</h3>
	<button onclick="location.href='/item/orderView'">주문 조회</button>
	<button onclick="location.href='/'">홈으로</button>
<%} else { %>
	<h3>주문이 완료 되었습니다.</h3>
	<h5>주문 조회시 비회원 주문 번호가 필요하오니 이점 유의해주시기 바랍니다.</h5>
	<p>비회원 주문 조회 번호 : <%=nonmber %></p>
	<button onclick="location.href='/item/orderView?nonmber=<%=nonmber%>'">비회원 주문 조회</button>
	<button onclick="location.href='/'">홈으로</button>
	<input type="hidden" value="<%=nonmber%>" name="nonmber"/>
<%} %>
</body>
</html>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ItemVo ivo = (ItemVo)request.getAttribute("ivo");
                 ivo.getStok();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이템 디테일 화면</title>
</head>
<body>
	<jsp:include page="/views/navbar.jsp" />
	<jsp:include page="/views/item/navigation.jsp"></jsp:include>

<table>
	<tr>
		<td>상품파일경로<%=ivo.getFilepath() %></td>
		<td>상품프리뷰<%=ivo.getPreview() %></td>
		<td>상품명<%=ivo.getItem_name() %></td>
	</tr>
	<tr>
		<td>상품코드<%=ivo.getCode() %></td>
		<td>상품색깔<%=ivo.getColor() %></td>
	</tr>
	<tr>
		<td>상품번호<%=ivo.getItem_number() %></td>
		<td>상품<%=ivo.getItem_rating() %></td>
	</tr>
	<tr>
		<td>상품사이즈<%=ivo.getSize() %></td>
	</tr>
	<tr>
		<td>상품가격<%=ivo.getPrice() %></td>
	</tr>
	<tr>
		<td>상품할인된 가격<%=ivo.getPrice() - (ivo.getPrice() * ivo.getDiscount() /100)%></td>
	</tr>
	<%if(ivo.getStok() == 0 ) {%>
	<tr>
		<td>임시품절 상태입니다.</td>
	</tr>
	<%} else { %>
	<tr>
		<td onclick="location.href='/item/shopBasket'">장바구니</td>
		<td onclick="location.href='/item/purchase'">바로 구매</td>
	</tr>
	<%} %>
</table>
</body>
</html>
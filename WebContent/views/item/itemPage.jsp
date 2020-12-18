<%@page import="shop.beggar.common.Pagenation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   	ArrayList<ItemVo> list = (ArrayList<ItemVo>)request.getAttribute("list");
   	Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
   	String pn = (String)request.getAttribute("pn");
   	String category = (String)request.getParameter("category");
//    	String pn = (String)request.getParameter("pn");
   	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품홈</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="/views/css/itemHome.css" type="text/css">
</head>
<script>
	function dummy() {
		location.href="/item/dummy.do";
	}
</script>
    <script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
    <button onclick="location.href='/item/mainItem.do?category=0'">홈배너</button>
    <button onclick="location.href='/'">홈</button>
    <button onclick="dummy()">dummy넣기</button>
<body>
<table>
	<tr>
		<td><a href="/item/mainItem.do?category=1">NEW IN</a></td>
		<td><a href="/item/mainItem.do?category=2">EARRINGS</a></td>
		<td><a href="/item/mainItem.do?category=3">NECKLACES</a></td>
		<td><a href="/item/mainItem.do?category=4">BRACELETS</a></td>
		<td><a href="/item/mainItem.do?category=5">RINGS</a></td>
		<td><a href="/item/mainItem.do?category=6">ANKLETS</a></td>
		<td><a href="/item/mainItem.do?category=7">BEST</a></td>
	</tr>
</table>
<!-- 홈화면 기준은 날짜 별로 기준인것 같음 모든항목 출력인듯하고 -->
<table>
<tr>
	<th>상품이미지주소</th>
	<th>상품프리뷰</th>
	<th>상품이름</th>
	<th>상품가격</th>
	<th>상품카테고리번호</th>
	<th>상품할인율</th>
	<th>상품시퀀스번호</th>
	<th>상품할인가격</th>
</tr>
<% for (int i = 0; i <list.size(); i++) {%>
  <tr onclick="location.href='/item/detail.do?item_sq=<%=list.get(i).getItem_sq()%>'">
    <th><%=list.get(i).getFilepath() %></th>
    <th><%=list.get(i).getPreview() %></th>
    <th><%=list.get(i).getItem_name() %></th>
    <th><%=list.get(i).getPrice() %></th>
    <th><%=list.get(i).getCategory() %></th>
    <td><%=list.get(i).getDiscount() %></td>
    <td><%=list.get(i).getItem_sq() %></td>
    <td><%=list.get(i).getPrice() - (list.get(i).getPrice() * list.get(i).getDiscount() /100)%></td>
  </tr>
<!--   <tr> -->
<%--     <td><%=list.get(i).getDisprice() %></td> --%>
    
<!--   </tr> -->
<% } %>

</table>

<%if (pagenation.getStartPageNumber() != 1) {%>
<a href="/item/mainItem.do?pn=<%=pagenation.getStartPageNumber() - 1 %>&category=<%=category%>"> < </a>
<% } %>

<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
	<%if (i != Integer.parseInt(pn)) {%>
		<a href="/item/mainItem.do?pn=<%=i %>&category=<%=category%>"> <%=i%> </a>
	<%} else  {%>
		<%=i %>
	<%} %>
<%} %>
<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
		<a href="/item/mainItem.do?pn=<%=pagenation.getEndPageNumber() + 1 %>&category=<%=category%>"> > </a>
<% }%>

<%--     <th><%=list.get(i).getPreview() %></th> --%>
<%--     <th><%=list.get(i).getCategory() %></th> --%>
<%--     <td><%=list.get(i).getDiscount() %></td> --%>
<%--     <td><%=list.get(i).getItem_sq() %></td> --%>

<!--   <tr> -->
<%--     <td><%=list.get(i).getDisprice() %></td> --%>
    
<!--   </tr> -->
<div class="row row-cols-1 row-cols-md-4">
<% for (int i = 0; i <list.size(); i++) {%>
	<div class="col mb-3">
		<div class="card" onclick="location.href='/item/detail.do?item_sq=<%=list.get(i).getItem_sq()%>'">
  			<img src="..." class="card-img-top" alt="...">
  			<div class="card-body">
    			<h5 class="card-title">이름<%=list.get(i).getItem_name() %></h5>
    			<p class="card-text">가격<%=list.get(i).getPrice() %></p>
    			<p class="card-text">할인가격<%=list.get(i).getPrice() - (list.get(i).getPrice() * list.get(i).getDiscount() /100)%></p>
				<p class="card-text">할인율<%=list.get(i).getDiscount() %></p>
  			</div>
		</div>
	</div>
<% } %>
</div>
</body>
</html>
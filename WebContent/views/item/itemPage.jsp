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
		location.href="/item/dummy";
	}
</script>
    <script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
    <button onclick="location.href='/item/mainItem?category=0'">홈배너</button>
    <button onclick="location.href='/'">홈</button>
    <button onclick="dummy()">dummy넣기</button>
<body>
<!-- 홈화면 기준은 날짜 별로 기준인것 같음 모든항목 출력인듯하고 -->

<div class="row row-cols-1 row-cols-md-4 item_full">
<% for (int i = 0; i <list.size(); i++) {%>
	<div class="col mb-3 item">
		<div class="card" onclick="location.href='/item/detail?item_sq=<%=list.get(i).getItem_sq()%>'">
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

<div class="pagenations item">
<%if (pagenation.getStartPageNumber() != 1) {%>
<a class="page_prev" href="/?pn=<%=pagenation.getStartPageNumber() - 1 %>&category=<%=category%>"> < </a>
<% } %>

<% for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) { %>
	<%if (i != Integer.parseInt(pn)) {%>
		<a class="page_num" href="/?pn=<%=i %>&category=<%=category%>"> <%=i%> </a>
	<%} else  {%>
		<p class="page_frt"><%=i %></p>
	<%} %>
<%} %>
<%if (pagenation.getTotalPageCount() != pagenation.getEndPageNumber()) {%>
		<a class="page_next" href="/?pn=<%=pagenation.getEndPageNumber() + 1 %>&category=<%=category%>"> > </a>
<% }%>
</div>
</body>
</html>
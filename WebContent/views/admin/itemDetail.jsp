<%@page import="shop.beggar.common.Parser"%>
<%@page import="shop.beggar.beggar.vo.ItemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ItemVo itemVo = (ItemVo) request.getAttribute("itemVo");
	String pn = (String) request.getAttribute("pn");
	
	String category = itemVo.getCategory();
	
	String content = Parser.chgToHtml(itemVo.getExplanation());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/item/itemDetailView.css" type="text/css">

<script type="text/javascript">
	var content = '<%=content%>';
</script>

<script type="text/javascript">
	function modifyView() {
		$('#viewForm').submit();
	}
	function cancle() {
		location.href = "/admin/itemList?pn=" + <%=pn%>;
	}
	function show(isShow) {
		location.href = "/admin/itemShow?isShow=" + isShow + "&pn=" + <%=pn%> + "&sq=" + <%=itemVo.getItem_sq()%>;
	}
	function del(isDel) {
		location.href = "/admin/itemDel?isDel=" + isDel + "&pn=" + <%=pn%> + "&sq=" + <%=itemVo.getItem_sq()%>;
	}
</script>
</head>
<body>
	<jsp:include page="/views/admin/adminNavbar.jsp" />
	<div class="itemDetailView">
		<div class="detailView">
			<section>
				<button class="detailView_button" onclick="modifyView()">수정</button>
				<%if (itemVo.isShow_fl() == true) { %>
					<button class="detailView_button" onclick="show(false)">등록중지</button>
				<%} else { %>
					<button class="detailView_button" onclick="show(true)">등록하기</button>
				<%} %>
				<%if (itemVo.isDel_fl() == true) { %>
					<button class="detailView_button" onclick="del(false)">다시한번?</button>
				<%} else { %>
					<button class="detailView_button" onclick="del(true)">삭제하기</button>
				<%} %>
				<button class="detailView_button" onclick="cancle()">취소</button>
				
				<form action="/admin/itemModifyView?pn=<%=pn %>" method="post" id="viewForm">
					상품일련번호<input type="text" id="item_sq" name="item_sq" value="<%=itemVo.getItem_sq() %>" readonly="readonly" /><br>
					상품이름<input type="text" id="item_name" name="item_name" value="<%=itemVo.getItem_name() %>" /><br>
					카테고리
						<select id="category" name="category">
							<option value="1" <%="1".equals(category)?"selected":"" %>>NEW IN(뉴어라이벌)</option>
							<option value="2" <%="2".equals(category)?"selected":"" %>>EARRINGS(이어링)</option>
							<option value="3" <%="3".equals(category)?"selected":"" %>>NECKLACES(네크리스)</option>
							<option value="4" <%="4".equals(category)?"selected":"" %>>BRACELETS(브레이슬릿)</option>
							<option value="5" <%="5".equals(category)?"selected":"" %>>RINGS(링)</option>
							<option value="6" <%="6".equals(category)?"selected":"" %>>ANKLETS(앵클릿)</option>
							<option value="7" <%="7".equals(category)?"selected":"" %>>BEST(베스트 셀러)</option>
						</select>
						<br>
					상품코드<input type="text" id="code" name="code" value="<%=itemVo.getCode() %>" /><br>
					가격<input type="text" id="price" name="price" value="<%=itemVo.getPrice() %>" /><br>
					상품 할인율<input type="text" id="discount" name="discount" value="<%=itemVo.getDiscount() %>" /><br>
					재고<input type="text" id="stok" name="stok" value="<%=itemVo.getStok() %>" /><br>
					상품컬러<input type="text" id="color" name="color" value="<%=itemVo.getColor() %>" /><br>
					상품넘버링<input type="text" id="item_number" name="item_number" value="<%=itemVo.getItem_number() %>" /><br>
					상품 등급<input type="text" id="item_rating" name="item_rating" value="<%=itemVo.getItem_rating() %>" /><br>
					사이즈<input type="text" id="size" name="size" value="<%=itemVo.getSize() %>" /><br>
					<div>
	<!-- 					<input type="file" id="input_img" /> -->
						<div class="img_wrap">
							<img id="img" style="width: 200px; height: 200px;" src="<%=itemVo.getFilepath() %>" />
						</div>
					</div><br>
					<div>
						<%=content %>
					</div>
				</form>
				
				<button class="detailView_button" onclick="modifyView()">수정</button>
				<%if (itemVo.isShow_fl() == true) { %>
					<button class="detailView_button" onclick="show(false)">등록중지</button>
				<%} else { %>
					<button class="detailView_button" onclick="show(true)">등록하기</button>
				<%} %>
				<%if (itemVo.isDel_fl() == true) { %>
					<button class="detailView_button" onclick="del(false)">다시한번?</button>
				<%} else { %>
					<button class="detailView_button" onclick="del(true)">삭제하기</button>
				<%} %>
				<button class="detailView_button" onclick="cancle()">취소</button>
				
			</section>
		</div>
	</div>
	<jsp:include page="/views/admin/adminNavigation.jsp" />
</body>
</html>
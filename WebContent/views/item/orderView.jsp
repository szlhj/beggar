<%@page import="shop.beggar.common.Parser"%>
<%@page import="shop.beggar.beggar.vo.OrderVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.beggar.beggar.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	MemberVo mberVo = (MemberVo) session.getAttribute("vo");
    	String nonmber = (String) request.getAttribute("nonmber");
    	ArrayList<OrderVo> orderList = (ArrayList<OrderVo>) request.getAttribute("orderVoList");
    	OrderVo vo = (OrderVo) request.getAttribute("orderVo");
    	
    	int mber_sq = 0;
    
    	if (mberVo == null || mberVo.equals("0")) {
    		mber_sq = 0;
    	} else {
    		mber_sq = mberVo.getMber_sq();
    	}
    	
    	if (nonmber == null) {
    		nonmber = "";
    	}
    	
    	String formAddr = "";
    	String formName = "";
    	String formPhone = "";
    	String toAddr = "";
    	String toName = "";
    	String toPhone = "";
    	String record = "";
    	String dttm = "";
    	
    	if (vo != null || vo.getMber_sq() != 0) {
	    	formAddr = vo.getAddr_form();
	    	formName = vo.getName_form();
	    	formPhone = vo.getName_form_phone();
	    	toAddr = vo.getAddr_to();
	    	toName = vo.getName_to();
	    	toPhone = vo.getName_to_phone();
	    	record = vo.getRecord_item();
	    	dttm = vo.getOrder_dttm();
    	}
    	
    	int total = 0;
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/item/orderView.css" type="text/css">

<script type="text/javascript">
	function cancle() {
		location.href="/";
	}
	function orderDelete() {
		$("#orderDeleteForm").submit();
	}
</script>

</head>
<body>
	<jsp:include page="/views/navbar.jsp" />
	<jsp:include page="/views/member/myPageNavigation.jsp" />
	
	<%if (orderList == null) { %>
		결제할 내용이 없습니다.
	<%} else { %>
		<table class="table" border="1">
			<tr>
				<td class="tableTitle itemImg">이미지</td>
				<td class="tableTitle itemName">상품명</td>
				<td class="tableTitle itemPrice">가격</td>
				<td class="tableTitle itemStoc">수량</td>
				<td class="tableTitle itemPrice">결재금액</td>
				<td class="tableTitle itemDttmShipping">결재일자</td>
				<td class="tableTitle itemDttmShipping">배송상태</td>
			</tr>
		<%for (int i = 0; i < orderList.size(); i++) { %>
			<tr>
				<td class="itemImg"><img class="itemImg" src="<%=orderList.get(i).getItem_img() %>"></td>
				<td class=""><%=orderList.get(i).getItem_name() %></td>
				<td class="tableNumber"><%=Parser.comma(orderList.get(i).getPrice()) %></td>
				<td class="tableNumber"><%=orderList.get(i).getItem_stok() %> EA</td>
				<td class="tableNumber"><%=Parser.comma(orderList.get(i).getPrice() * orderList.get(i).getItem_stok()) %></td>
				<td class="tableTd"><%=orderList.get(i).getOrder_dttm() %></td>
				<td class="tableTd"><%=Parser.shipping(orderList.get(i).getShipping()) %></td>
			</tr>
		<% total += orderList.get(i).getPrice() * orderList.get(i).getItem_stok();
		} %>
		</table>
		<br>
		<table>
			<tr>
				<td class="tableTitle itemImg"></td>
				<td class="tableTitle itemName"></td>
				<td class="tableTitle itemPrice">총합계 금액</td>
				<td class="tableTitle itemStoc"></td>
				<td class="tableTitle tableNumber"><%=Parser.comma(total) %></td>
			</tr>
		</table>
	<%} %>
	<form action="/item/orderDelete" method="post" id="orderDeleteForm">
			<table class="table" border="1">
			<tr>
				<td colspan="2">보내는 사람
					<input type="hidden" id="mber_sq" name="mber_sq" value="<%=mber_sq %>">
					<input type="hidden" id="payment" name="payment">
					<input type="hidden" id="nonmber" name="nonmber" value="<%=nonmber %>">
				</td>
			</tr>
			<tr>
				<td class="orderName">이름</td>
				<td><input class="orderName" type="text" id="formName" name="formName" value="<%=formName %>"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" id="formPhone" name="formPhone" value="<%=formPhone %>"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="formAddr" name="formAddr" value="<%=formAddr %>"></td>
			</tr>
			<tr>
				<td colspan="2">받는 사람</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" id="toName" name="toName" value="<%=toName %>"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" id="toPhone" name="toPhone" value="<%=toPhone %>"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="toAddr" name="toAddr" value="<%=toAddr %>"></td>
			</tr>
			<tr>
				<td>요청사항</td>
				<td><input type="text" id="record" name="record" value="<%=record %>"></td>
			</tr>
		</table>
	</form>
	<button onclick="orderDelete()">주문 삭제</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button onclick="cancle()">취소</button>
</body>
</html>
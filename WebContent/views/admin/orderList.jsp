<%@page import="shop.beggar.common.Parser"%>
<%@page import="shop.beggar.beggar.vo.OrderVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	ArrayList<OrderVo> list = (ArrayList<OrderVo>) request.getAttribute("orderList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문현황</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/admin/orderList.css" type="text/css">

<script type="text/javascript">
	var check = false;
	function CheckAll(){
		var chk = document.getElementsByName("order_sq");
		if(check == false){
			check = true;
			for(var i = 0; i < chk.length; i++){                                                                    
				chk[i].checked = true;     //모두 체크
			}
		} else {
			check = false;
			for(var i = 0; i < chk.length; i++){                                                                    
				chk[i].checked = false;     //모두 해제
			}
		}
	}
	
	function itemDelete() {
		location.href="/admin/orderListItemDelete";
	}
	
	function itemDelete() {
		location.href="/admin";
	}
	
	function shipping(shipping) {
		var $form = $('#orderListForm');
		var check_count = document.getElementsByName("order_sq").length;
		var order_sq = "";
		
        for (var i = 0; i < check_count; i++) {
            if (document.getElementsByName("order_sq")[i].checked == true) {
                order_sq += document.getElementsByName("order_sq")[i].value + ",";
            }
        }
        alert(shipping);
        $('#orderSq').val(order_sq);
        $('#shipping').val(shipping);
        
		$form.submit();
	}
</script>

</head>
<body>
	<jsp:include page="/views/admin/adminNavbar.jsp" />

	<div class="orderListForm">
		<div class="orderList">
			<form action="/admin/orderListProc?" method="post" id="orderListForm">
				<input type="hidden" id="orderSq" name="orderSq" />
				<input type="hidden" id="shipping" name="shipping" />
				<table>
					<%if (list == null) { %>
						<tr>
							<td>주문 확인 리스트가 없습니다.</td>
						</tr>
					<%} else { %>
						<tr>
							<th><input type="checkbox" name="checkall" onclick="CheckAll()" /></th>
							<th>상품명</th>
							<th>수량</th>
							<th>판매가</th>
							<th>주문가격</th>
							<th>결재방식</th>
							<th>비회원주문번호</th>
							<th>보내는사람 이름</th>
							<th>보내는사람 전화번호</th>
							<th>보내는사람 주소</th>
							<th>받는사람 이름</th>
							<th>받는사람 전화번호</th>
							<th>받는사람 주소</th>
							<th>요청사항</th>
							<th>현재상태</th>
							<th>주문일자</th>
						</tr>
						
						<%for (int i = 0; i < list.size(); i++) { %>
							<tr>
								<td><input type="checkbox" id="order_sq" name="order_sq" value="<%=list.get(i).getOrder_sq() %>" /></td>
								<td><%=list.get(i).getItem_name() %></td>
								<td><%=Parser.stok(list.get(i).getItem_stok()) %></td>
								<td><%=Parser.comma(list.get(i).getPrice()) %></td>
								<td><%=Parser.itemSumPrice(list.get(i).getItem_stok(),list.get(i).getPrice()) %></td>
								<td><%=Parser.payment(list.get(i).getOrder_payment_plan()) %></td>
								<td><%=list.get(i).getNonmber() %></td>
								<td><%=list.get(i).getName_form() %></td>
								<td><%=list.get(i).getName_form_phone() %></td>
								<td><%=list.get(i).getAddr_form() %></td>
								<td><%=list.get(i).getName_to() %></td>
								<td><%=list.get(i).getName_to_phone() %></td>
								<td><%=list.get(i).getAddr_to() %></td>
								<td><%=list.get(i).getRecord_item() %></td>
								<td><%=Parser.shipping(list.get(i).getShipping()) %></td>
								<td><%=list.get(i).getOrder_dttm() %></td>
							</tr>
						<%} %>
					<%} %>
				</table>
			</form>
			
			<button class="orderList_button" onclick="shipping('2')">배송 준비중</button>
			<button class="orderList_button" onclick="shipping('3')">집하장 도착</button>
			<button class="orderList_button" onclick="shipping('4')">지점으로 출발</button>
			<button class="orderList_button" onclick="shipping('5')">배송완료</button>
			<button class="orderList_button" onclick="itemDelete()">주문삭제</button>
			<button class="orderList_button" onclick="cancle()">취소</button>
		</div>
	</div>
	
	<jsp:include page="/views/admin/adminNavigation.jsp" />
</body>
</html>
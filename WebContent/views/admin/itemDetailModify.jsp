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

<script type="text/javascript">
	var content = '<%=content%>';
	
	function modify() {

		var $item_name = $('#item_name');
		var $categore = $('#categore');
		var $code = $('#code');
		var $price = $('#price');
		var $discount = $('#discount');
		var $stok = $('#stok');
		var $color = $('#color');
		var $item_number = $('#item_number');
		var $item_rating = $('#item_rating');
		var $size = $('#size');
		var $explanation = $('#explanation');

 		if (!$item_name.val()) {
 			alert('상품이름을 입력 하세요.');
 			$item_name.focus;
 			return;
 		}
 		if (!$code.val()) {
 			alert('상품코드를 입력 하세요.');
 			$code.focus;
 			return;
 		}
 		if (!$price.val()) {
 			alert('상품가격을 입력 하세요.');
 			$price.focus;
 			return;
 		}
 		if (!$discount.val()) {
 			alert('상품할인율을 입력 하세요.(0~100)');
 			$discount.discount;
 			return;
 		}

 		if (!$stok.val()) {
 			alert('상품의 재고를 입력 하세요.');
 			$stok.discount;
 			return;
 		}

 		if (!$color.val()) {
 			alert('상품의 컬러를 입력 하세요.');
			$color.discount;
 			return;
 		}
 		if (!$item_number.val()) {
 			alert('상품의 넘버를 입력하세요.');
 			$item_number.discount;
 			return;
 		}
 		if (!$item_rating.val()) {
 			alert('상품의 등급을 입력하세요.');
 			$item_rating.discount;
 			return;
 		}
 		if (!$size.val()) {
 			alert('상품의 사이즈를 입력하세요.');
 			$size.discount;
 			return;
 		}
		
		saveContent();
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

<script type="text/javascript">
 $(document).ready(function() {
 	$('#input_img').on('change', function (){
		var data = new FormData();
		data.append("file", $('#input_img').prop('files')[0]);
		console.log(data);
		$.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/admin/fileUpload",
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (result) {
                console.log("SUCCESS : ", "");//result.data.url);
//                 $('#objectUrl').attr("disabled", true) 
//                 $('#objectUrl').val(result.data.url)
                
                $("#img").attr("src", "");
//                 handleImgFileSelect;
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
 	
 	});
 });
</script>

</head>
<body>
	<div>
		<section>
			<form action="/admin/itemModifyProc?pn=<%=pn %>" method="post" id="editorForm">
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
					<input type="file" id="input_img" />
					<div class="img_wrap">
						<img id="img" style="width: 200px; height: 200px;" src="<%=itemVo.getFilepath() %>" />
					</div>
				</div><br>
				<div>
					<jsp:include page="/editor/editorSkinForModify.jsp" flush="false" />
				</div>
			</form>
			<button onclick="modify()">수정</button>
			<%if (itemVo.isShow_fl() == true) { %>
				<button onclick="show(false)">등록중지</button>
			<%} else { %>
				<button onclick="show(true)">등록하기</button>
			<%} %>
			<%if (itemVo.isDel_fl() == true) { %>
				<button onclick="del(false)">다시한번?</button>
			<%} else { %>
				<button onclick="del(true)">삭제하기</button>
			<%} %>
			<button onclick="cancle()">취소</button>
		</section>
	</div>
</body>
</html>
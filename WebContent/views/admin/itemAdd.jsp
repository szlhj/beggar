<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품추가</title>

<script type="text/javascript" src="/views/js/jquery-3.5.1.js"></script>
<link rel="stylesheet" href="/views/css/item/itemAdd.css" type="text/css">

<script type="text/javascript">
	function join() {

		var $item_name = $('#item_name');
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
		location.href = "/admin";
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
                console.log("SUCCESS : ", result.data.url);
                $('#objectUrl').attr("disabled", true) 
                $('#objectUrl').val(result.data.url)
                
                
            },
            error: function (e) {
                console.log("ERROR : ", e);
            }
        });
 	});
 });
</script>

<style type="text/css">
	.img_wrap {
		width:200px;
		margin-top: 5px;
	}
	.img_wrap img {
		max-width: 100%;
	}
</style>


</head>
<body>
	<jsp:include page="/views/admin/adminNavbar.jsp" />
	<div class="itemAdd">
		<div class="add">
			<section>
				<form action="/admin/itemAddProc" method="post" id="editorForm">
					<table>
						<tr>
							<td class="td1">상품이름</td>
							<td class="td2"><input type="text" id="item_name" name="item_name" /></td>
						</tr>
						<tr>
							<td class="td1">카테고리</td>
							<td class="td2">
								<select id="category" name="category">
									<option value="1" selected>NEW IN(뉴어라이벌)</option>
									<option value="2">EARRINGS(이어링)</option>
									<option value="3">NECKLACES(네크리스)</option>
									<option value="4">BRACELETS(브레이슬릿)</option>
									<option value="5">RINGS(링)</option>
									<option value="6">ANKLETS(앵클릿)</option>
									<option value="7">BEST(베스트 셀러)</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="td1">상품코드</td>
							<td class="td2"><input type="text" id="code" name="code" /></td>
						</tr>
						<tr>
							<td class="td1">가격</td>
							<td class="td2"><input type="text" id="price" name="price" /></td>
						</tr>
						<tr>
							<td class="td1">상품 할인율</td>
							<td class="td2"><input type="text" id="discount" name="discount" /></td>
						</tr>
						<tr>
							<td class="td1">재고</td>
							<td class="td2"><input type="text" id="stok" name="stok" /></td>
						</tr>
						<tr>
							<td class="td1">상품컬러</td>
							<td class="td2"><input type="text" id="color" name="color" /></td>
						</tr>
						<tr>
							<td class="td1">상품넘버링</td>
							<td class="td2"><input type="text" id="item_number" name="item_number" /></td>
						</tr>
						<tr>
							<td class="td1">상품 등급</td>
							<td class="td2"><input type="text" id="item_rating" name="item_rating" /></td>
						</tr>
						<tr>
							<td class="td1">사이즈</td>
							<td class="td2"><input type="text" id="size" name="size" /></td>
						</tr>
						<tr>
							<td class="td1">이미지</td>
							<td class="td2">
								<input type="file" id="input_img" />
								<div class="img_wrap">
									<img id="img" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="td1" colspan="2">
								<div>
									<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
								</div>
							</td>
						</tr>
					</table>
				</form>
				<br>
			</section>
			<button class="add_button" onclick="join()">등록</button>
			<button class="add_button" onclick="cancle()">취소</button>
		</div>
	</div>
	<jsp:include page="/views/admin/adminNavigation.jsp" />
</body>
</html>
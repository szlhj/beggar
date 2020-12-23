<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function write() {
		
	}
</script>

</head>
<body>
	<form action="" method="post" id="wrForm">
		<table>
			<tr>
				<td>구분</td>
				<td>
					<select id="board_number">
						<option value="1" selected>자주하는 질문</option>
						<option value="2" >제품관련 문의</option>
						<option value="3" >1:1문의</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" id="title" name="title" />
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="10" id="content" name="content"></textarea>
				</td>
			</tr>
		</table>
	</form>
	
	<button onclick="write()">글쓰기</button>
	<button onclick="location.href='/'">취소</button>
</body>
</html>
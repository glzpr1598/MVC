<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상세보기</title>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
	table, th, tr, td {
		border: thin solid black;
		border-collapse: collapse;
		padding: 5px;
	}
	
	table {
		width: 400px;
	}
</style>
</head>
<body>
	<form action="modifyForm.do" method="post">
		<input type="hidden" name="bId" value="${ dto.bId }"/>
		<input type="hidden" name="bName" value="${ dto.bName }"/>
		<input type="hidden" name="bTitle" value="${ dto.bTitle }"/>
		<input type="hidden" name="bContent" value="${ dto.bContent }"/>
		<table>
			<tr>
				<th>글번호</th>
				<td>${ dto.bId }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${ dto.bTitle	}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ dto.bName }</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${ dto.bDate }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${ dto.bHit }</td>
			</tr>
			<tr>
				<td colspan="2">${ dto.bContent }</td>
			</tr>
		</table>
		<input type="button" id="btnList" value="목록"/>
		<input type="submit" value="수정"/>
		<input type="button" id="btnDelete" value="삭제"/>
	</form>
</body>
<script>
	// 목록 버튼
	$("#btnList").on("click", function() {
		location.href="./list.do";
	});
	
	// 삭제 버튼
	$("#btnDelete").on("click", function() {
		if (confirm("정말 삭제하시겠습니까?")) {
			location.href="./delete.do?bId=${dto.bId}";
		}
	});
	
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
		<style>
			table, tr, th, td {
				border: thin solid black;
				border-collapse: collapse;
				padding: 5px;
			}
			table {
				width: 400px;
			}
			th {
				width: 70px;
			}
			input[type="text"] {
				width: 98%;
			}
			textarea {
				width: 98%;
				resize: none;
			}
		</style>
	</head>
	<body>
		<table>
			<tr>
				<th>글번호</th>
				<td id="idx">${ dto.idx }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="${ dto.subject }"/></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${ dto.user_name }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${ dto.reg_date }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${ dto.bHit }</td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="content" rows="20">${ dto.content }</textarea></td>
			</tr>
		</table>
		<input type="button" id="btnUpdate" value="수정"/>
	</body>
	<script>
		$("#btnUpdate").click(function() {
			$.ajax({
				url: "./update",
				type: "post",
				data: {
					idx: $("#idx").html(),
					subject: $("input[name='subject']").val(),
					content: $("textarea").val()
				},
				dataType: "json",
				success: function(data) {
					// 수정 성공
					if (data.success > 0) {
						alert("수정 완료");
					} else {
						alert("수정 실패");
					}
					location.href="./detail?idx="+${dto.idx};
				},
				error: function(e) {console.log(e)}
			});
		});
	</script>
</html>
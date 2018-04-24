<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
</head>
<style>
	table, tr, th, td {
		border: thin solid black;
		border-collapse: collapse;
		padding: 5px;
		text-align: center;
	}
	table {
	    height: 500px;
	    width: 500px;
	}
	input[type=text] {
		width: 100%;
	}
</style>
<body>
<jsp:include page="loginBox.jsp"/>
	<form action="update" method="post">
		<input type="hidden" name="idx" value="${ bbs.idx }"/>
	    <table>
	        <tr>
	            <th>제목</th>
	            <td><input name="subject" type="text" value="${ bbs.subject }"/></td>
	        </tr>
	        <tr>
	            <th colspan="2">내용</th>
	        </tr>
	        <tr>
	            <th colspan="2"><textarea name="content" rows="30" cols="80" >${ bbs.content }</textarea></th>
	        </tr>
	        <tr>
	            <th colspan="2"><input type="submit" value="수정"></th>
	        </tr>
	    </table>
	</form>
</body>
<script> 
	// ID 일치 여부 확인
	var loginId ="${sessionScope.loginId}";
	if (loginId != "${bbs.user_name}") {
		alert("작성자만 수정할 수 있습니다.");
		location.href="detail?idx=" + "${bbs.idx}";
	}
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 을 사용하기 위해서는 추가 -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		table,tr,th,td{
		border: 1px solid black;
		border-collapse: collapse;
		padding: 5px;
		}
		td{
			text-align: center;
		}
		table{
			
		}
	</style>
	</head>
	<body>
	<jsp:include page="loginBox.jsp"/>
		<table>
			<tr>
				<th>글번호</th>
				<td>${info.idx}</td>
			</tr>
			<tr>	
				<th>작성자</th>
				<td>${info.user_name}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${info.subject}</td>
			</tr>
			<tr>	
				<th>게시일</th>
				<td>${info.REG_DATE}</td>
			</tr>
			<tr>
				<th colspan="2">내용</th>
			</tr>
			<tr>
				<td colspan="2">${info.content}</td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="list">리스트</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- 수정 페이지로 이동(데이터가 담겨 있어야 함) updateForm.jsp -->
					<a href="updateForm?idx=${info.idx}">수정</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="delete?idx=${info.idx}">삭제</a>
				</td>
			</tr>
		</table>
	</body>
	<script>
		var msg = "${msg}";
		if (msg != "") { //원하는 값이 없을경우 list으로 다시 보낸다
			alert(msg);
			//list을 호출하므로 뒤에 요청 주소가 남지 않는다
			location.href="list";
		}
	</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style>
		table,th,td{
			border: 1px solid black;
			border-collapse: collapse;
			padding: 5px 10px;
		}
		table{
			width: 500px;
		}
		input[type='text']{
			width:100%;
		}
		textarea{
			width:100%;
			resize:none;
		}
		td{
			text-align: center;
		}
		
	</style>
</head>
<body>
	<form action="write" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="userName"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="15" name="content"></textarea></td>
		</tr>
		<tr>
			<th>사진</th>
			<td><input type="file" name="photo"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="./">리스트 가기</a><!-- ./ 현재의 루트 -->
				&nbsp;&nbsp;
				<button>저장</button>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>







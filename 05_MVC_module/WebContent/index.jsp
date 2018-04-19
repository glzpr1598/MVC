<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<html>
    <head>
        <meta charset="utf-8">
        <title>회원가입</title>
        <style>
            table, tr, th, td {
            	border: thin solid black;
            	border-collapse: collapse;
            	padding: 5px;
            	text-align: center;
            }
        </style>
        <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    </head>
    <body>
    	<h1>회원가입</h1>
        <form action="Join" method="post">
        	<table>
	        	<tr>
	        		<td>아이디</td>
	        		<td><input type="text" name="id"/></td>
	        	</tr>
	        	<tr>
	        		<td>비밀번호</td>
	        		<td><input type="password" name="pw"/></td>
	        	</tr>
	        	<tr>
	        		<td>이름</td>
	        		<td><input type="text" name="name"/></td>
	        	</tr>
	        	<tr>
	        		<td>나이</td>
	        		<td><input type="text" name="age"/></td>
	        	</tr>
	        	<tr>
	        		<td>성별</td>
	        		<td>
	        			<input type="radio" name="gender" value="남"/>남성
	        			<input type="radio" name="gender" value="여"/>여성
	        		</td>
	        	</tr>
	        	<tr>
	        		<td>이메일</td>
	        		<td><input type="text" name="email"/></td>
	        	</tr>
	        	<tr>
	        		<td colspan="2">
	        			<input type="submit" value="회원가입">
	        		</td>
	        	</tr>
        	</table>
        </form>
    </body>
    <script>

    </script>
</html>
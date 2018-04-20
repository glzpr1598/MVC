<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <title>JSP</title>
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
    	<h1>로그인</h1>
        <form action="login" method="post">
            <table>
                <tr>
                    <th>아이디</th>
                    <td>
                        <input type="text" name="id" placeholder="아이디를 입력하세요.">
                    </td>
                    <td rowspan="2">
                        <input type="submit" value="로그인">
                    </td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td>
                        <input type="password" name="pw" placeholder="비밀번호를 입력하세요.">
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input id="btnSignUp" type="button" value="회원가입">
                        <input id="btnFind" type="button" value="아이디/비밀번호 찾기">
                    </td>
                </tr>
            </table>
        </form>
        
    </body>
    <script>
    	var msg = "${msg}";
    	if (msg != "") {
    		alert(msg);
    	}
    
    	// 회원가입 버튼
		$("#btnSignUp").click(function() {
			location.href="joinForm.jsp";
		});
    </script>
</html>

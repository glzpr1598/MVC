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
            	text-align: center;
            }
        </style>
	</head>
	<body>
        <table>
            <tr>
                <th>아이디</th>
                <td>
                    <input type="text" id="userId" placeholder="아이디를 입력하세요.">
                </td>
                <td rowspan="2">
                    <input id="loginBtn" type="button" value="로그인">
                </td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td>
                    <input type="password" id="userPw" placeholder="비밀번호를 입력하세요.">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input id="btnSignUp" type="button" value="회원가입">
                    <input id="btnFind" type="button" value="아이디/비밀번호 찾기">
                </td>
            </tr>
        </table>
	</body>
	<script>
		$("#loginBtn").click(function() {
			$.ajax({
				type: "post",
				url: "./login",
				data: {
					id: $("#userId").val(),
					pw: $("#userPw").val()
				},
				dataType: "json",
				success: function(data) {
					console.log(data);
					if(data.success == 1) {
						alert("로그인 성공");
						location.href="./main.jsp";
					} else {
						alert("로그인 실패");
					}
				},
				error: function(err) {console.log(err);}
			});
		});
	</script>
</html>
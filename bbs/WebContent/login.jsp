<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<html>
    <head>
        <meta charset="UTF-8">
	<style>
		table,th,td{
			border: 1px solid black;
			border-collapse: collapse;
			padding: 5px 10px;
			text-align: center;
		}
	</style>
    </head>
    <body>
    <!-- 버튼 태그는 submit 을 포함 하고 있어 서버로 입력 한 정보를 보내준다
    	input type ="button" 태그는 페이지 이동 이벤트를 걸어주면 단순히 페이지 이동만 이동시켜줌
    	 따라서 , 서버로 전송 할 것인지 , 단순히 페이지를 이동 시켜주는 기능을 할 것 인지 구분하여
    	 태그를 만들어주자.
    -->
    <!--  -->
        <form action="./login" method="post"> 
            <table border="1px">

            <tr>
                <th>ID</th>
                <td><input type="text" name="userId" placeholder="아이디를 입력하세요"/></td>

                <th rowspan="2"><input type="submit" value="로그인"/></th>
            </tr>

            <tr>
                <th>PW</th>
                <td><input type="password" name="userPw" placeholder="비밀번호를 입력하세요"/></td>
            </tr>

            </table>
        </form>
    </body>
    <script>
    	var msg ="${msg}";
    	if(msg !=""){
    		alert("${msg}");
    	}
    </script>
</html>
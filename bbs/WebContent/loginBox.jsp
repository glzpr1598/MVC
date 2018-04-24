<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="login">로그인 테스트</div>
<script>
	var loginId ="${sessionScope.loginId}";
	if(loginId==""){
		alert("로그인이 필요한 서비스 입니다.");
		location.href ="index.jsp";
	}else{
	 var content ="안녕하세요,"+loginId+"님";
	 content += "<a href='logout'>  [로그아웃]</a>";
	 document.getElementById("login").innerHTML = content;
	}
</script>
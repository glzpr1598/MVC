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
            div.floating{
            	position: absolute;
            	width: 200px;
            	height: 100px;
            	background: white;
            	border: thin solid black;
            	top: 25%;
            	left: 35%;
            	text-align: center;
            }
		</style>
	</head>
	<body>
		<button id="popup1">1번 팝업</button>
		<button id="popup2">2번 팝업</button>
		<button id="popup3">3번 팝업</button>
		<!-- 외부 페이지에 있는 리스트 가져오기 -->
		<div id="listArea"></div>
		<!-- 외부 페이지에 있는 팝업 가져오기 -->
		<div id="noticeArea"></div>
	</body>
	<script>
		$(document).ready(function() {
			$.ajax({
				type: "get",
				url: "./include/list.html",
				dataType: "html",
				success: function(data) {
					$("#listArea").html(data);
				},
				error: function(err) {
					console.log(err);
				}
			});
		});
		
		$("button").click(function(e) {
			console.log(e.target.id);
			// $("선택자").load(url 선택자, callBack);
			$("#noticeArea").load("./include/notice.html #" + e.target.id,
				function(res, stat) {
					console.log(res); // 전체 소스
					console.log(stat); // 성공, 실패
				}
			)
		});
	</script>
</html>
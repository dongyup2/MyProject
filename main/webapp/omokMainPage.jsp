<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>오목 메인페이지</h1>
<hr>
<form action="detailUser" method="GET">
    <button type="submit">마이페이지</button>
</form>
<span>쿠키에 저장된 아이디: <span id="storedId"></span></span>
<script>
window.onload = function() {
	  var storedId = document.getElementById("storedId");
	  var cookies = document.cookie.split("; ");

	  for (var i = 0; i < cookies.length; i++) {
	    var keyValue = cookies[i].split("=");
	    if (keyValue[0] === "id") {
	      storedId.textContent = keyValue[1];
	    }
	  }
	};
</script>
</body>
</html>
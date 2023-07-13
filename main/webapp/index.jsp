<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.7.0.js"></script>
</head>
<body>
	<h1>오목게임 플랫폼</h1>
	<hr>
	<a href="omokMainPage.jsp">오목</a>
	<br>
	<a href="egg">알까기</a>
	<br>
	<span>쿠키에 저장된 아이디: <span id="storedId"></span></span><br>
	<a href="/logout">로그아웃</a>
<script>
/* document.getElementById("logout").addEventListener("click", async function() {
	  await fetch('/logout', { method: 'GET' });
	  deleteAllCookies();
	  window.location.href = "/index";
	});


function deleteCookie(name, path) {
	  document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:01 GMT; path=" + path;
	}
	
function deleteAllCookies() {
	  var cookies = document.cookie.split("; ");
	  
	  for (var i = 0; i < cookies.length; i++) {
	    var cookie = cookies[i];
	    var name = cookie.split("=")[0];
	    deleteCookie(name, '/');
	  }
	}
*/
window.onload = function() {
	  var storedId = document.getElementById("storedId");
	  var cookies = document.cookie.split("; ");

	  for (var i = 0; i < cookies.length; i++) {
	    var keyValue = cookies[i].split("=");
	    if (keyValue[0] === "id") {
	      storedId.textContent = keyValue[1];
	    }
	  }
	}
	
function fetchPrincipal() {
    $.ajax({
        type: "GET",
        url: "/principal",
        dataType: "json",
        success: function(response) {
            console.log(response);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error(textStatus, errorThrown);
        }
    });
}

fetchPrincipal();
</script>
</body>
</html>
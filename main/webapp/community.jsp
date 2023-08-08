<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="js/jquery-3.7.0.js"></script>
<title>Document</title>
<style>
body {
	margin: 0px;
	padding: 0px;
	background-color: white;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.main-content {
	display: flex;
	flex-direction: row;
	width: 80%;
	max-width: 90%; /* 이 값을 변경시 중앙 내용 섹션의 최대 너비. */
}

#box {
	background-color: #d2d2d2;
	max-width: 1064px;
	width: 70%;
	min-height: 0px;
	top: 40px;
	padding: 10px;
}

#nav {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	align-items: center;
	width: 300px;
	height: 30px;
	margin-left: 30px;
}

#mainBox {
	display: flex;
	flex-direction: row;
	width: 100%;
	height: 90%;
	margin: 1px;
}

#listBox {
	background-color: white;
	width: 100%;
	height: 100%;
	padding-bottom: 15px;
	overflow-y: scroll;
}


.list1 {
	background-color: white;
	font-size: 10px;
	width: 100%;
	border-bottom: 1px solid gray;
}

.postRoomList {
	background-color: white;
	width: 100%;
	height: 35px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.2);
	display: flex;
	flex-direction: row;
	justify-content: space-evenly;
	align-items: top;
	padding-top: 2px;
	cursor: pointer;
}

.postRoomList:hover {
	background-color: #ECECEC;
}

.user2 {
	display: flex;
	height: 25px;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.postMno {
	font-size: 13px;
}
.postTitle{
	font-size: 13px;
}
.postWriter {
	font-size: 14px;
	font-weight: bold;
}
.userBtn {
	background-color: #D2DFF0;
	width: 25px;
	height: 10px;
	font-size: 10px;
	border-radius: 3px;
	text-align: center;
	line-height: 10px;
}

.userlist {
	display: flex;
	width: 100%;
	height: 20px;
	flex-direction: row;
	justify-content: space-around;
	align-items: center;
	cursor: pointer;
}
.userlist:hover {
	background-color: #ECECEC;
}
#chatMainbox {
	display: flex;
	flex-direction: column;
	position: relative;
	background-color: #d2d2d2;
	width: 30%;
	height: 100vh;
}
.postTable {
    width: 100%;
    border-collapse: collapse;
}
.postTable th,
.postTable td {
    border-bottom: 1px solid #d2d2d2;
    padding: 4px;
    text-align: left;
}
.postTable th {
    border-bottom: 2px solid #d2d2d2;
}
.postTable td .userBtn {
    cursor: pointer;
}
.postTable td:hover {
   background-color: #ececec;
}
</style>
</head>
<body>
	<div class="main-content">
		<div id="box">
			<div id="nav">
				<h1>게시판</h1>
			</div>
			<!-- Create Room Modal -->
			<div id="mainBox">
    <div id="listBox">
        <table class="postTable">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회</th>
                </tr>
            </thead>
            <tbody id="postTableBody">
                <%-- <c:forEach var="post" items="${postList}">
                    <tr class="postRoomList">
                        <td class="postMno">${post.bno}</td>
                        <td class="postTitle">${post.title}</td>
                        <td>
                            <div class="postWriter">
                                <span class="userName">${post.writer}</span>
                            </div>
                        </td>
                        <td class="postCreateDate">${post.createDate}</td>
                        <td>
                            <div class="userBtn">>></div>
                        </td>
                    </tr>
                </c:forEach> --%>
            </tbody>
        </table>
    </div>
</div>
		</div>
		<div id="chatMainbox">
			<!-- <div class="temp"></div> -->
			
	</div>
	 <div id="communityWindow" class="community-window">
  		
  </div>
	 <script> 
		// 사용할 변수 선언
		var postTableBody = $("#postTableBody");

		// 페이지가 로드되면 게시물 목록을 불러오는 함수 호출
		$(document).ready(function() {
    		loadPostList();
		});
	
		function loadPostList() {
			$.ajax({
				url: "CommunityServlet",
				type: "GET",
				dataType: "json",
				success: function(data) {
					postTableBody.empty(); // 기존에 표시된 게시물 목록 제거
					
					$.each(data, function(index, post) {
						var postRow = "<tr class='postRoomList'>"
									  + "<td class='postMno'>" + post.bno + "</td>"
									  + "<td class='postTitle'>" + post.title + "</td>"
									  + "<td><div class='postWriter'><span class='userName'>" + post.writer + "</span></div></td>"
									  + "<td class='postCreateDate'>" + post.createDate + "</td>"
									  + "<td><div class='userBtn'>" + post.views + "</div></td>"
									  + "</tr>";					
						postTableBody.append(postRow);
					});
				},
				error: function(xhr, status, error) {
					console.error("게시물 목록을 불러오지 못했습니다.");
				}
			});
		}
	</script>
</body>
</html>
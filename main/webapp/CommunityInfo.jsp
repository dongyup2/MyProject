<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
}

html, body, .main-content, #box {
	width: 100vw;
	height: 98.6vh;
}

.main-content {
	justify-content: center;
	align-items: center;
	display: flex;
	flex-direction: row;
	max-width: 90%; /* 이 값을 변경시 중앙 내용 섹션의 최대 너비. */
	margin: 0 auto;
}

#box {
	background-color: #d2d2d2;
	max-width: 1064px;
	width: 100%;
	top: 40px;
	padding: 10px;
}

#nav {
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 50px;
}

#mainBox {
	background-color: white;
	display: flex;
	flex-direction: column;
	width: 100%;
	height: 87%;
	margin: 1px;
	margin-bottom: 15px;
	overflow-y: scroll;
}

.postTable tr:hover td {
	background-color: #ececec;
}

#listBox {
	
}

.postRoomList {
	background-color: white;
	width: 100%;
	height: 50px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.2);
	flex-direction: row;
	justify-content: space-evenly;
	align-items: top;
	padding-top: 2px;
	cursor: pointer;
}

.postTable {
	width: 100%;
	border-collapse: collapse;
}

.postTable th, .postTable td {
	border-bottom: 1px solid #d2d2d2;
	padding: 4px;
	text-align: left;
	position: sticky;
}

.postTable th {
	border-bottom: 2px solid #d2d2d2;
	top: 0;
	background-color: white;
}

.postTable td:hover {
	background-color: #ececec;
}

.fixed-header, .fixed-header thead tr {
	position: sticky;
	top: 0;
	background-color: white;
}
</style>
</head>
<body>
	<div class="main-content">
		<div id="box">
			<div id="nav">
				<button id="backToList">나가기</button>
				<h1>실시간 게시판</h1>
			</div>
			<div id="mainBox">
				<div id="listBox">
					<table class="postTable fixed-header">
						<thead id="postThead">
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>조회</th>
								<th>추천</th>
							</tr>
						</thead>
						<tbody id="postTableBody">

						</tbody>
					</table>
				</div>
				<div class="container">
        <button id="show-form-button">게시글 등록하기</button>
        <div id="post-form-container" style="display:none;">
            <form id="post-form">
                <div>
                    <label for="title">제목: </label>
                    <input type="text" id="title" name="title" required>
                </div>
                <div>
                    <label for="content">내용: </label>
                    <textarea id="content" name="content" rows="5" required></textarea>
                </div>
                <button type="submit">글 작성하기</button>
            </form>
        </div>
        <div id="result"></div>
    </div>
			</div>
		</div>
		<div id="communityWindow" class="community-window"></div>
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
                                      + "<hr>"
                                      + "<td><div class='postWriter'><span class='userName'>" + post.writer + "</span></div></td>"
                                      + "<td class='postCreateDate'>" + post.create_Date + "</td>"
                                      + "<td><div class='userBtn'>" + post.views + "</div></td>"
                                      + "<td><div class='userBtn'>" + post.recommend+ "</div></td>"
                                      + "</tr>";                   
                        postTableBody.append(postRow);
                    });
                },
                error: function(xhr, status, error) {
                    console.error("게시물 목록을 불러오지 못했습니다.");
                }
            });
        }
     // 게시물 행을 클릭했을 때의 이벤트 핸들러 추가
        $("#postTableBody").on("click", ".postRoomList", function() {
            var postId = $(this).find(".postMno").text(); // 게시물 ID 가져오기
            loadPostContent(postId); // 게시물 내용 가져오기 함수 호출
        });

        function loadPostContent(postId) {
            $.ajax({
                url: "CommunityPostDetailServlet", 
                type: "GET",
                data: { postId: postId }, 
                dataType: "json",
                success: function(data) {
                	console.log(data);
                    // 게시물 내용을 가지고 있는 객체 "data"를 "mainBox"에 채우기
                    var postContent = "<div class='post-content'>"
                                    + "<h2 class='post-title'>" + data.title + "</h2>"
                                    + "<div class='post-meta'>작성자: " + data.writer
                                    + " | 작성일: " + data.create_Date
                                    + " | 조회: " + data.views
                                    + " | 추천: " + data.recommend+ "</div>"
                                    + "<div class='post-body'>" + data.content + "</div>"
                                    + "</div>";
                    $("#mainBox").html(postContent); 
                },
                error: function(xhr, status, error) {
                    console.error("게시물 내용을 불러오지 못했습니다.");
                }
            });
        }
        $("#backToList").on("click", function() {
            loadPostList(); // 게시물 목록을 불러오는 함수 호출
        });
        
    </script>
</body>
</html>

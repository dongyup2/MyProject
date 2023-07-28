<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>오목 게임</title>
<script src="js/jquery-3.7.0.js"></script>
<script src="js/omok.js"></script>
<script src="js/omok_btn.js"></script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

@import url("css/omokboard.css?5");
</style>
</head>
<body>
	<div class="nav">오목 게임</div>
	<!-- <button id="exit-room-btn">나가기</button> -->
	<div></div>
	<div class="empty"></div>
	<div class="container">
		<div class="profile-left">
			<img src="img/근육보노보노.jpg" alt="Player 1 이미지">
			<div class="profile-name" id="user1-name"></div>
			<div class="profile-info" id="user1-info"></div>

			<div id="timer1">남은 시간: 30초</div>
		</div>
		<div class="board" id="board"></div>
		<div class="profile-right">
			<img src="img/잔망루피.jpg" alt="Player 2 이미지">
			<div class="profile-name" id="user2-name">Player 2</div>
			<div class="profile-info" id="user2-info">
				유저2 정보<br> 승 / 패 / 무 / 승률
			</div>
			<div id="timer2">남은 시간: 30초</div>
		</div>
	</div>
	<br>
	<div class="currentTurn" id="currentTurn">Player 1's Turn</div>
	<div class="footer"></div>
	<script></script>
</body>
</html>
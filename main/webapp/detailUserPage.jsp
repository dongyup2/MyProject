<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Game Info</title>
</head>
<body>
    <h1>User Game Info</h1>
    <h3>Member Number: <span>${userGameInfo.mno}</span></h3>
    <h3>Name: <span>${userGameInfo.name}</span></h3>
    <h3>ID: <span>${userGameInfo.id}</span></h3>
    <h3>Email: <span>${userGameInfo.email}</span></h3>
    <h3>regDate: <span>${userGameInfo.regDate}</span></h3>
    <h3>Game record ID: <span>${userGameInfo.record_id}</span></h3>
    <h3>Win: <span>${userGameInfo.win}</span></h3>
    <h3>Lose: <span>${userGameInfo.lose}</span></h3>
    <h3>Draw: <span>${userGameInfo.draw}</span></h3>
    <h3>Last play date: <span>${userGameInfo.play_date}</span></h3>
</body>
</html>

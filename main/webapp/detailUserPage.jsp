<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Game Info</title>
</head>
<body>
    <h1>User Game Info</h1>
    <h3>Member Number: ${userGameInfo.mno}</h3>
    <h3>Name: ${userGameInfo.name}</h3>
    <h3>ID: ${userGameInfo.id}</h3>
    <h3>Email: ${userGameInfo.email}</h3>
    <h3>regDate : ${userGameInfo.regDate }</h3>
    <h3>Game record ID: ${userGameInfo.record_id}</h3>
    <h3>Win: ${userGameInfo.win}</h3>
    <h3>Lose: ${userGameInfo.lose}</h3>
    <h3>Draw: ${userGameInfo.draw}</h3>
    <h3>Last play date: ${userGameInfo.play_date}</h3>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

 <link rel='stylesheet' href='stylesforform.css'/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<!-- jQuery -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<!-- common CSS -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/common.css">





<title>MH</title>
</head>
<body>

<form method="post" role = "form" style="text-align: center;">

<div id = "sform">
		아이디:<input type="text" name="id" placeholder = "ID"><br/>
		비밀번호:<input type="password" name="pw" placeholder = "password"><br/>
		닉네임:<input type="text" name="name" placeholder = "nickname"><br/>
		이메일:<input type="email" name = "email" placeholder = "example@email.mail"><br/>
		
		<input type="submit" value="가입">
</div>		

	</form>

    </body>
</html>
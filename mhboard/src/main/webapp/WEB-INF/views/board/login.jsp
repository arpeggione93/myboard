<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">


<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<!-- jQuery -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<!-- common CSS -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/login.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

 <style>
        .login {
            width: 500px;
            margin: 200px auto auto auto;
            text-align: center;
}



input[type=password] {
  background-color: #f6f6f6;
  border: none;
  color: #0d0d0d;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 10px;
  width: 85%;
  border: 2px solid #f6f6f6;
  -webkit-transition: all 0.5s ease-in-out;
  -moz-transition: all 0.5s ease-in-out;
  -ms-transition: all 0.5s ease-in-out;
  -o-transition: all 0.5s ease-in-out;
  transition: all 0.5s ease-in-out;
  -webkit-border-radius: 5px 5px 5px 5px;
  border-radius: 5px 5px 5px 5px;
}


.button.test {
    background-color: #56baed;
    border: none;
    color: white;
    padding: 15px 80px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    text-transform: uppercase;
    font-size: 13px;
    -webkit-box-shadow: 0 10px 30px 0 rgb(95 186 233 / 40%);
    box-shadow: 0 10px 30px 0 rgb(95 186 233 / 40%);
    -webkit-border-radius: 5px 5px 5px 5px;
    border-radius: 5px 5px 5px 5px;
    margin: 5px 20px 40px 20px;
    -webkit-transition: all 0.3s ease-in-out;
    -moz-transition: all 0.3s ease-in-out;
    -ms-transition: all 0.3s ease-in-out;
    -o-transition: all 0.3s ease-in-out;
    transition: all 0.3s ease-in-out;
}

.fadeIn.fourth {
    -webkit-animation-delay: 1.0s;
    -moz-animation-delay: 1.0s;
    animation-delay: 1.0s;
}



    </style>



<title>MH</title>
</head>
<body>


<!-- ???????????? ????????? ????????? ???????????? -->
<script type="text/javascript">

var msg = '${msg1}';

if(!msg){
	
	<%-- ??????????????? --%>
	

}else{
	
	alert('${msg1}'); 
	
}

</script>





	<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <img src="${pageContext.request.contextPath}/resources/img/loginlogo.png" id="icon" alt="User Icon" />
    </div>

    <!-- Login Form -->
    <form method = "post">
    <br>
    <br>
      <input type="text" id="memberId" class="form-control fadeIn second" name="memberId" placeholder="?????????">
<br>

      <input type="password" id="memberPw" class="form-control fadeIn second" name="memberPw" placeholder="????????????"><p></p>
<br>

      <input type="submit" class="fadeIn third" value="Sign In">
      <c:if test="${chk eq 0}">
      <button type ="button" class="fadeIn fourth button test" onclick="location.href='${pageContext.request.contextPath}/board/regist';">Sign Up</button>
    	</c:if>
    </form>

    <!-- Remind Passowrd -->
<!--  
    <div id="formFooter">
      <a class="underlineHover" href="#">Forgot Password?</a>
    </div>
-->



  </div>
  
  
<!--   <div>
<button value = "???????????? ?????????">???????????? ?????????</button>
</div> -->
  
</div>





</body>
</html>
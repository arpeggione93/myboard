
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<style>

.bg-dark11 {
    background-color: #000000;
}

</style>


<!-- jQuery -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>



<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">



<!-- common CSS -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/common.css">




<!--메뉴바 추가 부분-->

<nav class="navbar navbar-expand-sm navbar-dark bg-dark11">

<%--   <a class="navbar-brand" href="${pageContext.request.contextPath}/board/readList">MH Blog</a>
 --%>
 
   <a class="navbar-brand" href="${pageContext.request.contextPath}/board/readList"> <img src="${pageContext.request.contextPath}/resources/img/mainlogo.png" id="icon2" alt="User Icon" />
</a>
 
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">

    <span class="navbar-toggler-icon"></span>

  </button>



  <div class="collapse navbar-collapse" id="navbarsExample03">

    <ul class="navbar-nav mr-auto">

      <li class="nav-item active">

        <a class="nav-link" href="${pageContext.request.contextPath}/board/readList">글 목록<span class="sr-only">(current)</span></a>

      </li>

 <!--      <li class="nav-item">

        <a class="nav-link" href="#">Link</a>

      </li> -->

   <%--    <li class="nav-item">

        <a class="nav-link" href="${pageContext.request.contextPath}/">로그인하러가기러기</a>

      </li> --%>

      <!-- <li class="nav-item dropdown">

        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>

        <div class="dropdown-menu" aria-labelledby="dropdown03">

          <a class="dropdown-item" href="#">Action</a>

          <a class="dropdown-item" href="#">Another action</a>

          <a class="dropdown-item" href="#">Something else here</a>
	
		
        </div>

      </li> -->




		
					
	
    </ul>

    <form class="form-inline my-2 my-md-0">

<c:if test="${!empty loginMember}">

	<li class = "nav-item active">				
								
			<font size = 5px color ="grey"><c:out value="${loginMember.nickName}"/></font><font color = white>님 환영합니다.</font>
								
	</li>		
				<li>	
			<button type="button" class ="btn btn-danger float-right" onclick="location.href='${pageContext.request.contextPath}/logout';"> 로그아웃 </button>
				</li>				
						
			<c:if test="${admin eq 'jmh8649'}">
					<li>
					<button type="button" class ="btn btn-danger float-right" onclick="location.href='${pageContext.request.contextPath}/admin';"> 로그아웃 </button>
					</li>	
			</c:if>
							
		</c:if>


				<c:if test="${empty loginMember}">

				<tr>
			<button type="button" class ="btn btn-danger float-right"  onclick="location.href='${pageContext.request.contextPath}/';"> 로그인 </button>
				</tr>
				</c:if>
	




  <!--     <input class="form-control" type="text" placeholder="Search" disabled hidden> -->
    </form>

  </div>

</nav>
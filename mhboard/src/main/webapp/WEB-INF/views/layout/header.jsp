
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<!-- jQuery -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>



<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">



<!-- common CSS -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/common.css">




<!--메뉴바 추가 부분-->

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">

  <a class="navbar-brand" href="${pageContext.request.contextPath}/board/readList">MH Blog</a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">

    <span class="navbar-toggler-icon"></span>

  </button>



  <div class="collapse navbar-collapse" id="navbarsExample03">

    <ul class="navbar-nav mr-auto">

      <li class="nav-item active">

        <a class="nav-link" href="${pageContext.request.contextPath}/board/readList">게시판<span class="sr-only">(current)</span></a>

      </li>

      <li class="nav-item">

        <a class="nav-link" href="#">Link</a>

      </li>

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




		<c:if test="${!empty loginMember}">

	<li class = "nav-item active">				
								<tr>
							<td><a class="nav-link" href="#"><c:out value="${loginMember.memberId}"/>님 환영합니다.</a></td>
								</tr>
			</li>		
				
				<li>	
					
					<button type="button" class ="btn btn-danger float-right" onclick="location.href='${pageContext.request.contextPath}/logout';"> 로그아웃 </button>
							
							
			</li>				
							
					</c:if>
					
					<li class = "nav-item active">
					<c:if test="${empty loginMember}">

					<tr>
							<td><button class ="btn btn-danger float-right" type="button" onclick="location.href='${pageContext.request.contextPath}/';"> 로그인 </button></td>
							</tr>
							</c:if>
						</li>
					

					







    </ul>

    <form class="form-inline my-2 my-md-0">

      <input class="form-control" type="text" placeholder="Search">

    </form>

  </div>

</nav>
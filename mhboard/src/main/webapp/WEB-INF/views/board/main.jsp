<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<!-- jQuery -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>



<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">



<!-- common CSS -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/common.css">






<title>MH</title>

<style>



</style>


</head>

<!-- 검색기능 추가 -->
<c:url var="readListURL" value="/board/readList"></c:url>


<script>

	$(document).on('click', '#btnWriteForm', function(e){
		e.preventDefault();
		location.href = "${pageContext.request.contextPath}/board/write";
	});

	
	
	
	
	
	function fn_contentView(bid){
		var url = "${pageContext.request.contextPath}/board/readContent";
		url = url + "?bid="+bid;
		location.href = url;
	}
	
	
	
</script>


<!-- 페이징처리 -->
<script>

//이전 버튼 이벤트

function fn_prev(page, range, contentSize) {

		var page = ((range - 2) * contentSize) + 1;

		var range = range - 1;

		var url = "${pageContext.request.contextPath}/board/readList";

		url = url + "?page=" + page;

		url = url + "&range=" + range;

		location.href = url;

	}



  //페이지 번호 클릭

	function fn_paging(page, range, contentSize, searchType, keyword) {

		var url = "${pageContext.request.contextPath}/board/readList";

		url = url + "?page=" + page;

		url = url + "&range=" + range;

		location.href = url;	

	}



	//다음 버튼 이벤트

	function fn_next(page, range, contentSize) {

		var page = parseInt((range * contentSize)) + 1;

		var range = parseInt(range) + 1;

		var url = "${pageContext.request.contextPath}/board/readList";

		url = url + "?page=" + page;

		url = url + "&range=" + range;

		location.href = url;

	}
	
	
	$(document).on('click', '#btnSearch', function(e){

		e.preventDefault();

		var url = "${pageContext.request.contextPath}/board/readList";

		url = url + "?searchType=" + $('#searchType').val();

		url = url + "&keyword=" + $('#keyword').val();

		location.href = url;

		console.log(url);

	});	
	
	
	

</script>

<!-- 여기까지 페이징처리 -->



<body>

<article>
<div class="container-fluid">

<div class="table-responsive">

<table class="table table-hover">



<br>
<h2>개인 블로그</h2>

		<colgroup>
			<col style="width:5%;" />
			<col style="width:auto;" />
			<col style="width:15%;" />
			<col style="width:10%;" />
			<col style="width:10%;" />
		</colgroup>
		<thead>
			<tr>
				<th>NO</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>

		</thead>

		<tbody>

			<c:choose>

				<c:when test="${empty boardList }" >

					<tr><td colspan="5" align="center">데이터가 없습니다.</td></tr>

				</c:when> 

				<c:when test="${!empty boardList}">

					<c:forEach var="list" items="${boardList}">
						<tr>
							<td><c:out value="${list.bid}"/></td>
		<td>					
<a href="#" onClick="fn_contentView(<c:out value="${list.bid}"/>)">

<c:out value="${list.title}"/>

</a>
</td>
							<td><c:out value="${list.regId}"/></td>
							<td><c:out value="${list.viewCnt}"/></td>
							<td><c:out value="${list.regDt}"/></td>
						</tr>

					</c:forEach>

				</c:when>

			</c:choose>

		</tbody>

	</table>


<!-- 글쓰기 버튼 -->

<!-- 여기까지 버튼 -->


<!-- 페이징처리 버튼 -->


<!-- paging{start} -->

	

<div class="container-fluid">

<br>
<div id ="container-fluid">
	<div id="paginationBox">
		<ul class="pagination justify-content-center">
		
			<c:if test="${paging.prev}">
				<li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${paging.page}', '${paging.range}', '${paging.contentSize}')">Previous</a></li>
			</c:if>

			<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="idx">

				<li class="page-item <c:out value="${paging.page == idx ? 'active' : ''}"/> "><a class="page-link" href="#" onClick="fn_paging('${idx}', '${paging.range}', '${paging.contentSize}')"> ${idx} </a></li>

			</c:forEach>

			<c:if test="${paging.next}">

				<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${paging.range}', 

'${paging.range}', '${paging.contentSize}')" >Next</a></li>

			</c:if>

		</ul>

	</div>
	
	
	</div>


	<!-- paging{end} -->

<c:if test="${!empty loginMember}">

	<button type="button" class="btn btn-sm btn-primary" id="btnWriteForm">글쓰기</button>
						
</c:if>

		
		
		

<!-- search{s} -->

		<div class="form-group row justify-content-center">

			<div class="w75" style="padding-right:10px">

				<select class="form-control form-control-sm" name="searchType" id="searchType">

					<option value="title">제목</option>

					<option value="content">본문</option>

					<option value="reg_id">작성자</option>

				</select>

			</div>

			<div class="w300" style="padding-right:10px">

				<input type="text" class="form-control form-control-sm" name="keyword" id="keyword">

			</div>

			<div>

				<button class="btn btn-sm btn-primary" name="btnSearch" id="btnSearch">검색</button>

			</div>

		</div>
</div>
		<!-- search{e} -->










<!-- 여기까지 페이징처리 버튼 -->


</div>


</div>

</article>



</body>

</html>
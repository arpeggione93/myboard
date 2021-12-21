<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ include file="/WEB-INF/views/layout/header.jsp"%>



<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">



<title>board</title>



<script>

	$(document).on('click', '#btnList', function(){

		location.href = "${pageContext.request.contextPath}/board/readList";

	});
	

	<%--수정 버튼 클릭 이벤트  --%>

	$(document).on('click', '#btnUpdate', function(){

		var url = "${pageContext.request.contextPath}/board/update";

		url = url + "?bid="+${Content.bid};

		location.href = url;

	});
	
	

	<%--삭제 버튼 클릭 이벤트--%>

	$(document).on('click', '#commentDeleteBtn', function(){

    var url = "${pageContext.request.contextPath}/board/deleteComment";

   	    url = url + "?bid=" + ${Content.bid};
	
		url = url + "&cid="+$(this).attr("data-cid");
    
    
		location.href = url;

	});
	
	
	
	
	<%-- 댓글 수정 이벤트 --%>
	$(document).on('click', '#commentUpdateBtn', function(){
	
		
		var url = "${pageContext.request.contextPath}/board/updateComment";
		
			url = url + "?bid=" + ${Content.bid};
			
			url = url + "&cid="+$(this).attr("data-cid");
		
		location.href = url;
		
	});
	
	<%-- 댓글 작성 이벤트 --%>
	<%-- 
	$(document).on('click', '#commentWriteBtn', function(){
	
		var url = "${pageContext.request.contextPath}/board/writeComment";
		
		location.href = url;
		
	}); --%>
	
	
	
</script>



</head>

<body>
<% request.setCharacterEncoding("UTF-8"); %>

	<article>

		<div class="container" role="main">

			<h2>게시글 상세내용</h2>

			

			<div class="bg-white rounded shadow-sm">

				<div class="board_title"><c:out value="${Content.title}"/></div>

				<div class="board_info_box">

					<span class="board_author"><c:out value="${Content.regId}"/>,</span><span class="board_date"><c:out value="${Content.regDt}"/></span>

				</div>

				<div class="board_content">${Content.content}</div>

				<div class="board_tag">TAG : <c:out value="${Content.tag}"/></div>

			</div>

			

			<div style="margin-top : 20px">

				<button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>

				<button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>

				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>

			</div>



	<!-- 댓글 창 추가 -->


	<div id = "comment">
	
		<ol class = "commentList">
		
			<c:forEach items = "${commentVO }" var = "commentVO">
			
				<li>
				
					<p>
					작성자 : ${commentVO.regId }<br/>
					작성일 : ${commentVO.regDt }<br/>
					</p>
					
					<p>작성 내용 : ${commentVO.content }</p>
					
					<div>
						<button type="button" class = "commentUpdateBtn" id = "commentUpdateBtn" data-cid ="${commentVO.cid }">수정</button>
						<button type="button" class = "commentDeleteBtn" id = "commentDeleteBtn" data-cid ="${commentVO.cid }">삭제</button>
						
					</div>
					
					</li>
			
			
			</c:forEach>
		
		
		</ol>
	
	
	</div>
	
	
	<!-- 댓글창 추가 여기까지 -->




	<!-- 댓글 작성창 추가 -->
<div class="container">
	<div class="form-group">
		<form method="post" action = " ${pageContext.request.contextPath}/board/writeComment ">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td style="border-bottom:none;" valign="middle"><br></td>
					<td><input type="hidden" name="bid" value="${Content.bid}" readonly="readonly"/></td>
					
					<td><div class="mb-3">

					<textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요" ></textarea>

				</div>
					</td>
					<td><input type="text" style="height:20px, weidth: 20px" class="form-control" placeholder="작성자" name = "regId"></td>
					<td><br><br><input type="submit" class = "commentWriteBtn" id = "commentWriteBtn" value = "댓글등록"></input>
						</td>
				</tr>
				<tr>
					<td colspan="3"><input type="file" name="fileName"></td>
				</tr>
			</table>
		</form>
	</div>
</div>

	<!-- 댓글 작성창 여기까지 -->


		</div>

		

	</article>

</body>

</html>
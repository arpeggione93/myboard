<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<html>
	<head>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>게시판</title>
	</head>
	<script type="text/javascript">
		
		
		
			<%-- 댓글 수정 취소 이벤트 --%>
	$(document).on('click', '#commentCancelBtn', function(){
	
		
		var url = "${pageContext.request.contextPath}/board/readContent";
		
			url = url + "?bid=" + ${deleteComment.bid};
			
		location.href = url;
		
	});
		
		
		
		
		
		
	</script>
	<body>
	
		<div id="root">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
	
			<hr />
			
			<section id="container">
				<form name="updateForm" role="form" method="post">
					<input type="hidden" name="bid" value="${deleteComment.bid}" readonly="readonly"/>
					<input type="hidden" id="cid" name="cid" value="${deleteComment.cid}" />
						<table>
						<tbody>
							<tr>
								<td>
									<label for="content">댓글 내용</label><input type="text" id="content" name="content" value="${deleteComment.content}"/>
								</td>
							</tr>	
							
						</tbody>			
					</table>
					<div>
						<p>삭제 하시겠습니까?</p>
						<button type="submit" class="commentDeleteBtn" id = commentDeleteBtn>예 삭제합니다.</button>
						<button type="button" class="commentCancelBtn" id = commentCancelBtn>아니오. 삭제하지 않습니다.</button>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	 	<title>댓글수정</title>
	</head>
	<script type="text/javascript">
	
		
		<%-- 댓글 수정 취소 이벤트 --%>
	$(document).on('click', '#commentCancelBtn', function(){
	
		
		var url = "${pageContext.request.contextPath}/board/readContent";
		
			url = url + "?bid=" + ${updateComment.bid};
			
		location.href = url;
		
	});
		
		
		
		
		
		
		
		
		
	</script>
	<body>
	
		<div id="root">
			<header>
				<h1>댓글수정중</h1>
			</header>
			<hr />
			 
		
			<hr />
			
			<section id="container">
				<form name="updateForm" role="form" method="post">
					<input type="hidden" name="bid" value="${updateComment.bid}" readonly="readonly"/>
					<input type="hidden" id="cid" name="cid" value="${updateComment.cid}" />
					<table>
						<tbody>
							<tr>
								<td>
									<label for="content">댓글 내용</label><input type="text" id="content" name="content" value="${updateComment.content}"/>
								</td>
							</tr>	
							
						</tbody>			
					</table>
					<div>
						<button type="submit" class="commentUpdateBtn" id = "commentUpdateBtn">수정</button>
						<button type="button" class="commentCancelBtn" id = "commentCancelBtn">취소</button>
					</div>
				</form>
			</section>
			<hr />
		</div>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form" %> 


<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<!-- 위지윅 에디터 추가 -->
<!-- <script src="https://cdn.ckeditor.com/ckeditor5/31.1.0/classic/ckeditor.js"></script>
 <script>
 var ckeditor_config = {
		 
		 resize_enable : false,
		 entrerMode : CKEDITOR.ENTER_BR,
		 shiftEnterMode : CKEDITOR.ENTER_P,
		 filebrowserUploadURL : "/common/ckUpload"
		 
 };
 
 </script> -->
 
 
 <!-- jQuery -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>


<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">


<!-- 위지윅 editor -->

<script src="/resources/ckeditor/ckeditor.js"></script>

<title>board</title>

<script>

$(document).ready(function(){
	
	fn_addFile();
})

	$(document).on('click', '#btnSave', function(e){
		e.preventDefault();
		$("#form").submit();
		fn_addFile();
	});

	$(document).on('click', '#btnList', function(e){
		e.preventDefault();

		location.href="${pageContext.request.contextPath}/board/readList";

	});
	
	</script>
	
	
<script type="text/javascript">
function fn_addFile(){
	var fileIndex = 1;
	$(".fileAdd_btn").on("click", function(){
		$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button type='button' style='float:right;' id='fileDelBtn'>"+"삭제"+"</button></div>");
	});
	$(document).on("click","#fileDelBtn", function(){
		$(this).parent().remove();
		
	});
}

</script>

<style>

body {

  padding-top: 0px;

  padding-bottom: 10px;

}



</style>

</head>

<body>

	<article>

		<div class="container" role="main">

			<h2>게시글 작성</h2>

			<form name="form" id="form" role="form" method="post" enctype = "multipart/form-data" action="${pageContext.request.contextPath}/board/write">

				<div class="mb-3">

					<label for="title">제목</label>

					<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">

				</div>

				

				<div class="mb-3">

					<label for="reg_id">작성자(닉네임)</label>

					<input type="text" class="form-control" name="regId" id="regId" value =<c:out value="${loginMember.nickName}"/> readonly>

				</div>

				

				<div class="mb-3" contentEditable="true">

					<label for="content">내용</label>

					<textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요" ></textarea>
				<textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요" ></textarea>
					<script>
 					var ckeditor_config = {
  							 resize_enaleb : false,
  						 enterMode : CKEDITOR.ENTER_BR,
   							shiftEnterMode : CKEDITOR.ENTER_P,
  								 filebrowserUploadUrl : "/admin/goods/ckUpload"
 							};
 
					 CKEDITOR.replace("content", ckeditor_config);
					</script>

				</div>

				

				<div class="mb-3">

					<label for="tag">TAG</label>

					<input type="text" class="form-control" name="tag" id="tag" placeholder="태그를 입력해 주세요">

				</div>

			<table> 
			<tr>
			<td id ="fileIndex"></td>
			</tr>
			
			</table>
			
			
			<div>
			<br>
			<button class="fileAdd_btn btn-sm btn-primary" type="button">파일추가</button>	
			<br>
			</div>
			<!-- <input type="file" name="file"/>
			 -->

			</form>

			<div >

				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>

				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>

			</div>

		</div>

	</article>
	
<script src="${pageContext.request.contextPath}/resources/common/js/ckeditor.js"></script>

</body>

</html>



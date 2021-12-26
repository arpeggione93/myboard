<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<%@ taglib prefix="form" uri = "http://www.springframework.org/tags/form" %> 


<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<!-- 위지윅에디터 추가중 -->
<script src = "${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>


<!-- jQuery -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>


<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<title>board</title>

<script>

$(document).ready(function(){
	
	
	fn_addFile();
	
	
	})


<%-- 수정하기 버튼을 눌렀을 때, 입력 'form'을 전송해주는 버튼임--%>
	$(document).on('click', '#btnUpdate', function(e){
		e.preventDefault();
		$("#form").submit();
	});

	
	$(document).on('click', '#btnList', function(e){
		e.preventDefault();

		location.href="${pageContext.request.contextPath}/board/readList";

	});
	
	
	
	<%-- 다중 파일 수정 기능 구현중 --%>
	function fn_addFile(){
		var fileIndex = 1;
		$(".fileAdd_btn").on("click", function(){
			$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button type='button' style='float:right;' id='fileDelBtn'>"+"삭제"+"</button></div>");
		});
		$(document).on("click","#fileDelBtn", function(){
			$(this).parent().remove();
			
		});
	}
		var fileNoArry = new Array();
		var fileNameArry = new Array();
		
		function fn_del(value, name){
			
			fileNoArry.push(value);
			fileNameArry.push(name);
			$("#fileNoDel").attr("value", fileNoArry);
			$("#fileNameDel").attr("value", fileNameArry);
		}
	
	


</script>

<style>

body {

  padding-top: 0px;

  padding-bottom: 30px;

}



</style>

</head>

<body>

	<article>

		<div class="container" role="main">

			<h2>게시글 수정</h2>

			<form name="form" id="form"  enctype = "multipart/form-data" role="form" method="post" action="${pageContext.request.contextPath}/board/update">
				
				<div class="mb-3">
					<input type="hidden" class="form-control" name="bid" id="bid" value = "${Content.bid}"  readonly>
					
				</div>


				<div class="mb-3">
					<label for="title">제목</label>
					<input type="text" class="form-control" name="title" id="title" value = "${Content.title }">
				</div>

				

				<div class="mb-3">
					<label for="regId">작성자</label>
					<input type="text" class="form-control" name="regId" id="regId" value = "${Content.regId}" readonly>
				</div>

				
		<label>내용</label>
				<div class="mb-3">
					
					<textarea class="form-control" rows="5" name="content" id="content">${Content.content}</textarea>
				<script>

 				CKEDITOR.replace("content");
				</script>
				</div>

				
				<div class="mb-3">
					<label for="tag">TAG</label>
					<input type="text" class="form-control" name="tag" id="tag" value = "${Content.tag}">
				</div>
			
			<input type="hidden" id="fileNoDel" name="fileNoDel[]" value=""> 
										<input type="hidden" id="fileNameDel" name="fileNameDel[]" value=""> 
					

			

		<table>
		
		<tr>
					<td id="fileIndex">
					<c:if test="${not empty file}">
									<c:forEach var="file" items="${file}" varStatus="var">
									<div>
										
										<input type="hidden" id="FILE_NO" name="FILE_NO_${var.index}" value="${file.fid}">
										<input type="hidden" id="FILE_NAME" name="FILE_NAME" value="FILE_NO_${var.index}">
										<a href="#" id="fileName" onclick="return false;">${file.org_file_name}</a>(${file.file_size}kb)
										<button id="fileDel" onclick="fn_del('${file.fid}','FILE_NO_${var.index}');" type="button">삭제</button><br>
									</div>
									</c:forEach>
								</c:if>
								</td>
							</tr>
		
		</table>

</form>
	<div>
	
	<button type="button" class="fileAdd_btn btn-sm btn-primary">파일추가</button>
					<br>
					<br>
	</div>

			<div >

				<button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정하기</button>

				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>

			</div>

		</div>

	</article>

</body>

</html>



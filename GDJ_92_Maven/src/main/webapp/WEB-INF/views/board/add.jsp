<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>home</title>
		
		<c:import url="/WEB-INF/views/include/head_css.jsp"></c:import>
	</head>
	
	<body id="page-top">
		<div id="wrapper">
			<c:import url="/WEB-INF/views/include/sidebar.jsp"></c:import>
			<div id="content-wrapper" class="d-flex flex-column">
				<div id="content">
                	<c:import url="/WEB-INF/views/include/topbar.jsp"></c:import>
                	<div class="container-fluid">
                		<!-- page contents 내용 시작 -->
                		<h1>Notice Add</h1>
                		<div class="card shadow mb-4">
                			<div class="card-body">
								<form method="post" enctype="multipart/form-data">
	                			<!-- action 안 쓰면 현재 위치와 동일한 경로로 감 -->
	                				<input type="hidden" name="boardNum" value="${vo.boardNum}">
									<div class="mb-3">
										<label for="boardWriter" class="form-label">Writer</label>
										<input type="text" class="form-control" id="boardTitle" name="boardWriter"  value="${vo.boardWriter}">
									</div>
									<div class="mb-3">
										<label for="boardTitle" class="form-label">Title</label>
										<input type="text" class="form-control" id="boardTitle" name="boardTitle" value="${vo.boardTitle}">
									</div>
									<div class="mb-3">
										<label for="boardContents" class="form-label">Contents</label>
										<textarea class="form-control" id="boardContents" rows="10" name="boardContents">${vo.boardContents}</textarea>
									</div>

									<div>
										<button class="btn btn-primary mb-3" type="button" id="add">ADD</button>
									</div>
									
									<div class="mb-3">
										<c:forEach items="${vo.boardFileVOs}" var="f">
											<button class="deleteFile mb-1 btn" type="button" data-file-num = "${f.fileNum}">${f.oriName}</button>
										</c:forEach>
									</div>
									
									<!-- ${fn:length(vo.boardFileVOs)} -->
									<div id="result" data-file-count="${vo.boardFileVOs.size()}">
										<%-- <div class="mb-3">
											<input type="file" name="attaches">
										</div> --%>
									</div>
									
									<button type="submit" class="btn btn-primary">Submit</button>
								</form>
                			</div>
                		</div>
					<!-- page contents 내용 끝 -->
                	</div>
				</div>
				<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
		<script type="text/javascript" src="/js/board/board_add.js"></script>
	</body>
</html>
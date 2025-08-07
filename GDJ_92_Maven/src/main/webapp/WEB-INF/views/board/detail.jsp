<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
				  		<div class="col-md-8 offset-md-2">
					  		<h1 style="text-transform: capitalize">${board} Detail</h1>
					  		<div class="card shadow mb-4">
					  			<div class="card-header py-3">
					  				<h5 class="m-0 font-weight-bold text-primary">제목: ${vo.boardTitle}</h5>
					  			</div>

					  			<div class="card-body">
									<span>조회수: ${vo.boardHit}</span>
									<span> | </span>
									<span>작성일: ${vo.boardDate}</span>
									<p>작성자: ${vo.boardWriter}</p>
									<hr>
									<p>${vo.boardContents}</p>
									<hr>
									<div>
										<c:if test="">
							  				<b>첨부파일</b>
										</c:if>
						  				<c:forEach items="${vo.boardFileVOs}" var="f">
							  				<p><a href="/files/${board}/${f.saveName}">${f.oriName}</a></p>
							  				<p>${f.saveName}</p>
						  				</c:forEach>
						  			</div>
								</div>
					  		</div>

					  		<div>
					  			<form id="frm">
					  				<input type="hidden" name="boardNum" value="${vo.boardNum}">
					  			</form>
					  			
					  			<button class="btn btn-success action" data-kind="u">Update</button>
					  			<button class="btn btn-danger action" data-kind="d">Delete</button>
					  			<c:if test="${board ne 'notice'}">
					  				<button class="btn btn-primary action" data-kind="r">Reply</button>
					  			</c:if>
					  		</div>
				  		</div>
				  		<!-- page contents 내용 끝 -->
				  	</div>
				</div>
				<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
		
		<script type="text/javascript" src="/js/board/board_detail.js"></script>
	</body>
</html>
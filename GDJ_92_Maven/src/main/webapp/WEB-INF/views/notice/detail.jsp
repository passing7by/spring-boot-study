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
                		<h1>detail</h1>
                		<div class="card shadow mb-4">
                			<div class="card-header py-3">
                				<h6 class="m-0 font-weight-bold text-primary">${NoticeVO.boardTitle}</h6>
                			</div>
                			<div class="card-body">
								<p>${NoticeVO.boardNum}</p>
								<p>${NoticeVO.boardContents}</p>
								<p>${NoticeVO.boardWriter}</p>
								<p>${NoticeVO.boardDate}</p>
								<p>${NoticeVO.boardHit}</p>
							</div>
                		</div>
                		<!-- page contents 내용 끝 -->
                	</div>
				</div>
				<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
	</body>
</html>
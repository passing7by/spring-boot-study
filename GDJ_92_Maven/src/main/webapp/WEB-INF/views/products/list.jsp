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
                		<h1>Products List</h1>
                		<div class="row col-md-8 offset-md-2">
	                		<table class="table table-hover">
	                			<thead>
	                				<tr>
	                					<th>NUM</th>
	                					<th>NAME</th>
	                					<th>DATE</th>
	                					<th>RATE</th>
	                					<th>KIND_NUM</th>
	                				</tr>
	                			</thead>
	                			<tbody>
	                				<c:forEach items="${list}" var="ProductVO">
		                				<tr>
		                					<td>${ProductVO.productNum}</td>
		                					<td><a href="./detail?productNum=${ProductVO.productNum}">${ProductVO.productName}</a></td>
		                					<td>${ProductVO.productDate}</td>
		                					<td>${ProductVO.productRate}</td>
		                					<td>${ProductVO.kindNum}</td>
		                				</tr>
	                				</c:forEach>
	                			</tbody>
	                		</table>
	                		
	                		<div>
	                			<a class="btn btn-outline-success" href="./add" role="button">글쓰기</a>
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
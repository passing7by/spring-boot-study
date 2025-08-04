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
                		<h1>Product Form</h1>
                		
                		<div class="card shadow mb-4">
                			<div class="card-body">
								<form method="post">
	                			<!-- action 안 쓰면 현재 위치와 동일한 경로로 감 -->
	                				<input type="hidden" name="productNum" value="${vo.productNum}">
									
									<div class="mb-3">
										<label for="kindNum" class="form-label">kindName</label>
										<select class="form-control" id="kindNum" name="kindNum">
											<option value="1">예금</option>
											<option value="2">적금</option>
											<option value="3">대출</option>
										</select>
									</div>
									<div class="mb-3">
										<label for="productName" class="form-label">ProductName</label>
										<input type="text" class="form-control" id="productName" name="productName"  value="${vo.productName}">
									</div>
									<div class="mb-3">
										<label for="productRate" class="form-label">Rate</label>
										<input type="number" class="form-control" id="productRate" name="productRate" value="${vo.productRate}">
									</div>
									<div class="mb-3">
										<label for="productContents" class="form-label">Contents</label>
										<textarea class="form-control" id="productContents" rows="10" name="productContents">${vo.productContents}</textarea>
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
	</body>
</html>
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
	                		<h1 style="text-transform: capitalize">${board} List</h1>
	                		
	                		<div>
	                			<form method="get" id="searchForm">
									<div class="input-group mb-3">
										<input type="hidden" id="pageNum" name="pageNum">
										<select class="form-select" name="kind" id="inputGroupSelect02">
											<option value="k1" ${pager.kind eq 'k1' ? 'selected' : ''}>Title</option>
											<option value="k2" ${pager.kind eq 'k2' ? 'selected' : ''}>Contents</option>
											<option value="k3" ${pager.kind eq 'k3' ? 'selected' : ''}>Writer</option>
										</select>
										<input type="text" class="form-control" value="${pager.keyword}" name="keyword">
										<button class="btn btn-outline-secondary" type="submit" id="button-addon2">Button</button>
									</div>
								</form>
							</div>
	                		
                			<table class="table table-hover">
                				<thead>
                					<tr>
                						<th>Num</th>
                						<th>Title</th>
                						<th>Writer</th>
                						<th>Date</th>
                						<th>Hit</th>
                					</tr>
                				</thead>
                				<tbody>
                					<c:forEach items="${list}" var="vo">
	                					<tr>
	                						<td>${vo.boardNum}</td>
	                						<td>
	                							<!-- Notice 기능에서는 boardDepth가 없어서 예외 발생 => 예외가 발생할 수 있는 태그를 catch로 감싸서 예외처리 -->
	                							<c:catch>
		                							<c:forEach begin="2" end="${vo.boardDepth}">
		                							&nbsp;&nbsp;
		                							</c:forEach>
		                							<c:if test="${vo.boardDepth > 0}">
		                							ㄴ
		                							</c:if>
	                							</c:catch>
	                							<a href="./detail?boardNum=${vo.boardNum}">${vo.boardTitle}</a>
                							</td>
	                						<td>${vo.boardWriter}</td>
	                						<td>${vo.boardDate}</td>
	                						<td>${vo.boardHit}</td>
	                					</tr>
                					</c:forEach>
                				</tbody>
                			</table>
                			
                			<div class="row">
	                			<div>
									<nav aria-label="Page navigation example">
										<ul class="pagination">
											<li class="page-item">
												<a class="page-link pn" data-pn="${pager.startNum - 1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
											</li>
											
											<c:forEach begin="${pager.startNum}" end="${pager.endNum}" var="i">
												<li class="page-item"><a class="page-link pn" data-pn="${i}">${i}</a></li>
											</c:forEach>
											
											<li class="page-item">
												<a class="page-link pn" data-pn="${pager.endNum + 1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
											</li>
										</ul>
									</nav>
								</div>
								
	                			<div>
	                				<a class="btn btn-outline-success" href="./add" role="button">글쓰기</a>
	                			</div>
                			</div>
                		</div>
                		<!-- page contents 내용 끝 -->
                	</div>
				</div>
				<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/include/tail.jsp"></c:import>
		
		<script type="text/javascript" src="/js/board/board_list.js"></script>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                		<h1>User Detail</h1>
                		
                		<div>
                			<!-- 세션에서 꺼내오는 방법 -->
<%-- 	               			<sec:authentication property="principal" var="user"/>
	               			<p>${user.username }</p>
	               			<p>${user.password }</p>
	               			<p>${user.name }</p>
	               			<p>${user.email }</p>
	               			<p>${user.phone }</p>
	               			<p>${user.birth }</p>
	               			<img src="/files/member/${user.profileVO.saveName }"/>
	                		<c:forEach items="${user.roleVOs }" var="role">
	                			<p>${role.roleName }</p>
							</c:forEach>   --%>
							
							<!-- DB에서 조회해오는 방법 -->
							<p>${member.username }</p>
                			<p>${member.password }</p>
                			<p>${member.name }</p>
                			<p>${member.email }</p>
                			<p>${member.phone }</p>
                			<p>${member.birth }</p>
                			<img src="/files/member/${member.profileVO.saveName }"/>
	                 		<c:forEach items="${member.roleVOs }" var="role">
		                		<p>${role.roleName }</p>
							</c:forEach>
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
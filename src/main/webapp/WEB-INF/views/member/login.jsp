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
                		<h1>Login Page</h1>
                		
                		<h3>${param.failMessage }</h3>
                		<form action="/member/login" method="post">
							<div class="mb-3">
								<label for="username" class="form-label">ID</label>
								<input type="text" class="form-control" id="username" name="username" value="${not empty cookie.rememberId.value ? cookie.rememberId.value : '' }">
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">PASSWORD</label>
								<input type="password" class="form-control" id="password" name="password">
							</div>
							<div class="mb-3 form-check">
								<input type="checkbox" class="form-check-input" id="rememberId" 
									name="rememberId" value="1" ${not empty cookie.rememberId.value ? 'checked' : '' }>
								<!-- ${not empty cookie.rememberId.value ? 'checked=true' : 'checked=false' } 는 의도한 결과가 나오지 않음
									(태그에 checked=false 가 있어도 체크되어 있음) -->
								<label for="rememberId" class="form-label">ID 기억하기</label>
							</div>
							<div class="mb-3 form-check">
								<input type="checkbox" class="form-check-input" id="remember-me" 
									name="remember-me" value="1">
								<!-- ${not empty cookie.rememberId.value ? 'checked=true' : 'checked=false' } 는 의도한 결과가 나오지 않음
									(태그에 checked=false 가 있어도 체크되어 있음) -->
								<label for="remember-me" class="form-label">자동 로그인</label>
							</div>
							
							<div>
								<button class="btn btn-primary mb-3" id="login">LOGIN</button>
							</div>
						</form>
						
						<div>
							<a class="btn btn-warning" href="/member/kakaoLogin">카카오로 로그인</a>
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
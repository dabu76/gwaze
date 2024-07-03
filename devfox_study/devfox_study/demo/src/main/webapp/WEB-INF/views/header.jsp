<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jsp태그 라이브러리로 만약 로그인이 되어있을시 아래 문장과 링크를 띄움 -->
<sec:authorize access="isAuthenticated()">
    <p>환영합니다, <sec:authentication property="principal.username" />님!</p>
    <a href="<c:url value='/logout' />">로그아웃</a>
</sec:authorize>

<!-- 로그인이 안되어있을시에는 로그인 링크를 띄움 -->
<sec:authorize access="!isAuthenticated()">
    <a href="<c:url value='/login' />">로그인</a>
</sec:authorize>
	<a href="/">메인</a>
	<a href="/board">게시판</a>
</body>
</html>

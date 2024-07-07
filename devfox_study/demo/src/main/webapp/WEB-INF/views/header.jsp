<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 条件文や反復文などを使用する時に使うタグライブラリc: で始める-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--日付メッセージなどの形式を処理してサポートしてくれるタグライブラリfmt:で始まる-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!--セキュリティ関連機能を実装する際に使用され、ユーザー認証状態の確認、特定権限の有無の確認などを処理するsec:から始まる-->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jspタグライブラリでもしログインされている場合、以下の文章とリンクを表示
sec:authorizeを通じて認証されているかを確認し、もし認証されていたら-->
<sec:authorize access="isAuthenticated()">
    <p>환영합니다, <sec:authentication property="principal.username" />님!</p>
    <a href="<c:url value='/logout' />">로그아웃</a>
</sec:authorize>

<!-- ログインができない場合はログインリンクを表示 
 sec:authorizeを通じて認証されているかを確認し、もし認証されていなければ-->
<sec:authorize access="!isAuthenticated()">
    <a href="<c:url value='/login' />">로그인</a>
</sec:authorize>
	<a href="/">메인</a>
	<a href="/board">게시판</a>
</body>
</html>

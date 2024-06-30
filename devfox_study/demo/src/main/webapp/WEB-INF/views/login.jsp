<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>devfox</title>
</head>
<body>
<!-- 로그인 화면 구현  -->
<h1>login</h1>
<form action="/login" method="post">
	<div>
		<label>ID</label>
		<input type="text" name="userName"></input>
	</div>
	<div>
		<label>PW</label>
		<input type="password" name="password"></input>
	</div>
	<div>
		<button type="submit">로그인</button>
		<button type="button">회원가입</button>
	</div>
</form>
</body>
</html>
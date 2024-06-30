<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>devfox</title>
</head>
<body>
<h1>회원가입</h1>
<form action="/join" method="post">
	<div>
		<label>ID</label>
		<input type="text" name="userName"></input>
	</div>
	<div>
		<label>PW</label>
		<input type="password" name="password"></input>
	</div>
	<div>
		<button type="submit">회원가입</button>
	</div>
</form>
</body>
</html>
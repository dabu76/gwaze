<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<!--修正をする時に使うページ-->
   <div class="container">
	<div class="detail">
		<!-- フォームを作ってsubmitをすれば /modifyにポスト形式で送ってくれる -->
		<form action="/modify" method="post">
			<!--ヒドゥンタイプでpost_idを送って、残りのタイトル内容を入力して
			その後にユーザーネームと一緒に送ってくれる -->
			<input type="hidden" name="post_id" value="${save.post_id}">
			<div>
				<label for="title">제목:</label>
				<input type="text" id="title" name="title" value="${save.title}">
			</div>
			<div>
				<label for="content">내용:</label>
				<textarea id="content" name="content">${save.content}</textarea>
			</div>
			<div>
				<label for="username">작성자:${save.username}</label>
			</div>
			<div>
				<input type="submit" value="수정">
			</div>
		</form>
	</div>
</div>
</body>
</html>

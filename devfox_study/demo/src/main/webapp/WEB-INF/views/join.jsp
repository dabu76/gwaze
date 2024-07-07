<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>devfox</title>
<!-- ユーザーIDやパスワードを入力していないときに表示する通知 -->
<script>
    function check() {
        if (join.userName.value == "") {
            alert("아이디를 입력하세요");
            join.userName.focus();
            return false;
        }
        if (join.password.value == "") {
            alert("비밀번호를 입력");
            join.password.focus();
            return false;
        }
        return true;
    }
</script>
</head>
<body>
<!-- 会員登録ページでformを通じてusernameとpasswordを渡した -->
<h1>회원가입</h1>
<form name="join" action="/join" method="post" onsubmit="return check()">
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
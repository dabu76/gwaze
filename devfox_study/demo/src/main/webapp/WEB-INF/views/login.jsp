<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>devfox</title>
<script>
    function check() {
        if (login.userName.value == "") {
            alert("아이디를 입력하세요");
            login.userName.focus();
            return false;
        }
        if (login.password.value == "") {
            alert("비밀번호를 입력");
            login.password.focus();
            return false;
        }
        return true;
    }
</script>
</head>
<body>
<!-- ログイン画面の実装
フォームを作ってポスト方式で渡して、
submitを先に押した時にはscriptにあるfunction機能を実行してチェックしてから戻ってくる
その後、コントローラーにある/loginに渡す
 -->
<h1>login</h1>
<form name="login" action="/login" method="post" onsubmit="return check()">
	<div>
		<label>ID</label>
		<input type="text" name="userName"></input>
	</div>
	<div>
	<!-- タイプをpasswordにすると画面に出力してくれる時、
	***のように画面に見えなくなる。この時にパスワードが韓国語だと一致しなくなる -->
		<label>PW</label>
		<input type="password" name="password"></input>
	</div>
	<div>
		<button type="submit">로그인</button>
		<!-- 会員登録ボタンを押すとコントローラーにある/joinでページをめくる -->
		<button type="button" onclick="location.href='/join'">회원가입</button>
		
	</div>
</form>
</body>
</html>
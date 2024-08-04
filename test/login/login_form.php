<script>
	function check_input()
	{
    	if (!document.login.id.value) {
        	alert("아이디를 입력하세요");    
        	document.login_form.id.focus();
        	return false;
    	}

    	if (!document.login.pass.value) {
        	alert("비밀번호를 입력하세요");    
        	document.login.pass.focus();
        	return false;
    	}
    	return true;
	}
</script>	
<form name="login" method="post" action="login.php" onsubmit="return check_input()">		       	
    <div class="login_form">
		<h2 class="login_title">로그인</h2>
		<ul>
            <li>
				<span class="col1">아이디</span>
				<span class="col2"><input type="text" name="id" placeholder="아이디"></span>
			</li>	
			<li>			
				<span class="col1">비밀번호</span>
				<span class="col2"><input type="password" name="pass" placeholder="비밀번호"></span>
			</li>
			<li><input type="submit" value="로그인" style="width:60px; float:right;" ></li>
        </ul>	 
	</div>  	
</form>

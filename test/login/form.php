<script>
   function check_input() {
      if (!document.member.id.value) {
          alert("아이디를 입력하세요!");    
          document.member.id.focus();
          return false;
      }
      if (!document.member.pass.value) {
          alert("비밀번호를 입력하세요!");    
          document.member.pass.focus();
          return false;
      }
      if (!document.member.pass_confirm.value) {
          alert("비밀번호확인을 입력하세요!");    
          document.member.pass_confirm.focus();
          return false;
      }
      if (!document.member.name.value) {
          alert("이름을 입력하세요!");    
          document.member.name.focus();
          return false;
      }
      if (!document.member.email.value) {
          alert("이메일 주소를 입력하세요!");    
          document.member.email.focus();
          return false;
      }
      if (document.member.pass.value != document.member.pass_confirm.value) {
          alert("비밀번호가 일치하지 않습니다.\n다시 입력해 주세요!");
          document.member.pass.focus();
          document.member.pass.select();
          return false;
      }
      return true;
   }

   function check_id() {
     window.open("check_id.php?id=" + document.member.id.value,
         "IDcheck",
          "left=700,top=300,width=380,height=160,scrollbars=no,resizable=yes");
   }
</script>
<form name="member" action="insert.php" method="post" onsubmit="return check_input();">
    <div class="join_form">
		<h2>회원 가입</h2>
    	<ul>
            <li>
                <span class="col1">아이디</span>
                <span class="col2"><input type="text" name="id"></span>
                <span class="col3"><button type="button" onclick="check_id()">중복체크</button></span>                    
            </li>
            <li>
                <span class="col1">비밀번호</span>
                <span class="col2"><input type="password" name="pass"></span>               
            </li>
            <li>
                <span class="col1">비밀번호 확인</span>
                <span class="col2"><input type="password" name="pass_confirm"></span>               
            </li>            
            <li>
                <span class="col1">이름</span>
                <span class="col2"><input type="text" name="name"></span>               
            </li>
            <li>
                <span class="col1">이메일</span>
                <span class="col2"><input type="text" name="email"></span>               
            </li>                        
            </ul>   
            
            <ul class="buttons" >
	        <li><input type="submit" value="저장하기" style="width:80px" ></li>
            <li><button type="button" onclick="if (confirm('리셋하시겠습니까?')) { document.member.reset(); document.member.id.focus(); }">취소하기</button></li>

        </ul>
    </div>
</form>
<script>
   function check_input() {
      if(!document.member.id.value){
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
      if (document.member.pass.value != 
            document.member.pass_confirm.value) {
          alert("비밀번호가 일치하지 않습니다.\n다시 입력해 주세요!");
          document.member.pass.focus();
          document.member.pass.select();
          return false;
      }
        return true;
   }
</script>
<?php    
    include "../include/connect.php";
    $sql    = "select * from _mem where id='$userid'";
    $result = mysqli_query($con, $sql);
    $row    = mysqli_fetch_assoc($result);

    $pass = $row["pass"];
    $name = $row["name"];
    $email = $row["email"];

    mysqli_close($con);
?>    
<form name="member" action="modify.php" method="post" onsubmit="return check_input()"> 
    <div class="join_form">
		<h2>정보 수정</h2>
    	<ul>
            <li>
                <span class="col1">아이디</span>
                <span class="col2"><input type="text" name="id" value="<?=$userid?>"></span>                
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
                <span class="col2"><input type="text" name="name" value="<?=$name?>"></span>               
            </li>
            <li>
                <span class="col1">이메일</span>
                <span class="col2"><input type="text" name="email" value="<?=$email?>"></span>               
            </li>                        
        </ul>                       
		<ul class="buttons">
	        <li><input type="submit" value="저장" style="width:80px"></li>
            <li><button type="button" onclick="if (confirm('취소하시겠습니까?')) { document.member.id.value = ''; document.member.pass.value = ''; document.member.pass_confirm.value = ''; document.member.name.value = ''; document.member.email.value = ''; document.member.id.focus(); }">취소하기</button></li>
                <!--위에서 이미 벨류값을 주므로 따로 따로 하나씩다 공백을 줘야함-->    
        </ul>
    </div>   
</form>
<?php
   $id = $_GET["id"];

   if(!$id) {
      echo("아이디를 입력해 주세요!");
   }
   else {
      $con = mysqli_connect("localhost", "user", "12345", "sample");
      $sql = "select * from members where id='$id'";
      $result = mysqli_query($con, $sql);

      $num_record = mysqli_num_rows($result);

      if ($num_record) {
         echo $id." 아이디는 중복됩니다.<br>";
         echo "다른 아이디를 사용해 주세요!<br>";
      }
      else {
         echo $id." 아이디는 사용 가능합니다.<br>";
      }    
      mysqli_close($con);
   }
?>

<?php
    $id   = $_POST["id"];               
    $pass = $_POST["pass"];             
    $name = $_POST["name"];             
    $email  = $_POST["email"];          

    $regist_day = date("Y-m-d (H:i)");  
              
    $con = mysqli_connect("localhost", "user", "12345", "sample");  

	$sql = "insert into members (id, pass, name, email, regist_day, level, point) ";    // 데이터 삽입 명령
	$sql .= "values('$id', '$pass', '$name', '$email', '$regist_day', 9, 0)";       

	mysqli_query($con, $sql);       
    mysqli_close($con);     
?>
<?php
    $id   = $_POST["id"];               
    $pass = $_POST["pass"];             
    $name = $_POST["name"];             
    $email  = $_POST["email"];          
    $regist_day = date("Y-m-d (H:i)");  
    $hashed_password = password_hash($pass,PASSWORD_DEFAULT);
    include "../include/connect.php";
    $sql = "select * from _mem where id='$id'";
    $result = mysqli_query($con, $sql);
    $num_record = mysqli_num_rows($result);

	if ($num_record) {
		echo "<script>
				alert('이미 사용하고 있는 아이디입니다');
                location.href = 'form.php';
				</script>
		";
		exit;
	}
    
	$sql = "insert into _mem (id, pass, name, email, regist_day, level, point) ";    
	$sql .= "values('$id', '$hashed_password', '$name', '$email', '$regist_day', 9, 100)";
	mysqli_query($con, $sql);       

    mysqli_close($con);     
    echo "<script>
	          location.href = 'selecttype.php?type=login_form';
	      </script>";
?>
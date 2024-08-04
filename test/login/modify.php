<?php
    $id = $_post["id"];
    $pass = $_POST["pass"];
    $name = $_POST["name"];
    $email = $_POST["email"];
    $hashed_password = password_hash($pass,PASSWORD_DEFAULT);
          
    include "../include/connect.php";

    $sql = "update _mem set id='$id',pass='$hashed_password', name='$name', email='$email'";
    $sql .= " where id='$id'";
    mysqli_query($con, $sql);

    mysqli_close($con);     

    echo "
	      <script>
	          location.href = '../main/index.php';
	      </script>
	  ";
?>
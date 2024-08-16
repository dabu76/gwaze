<?php
include "../include/connect.php";
include "../include/header.php";

if (!$userid) {
    echo "
            <script>
            alert('로그인 후 이용해 주세요!');
            location.href='../login/selecttype.php?type=login_form'
            </script>";
    exit;
}
 $type = $_GET["type"];
 $num = $_GET["num"];
 $type_ripple = $type."_ripple";
 $ripple_content = $_POST["ripple_content"];
    $ripple_content = htmlspecialchars($ripple_content, ENT_QUOTES);
    $regist_day = date("Y-m-d (H:i)");  
    $sql = "insert into $type_ripple (parent, id, name, content, regist_day) ";
    $sql .= "values($num, '$userid', '$username', '$ripple_content', '$regist_day')";
    mysqli_query($con, $sql);  
	mysqli_close($con);       
	echo "<script>
	    location.href = 'view.php?type=$type&num=$num';
	   </script>";


 ?>
 
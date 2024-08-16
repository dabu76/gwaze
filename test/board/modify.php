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

    $subject = $_POST["subject"];
    $content = $_POST["content"];

	$subject = htmlspecialchars($subject, ENT_QUOTES);
	$content = htmlspecialchars($content, ENT_QUOTES);

    $is_html = isset($_POST["is_html"]) ? $_POST["is_html"] : '';  
	$regist_day = date("Y-m-d (H:i)");  

	$sql = "update $type set subject='$subject', is_html='$is_html', ";
	$sql .= "content='$content', regist_day='$regist_day' where num=$num";

	mysqli_query($con, $sql);  

	mysqli_close($con);       

	echo "
	   <script>
	    location.href = 'selectboard.php?type=$type&num=$num';
	   </script>
	";
?>
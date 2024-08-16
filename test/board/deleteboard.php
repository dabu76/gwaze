<?php
    include "../include/header.php";
    include "../include/connect.php";
    $type = $_GET["type"];
    $num = $_GET["num"];
    $sql = "delete from `$type` where num=$num";
    mysqli_query($con, $sql);  
	mysqli_close($con);       
	echo "<script>
	    location.href = 'selectboard.php?type=$type';
	   </script>";
?>

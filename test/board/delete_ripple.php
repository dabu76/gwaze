<?php
    include "../include/header.php";
    include "../include/connect.php";
    $type = $_GET["type"];
    $num = $_GET["num"];
    $ripple_num   = $_GET["ripple_num"];
    $ripple = $type."_ripple";
    $sql = "delete from `$ripple` where num=$ripple_num";
    mysqli_query($con, $sql);  
	mysqli_close($con);       
	echo "<script>
	    location.href = 'view.php?type=$type&num=$num';
	   </script>";
?>

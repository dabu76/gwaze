<?php
    include "../include/header.php";
    include "../include/connect.php";
    $type = $_GET["type"];
    $num = $_GET["num"];
    $sql1 = "DELETE FROM reports WHERE num = '$num'";
    mysqli_query($con, $sql1); 
    $sql2 = "DELETE FROM _youtube WHERE num = '$num'";
    mysqli_query($con, $sql2);
	mysqli_close($con);       
	echo "<script>
        alert('삭제가 완료되었습니다');
	    location.href = 'selectboard.php?type=$type';
	   </script>";
?>

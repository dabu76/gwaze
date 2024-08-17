<?php
include "../include/header.php";
include "../include/connect.php";
$reportid = $_SESSION["userid"]; 
$type = $_GET["type"];
$id = $_GET["id"];
$num = $_GET["num"];
$count = $_GET["count"] +1;
$sql = "select * from reports where reported_id='$reportid' and num= $num";

$result = mysqli_query($con,$sql);
if(!isset($result)){
$sql2 = "INSERT INTO reports (report_id, count, type_report, num, reported_id) VALUES ('$id', $count, '$type', $num, '$reportid')";
echo "<script>
        alert('신고가 완료되었습니다');
        history.go(-1)
</script>";
mysqli_query($con,$sql2);
}else{
        echo "<script>
                alert('이미 신고하셨습니다');
                        history.go(-1)

                </script>";
}

mysqli_close($con);

?>





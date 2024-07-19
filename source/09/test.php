<?php
    $servername = "localhost";
    $username = "user";
    $password = "12345";
    $dbname = "sample";

    $conn = mysqli_connect($servername,$username,$password,$dbname);

    $sql = "update friend set name = 7 where name = dd"
    $result = mysqli_query($conn,$result);
    if($result){
        echo "수정완료";
    }else{
        echo "수정실패".mysqli_error($result);
    }

    ?>

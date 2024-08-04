<?php
    $id   = $_POST["id"];
    $pass = $_POST["pass"];
    $hashed_password = password_hash($pass,PASSWORD_DEFAULT);
    include "../include/connect.php";
    $sql = "select * from _mem where id='$id'";
    $result = mysqli_query($con, $sql);

    $num_match = mysqli_num_rows($result);

    if(!$num_match) {
      echo "<script>
             window.alert('등록되지 않는 아이디입니다!')
             location.href = 'selecttype.php?type=login_form';
           </script>";
    }
    else {
        $row = mysqli_fetch_assoc($result);
        $db_pass = $row["pass"];

        mysqli_close($con);

        if(!password_verify($pass,$hashed_password)) {
           echo "<script>
                window.alert('비밀번호가 틀립니다!')
             location.href = 'selecttype.php?type=login_form';
              </script>";
           exit;
        }
        else {
            session_start();
            $_SESSION["userid"] = $row["id"];
            $_SESSION["username"] = $row["name"];
            $_SESSION["userlevel"] = $row["level"];

            echo "<script>
                location.href = '../main/index.php';
              </script>";
        }
     }        
?>

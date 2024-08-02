<?php
    session_start();

    if (isset($_SESSION["userid"])) 
        $userid = $_SESSION["userid"];
    else {
        $userid = "";
    }
    
    if (isset($_SESSION["username"])) 
        $username = $_SESSION["username"];
    else 
        $username = "";
    
    if (isset($_SESSION["userlevel"])) 
        $userlevel = $_SESSION["userlevel"];
    else 
        $userlevel = "";
?>
<!DOCTYPE html>
<html>
<head> 
<meta charset="utf-8">
<title>테스트 사이트</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
<header>
    <h3 class="logo">
        <a href="../index.php">테스트 사이트</a>
    </h3>
    <ul class="top_menu">
<?php
    if(!$userid) {
        echo "<li>로그인을 하지 않았습니다 </li>";
    }
    else {
        $level = if($userlevel == 1){
            $level = "관리자"
        }else{
            $level = "유저"
        }
        $logged = $username." ".$level. "님 환영합니다. ";
        echo "<li>$logged</li>";
    }
    
?>
    </ul> 

    <ul class="main_menu">
<?php
    if(!$userid) {
?>
        <li><a href="../login/login.php">로그인</a></li>
<?php
    } else {
?>
        <li><a href="../login/logout.php">로그아웃</a> </li>
<?php
    }
?>
</header>
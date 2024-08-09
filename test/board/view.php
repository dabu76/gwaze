<?php
    include "../include/header.php";
    include "../include/connect.php";

    $type = $_GET["type"];
    $num = $_GET["num"];
    $sql = "SELECT * FROM `$type` WHERE `num` = $num";
	$result = mysqli_query($con, $sql);			
    $row = mysqli_fetch_assoc($result);			
    $id      = $row["id"];						
	$name      = $row["name"];					
	$subject    = $row["subject"];				
	$regist_day   = $row["regist_day"];			
    $content    = $row["content"];			
    if($type == "_qna"){
        $title = "Q&A";
    }elseif($type =="_notice"){
        $title = "notice";

    }else{
        $title ="자유게시판";
    }

?>

<ul class="board_view">
	<h2 class="title"><?=$title?> > 내용보기</h2>
	<li class="row1">
		<span class="col1"><b>제목 :</b> <?=$subject?></span>	<!-- 제목 출력 -->
		<span class="col2"><?=$name?> | <?=$regist_day?></span>	<!-- 이름, 작성일 출력 -->
	</li>
	<li class="row2">
        <?=$content;?>
	</li>
</ul>
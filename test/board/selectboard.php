<?php
include "../include/header.php";
include "../include/connect.php";

$scale = 10;
$page = isset($_GET["page"]) ? intval($_GET["page"]) : 1;
$type = isset($_GET["type"]) ? $_GET["type"] : '';

$sql = "SELECT * FROM `$type` order by num desc";

$result = mysqli_query($con, $sql);

$rows = [];
while ($row = mysqli_fetch_assoc($result)) {
    $rows[] = $row;
}
$totalrecord = count($rows);

$totalpage = ($totalrecord % $scale == 0) ? ceil($totalrecord / $scale) : ceil($totalrecord / $scale) + 1;

$start = ($page - 1) * $scale;
$inserturl ="insertform.php?type=$type";

?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>게시판</title>
</head>
<body>
<ul class="board_list">
    <?php
    if ($type == "_notice") {
        echo '<h2>공지사항 > 목록보기</h2>';
    } elseif ($type == "_qna") {
        echo '<h2>Q&A > 목록보기</h2>';
    } else {
        echo '<h2>자유게시판 > 목록보기</h2>';
    }

    ?>
    <li>
        <span class="col1">번호</span>
        <span class="col2">제목</span>
        <span class="col3">글쓴이</span>
        <span class="col4">첨부</span>
        <span class="col5">등록일</span>
    </li>

    <?php
    for ($x = $start; $x < $start + $scale && $x < $totalrecord; $x++) {
        $num = htmlspecialchars($rows[$x]["num"], ENT_QUOTES, 'UTF-8');
        $name = htmlspecialchars($rows[$x]["name"], ENT_QUOTES, 'UTF-8');
        $subject = htmlspecialchars($rows[$x]["subject"], ENT_QUOTES, 'UTF-8');
        $regist_day = htmlspecialchars($rows[$x]["regist_day"], ENT_QUOTES, 'UTF-8');
        $file_name = htmlspecialchars($rows[$x]["file_name"], ENT_QUOTES, 'UTF-8');
        $file_copied = htmlspecialchars($rows[$x]["file_copied"], ENT_QUOTES, 'UTF-8');

        $file_image = $file_name ? "<img src='../img/file.png'>" : " ";
        ?>


        <li>
            <span class="col1"><?= $num ?></span>
            <?php 
				$view= "view.php?type=$type&num=$num"; 
                $count = '';
                $ripple = $type."_ripple";
                $sql = "select * from $ripple where parent=$num";
                $result2 = mysqli_query($con, $sql);
                $num_ripple = mysqli_num_rows($result2);

                  if ($num_ripple)
                   $count = " [$num_ripple]";
 ?>

            <span class="col2"><a href = "<?=$view?>"><?= $subject ?><?=$count?></span></a>
        
            <span class="col3"><?= $name ?></span>
            <span class="col4"><?= $file_image ?></span>
            <span class="col5"><?= $regist_day ?></span>
        </li>

        <?php
    }
    ?>
	<ul class="page_num"> 	
<?php
	if ($totalpage>=2 && $page >= 2) {
		$newpage = $page-1;
		echo "<li><a href='selectboard.php?type=$type&page=$newpage'>◀ </a> </li>";
	}		
	else {
		echo "<li>&nbsp;</li>";
    }
   	for ($i=1; $i <$totalpage; $i++) {
		if ($page == $i)    
			echo "<li><b> $i </b></li>";
		else
			echo "<li> <a href='selectboard.php?type=$type&page=$i'> $i </a> <li>";
   	}
		if ($totalpage>=2 && $page != $totalpage)	{
		$newpage = $page+1;	
		echo "<li> <a href='selectboard.php?type=$type&page=$newpage'> ▶</a> </li>";
	}
	else{
		echo "<li>&nbsp;</li>";	
    }	
?>

</ul> 
<ul class="buttons">
    
    <li><a href = <?=$inserturl?>>글쓰기</a></li>
   

</ul>		

</ul>
</body>
</html>

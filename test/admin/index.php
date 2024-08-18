<?php
include "../include/adminheader.php";
include "../include/connect.php";

?>
<div class="notice">
    <h4>신고당한 게시글</h4>
    <?php
    $sql = "SELECT num FROM reports WHERE count <= 1";
    $result = mysqli_query($con, $sql);
    
    $items = []; 
    
    while($row = mysqli_fetch_assoc($result)){
        $num = $row["num"];
        
        $sql2 = "SELECT * FROM _youtube WHERE num = '$num' ORDER BY num DESC LIMIT 5";
        $result2 = mysqli_query($con, $sql2);
        
        while($row2 = mysqli_fetch_assoc($result2)) {
            $items[] = $row2;
        }
    }

    foreach($items as $item) {
        $num = $item["num"];
        $name = $item["name"];
        $date = substr($item["regist_day"], 0, 10);
        $subject = htmlspecialchars_decode($item["subject"], ENT_QUOTES);
?>
        <div class="item">
            <span class="col1">
                <a href="../board/view.php?type=_youtube&num=<?=$num?>">
                    <?=$subject?>
                </a>
            </span>
            <span class="col2"><?=$date?></span>
        </div>
<?php
    }
?>

</div> 

<div class="qna">
    <h4>QNA 게시판</h4>
<?php
	$sql = "select * from _qna order by num desc limit 5";
	$result = mysqli_query($con, $sql);

	while($row = mysqli_fetch_assoc($result))
	{
		$num    = $row["num"];
		$name    = $row["name"];
		$date    = $row["regist_day"];
	  	$date = substr($date, 0, 10);

		$subject = $row["subject"];
	  	$subject = htmlspecialchars_decode($subject, ENT_QUOTES);
?>
        <div class="item">
            <span class="col1"><a href="../board/view.php?type=_qna&num=<?=$num?>">
                <?=$subject ?></a>
                <?php
					$sql = "select * from _qna_ripple where parent=$num";
	  			    $result2 = mysqli_query($con, $sql);
	  				$num_ripple = mysqli_num_rows($result2);

					if ($num_ripple)
				 		echo " [$num_ripple]";
	  			?>
            </span>
            <span class="col2"><?=$date?></span>
        </div>
<?php
	}

	mysqli_close($con);
?>
</div>
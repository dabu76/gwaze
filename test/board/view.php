<?php
include "../include/header.php";
include "../include/connect.php";

$type = $_GET["type"];
$num = $_GET["num"];

$sql = "SELECT * FROM `$type` WHERE `num` = $num";
$sql2 = "SELECT count FROM reports WHERE num = $num AND type_report = '$type'";

$result = mysqli_query($con, $sql);

$count_result = mysqli_query($con, $sql2);

$row = mysqli_fetch_assoc($result);
$row2 = mysqli_fetch_assoc($count_result);

if ($row) {
    $id = $row["id"];
    $name = $row["name"];
    $subject = $row["subject"];
    $regist_day = $row["regist_day"];
    $content = $row["content"];
    $is_html = $row["is_html"];
    $file_name = $row["file_name"];
    $file_type = $row["file_type"];
    $file_copied = $row["file_copied"];

    if ($is_html == "y") {
        $content = htmlspecialchars_decode($content, ENT_QUOTES);
    } else {
        $content = str_replace(" ", "&nbsp;", $content);
        $content = str_replace("\n", "<br>", $content);
    }
} else {
    die("No results found for the given parameters.");
}

$report = 0;
if ($type == "_youtube" && $row2) {
    $report = $row2["count"];
}

$title = ($type == "_qna") ? "Q&A" : (($type == "_notice") ? "notice" : "자유게시판");
?>

<script>
function check() {    
    if (!document.ripple_form.ripple_content.value) {
        alert("내용을 입력하세요!");
        document.ripple_form.ripple_content.focus();
        return false;
    }
    return true;
}
</script>

<ul class="board_view">
    <h2 class="title"><?=$title?> > 내용보기</h2>
    <li class="row1">
        <span class="col1"><b>제목 :</b> <?=$subject?></span>    
        <span class="col2"><?=$name?> | <?=$regist_day?> 
        <?php if ($type == "_youtube") {
            echo "신고수 |  ".$report;
        } ?>
        </span>    
    </li>
    <li class="row2">
        <?php
            if ($file_name) {
                $file_path = "./data/".$file_copied;
                $file_size = filesize($file_path);
            
                echo "▷ 첨부파일 : $file_name ($file_size Byte) &nbsp;&nbsp;&nbsp;&nbsp;
                   <a href='download.php?num=$num&file_copied=$file_copied&file_name=$file_name&file_type=$file_type'>[저장]</a><br><br>";
            }
            echo $content;
        ?>
    </li>
</ul>

<?php
if ($type == "_qna" || $type == "_youtube") {
    $ripple = $type."_ripple";

    $sql = "SELECT * FROM $ripple WHERE parent='$num' ORDER BY num";
    $ripple_result = mysqli_query($con, $sql);

    if (!$ripple_result) {
        die("Query failed: " . mysqli_error($con));
    }

    while ($row_ripple = mysqli_fetch_assoc($ripple_result)) {
        $ripple_num = $row_ripple["num"];
        $ripple_id = $row_ripple["id"];
        $ripple_name = $row_ripple["name"];
        $ripple_content = $row_ripple["content"];

        $ripple_content = str_replace("\n", "<br>", $ripple_content);
        $ripple_content = str_replace(" ", "&nbsp;", $ripple_content);
        $ripple_date = $row_ripple["regist_day"];
?>
        <div class="ripple_title">
            <span class="col1"><?=$ripple_name?></span>
            <span class="col2"><?=$ripple_date?></span>
            <span class="col3">
                <?php
                    if ($userlevel == 1 || $userid == $ripple_id) {
                        echo "<a href='delete_ripple.php?type=$type&num=$num&ripple_num=$ripple_num'>삭제</a>";
                    }
                ?>
            </span>
        </div>
        <div class="ripple_content">
            <?=$ripple_content?>
        </div>
<?php
    }

    mysqli_close($con);
?>
    <div class="ripple_box">
        <form name="ripple_form" method="post" action="insert_ripple.php?type=<?=$type?>&num=<?=$num?>" onsubmit="return check()">
            <div class="ripple_box1"><img src="../img/ripple_title.png"></div>
            <div class="ripple_box2"><textarea name="ripple_content"></textarea></div>
            <div class="ripple_box3"><button type="submit"><img src="../img/ripple_button.png"></button></div>
        </form>
    </div>  
<?php
}
?>
<ul class="buttons">
    <?php
        $list_url = "selectboard.php?type=$type";
        $modify_url = "modifyform.php?type=$type&num=$num";
        $delete_url = "deleteboard.php?type=$type&num=$num";
    ?>
    <li><button onclick="location.href='<?=$list_url?>'">목록보기</button></li>
    <?php
        if ($userlevel == 1 || $userid == $id) {
    ?>
        <li><button onclick="location.href='<?=$modify_url?>'">수정하기</button></li>   
        <li><button onclick="location.href='<?=$delete_url?>'">삭제하기</button></li>
    <?php
        }
    ?>
    <?php
        if ($userid != $id && $type == "_youtube") {
            $report_url = "report.php?type=$type&id=$id&num=$num&count=$report";
    ?>
        <li><button onclick="location.href='<?=$report_url?>'">신고하기</button></li>
    <?php
        }
    ?>
</ul>

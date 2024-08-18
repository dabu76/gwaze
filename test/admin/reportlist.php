<?php
include "../include/adminheader.php";
include "../include/connect.php";

$scale = 4;
$page = isset($_GET["page"]) ? intval($_GET["page"]) : 1;

$sql = "SELECT * FROM reports ORDER BY num DESC";
$result = mysqli_query($con, $sql);

$items = [];
while ($row = mysqli_fetch_assoc($result)) {
    $num = $row["num"];
    $count = $row["count"];
    $sql2 = "SELECT * FROM _youtube WHERE num = '$num' ORDER BY num DESC";
    $result2 = mysqli_query($con, $sql2);

    while ($row2 = mysqli_fetch_assoc($result2)) {
        $row2['count'] = $count; 
        $items[] = $row2;
    }
}

$totalrecord = count($items);

$totalpage = ceil($totalrecord / $scale);

$start = ($page - 1) * $scale;

$display_items = array_slice($items, $start, $scale);
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>신고게시판</title>
</head>

<body>
<ul class="board_list">
    <li>
        <span class="col1">번호</span>
        <span class="col2">제목</span>
        <span class="col3">글쓴이</span>
        <span class="col4">첨부</span>
        <span class="col5">등록일</span>
    </li>

    <?php
    foreach ($display_items as $item) {
        $num = htmlspecialchars($item["num"], ENT_QUOTES, 'UTF-8');
        $name = htmlspecialchars($item["name"], ENT_QUOTES, 'UTF-8');
        $subject = htmlspecialchars($item["subject"], ENT_QUOTES, 'UTF-8');
        $regist_day = htmlspecialchars($item["regist_day"], ENT_QUOTES, 'UTF-8');
        $file_name = htmlspecialchars($item["file_name"], ENT_QUOTES, 'UTF-8');
        $file_image = $file_name ? "<img src='../img/file.png'>" : " ";
        $count = htmlspecialchars($item['count'], ENT_QUOTES, 'UTF-8'); 

        $view = "../board/view.php?type=_youtube&num=$num";
        ?>

        <li>
            <span class="col1"><?= $num ?></span>
            <span class="col2"><a href="<?= $view ?>"><?= $subject ?> (<?= $count ?>)</a></span>
            <span class="col3"><?= $name ?></span>
            <span class="col4"><?= $file_image ?></span>
            <span class="col5"><?= $regist_day ?></span>
        </li>

        <?php
    }
    ?>
</ul>

<ul class="page_num">
    <?php
    if ($totalpage >= 2 && $page >= 2) {
        $newpage = $page - 1;
        echo "<li><a href='reportlist.php?page=$newpage'>◀ </a></li>";
    } else {
        echo "<li>&nbsp;</li>";
    }

    for ($i = 1; $i <= $totalpage; $i++) {
        if ($page == $i) {
            echo "<li><b> $i </b></li>";
        } else {
            echo "<li><a href='reportlist.php?page=$i'> $i </a></li>";
        }
    }

    if ($totalpage >= 2 && $page != $totalpage) {
        $newpage = $page + 1;
        echo "<li><a href='reportlist.php?page=$newpage'> ▶</a></li>";
    } else {
        echo "<li>&nbsp;</li>";
    }
    ?>
</ul>
</body>
</html>

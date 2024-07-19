<?php
   	$x = 1;
    $sum = 0;

    while ($x <= 100) {
        $sum += $x;              // $sum = $sum + $x와 동일
        $x++;
        echo $x;
        echo $sum;
    }

    echo "합계 : ".$sum;
?>
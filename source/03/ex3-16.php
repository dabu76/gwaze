<?php
    echo "-------------------<br>";
    echo "섭씨 화씨<br>";
    echo "-------------------<br>";

    for ($c=-10; $c<= 30; $c+=5) {
        $f = ($c * 9/5) + 32;       // $f : 화씨
        echo $c." &nbsp; ".$f."<br>";
    }
    echo "--------------------";    
?>
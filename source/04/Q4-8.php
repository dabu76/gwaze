<?php
    $score = array(93, 94, 78, 47, 89, 78, 42, 97, 88, 74,
                    92, 93, 84, 72, 69, 71, 82, 77, 58, 32,
                    92, 100, 39, 68, 89, 70, 65, 97, 68, 83);

    $su = 0;           
    $wu = 0;           
    $mi = 0;            
    $yang = 0;          
    $ga = 0;            
        
    for ($i=0; $i<count($score); $i++) {
        if ($score[$i] >= 90)
            $su += 1;
        elseif ($score[$i] >= 80)
            $wu += 1;
        elseif ($score[$i] >= 70)
            $mi += 1;
        elseif ($score[$i] >= 60)
            $yang += 1; 
        else
            $ga += 1;
    }
 
    echo "수 : ".$su."명<br>";
    echo "우 : ".$wu."명<br>";
    echo "미 : ".$mi."명<br>";
    echo "양 : ".$yang."명<br>";
    echo "가 : ".$ga."명<br>";
?>

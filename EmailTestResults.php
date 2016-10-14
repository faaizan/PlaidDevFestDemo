<?php
echo "string";
$string = file_get_contents('spoon-output/result.json');
$content = json_decode($string,true);
$filename = 'HTMLEmailResult.html';
$fh = fopen($filename, 'w') or die("can't open file");
fwrite($fh, '<html><head><style>
    th , td {
        width: 500px;
        text-align:center;
        border:1px solid #717171;
    }
    div {
        text-align:left;
        margin-left:480px;
    }
    body {
        background:#7663ee;
    }
    .red {
        background:red;
        color:black;
    }
    .green {
        background:#6bea93;
        color:black;
    }
    .blue {
        background:#c0fff4;
        margin-left:50px
        color:black;
    }
    .gray{
        background:#31637d;
        color:black;
    }
    .title{
        background:#7663ee;
        color:#f6f6f6;
    }
    </style><body>');
fwrite($fh, "<h1 class='title'>Spoon Test Results</h1>");
fwrite($fh, "<table border=1 class = 'blue'>");
foreach($content['results'] as $deviceCode => $devices) {    
    foreach ($devices as $device) {        
        if(is_array($device)){
            foreach ($device as $deviceDetails => $details) {                      
               if(is_array($details)){
                foreach ($details as $testResults => $test) {                    
                    if(is_array($test)){
                        foreach ($test as $testName => $testVal) { 
                              if($testName == "methodName"){
                                fwrite($fh, "<tr class='blue'><td><div>$testVal</div></td>"); 
                              }
                              if($testName == "status"){
                                if($testVal == "FAIL"){
                                    fwrite($fh, "<td class='red'><div>$testVal</div></td></tr>");     
                                }else{
                                    fwrite($fh, "<td class='green'><div>$testVal</div></td></tr>");     
                                }
                                
                              }                                 
                            }    
                    }
                    
                }
               }else{
                if($deviceDetails=="model"){
                   fwrite($fh, "<tr><td><h3>$details</h3></td></tr>");
                }
                
               }
            }    
        }       
    }
}
fwrite($fh, '</table></body></html>');
fclose($fh);
?>
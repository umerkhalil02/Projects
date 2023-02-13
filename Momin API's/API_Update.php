<?php

include "API_Connect.php";

$conn = mysqli_connect($host,$user,$pwd,$db);

$id = $_REQUEST['mosqueId'];
$fajr = $_REQUEST['Fajr'];
$zuhr = $_REQUEST['Zuhr'];
$asr = $_REQUEST['Asr'];
$maghrib = $_REQUEST['Maghrib'];
$isha = $_REQUEST['Isha'];
$jumma = $_REQUEST['Jumma'];
$date = $_REQUEST['Last_Updated'];

if(!$conn){
	die("Error in connection: ".mysqli_connect_error());
}
$sql_query = " UPDATE timings SET Fajr ='$fajr',Zuhr = '$zuhr',Asr = '$asr',Maghrib = '$maghrib',Isha = '$isha',Jumma = '$jumma',Last_Updated ='$date' WHERE mosqueId = '$id' ";
$result = mysqli_query($conn,$sql_query);
if($result){
	echo "Timings updated successfully!";
}
else{
	echo "Timings not updated!";
}

mysqli_close($conn);
?>

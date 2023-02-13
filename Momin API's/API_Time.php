<?php

include "API_Connect.php";

$conn = mysqli_connect($host,$user,$pwd,$db);

$id = $_REQUEST['mosqueId'];

if(!$conn){
	die("Error in connection: ".mysqli_connect_error());
}
$response = array();
$sql_query = "SELECT * FROM timingS t WHERE t.mosqueId = $id";
$result = mysqli_query($conn,$sql_query);
while ($row = mysqli_fetch_assoc($result)){
	$jsonresult[] = $row;
}
echo json_encode($jsonresult);
mysqli_close($conn);
?>

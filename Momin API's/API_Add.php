<?php

include "API_Connect.php";

$name = $_REQUEST['Name'];
$sect = $_REQUEST['Sect'];
$imam = $_REQUEST['imamId'];
$date = $_REQUEST['Registered'];
$fajr = $_REQUEST['Fajr'];
$zuhr = $_REQUEST['Zuhr'];
$asr = $_REQUEST['Asr'];
$maghrib = $_REQUEST['Maghrib'];
$isha = $_REQUEST['Isha'];
$jumma = $_REQUEST['Jumma'];
$address = $_REQUEST['Address'];
$city = $_REQUEST['City'];
$lat = $_REQUEST['Latitude'];
$long = $_REQUEST['Longitude'];

if(!$conn){
	die("Error in connection: ".mysqli_connect_error());
}
$sql_query = " SELECT mosqueId FROM `mosque` ORDER BY mosqueId DESC LIMIT 1;";
$result = mysqli_query($conn,$sql_query);
if($result){
	while ($row = mysqli_fetch_assoc($result)){
	$jsonresult[] = $row;
	}
	$id = array_values($jsonresult[0])[0];
	$id = $id+1;
}
$qry1= "INSERT INTO mosque(mosqueId,Name,Sect,imamId,Registered) VALUES ('$id','$name','$sect','$imam','$date')";
$qr2 = "INSERT INTO location(mosqueId,City,Address,Latitude,Longitude) VALUES ('$id','$city','$address','$lat','$long')";																
$qr3 = "INSERT INTO timings(mosqueId,imamId,Fajr,Zuhr,Asr,Maghrib,Isha,Jumma,Last_Updated) VALUES ('$id','$imam','$fajr','$zuhr','$asr','$maghrib','$isha','$jumma','$date')";
$ins1 = mysqli_query($conn,$qry1);
$ins2= mysqli_query($conn,$qr2);
$ins3 = mysqli_query($conn,$qr3);
if($ins1 == true and $ins2 == true and $ins3 == true){
		echo "Mosque added successfully!";
}

else{
echo "Data wasnt inserted";
}

?>
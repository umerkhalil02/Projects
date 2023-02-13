<?php

include "API_Connect.php";

$conn = mysqli_connect($host,$user,$pwd,$db);

$id = $_REQUEST['imamId'];
$pass = $_REQUEST['Password'];
$name = $_REQUEST['Name'];
$date = $_REQUEST['Registered'];

if(!$conn){
	die("Error in connection: ".mysqli_connect_error());
}
$sql_query = "SELECT imamId FROM imam i WHERE i.imamId = '$id'";
$result = mysqli_query($conn,$sql_query);

if (mysqli_num_rows($result)>0){
	echo "Email address already in use!";
}
else{
	$sql_query = "INSERT INTO imam (imamId,Name,Password,Registered)
	VALUES ('$id', '$name','$pass','$date')";
	$end_result = mysqli_query($conn,$sql_query);
	if($end_result){
		echo "Imam created successfully!";
	}
	else{
		echo "Account not created!";
	}
}
mysqli_close($conn);
?>

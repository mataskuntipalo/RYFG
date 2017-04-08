<?php
	include 'connection.php';
	
	$name = $_FILES['video']['name'];
	echo $name;
	$temp = $_FILES['video']['tmp_name'];
	move_uploaded_file($temp,'vdo/'.$name);
	
	$strSQL = "INSERT INTO vdo(name)VALUES('$name')";
	//$objQuery = mysqli_query($conn,$strSQL);

?>
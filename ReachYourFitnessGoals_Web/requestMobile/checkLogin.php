<?php
 require('connection.php');

 if($_SERVER['REQUEST_METHOD']=='POST'){
	$username = $_POST['username'];
	$password = $_POST['password'];
	
	$md5Pass = md5($password);
 
	$sql = "SELECT * FROM member WHERE username = '$username' AND password='$md5Pass'";
	$result = mysqli_query($conn,$sql);
	$check = mysqli_fetch_array($result,MYSQLI_ASSOC);
	$member_id = $check["member_id"];
 
	if(isset($check)){
		echo $member_id;
	}else{
		echo 'failure';
	}
 }
 ?>
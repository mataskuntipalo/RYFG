<?php
 require('connection.php');

 if($_SERVER['REQUEST_METHOD']=='POST'){
	$email = $_POST['email'];
	$pass = $_POST['pass'];
	$f_name = $_POST['f_name'];
	$l_name = $_POST['l_name'];
	$gender = $_POST['gender'];
	$age = $_POST['age'];
	$weight = $_POST['weight'];
	$height = $_POST['height'];
	
	$sqlMember = "INSERT INTO member (username,password) VALUES ('$email','$pass')";
	if(mysqli_query($conn,$sqlMember)){
		$sqlMemberID = "SELECT member_id From member WHERE password = '$pass'";
		$result = mysqli_query($conn,$sqlMemberID);
		$row=mysqli_fetch_array($result,MYSQLI_ASSOC);
		$member_id = $row["member_id"];

		$sqlPersonal = "INSERT INTO personal (f_name,l_name,age,gender,weight,height,member_id) 
		VALUES ('$f_name','$l_name','$age','$gender','$weight','$height','$member_id')";
		$resultPersonal = mysqli_query($conn,$sqlPersonal);
		
		echo $member_id;
	}else{
		echo "Could not register";
	};
};
 ?>
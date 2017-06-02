<?php
 require('connection.php');

	$email = "assd11";
	$pass ="211122222aaa";

	
	$sqlMember = "INSERT INTO member (username,password) VALUES ('$email','$pass')";
	if(mysqli_query($conn,$sqlMember)){
		$sqlMemberID = "SELECT member_id From member WHERE password = '$pass'";
		$result = mysqli_query($conn,$sqlMemberID);
		$row=mysqli_fetch_array($result,MYSQLI_ASSOC);
		$member_id = $row["member_id"];
		
		$sqlPersonal = "INSERT INTO personal (f_name,l_name,age,gender,weight,height,member_id) 
		VALUES ('aa','aa','12','1','11','11','$member_id')";
		$resultPersonal = mysqli_query($conn,$sqlPersonal);
		
		
		echo $member_id;
	}else{
		echo "Could not register";
	};
 ?>
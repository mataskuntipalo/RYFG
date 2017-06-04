<?php
	header("content-type:text/javascript;charset=utf-8");  
	require('connection.php');
	
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
	
	$obj = json_decode(file_get_contents("php://input"));
	$member_id =  $obj->{'member_id'};
	//$member_id =3;
		
	$sqlVDO = "SELECT * FROM vdo";
	$objQueryVDO = mysqli_query($conn,$sqlVDO);
	
	$sqlPersonal = "SELECT * FROM personal WHERE member_id = '$member_id'";
	$objQueryPersonal = mysqli_query($conn,$sqlPersonal);
	
	
	$sqlProgram = "SELECT * FROM program WHERE member_id = '$member_id'";
	$objQueryProgram = mysqli_query($conn,$sqlProgram);
	
	
	$sqlExercise = "SELECT * FROM exercise WHERE member_id = '$member_id'";
	$objQueryExercise = mysqli_query($conn,$sqlExercise);
	
	$outp = array("vdo" => $objQueryVDO->fetch_all(MYSQLI_ASSOC) , "personal" => $objQueryPersonal->fetch_all(MYSQLI_ASSOC)
					, "program" => $objQueryProgram->fetch_all(MYSQLI_ASSOC) , "exercise" => $objQueryExercise->fetch_all(MYSQLI_ASSOC));
	


	mysqli_close($conn);
	echo json_encode($outp);
	}
?>
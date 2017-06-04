<?php  
	header("content-type:text/javascript;charset=utf-8"); 
	require('connection.php');
	
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
	
	$obj = json_decode(file_get_contents("php://input"));
	$member_id =  $obj->{'member_id'};
	//$personal =  $objDecode->{'personal'}[0]->{'age'};
	$personal= json_decode($obj->{'personal'});
	$program =  json_decode($obj->{'program'});
	$exercise =  json_decode($obj->{'exercise'});
	
	
	
	$sqlPersonal = "SELECT * FROM personal WHERE member_id = '$member_id'";
	$objQueryPersonal = mysqli_query($conn,$sqlPersonal);
	$check = mysqli_fetch_array($objQueryPersonal,MYSQLI_ASSOC);
	$f_name = $personal->{'f_name'};
	$l_name = $personal->{'l_name'};
	$age = $personal->{'age'};
	$gender = $personal->{'gender'} ;
	$weight = $personal->{'weight'};
	$height = $personal->{'height'};
	
	if(isset($check)){
		$sqlPersonalUpdate = "UPDATE personal SET f_name = '$f_name', l_name = '$l_name' , age = '$age', gender = '$gender', weight = '$weight', height = '$height' WHERE member_id = '$member_id'";
		$resultPersonalUpdate = mysqli_query($conn,$sqlPersonalUpdate);
	}else{
		$sqlPersonalInsert = "INSERT INTO personal (f_name, l_name, age, gender, weight, height, member_id) VALUES ('$f_name', '$l_name', '$age', '$gender','$weight','$height','$member_id')";
		$resultPersonalInsert = mysqli_query($conn,$sqlPersonalInsert);
	};
	
	
	$sqlProgramDelete = "DELETE FROM program WHERE member_id = '$member_id'";
	$resultProgramDelete= mysqli_query($conn,$sqlProgramDelete);
	foreach($program as $programData){
		$typeGoal = $programData->{'typeGoal'};
		$weightGoal = $programData->{'weightGoal'};
		$totalCalorie = $programData->{'totalCalorie'};
		$kgPerWeek = $programData->{'kgPerWeek'};
		$year_date_begin = $programData->{'year_date_begin'};
		$month_date_begin = $programData->{'month_date_begin'};
		$day_date_begin = $programData->{'day_date_begin'};
		$status = $programData->{'status'};
		$percentFat = $programData->{'percentFat'};
		$programName = $programData->{'programName'};
		$sqlProgramInsert = "INSERT INTO program (typeGoal, weightGoal, totalCalorie, kgPerWeek, year_date_begin, month_date_begin, day_date_begin, status, percentFat, programName, member_id) VALUES ('$typeGoal', '$weightGoal' , '$totalCalorie','$kgPerWeek','$year_date_begin','$month_date_begin', '$day_date_begin','$status','$percentFat','$programName','$member_id')";
		$resultProgramInsert = mysqli_query($conn,$sqlProgramInsert);
	};
	
	$sqlExerciseDelete = "DELETE FROM exercise WHERE member_id = '$member_id'";
	$objQueryExerciseDelete = mysqli_query($conn,$sqlExerciseDelete);
	foreach($exercise as $exerciseData){
		$day = $exerciseData->{'day'};
		$month = $exerciseData->{'month'};
		$year = $exerciseData->{'year'};
		$vdo_id = $exerciseData->{'vdo_id'};
		$calorieInDay = $exerciseData->{'calorieInDay'};
		$calorieTotal = $exerciseData->{'calorieTotal'}; 
		$note = $exerciseData->{'note'};
		$time = $exerciseData->{'time'}; 
		$check_state_workout = $exerciseData->{'check_state_workout'};
		$sqlExerciseInsert = "INSERT INTO exercise (day, month, year, vdo_id, calorieInDay, calorieTotal, note, time, check_state_workout, member_id) VALUES ('$day','$month','$year','$vdo_id','$calorieInDay','$calorieTotal','$note','$time','$check_state_workout','$member_id')";
		$resultExerciseInsert = mysqli_query($conn,$sqlExerciseInsert);
	};
	
	
	
	
	
	mysqli_close($conn);
	$arr = array('complete' => "uploadComplete");
	echo json_encode($arr);
	};
?>
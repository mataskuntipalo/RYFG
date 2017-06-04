<?php
 require('connection.php');

	$arr = array('a' => 1);
	
	$arr1 = json_encode($arr);
	//$obj = json_decode(file_get_contents("php://input"));
	$obj = json_decode($arr1);
	//$personal =  $objDecode->{'personal'}[0]->{'age'};
	$personal = $obj->{'a'};
	
		
	
	echo $personal;
	
	$sqlProgramDelete = "DELETE FROM program WHERE member_id = '$member_id'";
	$resultProgramDelete= mysqli_query($conn,$sqlProgramDelete);
	for(int $i = 0 ; $i < sizeof($program) ; $i++){
		$programData = $program[i];
		$sqlProgramInsert = "INSERT INTO program (typeGoal, weightGoal, totalCalorie, kgPerWeek, year_date_begin, month_date_begin, day_date_begin, status, percentFat, programName, member_id) VALUES ('$programData->{'typeGoal'}', '$programData->{'weightGoal'}' , '$programData->{'totalCalorie'}','$programData->{'kgPerWeek'}','$programData->{'year_date_begin'}','$programData->{'month_date_begin'}', '$programData->{'day_date_begin'}','$programData->{'status'}','$programData->{'percentFat'}','$programData->{'programName'}','$member_id')";
		$resultProgramInsert = mysqli_query($conn,$sqlProgramInsert);
	};
	
	
	$sqlProgramDelete = "DELETE FROM program WHERE member_id = '$member_id'";
	$resultProgramDelete= mysqli_query($conn,$sqlProgramDelete);
	for(int $i = 0 ; $i < sizeof(json_decode($program)) ; $i++){
		$programData1 = json_decode($program[i]);
		$programData = json_decode($programData1);
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
	for($j = 0 ; $j < sizeof($exercise) ; $j++){
		$exerciseData = $exercise[i];
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
 
	
		
	
 ?>
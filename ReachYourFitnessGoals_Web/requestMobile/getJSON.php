<?php  
	require('connection.php');
	
	if($_SERVER['REQUEST_METHOD']=='POST'){
	$obj = json_decode(file_get_contents("php://input"));
	//$personal =  $objDecode->{'personal'}[0]->{'age'};
	$personal = $obj->{'personal'};
	$program =  $obj->{'program'};
	$exercise =  $obj->{'exercise'};
	$member_id = $obj->{'member_id'};
	
	
	$sqlPersonal = "SELECT * FROM personal WHERE member_id = '$member_id'";
	$objQueryPersonal = mysqli_query($conn,$sqlPersonal);
	$check = mysqli_fetch_array($objQueryPersonal,MYSQLI_ASSOC);
	if(isset($check)){
		$sqlPersonalUpdate = "UPDATE personal SET f_name = '$personal->{'f_name'}', l_name = '$personal->{'l_name'}' , age = '$personal->{'age'}', gender = '$personal->{'gender'}', weight = '$personal->{'weight'}', height = '$personal->{'height'}' WHERE member_id = $member_id";
		$resultPersonalUpdate = mysqli_query($conn,$sqlPersonalUpdate);
	}else{
		$sqlPersonalInsert = "INSERT INTO personal (f_name, l_name, age, gender, weight, height, member_id) VALUES ('$personal->{'f_name'}', '$personal->{'l_name'}', '$personal->{'age'}', '$personal->{'gender'}','$personal->{'weight'}','$personal->{'height'}','$member_id')";
		$resultPersonalInsert = mysqli_query($conn,$sqlPersonalInsert);
	}
	
	
	$sqlProgramDelete = "DELETE FROM program WHERE member_id = '$member_id'";
	$resultProgramDelete= mysqli_query($conn,$sqlProgramDelete);
	for(int $i = 0 ; $i < sizeof($program) ; $i++){
		$programData = $program[i];
		$sqlProgramInsert = "INSERT INTO program (typeGoal, weightGoal, totalCalorie, kgPerWeek, year_date_begin, month_date_begin, day_date_begin, status, percentFat, programName, member_id) VALUES ('$programData->{'typeGoal'}', '$programData->{'weightGoal'}' , '$programData->{'weightGoal'}', '$programData->{'totalCalorie'}','$programData->{'kgPerWeek'}','$programData->{'year_date_begin'}','$programData->{'month_date_begin'}', '$programData->{'day_date_begin'}','$programData->{'status'}','$programData->{'percentFat'}','$programData->{'programName'}','$member_id')";
		$resultProgramInsert = mysqli_query($conn,$sqlProgramInsert);
	}
	
	
	
	$sqlExerciseDelete = "DELETE FROM exercise WHERE member_id = '$member_id'";
	$objQueryExerciseDelete = mysqli_query($conn,$sqlExerciseDelete);
	for($j = 0 ; $j < sizeof($exercise) ; $j++){
		$exerciseData = $exercise[i];
		$sqlExerciseInsert = "INSERT INTO exercise (day, month, year, vdo_id, calorieInDay, calorieTotal, note, time, check_state_workout, member_id) VALUES ('$exerciseData->{'day'}','$exerciseData->{'month'}','$exerciseData->{'year'}','$exerciseData->{'vdo_id'}','$exerciseData->{'calorieInDay'}','$exerciseData->{'calorieTotal'}','$exerciseData->{'note'}','$exerciseData->{'time'}','$exerciseData->{'check_state_workout'}','$member_id')";
		$resultExerciseInsert = mysqli_query($conn,$sqlExerciseInsert);
	}	
	echo 'upload complete'
	mysqli_close($conn);
	}
?>
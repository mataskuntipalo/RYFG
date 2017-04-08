<!DOCTYPE html>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=utf-8">
</head>
<body>
<?php
	include 'connection.php';
	$strSQL = "SELECT * FROM vdo";
	$objQuery = mysqli_query($conn,$strSQL);
	$totalRow = mysqli_num_rows($objQuery);
	
	if (mysqli_num_rows($objQuery) > 0) {
     // output data of each row
		while($row = mysqli_fetch_assoc($objQuery)) {
			echo "id: " . $row["vdo_id"]. " - Name: " . $row["name"]. " " . $row["type"]. " " . $row["position"]. " " . $row["duration"]. " " . $row["calorie"]. "<br>";
		}
		echo "total VDO : " . $totalRow;
	} else {
		echo "0 results";
	}
	mysqli_close($conn);
?>
</body>
</html>

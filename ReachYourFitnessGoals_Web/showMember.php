<!DOCTYPE html>
<html>
<head>
</head>
<body>
<?php
	$objConnect = mysqli_connect('localhost','root','');
	$objDB = mysqli_select_db($objConnect,"ryfg");
	$strSQL = "SELECT * FROM member";
	$objQuery = mysqli_query($objConnect,$strSQL);
	$totalRow = mysqli_num_rows($objQuery);
	
	if (mysqli_num_rows($objQuery) > 0) {
     // output data of each row
		while($row = mysqli_fetch_assoc($objQuery)) {
			echo "id: " . $row["member_id"]. " - Name: " . $row["username"]. " " . $row["password"]. "<br>";
		}
		echo "total member : " . $totalRow;
	} else {
		echo "0 results";
	}
	mysqli_close($objConnect);
?>
</body>
</html>


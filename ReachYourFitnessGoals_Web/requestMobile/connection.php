<?php
	$conn = mysqli_connect('localhost','root','');
	mysqli_set_charset($conn, "utf8");
	$objDB = mysqli_select_db($conn,"ryfg");
?>
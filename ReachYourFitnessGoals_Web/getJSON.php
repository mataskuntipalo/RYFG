<?php

	$objConnect = mysql_connect('localhost','root','') or die ("error1");
	$objDB = mysql_select_db("ryfg",$objConnect)or die ("error2");
	$strSQL = "SELECT * FROM vdo";
	$objQuery = mysql_query($strSQL);
	$intNumField = mysql_num_fields($objQuery);

	$resultArray = array();

	while($obResult = mysql_fetch_array($objQuery))

	{

		$arrCol = array();

		for($i=0;$i<$intNumField;$i++)

		{

			$arrCol[mysql_field_name($objQuery,$i)] = $obResult[$i];

		}

		array_push($resultArray,$arrCol);
		

	}


	mysql_close($objConnect);
	echo json_encode($resultArray);

?>
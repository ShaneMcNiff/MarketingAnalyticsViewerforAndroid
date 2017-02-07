<?php

$response = array();

require_once __DIR__ . '/db_connect.php'

$db = new DB_CONNECT();

$result = mysql_query("select * from campaigns") or die(mysql_error());

if(mysql_num_rows($result) > 0){
	
	$response["campaigns"] = array();
	
	while($row = mysql_fetch_array($result)){
		$campaign = array();
		$campaign["id"] = $row["id"];
		$campaign["campaign_name"] = $row["campaign_name"];
		$campaign["country_id"] = $row["country_id"];
		$campaign["client_id"] = $row["client_id"];
		$campaign["current_entry_count"] = $row["current_entry_count"];
		$campaign["estimated_entry_count"] = $row["estimated_entry_count"];
		$campaign["positive_responses"] = $row["positive_responses"];
		$campaign["negative_responses"] = $row["negative_responses"];
		$campaign["start_date"] = $row["start_date"];
		$campaign["end_date"] = $row["end_date"];
		
		array_push($response["campaigns"], $campaign);
	}
	
	$response["success"] = 1;
	
	echo json_encode($response);
}else {
	
	$response["success"] = 0;
	$response["message"] = "No campaigns found";
	
	echo json_encode($response);
}
?>
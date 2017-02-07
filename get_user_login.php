<?php

$response = array();

require_once __DIR__ . '/db_connect.php'

$db = new DB_CONNECT();

if(isset($_GET["email"])){
	
	$pid = $GET["email"];
	
	$result = mysql_query("select * from users where email = $email")
	
	if(!empty($result)){
		
		if(mysql_num_rows($result) > 0){
			
			$result = mysql_fetch_array($result);
			
			$email = array();
			$email["id"] = $result["id"];
			$email["username"] = $result["username"];
			$email["email"] = $result["email"];
			$email["password"] = $result["password"];
			
			$response["success"] = 1;
			
			$response["email"] = array();
			
			array_push($response["email"], $email);
			
			echo json_encode($response);			
		} else {
			$response["success"] = 0;
			$response["message"] = "No user found";
			
			echo json_encode($response);
		}
	}else {
		$response["success"] = 0;
		$response["message"] = "No user found";
			
		echo json_encode($response);
	}
}else {
	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";
			
	echo json_encode($response);
}

?>
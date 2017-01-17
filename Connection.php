<?php
$db_name = "FYPDB";
$mysql_username = "shane";
$mysql_password = "Cartroneast1";
$server_name = "fypdb.cmjaptxoobvl.eu-west-1.rds.amazonaws.com";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
if($conn) {
	echo "Connection success";
}else{
	echo "Connection failed";
}
?>
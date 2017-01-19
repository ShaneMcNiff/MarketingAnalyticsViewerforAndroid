<?php
require "Connection.php";

$mysql_query = "select * from clients;";

$result = mysqli_query($conn, $mysql_query);
if(mysqli_num_rows($result) > 0) {
	echo "Success!";
}else{
	echo "Failed!";
}
?>
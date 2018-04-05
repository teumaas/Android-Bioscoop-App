<?php
include('databaseConnect.php');
global $conn;

if (!$conn) {
   	echo "API is currently offline";
} else {
	echo "API is currently online";
}
?>
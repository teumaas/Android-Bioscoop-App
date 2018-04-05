<?php

//Database settings
$servername = "";
$username = "";
$password = "";
$dbname = "";

//Make the connection with the database
$conn = new mysqli($servername, $username, $password, $dbname);

//Make sure the connection is working
if (!$conn) {
    die('Could not connect: ' . mysql_error());
}
?>

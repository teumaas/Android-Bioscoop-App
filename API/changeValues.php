<?php
//Includes
include('databaseConnect.php');
global $conn;

//Class that contains wheter the query was executed successfully or not
class returnCode {
    public $result = "false";
}

//Get values
$action = $_GET["action"];
$table = $_GET["table"];

//Check if the action should be create
if ($action == "Create" || $action == "create" || $action == "CREATE"){
    //Check if the action should be performed on the ticket table
    if ($table == "Ticket" || $table == "ticket" || $table == "TICKET"){
        //Gets remaining values
        $email = $_GET["email"];
        $qrcode = $_GET["qrcode"];
        $showid = $_GET["showid"];
        $chairid = $_GET["chairid"];

        //Makes the query
        $sql = "INSERT INTO `Ticket` (`Email`, `QRCode`, `ShowId`, `ChairId`) VALUES ('" . $email . "', '" . $qrcode . "', '" . $showid . "', '" . $chairid . "');";
    //Check if the action should be performed on the reservation table
    } else if ($table == "Reservation" || $table == "reservation" || $table == "RESERVATION"){
        //Gets remaining values
        $showid = $_GET["showid"];
        $chairid = $_GET["chairid"];

        //Makes the query
        $sql = "INSERT INTO `Reservation` (`ShowId`, `ChairId`) VALUES ('" . $showid . "', '" . $chairid . "');";
    } else if ($table == "Review" || $table == "review" || $table == "REVIEW"){
    	//Gets remaining values
    	$date = $_GET["date"];
        $content = $_GET["content"];
        $rating = $_GET["rating"];
        $movieid = $_GET["movieid"];

        $sql = "INSERT INTO `MovieReview` (`Date` , `Content`, `Rating`, `MovieId`) VALUES ('" . $date . "', '" . $content . "', '" . $rating . "', '" . $movieid . "');";
    }
//Check if the action should be delete
} else if ($action == "Delete" || $action == "delete" || $action == "DELETE"){
    //Check if the action should be performed on the reservation table
    if ($table == "Reservation" || $table == "reservation" || $table == "RESERVATION"){
        //Gets remaining values
        $reservationid = $_GET["reservationid"];

        //Makes the query
        $sql = "DELETE FROM `Reservation` WHERE `ReservationId` = '" . $reservationid . "';";
    }
}

//Execute query
$result = $conn->query($sql);
$returnCode = new returnCode();

//Check wheter or not the query was executed successfully
if ($result == "1"){
    $returnCode->result = "true";
} else {
    $returnCode->result = "false";
}

//Encode and print the message
echo json_encode($returnCode);

//Close connection
$conn->close();
?>
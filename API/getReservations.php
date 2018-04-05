<?php
//Includes
include('databaseConnect.php');
global $conn;

$where = $_GET["where"];

//Movie class
class Reservation {
    public $ReservationId = "";
    public $ShowId = "";
    public $ChairId = ""; 
}

//MovieObject class
class ReservationObject {
    public $Reservations = "";
} 

if ($where == "*"){
    $sql = "SELECT * FROM `Reservation`";
} else if ($where == "showid" || $where == "ShowId" || $where == "SHOWID"){
    $value = $_GET["value"];
    $sql = "SELECT * FROM `Reservation` WHERE `ShowId` = '" . $value . "';";
}

//Execute query
$result = $conn->query($sql);

//Initiate variables
$reservationArray[] = null;
$i = 0;

//Cycle through results
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $reservation = new Reservation();
        $reservation->ReservationId = $row["ReservationId"];
        $reservation->ShowId = $row["ShowId"];
        $reservation->ChairId = $row["ChairId"];

        $reservationArray[$i] = (json_encode($reservation));
        $i++;
    }
    $ReservationObject = new ReservationObject();
    $ReservationObject->Reservations = $reservationArray;
    
    //Encode results as a JSON
    echo json_encode($ReservationObject);
} else {
    echo "null";
}
//Close connection
$conn->close();
?>
<?php
//Includes
include('databaseConnect.php');
global $conn;

$where = $_GET["where"];

//Movie class
class Ticket {
    public $TicketId = "";
    public $Email = "";
    public $QRCode = "";
    public $ShowId = "";
    public $ChairId = "";
}

//MovieObject class
class TicketObject {
    public $Tickets = "";
}
// get domainname
$serverName = $_SERVER['HTTP_HOST'];

if ($where == "*"){
    $sql = "SELECT * FROM `Ticket`";
} else if ($where == "showid" || $where == "ShowId" || $where == "SHOWID"){
    $value = $_GET["value"];
    $sql = "SELECT * FROM `Ticket` WHERE `ShowId` = '" . $value . "';";
}

//Execute query
$result = $conn->query($sql);

//Initiate variables
$ticketArray[] = null;
$i = 0;

//Cycle through results
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $ticket = new Ticket();
        $ticket->TicketId = $row["TicketId"];
        $ticket->Email = $row["Email"];
        $ticket->QRCode = $serverName . "/encodeQR.php?code=" . $row["QRCode"];
        $ticket->ShowId = $row["ShowId"];
        $ticket->ChairId = $row["ChairId"];

        $ticketArray[$i] = (json_encode($ticket));
        $i++;
    }
    $TicketObject = new TicketObject();
    $TicketObject->Tickets = $ticketArray;

    //Encode results as a JSON
    echo json_encode($TicketObject, JSON_UNESCAPED_SLASHES);
} else {
    echo "null";
}

//Close connection
$conn->close();
?>
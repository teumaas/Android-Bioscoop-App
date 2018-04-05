<?php
//Includes
include('databaseConnect.php');
global $conn;

$where = $_GET["where"];

//Movie class
class Room {
    public $RoomId = "";
    public $Name = "";
}

//MovieObject class
class RoomObject {
    public $Rooms = "";
}

//Execute query
$sql = "SELECT * FROM `Room`";
$result = $conn->query($sql);

//Initiate variables
$roomArray[] = null;
$i = 0;

//Cycle through results
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $room = new Room();
        $room->RoomId = $row["RoomId"];
        $room->Name = $row["Name"];

        $roomArray[$i] = (json_encode($room));
        $i++;
    }
    $RoomObject = new RoomObject();
    $RoomObject->Rooms = $roomArray;
    
    //Encode results as a JSON
    echo json_encode($RoomObject);
} else {
    echo "null";
}
//Close connection
$conn->close();
?>
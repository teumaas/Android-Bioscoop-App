<?php
//Includes
include('databaseConnect.php');
global $conn;

$where = $_GET["where"];

//Movie class
class Chair {
    public $ChairId = "";
    public $Row = "";
    public $Number = "";
    public $RoomId = "";
}

//MovieObject class
class ChairObject {
    public $Chairs = "";
}

if ($where == "*"){
    $sql = "SELECT * FROM `Chair`";
} else if ($where == "roomid" || $where == "RoomId" || $where == "ROOMID"){
    $value = $_GET["value"];
    $sql = "SELECT * FROM `Chair` WHERE `RoomId` = '" . $value . "';";
}

$result = $conn->query($sql);

//Initiate variables
$chairArray[] = null;
$i = 0;

//Cycle through results
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $chair = new Chair();
        $chair->ChairId = $row["ChairId"];
        $chair->Row = $row["Row"];
        $chair->Number = $row["Number"];
        $chair->RoomId = $row["RoomId"];

        $chairArray[$i] = (json_encode($chair));
        $i++;
    }
    $ChairObject = new ChairObject();
    $ChairObject->Chairs = $chairArray;
    
    //Encode results as a JSON
    echo json_encode($ChairObject);
} else {
    echo "null";
}
//Close connection
$conn->close();
?>
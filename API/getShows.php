<?php
//Includes
include('databaseConnect.php');
global $conn;

$where = $_GET["where"];

//Movie class
class Show {
    public $ShowId = "";
    public $Date = "";
    public $Time = "";
    public $Price = "";
    public $MovieId = "";
    public $RoomId = "";
}

//MovieObject class
class ShowObject {
    public $Shows = "";
}

if ($where == "*"){
	$sql = "SELECT *  FROM `Show`";
} else if ($where == "MovieId" || $where == "movieid" || $where == "MOVIEID"){
	$movieid = $_GET["movieid"];
	$sql = "SELECT * FROM `Show` WHERE `MovieId` = '" . $movieid . "';";
}
//Execute query
$result = $conn->query($sql);

//Initiate variables
$showArray[] = null;
$i = 0;

//Cycle through results
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $show = new Show();
        $show->ShowId = $row["ShowId"];
        $date = date_create($row["Date"]);
        $show->Date = date_format($date, "d-m-Y");
        $show->Time = substr($row["Time"], 0, -3);
        $show->Price = $row["Price"];
        $show->MovieId = $row["MovieId"];
        $show->RoomId = $row["RoomId"];

        $showArray[$i] = (json_encode($show));
        $i++;
    }
    $showObject = new ShowObject();
    $showObject->Shows = $showArray;
    
    //Encode results as a JSON
    echo json_encode($showObject);
} else {
    echo "null";
}
//Close connection
$conn->close();
?>
<?php
//Includes
include('databaseConnect.php');
global $conn;

$where = $_GET["where"];

//Movie class
class Movie {
    public $MovieId = "";
    public $Title = "";
    public $Runtime = "";
    public $Year = ""; 
    public $Actors = "";
    public $Plot = "";
    public $Language = "";
    public $Genre = "";
    public $Image = "";
}

//MovieObject class
class MovieObject {
    public $Movies = "";
}

//Execute query
$sql = "SELECT * FROM `Movie`";
$result = $conn->query($sql);

//Initiate variables
$movieArray[] = null;
$i = 0;

//Cycle through results
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $movie = new Movie();
        $movie->MovieId = $row["MovieId"];
        $movie->Title = $row["Title"];
        $movie->Runtime = $row["Runtime"];
        $movie->Year = $row["Year"];
        $movie->Actors = $row["Actors"];
        $movie->Plot = $row["Plot"];
        $movie->Language = $row["Language"];
        $movie->Genre = $row["Genre"];
        $movie->Image = $row["Image"];

        $movieArray[$i] = (json_encode($movie));
        $i++;
    }
    $movieObject = new MovieObject();
    $movieObject->Movies = $movieArray;
    
    //Encode results as a JSON
    echo json_encode($movieObject);
} else {
    echo "null";
}
//Close connection
$conn->close();
?>
<?php
//Includes
include('databaseConnect.php');
global $conn;

$where = $_GET["where"];

//Movie class
class MovieReview {
    public $ReviewId = "";
    public $Date = "";
    public $Content = "";
    public $Rating = "";
    public $MovieId = "";
}

//MovieObject class
class MovieReviewObject {
    public $MovieReviewObjects = "";
}

if ($where == "*"){
	$sql = "SELECT * FROM `MovieReview`";
} else if ($where == "movieid" || $where == "MovieId" || $where == "MOVIEID"){
	$movieid = $_GET["movieid"];
	$sql = "SELECT * FROM `MovieReview` WHERE `MovieId` = '" . $movieid . "';";
}

//Execute query
$result = $conn->query($sql);

//Initiate variables
$movieReviewArray[] = null;
$i = 0;

//Cycle through results
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $MovieReview = new MovieReview();
        $MovieReview->ReviewId = $row["ReviewId"];
        $MovieReview->Date = $row["Date"];
        $MovieReview->Content = $row["Content"];
        $MovieReview->Rating = $row["Rating"];
        $MovieReview->MovieId = $row["MovieId"];

        $movieReviewArray[$i] = (json_encode($MovieReview));
        $i++;
    }
    $MovieReviewObject = new MovieReviewObject();
    $MovieReviewObject->MovieReviewObjects = $movieReviewArray;
    
    //Encode results as a JSON
    echo json_encode($MovieReviewObject);
} else {
    echo "null";
}
//Close connection
$conn->close();
?>
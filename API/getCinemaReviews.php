<?php
//Includes
include('databaseConnect.php');
global $conn;

$where = $_GET["where"];

//Movie class
class CinemaReview {
    public $ReviewId = "";
    public $Date = "";
    public $Content = "";
    public $Rating = "";
}

//MovieObject class
class CinemaReviewObject {
    public $CinemaReviewObjects = "";
}

//Execute query
$sql = "SELECT * FROM `CinemaReview`";
$result = $conn->query($sql);

//Initiate variables
$cinemaReviewArray[] = null;
$i = 0;

//Cycle through results
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        $CinemaReview = new CinemaReview();
        $CinemaReview->ReviewId = $row["ReviewId"];
        $CinemaReview->Date = $row["Date"];
        $CinemaReview->Content = $row["Content"];
        $CinemaReview->Rating = $row["Rating"];

        $cinemaReviewArray[$i] = (json_encode($CinemaReview));
        $i++;
    }
    $CinemaReviewObject = new CinemaReviewObject();
    $CinemaReviewObject->CinemaReviewObjects = $cinemaReviewArray;
    
    //Encode results as a JSON
    echo json_encode($CinemaReviewObject);
} else {
    echo "null";
}
//Close connection
$conn->close();
?>
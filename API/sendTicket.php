<?php

/**
 * User: Tom Smits
 * Date: 27-3-2018
 */

include('class.ticket.php');

$email = $_GET['email'];
$ticketID = $_GET['ticketID'];
$movieName = urlencode($_GET['movieName']);

$ticket = TicketConstructor::createBuilder()
    ->setTicketID($ticketID)
    ->setEmail($email)
    ->setQRCode($_GET['QRCode'])
    ->setShowID($_GET['showID'])
    ->setMovieName(str_replace("+", " ", $movieName))
    ->setChairID($_GET['chairID'])
    ->setDateTime(date("d-m-Y H:i:s"))
    ->setPaymentMethod($_GET['paymentMethod'])
    ->setPrice($_GET['price'])
    ->setTicketAmount($_GET['ticketAmount'])
    ->build();

$ticket->sendTicketToMail();

?>

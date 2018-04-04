<?php

/**
 * User: Tom Smits
 * Date: 28-3-2018
 */

include('class.payement.php');

$payment = new Payment();
$payment->setPayment($_GET['amount'],"sendTicket.php?ticketID={$_GET['ticketID']}&email={$_GET['email']}&QRCode={$_GET['QRCode']}&showID={$_GET['showID']}&movieName=". str_replace("+", "%20", urlencode($_GET['movieName'])) ."&chairID={$_GET['chairID']}&paymentMethod={$_GET['paymentMethod']}&price={$_GET['price']}&ticketAmount={$_GET['ticketAmount']}", $_GET['desc']);





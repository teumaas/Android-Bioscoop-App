<?php

/**
 * User: Tom Smits
 * Date: 28-3-2018
 */

include('class.payement.php');

$payment = new Payment();

$payment->setPayment(10.00, "index.php", "Betaling!");



<?php

/**
 * User: Tom Smits
 * Date: 28-3-2018
 */

require __DIR__ . "/vendor/autoload.php";

class Payment
{
   const API_KEY = "test_Adr2Upf9pAEKyMzV5FGqS36Uv2p6DF";

   public $mollie;

   function Payment() {
       $this->mollie = new Mollie_API_Client;
       $this->mollie->setApiKey("test_Adr2Upf9pAEKyMzV5FGqS36Uv2p6DF");
   }

   function setPayment($am, $rUrl, $desc) {
       $amount = $am;
       $redirectUrl = $rUrl;
       $description = str_replace("+", "%20", $desc);

       settype($amount, "double");
       settype($redirectUrl, "string");
       settype($description, "string");

       try
       {
           $protocol = isset($_SERVER['HTTPS']) && strcasecmp('off', $_SERVER['HTTPS']) !== 0 ? "https" : "http";
           $hostname = $_SERVER['HTTP_HOST'];

           $payment = $this->mollie->payments->create(array(
               "amount"       => $amount,
               "description"  => $description,
               "redirectUrl"  => "{$protocol}://{$hostname}/{$redirectUrl}",
           ));


           header("Location: " . $payment->getPaymentUrl(), true, 303);
       }
       catch (Mollie_API_Exception $e)
       {
           echo "API call failed: " . htmlspecialchars($e->getMessage());
       }
   }
}
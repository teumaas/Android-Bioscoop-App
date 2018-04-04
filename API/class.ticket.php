<?php

/**
 * Created by PhpStorm.
 * User: Tom Smits
 * Date: 27-3-2018
 */

require __DIR__ . "/vendor/autoload.php";

class TicketBuilder
{
    private $TicketID;
    private $Email;
    private $QRCode;
    private $ShowID;
    private $ChairID;
    private $DateTime;
    private $Price;
    private $PaymentMethod;
    private $MovieName;
    private $TicketAmount;

    function TicketBuilder() {
    }

    function setTicketID($ticketId){
        $this->TicketID = $ticketId;
        return $this;
    }

    function setEmail($email){
        $this->Email = $email;
        return $this;
    }

    function setQRCode($qrCode) {
        $this->QRCode = $qrCode;
        return $this;
    }

    function setShowID($showId) {
        $this->ShowID = $showId;
        return $this;
    }

    function setChairID($chairId) {
        $this->ChairID = $chairId;
        return $this;
    }

    function setDateTime($timeDate) {
        $this->DateTime = $timeDate;
        return $this;
    }

    function setPaymentMethod($paymentMethod) {
        $this->PaymentMethod = $paymentMethod;
        return $this;
    }

    function setMovieName($movieName) {
        $this->MovieName = $movieName;
        return $this;
    }

    function setPrice($price) {
        $this->Price = $price;
        return $this;
    }

    function setTicketAmount($ticketAmount) {
        $this->TicketAmount = $ticketAmount;
        return $this;
    }

    function getTicketID() {
        return $this->TicketID;
    }

    function getEmail() {
        return $this->Email;
    }

    function getQRCode() {
        return $this->QRCode;
    }

    function getShowID() {
        return $this->ShowID;
    }

    function getChairID() {
        return $this->ChairID;
    }

    function getDateTime() {
        return $this->DateTime;
    }

    function getPrice() {
        return $this->Price;
    }

    function getPaymentMethod() {
        return $this->PaymentMethod;
    }

    function getMovieName() {
        return $this->MovieName;
    }

    function getTicketAmount() {
        return $this->TicketAmount;
    }

    function build() {
        return new TicketConstructor($this);
    }
}

class TicketConstructor
{
    private $TicketID;
    private $Email;
    private $QRCode;
    private $ShowID;
    private $ChairID;
    private $DateTime;
    private $Price;
    private $PaymentMethod;
    private $MovieName;
    private $TicketAmount;

    static function createBuilder() {
        return new TicketBuilder();
    }

    function TicketConstructor(TicketBuilder $b) {
        $this->TicketID = $b->getTicketID();
        $this->Email = $b->getEmail();
        $this->QRCode = $b->getQRCode();
        $this->ShowID = $b->getShowID();
        $this->ChairID = $b->getChairID();
        $this->DateTime = $b->getDateTime();
        $this->PaymentMethod = $b->getPaymentMethod();
        $this->Price = $b->getPrice();
        $this->MovieName = $b->getMovieName();
        $this->TicketAmount = $b->getTicketAmount();
    }

    function getTicketID() {
        return $this->TicketID;
    }

    function getEmail() {
        return $this->Email;
    }

    function getQRCode() {
        return $this->QRCode;
    }

    function getShowID() {
        return $this->ShowID;
    }

    function getChairID() {
        return $this->ChairID;
    }

    function getPrice() {
        return $this->Price;
    }

    function getPaymentMethod() {
        return $this->PaymentMethod;
    }

    function getDateTime() {
        return $this->DateTime;
    }

    function getMovieName() {
        return $this->MovieName;
    }

    function getTicketAmount() {
        return $this->TicketAmount;
    }

    function buildTicketHTML() {
        $showID = $this->getShowID();
        $paymentMethod = $this->getPaymentMethod();
        $qrCode = $this->getQRCode();
        $ticketID = $this->getTicketID();
        $email = $this->getEmail();
        $dateTime = $this->getDateTime();
        $chairID = $this->getChairID();
        $orderPrice = $this->getPrice();
        $movieName = $this->getMovieName();

        return "<link href='https://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css' rel='stylesheet' id='bootstrap-css'><script src='https://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script><script src='https://code.jquery.com/jquery-1.11.1.min.js'></script><div class='container'><div class='row'><div class='col-xs-12'><div class='invoice-title'><h2>Film: " . $movieName . "</h2><h3 class='pull-right'>Ticket ID #" . $ticketID . "</h3></div><hr><div class='row'><div class='col-xs-6'><address><strong>Betaal methode:</strong><br>". $paymentMethod ."<br>" . $email . "</address></div><div class='col-xs-6'><address><strong>Besteldatum:</strong><br>" . $dateTime . "<br><br></address></div><div class='col-xs-6'> <address> <strong>QR-Code:</strong> <br> <br> <img src='https://api.teumaas.nl/encodeQR.php?code=". $qrCode ."' height='128' width='128'> <br><br> </address></div></div></div></div><div class='row'><div class='col-md-12'><div class='panel panel-default'><div class='panel-heading'><h3 class='panel-title'><strong>Ticketinformatie</strong></h3></div><div class='panel-body'><div class='table-responsive'><table class='table table-condensed'><thead><tr><td class='text-center'><strong>Film</strong></td><td class='text-center'><strong>Stoel</strong></td></tr></thead><tbody><tr><td class='text-center'>" . $showID . "</td><td class='text-center'>" . $chairID . "</td></tr><tr><td class='no-line'></td><td class='no-line'></td><td class='no-line text-center'><strong>Totaal</strong></td><td class='no-line text-right'>&euro; " . $orderPrice . "</td></tr></tbody></table></div></div></div></div></div></div>";
    }

    function sendTicketToMail() {
        $mpdf = new \Mpdf\Mpdf();
        $html = $this->buildTicketHTML();
        $filename = "Ticketbevestiging: {$this->getMovieName()} - Referentie: {$this->getTicketID()}.pdf";
        $amount = $this->getTicketAmount();

        ob_end_clean();

        for ($x = 0; $x < $amount; $x++) {
            $mpdf->AddPage();
            $mpdf->WriteHTML(utf8_encode($html));
        }

        $content = $mpdf->Output('', 'S');

        $attachment = new Swift_Attachment($content, $filename, 'application/pdf');

        $transport = Swift_SmtpTransport::newInstance('s2.securehoster.net', 25)
            ->setUsername('noreply@teumaas.nl')
            ->setPassword('p(7VqwVTZrUbM?ra');

        $mailer = Swift_Mailer::newInstance($transport);

        $message = Swift_Message::newInstance($filename, 'Beste ' . $this->getEmail() .',

Bedankt voor uw bestelling. De betaling is geslaagd.
Deze PDF bevat een QR-barcode deze kunt u scannen bij
de ticketcontrole in de bioscoop.

Met vriendelijke groet,
Bioscoop Avans')
            ->setFrom(array('noreply@teumaas.nl' => 'Bioscoop Avans'))
            ->setTo($this->getEmail())
            ->attach($attachment);

        $mailer->send($message);

        echo 'Ticket verzonden!';
    }
}

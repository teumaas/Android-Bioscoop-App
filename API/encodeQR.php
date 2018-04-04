<?php
// requires php-qr-code : https://github.com/ziplr/php-qr-code
require __DIR__ . "/vendor/autoload.php";

// set qr-code
$parm = $_GET['code'];
QRcode::png($parm, false, QR_ECLEVEL_H, 10, 0);

?>

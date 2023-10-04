<?php
$databaseHost = '127.0.0.1:3306';
$databaseName = 'myfpl';
$databaseUsername = 'root';
$databasePassword = 'anh03032003';
// $databaseHost = '103.173.227.188';
// $databaseName = 'tuananh1_myFPL';
// $databaseUsername = 'tuananh1_tuananh1';
// $databasePassword = 'tuananh1_tuananh1';

try {
	$dbConn = new PDO("mysql:host={$databaseHost};dbname={$databaseName}", 
						$databaseUsername, $databasePassword);
	$dbConn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
	echo $e->getMessage();
}
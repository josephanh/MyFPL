<?php
$databaseHost = '103.18.6.215';
$databaseName = 'w0kquymnawxi_myFPL';
$databaseUsername = 'w0kquymnawxi_tuan';
$databasePassword = 'w0kquymnawxi_tuan';

try {
	$dbConn = new PDO("mysql:host={$databaseHost};dbname={$databaseName}", 
						$databaseUsername, $databasePassword);
	$dbConn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
	echo $e->getMessage();
}
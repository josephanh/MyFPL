<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");


include_once('../../database/connection.php');
// http://127.0.0.1:3000/api/class/get_class_by_code.php?code=MOB12309
try {
    $code = $_GET['code'];
    $class = $dbConn->query("SELECT id, code, name FROM class where code='$code'");
    $result = $class -> fetch(PDO::FETCH_ASSOC);
    // kiem tra khoa hoc ton tai chua
    if ($class->rowCount() < 0) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "Get failed",
            "class" => null
        ));
    } else {
        echo json_encode(array(
            "status" => true,
            "messenger" => "Get class sucessfuly",
            "class" => $result
        ));
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e
    ));
}

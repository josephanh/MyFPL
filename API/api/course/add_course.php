<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");


include_once('../../database/connection.php');

try {
    $data = json_decode(file_get_contents("php://input"));
    $name = $data->name;
    $code = $data->code;


    if (empty($name) || empty($code)) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "name or code is empty"
        ));
    } else {
        $course = $dbConn->query("SELECT id, code FROM course where code='$code'");
        // kiem tra khoa hoc ton tai chua
        if ($course->rowCount() > 0) {
            echo json_encode(array(
                "status" => false,
                "messenger" => "course exists"
            ));
        } else {
            $sql = "INSERT INTO course (name, code) VALUES ('$name', '$code')";
            $dbConn->exec($sql);

            echo json_encode(array(
                "status" => true,
                "messenger" => "Add course sucessfuly"
            ));
        }
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e
    ));
}

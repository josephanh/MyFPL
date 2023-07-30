<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");


include_once('../../database/connection.php');

try {
    $id = $_GET['id'];
    $data = json_decode(file_get_contents("php://input"));
    $code = $data->code;
    $name = $data->name;


    if (empty($code) || empty($name)) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "name or code is empty"
        ));
    } else {
        $class = $dbConn->query("SELECT id, code FROM class where id='$id'");
        // kiem tra khoa hoc ton tai chua
        if ($class->rowCount() <= 0) {
            echo json_encode(array(
                "status" => false,
                "messenger" => "class don't exists"
            ));
        } else {
            $id = $class->fetch(PDO::FETCH_ASSOC)['id'];
    
            $sql = "UPDATE class SET name='$name', code='$code'  where id=$id";
            $dbConn->exec($sql);

            echo json_encode(array(
                "status" => true,
                "messenger" => "Edit class sucessfuly"
            ));
        }
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e
    ));
}

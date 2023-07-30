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
        $course = $dbConn->query("SELECT id, code, name FROM course where id=$id");
        // kiem tra khoa hoc ton tai chua
        if ($course->rowCount() <= 0) {
            echo json_encode(array(
                "status" => false,
                "messenger" => "course don't exists",
                "id" => $id
            ));
        } else {
            $idCourse = $course->fetch(PDO::FETCH_ASSOC)['id'];
            $sql = "UPDATE course SET name='$name', code='$code'  where id=$idCourse";
            $dbConn->exec($sql);

            echo json_encode(array(
                "status" => true,
                "messenger" => "Edit course sucessfuly"
            ));
        }
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e
    ));
}

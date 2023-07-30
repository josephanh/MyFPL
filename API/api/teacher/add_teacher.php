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
    $birthday = $data->birthday;
    $code_id = $data->code_id;
    $teacher_code = $data->teacher_code;
    


    if (empty($name) || empty($code_id) || empty($teacher_code) || empty($birthday)) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "field is empty"
        ));
    } else {
        $checkTeacherCode = $dbConn->query("SELECT id, name FROM teacher where code_id='$code_id'");
        $checkCodeId = $dbConn->query("SELECT id, name FROM teacher where teacher_code='$teacher_code'");
        // kiem tra khoa hoc ton tai chua
        if ($checkTeacherCode->rowCount() > 0 || $checkCodeId->rowCount() > 0) {
            echo json_encode(array(
                "status" => false,
                "messenger" => "teacher exists"
            ));
        } else {
            $sql = "INSERT INTO teacher (name, birthday, code_id, teacher_code) 
            VALUES ('$name', '$birthday', '$code_id', '$teacher_code')";
            $dbConn->exec($sql);

            echo json_encode(array(
                "status" => true,
                "messenger" => "Add teacher sucessfuly"
            ));
        }
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e
    ));
}

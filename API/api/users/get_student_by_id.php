<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../../database/connection.php';
// http://127.0.0.1:3000/api/users/get_student_by_id.php?student_id=1
try {
    $data = json_decode(file_get_contents("php://input"));
    $student_id = $_GET['student_id'];


    if (empty($student_id)) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "student id is empty",
        ));
    } else {
        $user = $dbConn->query("SELECT * FROM users where id='$student_id'");
        if ($user->rowCount() > 0) {
    
            date_default_timezone_set('Asia/Ho_Chi_Minh');
            $date = date('Y-m-d H:i:s');
            $day = date('Y-m-d');
            echo json_encode(array(
                "status" => true,
                "messenger" => "Get Schedule Sucessfuly",
                "student" => $user->fetch(PDO::FETCH_ASSOC),
                "created_at" => $date,
            ));
        } else {
            echo json_encode(array(
                "status" => true,
                "messenger" => "Student not exists",
            ));
        }
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e,
    ));
}

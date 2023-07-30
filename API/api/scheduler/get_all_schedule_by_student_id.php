<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../../database/connection.php';

try {
    $data = json_decode(file_get_contents("php://input"));
    $student_id = $_GET['student_id'];

    if (empty($student_id)) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "student id is empty",
        ));
    } else {
        $subjects = $dbConn->query("SELECT * FROM studing where student_id='$student_id'");
        // kiem tra khoa hoc ton tai chua
        $result = array();
        if ($subjects->rowCount() > 0) {
            while ($row = $subjects->fetch(PDO::FETCH_ASSOC)) {
                $class_id = $row['class_id'];
                $course_id = $row['course_id'];
                $schedule = $dbConn->query("SELECT * FROM studing s 
                inner join schedule c on s.`class_id` = c.`class_id`
                where $course_id = c.id ");
                array_push($result, $schedule ->fetch(PDO::FETCH_ASSOC));
                // echo json_encode(array(
                //     "row" => $row
                // ));
            }
            echo json_encode(array(
                "status" => true,
                "messenger" => "Get Schedule Sucessfuly",
                "schedule" => $result,
                "total" => $subjects -> rowCount(),
                "created_at" => date('Y-m-d H:i:s')
            ));
        } else {
            echo json_encode(array(
                "status" => true,
                "messenger" => "Subject not exists",
            ));
        }
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e,
    ));
}

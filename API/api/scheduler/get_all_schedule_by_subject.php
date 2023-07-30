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
    $course_id = $_GET['course_id'];

    if (empty($student_id)) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "student id is empty",
        ));
    } else {
        $subjects = $dbConn->query("SELECT * FROM studing where student_id='$student_id' and course_id='$course_id'");
        // kiem tra khoa hoc ton tai chua
        $result = array();
        // echo json_encode(array(
        //     "tes" => $subjects->fetchAll(PDO::FETCH_ASSOC)
        // ));
        if ($subjects->rowCount() > 0) {
            while ($row = $subjects->fetch(PDO::FETCH_ASSOC)) {
                $class_id = $row['class_id'];
                $schedule = $dbConn->query("SELECT * FROM studing stu 
                inner join schedule sch on stu.`course_id` = sch.`course_id`
                where stu.student_id='$student_id' and stu.course_id='$course_id'") ;
                array_push($result, $schedule ->fetch(PDO::FETCH_ASSOC));
            }
            echo json_encode(array(
                "status" => true,
                "messenger" => "Get Schedule Sucessfuly",
                "schedule" => $result,
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

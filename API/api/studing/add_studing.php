<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../../database/connection.php';

try {
    $data = json_decode(file_get_contents("php://input"));
    $student_id = $data->student_id;
    $course_id = $data->course_id;
    $class_id = $data->class_id;

    if (empty($student_id) || empty($course_id) || empty($class_id)) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "fields is empty",
        ));
    } else {
        $course = $dbConn->query("SELECT student_id, course_id FROM studing where student_id='$student_id'");
        // kiem tra khoa hoc ton tai chua
        if ($course->rowCount() >= 0) {
            while ($row = $course->fetch(PDO::FETCH_ASSOC)) {
                if ($row['student_id'] == $student_id && $row['course_id'] == $course_id) {
                    echo json_encode(array(
                        "status" => false,
                        "messenger" => "course exists",
                    ));
                    return;
                    exit();
                }
            }

            $sql = "INSERT INTO studing (student_id, course_id, class_id, type) VALUES ('$student_id', '$course_id', '$class_id', 1)";
            $dbConn->exec($sql);

            echo json_encode(array(
                "status" => true,
                "messenger" => "Add course sucessfuly",
            ));
        }
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e,
    ));
}

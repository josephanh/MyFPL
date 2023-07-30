<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");


include_once('../../database/connection.php');
// http://127.0.0.1:3000/api/course/get_all_course.php
try {
    $course = $dbConn->query("SELECT id, code, name FROM course");
    $result = $course -> fetchAll(PDO::FETCH_ASSOC);
    // kiem tra khoa hoc ton tai chua
    if ($course->rowCount() < 0) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "Get failed",
            "course" => null
        ));
    } else {
        echo json_encode(array(
            "status" => true,
            "messenger" => "Get course sucessfuly",
            "course" => $result
        ));
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e
    ));
}

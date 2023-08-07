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
        $subjects = $dbConn->query("SELECT * FROM studing where student_id='$student_id' and type=1");
        $result = array();
    
            date_default_timezone_set('Asia/Ho_Chi_Minh');
            $date = date('Y-m-d H:i:s');
            $day = date('Y-m-d');

            $result = $dbConn->query("SELECT
                -- u.name AS student_name,
                c.name AS course_name,
                c.code AS course_code,
                cl.name AS class_name,
                cl.code AS class_code,
                stu.type AS type
                FROM
                    users u
                JOIN
                    studing stu ON u.id = stu.student_id
                JOIN
                    course c ON stu.course_id = c.id
                JOIN
                    class cl ON stu.class_id = cl.id
                WHERE
                    u.id = '$student_id'");
            // Set the new timezone
           
            echo json_encode(array(
                "status" => true,
                "messenger" => "Get Schedule Sucessfuly",
                "schedule" => $result->fetchAll(PDO::FETCH_ASSOC),
                "total" => $result->rowCount(),
                "created_at" => $date,
            ));
        
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e,
    ));
}

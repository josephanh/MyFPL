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
        $subjects = $dbConn->query("SELECT * FROM studing where student_id='$student_id'");
        // kiem tra khoa hoc ton tai chua
        // echo json_encode(array(
        //     "subjects" => $subjects->fetchAll(PDO::FETCH_ASSOC)
        // ));
        // exit();
        $result = array();
        if ($subjects->rowCount() > 0) {
            // while ($row = $subjects->fetch(PDO::FETCH_ASSOC)) {
            //     $class_id = $row['class_id'];
            //     $course_id = $row['course_id'];
            //     $schedule = $dbConn->query("SELECT * FROM studing stu
            //     inner join schedule sch
            //     on stu.`class_id` = sch.`class_id`
            //     inner join course co
            //     on sch.`course_id` = co.id
            //     where $course_id = sch.id
            //     ");
            //     array_push($result, $schedule ->fetch(PDO::FETCH_ASSOC));
            //     // echo json_encode(array(
            //     //     "row" => $row
            //     // ));
            // }
            $result = $dbConn->query("SELECT
                    -- u.name AS student_name,
                    c.name AS course_name,
                    s.room,
                    s.day,
                    s.time,
                    t.name AS teacher_name,
                    cl.name AS class_name,
                    s.address AS address
                    FROM
                        users u
                    JOIN
                        studing stu ON u.id = stu.student_id
                    JOIN
                        course c ON stu.course_id = c.id
                    JOIN
                        class cl ON stu.class_id = cl.id
                    JOIN
                        schedule s ON stu.course_id = s.course_id AND stu.class_id = s.class_id
                    JOIN
                        teacher t ON s.teacher_id = t.id
                    WHERE
                        u.id = '$student_id'
                    AND 
                        s.course_id = '$course_id' ");
            // Set the new timezone
            date_default_timezone_set('Asia/Ho_Chi_Minh');
            $date = date('Y-m-d H:i:s');
            echo json_encode(array(
                "status" => true,
                "messenger" => "Get Schedule Sucessfuly",
                "schedule" => $result->fetchAll(PDO::FETCH_ASSOC),
                "total" => $result->rowCount(),
                "created_at" => $date,
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

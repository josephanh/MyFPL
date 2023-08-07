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
    $date_start = $_GET['date_start'];
    $date_end = $_GET['date_end'];

    if (!(strtotime($date_start) !== false) || !(strtotime($date_end) !== false) || new DateTime($date_start) > new DateTime($date_end)) {
        throw "Ngày không hợp lệ.";
    }

    if (empty($student_id)) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "student id is empty",
        ));
    } else {
        $subjects = $dbConn->query("SELECT * FROM studing where student_id='$student_id'");
        $result = array();

        date_default_timezone_set('Asia/Ho_Chi_Minh');
        $date = date('Y-m-d H:i:s');
        $day = date('Y-m-d');

        $result = $dbConn->query("SELECT
                -- u.name AS student_name,
                c.name AS course_name,
                c.code AS course_code,
                s.room,
                s.day,
                s.time,
                t.name AS teacher_name,
                cl.name AS class_name,
                cl.code AS class_code,
                s.address AS address,
                s.type
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
                AND s.day BETWEEN '$date_start' AND '$date_end'
                OR s.day = '$date_start' OR s.day = '$date_end'
                ORDER BY s.day ASC");
        // Set the new timezone

        echo json_encode(array(
            "status" => true,
            "messenger" => "Get Schedule Sucessfuly",
            "time_schedule" => array(
                "date_start" => $date_start,
                "date_end" => $date_end,
            ),
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

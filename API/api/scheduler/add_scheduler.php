    <?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../../database/connection.php';

try {
    $data = json_decode(file_get_contents("php://input"));

    $room = $data->room;
    $day = $data->day;
    $time = $data->time;
    $course_id = $data->course_id;
    $class_id = $data->class_id;
    $teacher_id = $data->teacher_id;
    $type = $data->type;

    if (empty($room) || empty($day) || empty($time) ||
        empty($course_id) || empty($class_id) || empty($teacher_id) || empty($type)) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "field is empty",
        ));
    } else {
        $schedule = $dbConn->query("SELECT id, room, class_id, course_id FROM schedule where class_id='$class_id'");
        // kiem tra khoa hoc ton tai chua
        // if ($schedule->rowCount() > 0) {
        //     echo json_encode(array(
        //         "status" => false,
        //         "messenger" => "course exists",
        //     ));
        // } else {
        $sql = "INSERT INTO schedule
                (room, day, time, course_id, class_id, teacher_id, type)
                VALUES ('$room', '$day', '$time', '$course_id', '$class_id', '$teacher_id', $type)";
        $dbConn->exec($sql);

        echo json_encode(array(
            "status" => true,
            "messenger" => "Add schedule sucessfuly",
        ));
        // }
    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e,
    ));
}

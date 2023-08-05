<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once "../database/connection.php";

try {
    $data = json_decode(file_get_contents("php://input"));
    $email = $data->email;
    $name = $data->name;
    $avatar = $data->avatar;


    if (empty($email)) {
        echo json_encode(array(
            "status" => false,
        ));
        return;
    } else {

        $pattern = '/@fpt\.edu\.vn$/';
        $matches = array();
        

        if (!preg_match($pattern, $email, $matches)) {
            echo json_encode(array(
                "status" => false,
                "messenger" => "Please login by gmail fpt",
            ));
            return;
        }

        $user = $dbConn->query("SELECT id, email FROM users where email='$email'");
        // kiem tra email co ton tai hay khong

        if ($user->rowCount() > 0) {
            $row = $user->fetch(PDO::FETCH_ASSOC);
            $id = $row['id'];
            $email = $row['email'];
        } else {

            $pattern = '/[A-Za-z]{2}\d+/';
            if (preg_match($pattern, $email, $matches)) {
                // Lấy chuỗi phù hợp từ mảng $matches
                $student_code = $matches[0];
            } else {
                echo json_encode(array(
                    "status" => false,
                    "messenger" => "No matching string found from email",
                ));
                return;
            }

            // $avatar = "";
            // $name = "Tuấn Anh";
            // $email = "anhntps24619@gmail.com";
            // $student_code = "ps24609";
            $gender = true;
            $birthday = "2023-7-20";
            $address = "Quận 12, Tp. Hồ Chí Minh";
            $course = "15.3";
            $major = "Lập trình máy tính (Mobile)";
            $day_admission = "2021-10-20";

            $dbConn->query("INSERT INTO users (avatar, name, email, student_code, gender,
            birthday, address, course,major, day_admission) VALUES ('$avatar', '$name', '$email',
            '$student_code', $gender, '$birthday', '$address', '$course', '$major', '$day_admission')");
        }
        $user = $dbConn->query("SELECT * FROM users where email='$email'");
        echo json_encode(array(
            "status" => true,
            "messenger" => "Login Sucessfuly",
            "student" => $user -> fetch(PDO::FETCH_ASSOC)
        ));
    }
} catch (Exception $e) {
    //throw $th;
    echo json_encode(array(
        "status" => false,
        "messenger" => $e,
    ));
}

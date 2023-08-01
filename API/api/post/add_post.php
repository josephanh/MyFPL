<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../../database/connection.php';

try {
    $data = json_decode(file_get_contents("php://input"));

    date_default_timezone_set('Asia/Ho_Chi_Minh');
    $date = date('Y-m-d H:i:s');

    $title = $data->title;
    $content = $data->content;
    $author = $data->author;
    $type = $data->type != null ? $data->type : 0;
    $department = $data ->department;
    $created_at = $date;

    if (empty($title) || empty($content) || empty($author)) {
        echo json_encode(array(
            "status" => false,
            "messenger" => "fields is empty",
        ));
    } else {

        $sql = "INSERT INTO posts (title, content, author, created_at, type, department) 
        VALUES ('$title', '$content', '$author', '$created_at', '$type', '$department')";
        $dbConn->exec($sql);
        echo json_encode(array(
            "status" => true,
            "messenger" => "Add post sucessfuly",
        ));

    }
} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e,
        "value" => $author
    ));
}

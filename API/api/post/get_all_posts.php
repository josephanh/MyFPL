<?php

header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include_once '../../database/connection.php';
// http://127.0.0.1:3000/api/post/get_all_posts.php
try {

    $page = $_GET['page'] != null ? $_GET['page'] : 1;
    $limit = 30;
    $offset = ($page - 1)*$limit;

    $posts = $dbConn->query("SELECT * FROM posts LIMIT $limit OFFSET $offset");
    $result = $posts->fetchAll(PDO::FETCH_ASSOC);

    date_default_timezone_set('Asia/Ho_Chi_Minh');
    $date = date('Y-m-d H:i:s');

    // kiem tra khoa hoc ton tai chua
    echo json_encode(array(
        "status" => true,
        "messenger" => "Get course sucessfuly",
        "posts" => $result,
        "total" => $posts -> rowCount(),
        "page" => $page,
        "called_at" => $date,
    ));

} catch (Exception $e) {
    echo json_encode(array(
        "status" => false,
        "messenger" => $e,
    ));
}

-- Create DATABASE

create database if not exists MyFPL;

use MyFPL;

-- Create TABLE

CREATE TABLE
    IF NOT EXISTS users (
        id INT PRIMARY KEY AUTO_INCREMENT,
        avatar VARCHAR(1000) NOT NULL,
        name NVARCHAR(100) NOT NULL,
        email VARCHAR(150) NOT NULL UNIQUE,
        -- UNIQUE : Duy Nhất 
        student_code VARCHAR(100) NOT NULL UNIQUE,
        gender BIT,
        -- BIT : Kiểu dữ liệu True False
        birthday DATE,
        address NVARCHAR(500),
        course NVARCHAR(100)
    );

-- Bài viết , thông báo , nội dung

CREATE TABLE
    IF NOT EXISTS posts (
        id INT PRIMARY KEY AUTO_INCREMENT,
        title NVARCHAR(100) NOT NULL,
        content NVARCHAR(5000) NOT NULL,
        created_at DATETIME NOT NULL
    );

-- Lịch Học ,Lịch Thi

CREATE TABLE
    IF NOT EXISTS schedule (
        id INT PRIMARY KEY AUTO_INCREMENT,
        room NVARCHAR(100) NOT NULL,
        day DATE NOT NULL,
        time NVARCHAR(100) NOT NULL,
        course_name NVARCHAR(100) NOT NULL,
        class_name NVARCHAR(100) NOT NULL,
        teacher_name NVARCHAR(100) NOT NULL,
        type BIT NOT NULL
    )
-- Create DATABASE

create database if not exists MyFPL;

use MyFPL;

-- Create TABLE

CREATE TABLE
    IF NOT EXISTS users (
        id INT PRIMARY KEY AUTO_INCREMENT,
        avatar NVARCHAR(1000) NOT NULL,
        name NVARCHAR(100) NOT NULL,
        email NVARCHAR(150) NOT NULL UNIQUE,
        -- UNIQUE : Duy Nhất 
        student_code NVARCHAR(100) NOT NULL UNIQUE,
        gender BIT,
        -- BIT : Kiểu dữ liệu True False
        birthday DATE,
        address NVARCHAR(500),
        course NVARCHAR(100),
        -- khóa
        major NVARCHAR(100),
        day_admission DATE
    );

-- Bài viết , thông báo , nội dung

CREATE TABLE
    IF NOT EXISTS posts (
        id INT PRIMARY KEY AUTO_INCREMENT,
        title NVARCHAR(100) NOT NULL,
        content NVARCHAR(5000) NOT NULL,
        department NVARCHAR(100) NOT NULL,
        created_at DATETIME NOT NULL,
        author NVARCHAR(100),
        type INT -- 0: học tập, 1: hoạt động, 2: việc làm
    );

-- Lịch Học ,Lịch Thi

CREATE TABLE
    IF NOT EXISTS schedule (
        id INT PRIMARY KEY AUTO_INCREMENT,
        room NVARCHAR(100) NOT NULL,
        day DATE NOT NULL,
        time NVARCHAR(100) NOT NULL,
        course_id INT NOT NULL,
        class_id INT NOT NULL,
        teacher_id INT NOT NULL,
        address NVARCHAR(500),
        type BIT NOT NULL,
        FOREIGN KEY (course_id) REFERENCES course(id),
        FOREIGN KEY (class_id) REFERENCES class(id),
        FOREIGN KEY (teacher_id) REFERENCES teacher(id)
    );

CREATE TABLE
    IF NOT EXISTS teacher (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name NVARCHAR(100) NOT NULL,
        birthday DATE,
        code_id NVARCHAR(15) UNIQUE NOT NULL,
        teacher_code NVARCHAR(15) UNIQUE NOT NULL
    );

CREATE TABLE
    IF NOT EXISTS class (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name NVARCHAR(100) NOT NULL,
        code NVARCHAR(100) NOT NULL UNIQUE
    );

CREATE TABLE
    IF NOT EXISTS course (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name NVARCHAR(100) NOT NULL,
        code NVARCHAR(100) NOT NULL UNIQUE
    );

CREATE TABLE
    IF NOT EXISTS studing (
        id INT PRIMARY KEY AUTO_INCREMENT,
        student_id INT NOT NULL,
        course_id INT NOT NULL,
        class_id INT NOT NULL,
        type BIT DEFAULT 1,
        FOREIGN KEY (student_id) REFERENCES users(id),
        FOREIGN KEY (course_id) REFERENCES course(id),
        FOREIGN KEY (class_id) REFERENCES class(id)
    );


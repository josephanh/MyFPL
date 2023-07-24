package nta.com.music.myfpl.model;

import java.util.Date;

public class Schedule {
    private int id_Schedule;
    private String subject,room,teacher,address,subject_code,subject_class;
    private int shift_school;
    private String date;



    public Schedule(int id_Schedule, String subject, String room, String teacher, String address, String subject_code, String subject_class, int shift_school) {
        this.id_Schedule = id_Schedule;
        this.subject = subject;
        this.room = room;
        this.teacher = teacher;
        this.address = address;
        this.subject_code = subject_code;
        this.subject_class = subject_class;
        this.shift_school = shift_school;
    }

    public Schedule(int id_Schedule, String subject, String room, String teacher, String address, String subject_code, String subject_class, int shift_school, String date) {
        this.id_Schedule = id_Schedule;
        this.subject = subject;
        this.room = room;
        this.teacher = teacher;
        this.address = address;
        this.subject_code = subject_code;
        this.subject_class = subject_class;
        this.shift_school = shift_school;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_Schedule() {
        return id_Schedule;
    }

    public void setId_Schedule(int id_Schedule) {
        this.id_Schedule = id_Schedule;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_class() {
        return subject_class;
    }

    public void setSubject_class(String subject_class) {
        this.subject_class = subject_class;
    }

    public int getShift_school() {
        return shift_school;
    }

    public void setShift_school(int shift_school) {
        this.shift_school = shift_school;
    }
}

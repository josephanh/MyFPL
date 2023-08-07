package nta.com.music.myfpl.model;

public class Schedule {
    private String course_name,room, teacher_name,address, course_code, class_name, class_code;

    private int time;
    private String type;
    private String day;

    public Schedule(String course_name, String room, String teacher_name, String address, String course_code, String class_name, String class_code, int time, String type, String day) {
        this.course_name = course_name;
        this.room = room;
        this.teacher_name = teacher_name;
        this.address = address;
        this.course_code = course_code;
        this.class_name = class_name;
        this.class_code = class_code;
        this.time = time;
        this.type = type;
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

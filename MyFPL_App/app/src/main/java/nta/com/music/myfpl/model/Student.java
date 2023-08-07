package nta.com.music.myfpl.model;

public class Student {
    private int id;
    private String avatar;
    private String name;
    private String email;
    private String student_code;
    private boolean gender;
    private String birthday;
    private String address;
    private String course;
    private String major;
    private String day_admission;

    public Student(int id, String avatar, String name, String email, String student_code, boolean gender, String birthday, String address, String course, String major, String day_admission) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.email = email;
        this.student_code = student_code;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.course = course;
        this.major = major;
        this.day_admission = day_admission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDay_admission() {
        return day_admission;
    }

    public void setDay_admission(String day_admission) {
        this.day_admission = day_admission;
    }
}

package nta.com.music.myfpl.DTO;

import nta.com.music.myfpl.model.Student;

public class StudentResponseDTO {
    private boolean status;
    private String message;
    private Student student;
    private String created_at;

    public StudentResponseDTO(boolean status, String message, Student student, String created_at) {
        this.status = status;
        this.message = message;
        this.student = student;
        this.created_at = created_at;
    }

    public StudentResponseDTO(boolean status, String message, Student student) {
        this.status = status;
        this.message = message;
        this.student = student;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}

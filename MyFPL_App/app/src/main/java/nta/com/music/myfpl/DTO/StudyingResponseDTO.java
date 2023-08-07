package nta.com.music.myfpl.DTO;

import java.util.List;

public class StudyingResponseDTO {
    private boolean status;
    private String messenger;
    private List<Study> schedule;
    private int total;
    private String created_at;

    public StudyingResponseDTO(boolean status, String messenger, List<Study> schedule, int total, String created_at) {
        this.status = status;
        this.messenger = messenger;
        this.schedule = schedule;
        this.total = total;
        this.created_at = created_at;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public List<Study> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Study> schedule) {
        this.schedule = schedule;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public static class Study {
        private String course_name, course_code, class_name, class_code;

        public Study(String course_name, String course_code, String class_name, String class_code) {
            this.course_name = course_name;
            this.course_code = course_code;
            this.class_name = class_name;
            this.class_code = class_code;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
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

        public String getClass_code() {
            return class_code;
        }

        public void setClass_code(String class_code) {
            this.class_code = class_code;
        }
    }
}

package nta.com.music.myfpl.DTO;

import java.io.Serializable;
import java.util.List;

import nta.com.music.myfpl.model.Schedule;

public class ScheduleResponseDTO implements Serializable {
    private boolean status;
    private String messenger;
    private Object time_schedule;
    private List<Schedule> schedule;
    private int total;
    private String created_at;

    public ScheduleResponseDTO(boolean status, String messenger, Object time_schedule, List<Schedule> schedule, int total, String created_at) {
        this.status = status;
        this.messenger = messenger;
        this.time_schedule = time_schedule;
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

    public Object getTime_schedule() {
        return time_schedule;
    }

    public void setTime_schedule(Object time_schedule) {
        this.time_schedule = time_schedule;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
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
}


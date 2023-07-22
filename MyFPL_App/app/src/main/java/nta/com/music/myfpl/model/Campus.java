package nta.com.music.myfpl.model;

public class Campus {
    private Integer campusId;
    private String campusName;

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public Campus(Integer campusId, String campusName) {
        this.campusId = campusId;
        this.campusName = campusName;
    }

    public Campus() {
    }
}

package nta.com.music.myfpl.model;

public class HistoryService {
    private long time;
    private float sotien;
    private String loai, mahoadon, hocky, chuthich;
    private int id;
    private String title;

    public HistoryService() {
    }

    public HistoryService(long time, float sotien, String loai, String mahoadon, String hocky, String chuthich) {
        this.time = time;
        this.sotien = sotien;
        this.loai = loai;
        this.mahoadon = mahoadon;
        this.hocky = hocky;
        this.chuthich = chuthich;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getSotien() {
        return sotien;
    }

    public void setSotien(float sotien) {
        this.sotien = sotien;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getHocky() {
        return hocky;
    }

    public void setHocky(String hocky) {
        this.hocky = hocky;
    }

    public String getChuthich() {
        return chuthich;
    }

    public void setChuthich(String chuthich) {
        this.chuthich = chuthich;
    }
}

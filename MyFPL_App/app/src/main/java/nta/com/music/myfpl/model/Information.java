package nta.com.music.myfpl.model;

import java.io.Serializable;

public class Information implements Serializable {
    private int id_Informations;
    private String name,author,notification;
    private long time;

    public Information() {
    }

    public Information(int id_Informations, String name, String author, String notification, long time) {
        this.id_Informations = id_Informations;
        this.name = name;
        this.author = author;
        this.notification = notification;
        this.time = time;
    }

    public int getId_Informations() {
        return id_Informations;
    }

    public void setId_Informations(int id_Informations) {
        this.id_Informations = id_Informations;
    }

    public Information(String name, String author, long time, String notification) {
        this.name = name;
        this.author = author;
        this.time = time;
        this.notification = notification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}

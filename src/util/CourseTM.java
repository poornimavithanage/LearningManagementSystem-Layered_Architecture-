package util;

import javafx.collections.ObservableList;

public class CourseTM {
    private String id;
    private String title;
    private String type;
    private String duration;

    public CourseTM() {
    }

    public CourseTM(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public CourseTM(String id, String title, String type, String duration) {
        this.setId(id);
        this.setTitle(title);
        this.setType(type);
        this.setDuration(duration);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return id +" - "+title+" - "+ type;
    }
}

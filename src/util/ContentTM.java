package util;

import java.sql.Date;

public class ContentTM {
    private String id;
    private String title;
    private Date date;
    private String lecturerID;
    private String moduleId;

    public ContentTM() {
    }

    public ContentTM(String id, String title, Date date, String lecturerID, String moduleId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.lecturerID = lecturerID;
        this.moduleId = moduleId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}

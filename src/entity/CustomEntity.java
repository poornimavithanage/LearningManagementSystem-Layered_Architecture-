package entity;

import java.sql.Date;

public class CustomEntity implements SuperEntity {
    private String title;
    private String type;
    private String courseId;
    private String lecturerId;
    private Date date;
    private String announcement;

    public CustomEntity() {
    }

    public CustomEntity(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public CustomEntity(String courseId, String lecturerId, Date date, String announcement) {
        this.courseId = courseId;
        this.lecturerId = lecturerId;
        this.date = date;
        this.announcement = announcement;
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

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    @Override
    public String toString() {
        return getAnnouncement();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

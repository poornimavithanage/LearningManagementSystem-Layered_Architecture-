package util;

import java.sql.Date;

public class AnnouncementTM {
    private String courseId;
    private String lecturerId;
    private Date date;
    private String announcement;

    public AnnouncementTM() {
    }

    public AnnouncementTM(String courseId, String lecturerId, Date date, String announcement) {
        this.courseId = courseId;
        this.lecturerId = lecturerId;
        this.date = date;
        this.announcement = announcement;
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

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    @Override
    public String toString() {
        return announcement;
    }
}

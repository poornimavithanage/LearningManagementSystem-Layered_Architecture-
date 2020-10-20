package entity;

import java.sql.Date;

public class Announcement implements SuperEntity{
    private AnnouncementPK announcementPK;
    private Date date;
    private String announcement;

    public Announcement() {
    }

    public Announcement(AnnouncementPK announcementPK, Date date, String announcement) {
        this.announcementPK = announcementPK;
        this.date = date;
        this.announcement = announcement;
    }

    public Announcement(String courseId, String lecturerId, Date date, String announcement) {
        this.setAnnouncementPK(new AnnouncementPK(courseId,lecturerId));
        this.date = date;
        this.announcement = announcement;
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

    public AnnouncementPK getAnnouncementPK() {
        return announcementPK;
    }

    public void setAnnouncementPK(AnnouncementPK announcementPK) {
        this.announcementPK = announcementPK;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementPK=" + announcementPK +
                ", date=" + date +
                ", announcement='" + announcement + '\'' +
                '}';
    }
}

package entity;


public class AnnouncementPK implements SuperEntity {
    private String courseId;
    private String lecturerId;

    public AnnouncementPK() {
    }

    public AnnouncementPK(String courseId, String lecturerId) {
        this.courseId = courseId;
        this.lecturerId = lecturerId;
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
}

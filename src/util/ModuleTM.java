package util;

public class ModuleTM {
    private String id;
    private String title;
    private String duration;
    private String credits;
    private String courseId;

    public ModuleTM() {
    }
    public ModuleTM(String id, String title, String duration, String credits, String courseId) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.credits = credits;
        this.courseId = courseId;
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

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return
                id+ " - " + title;
    }
}

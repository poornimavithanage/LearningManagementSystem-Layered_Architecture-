package entity;

public class FacultyLecturerPK implements SuperEntity{
    private String facultyId;
    private String lectureId;

    public FacultyLecturerPK() {
    }

    public FacultyLecturerPK(String facultyId, String lectureId) {
        this.facultyId = facultyId;
        this.lectureId = lectureId;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }
}

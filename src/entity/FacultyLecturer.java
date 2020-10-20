package entity;

public class FacultyLecturer implements SuperEntity {
    private FacultyLecturerPK facultyLecturerPK;

    public FacultyLecturer() {
    }

    public FacultyLecturer(FacultyLecturerPK facultyLecturerPK) {
        this.facultyLecturerPK = facultyLecturerPK;
    }
    public FacultyLecturer(String facultyId, String lecturerId) {
        this.facultyLecturerPK = new FacultyLecturerPK(facultyId,lecturerId);
    }

    public FacultyLecturerPK getFacultyLecturerPK() {
        return facultyLecturerPK;
    }

    public void setFacultyLecturerPK(FacultyLecturerPK facultyLecturerPK) {
        this.facultyLecturerPK = facultyLecturerPK;
    }
}

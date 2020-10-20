package entity;

public class StudentCourse implements SuperEntity{
    private StudentCoursePK studentCoursePK;

    public StudentCourse() {
    }

    public StudentCourse(StudentCoursePK studentCoursePK) {
        this.studentCoursePK = studentCoursePK;
    }

    public StudentCourse(String studentId, String courseId) {
        this.studentCoursePK = new StudentCoursePK(studentId,courseId);
    }


    public StudentCoursePK getStudentCoursePK() {
        return studentCoursePK;
    }

    public void setStudentCoursePK(StudentCoursePK studentCoursePK) {
        this.studentCoursePK = studentCoursePK;
    }
}

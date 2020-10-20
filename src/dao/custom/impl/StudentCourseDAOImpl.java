package dao.custom.impl;
import dao.CrudUtil;
import dao.custom.StudentCourseDAO;
import entity.StudentCourse;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDAOImpl implements StudentCourseDAO {
    @Override
    public List<StudentCourse> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM StudentCourse");
        ArrayList<StudentCourse> studentCourses = new ArrayList<>();
        while(rst.next()){
            studentCourses.add(new StudentCourse(rst.getString(1),rst.getString(2)));
        }
        return studentCourses;

    }

    @Override
    public StudentCourse find(String pk) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM StudentCourse WHERE studentId = ?",pk);
        while(rst.next()){
            return new StudentCourse(rst.getString(1),rst.getString(2));
        }
        return null;
    }

    @Override
    public boolean save(StudentCourse entity) throws Exception {
        return CrudUtil.execute("INSERT INTO StudentCourse VALUES (?,?)", entity.getStudentCoursePK().getStudentId(),
                entity.getStudentCoursePK().getCourseId());

    }

    @Override
    public boolean update(StudentCourse entity) throws Exception {
        return CrudUtil.execute("UPDATE StudentCourse SET courseId=? WHERE studentId=?",
                entity.getStudentCoursePK().getCourseId(),entity.getStudentCoursePK().getStudentId());

    }

    @Override
    public boolean delete(String pk) throws Exception {
        return CrudUtil.execute("DELETE FROM StudentCourse WHERE studentId=?",pk);
    }
}

package dao.custom;

import dao.CrudDAO;
import entity.Course;

public interface CourseDAO extends CrudDAO<Course,String> {
    String getCoursesCount() throws Exception;
    String getLasCoursesId() throws Exception;
}

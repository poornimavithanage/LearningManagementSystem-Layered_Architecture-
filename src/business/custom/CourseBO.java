package business.custom;

import business.SuperBO;
import util.CourseTM;

import java.util.List;

public interface CourseBO extends SuperBO {
    List<CourseTM> getAllCourses() throws Exception;
    boolean saveCourseDetails(String id, String title, String type, String duration) throws Exception;
    boolean deleteCourse(String id) throws Exception;
    boolean updateCourse(String title, String type, String duration, String id) throws Exception;
    String getNewCourseId() throws Exception;
    String getCoursesCount() throws Exception;
}

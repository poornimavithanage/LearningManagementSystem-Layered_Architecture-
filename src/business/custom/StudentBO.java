package business.custom;

import business.SuperBO;
import entity.CustomEntity;
import util.*;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    String getNewStudentId() throws Exception;
    List<StudentTM> getAllStudents() throws Exception;
    boolean saveStudent(String id, String facultyId, String name, String address, String contact, String nic, String email, String userId) throws Exception;
    boolean deleteStudent(String id)throws Exception;
    boolean updateStudent(String facultyId, String name, String address, String contact, String username, String password, String nic, String email, String id, String userId) throws Exception;
    List<CourseTM> getStudentCourses(String studentId) throws Exception;
    CourseTM getCourseDetails(String studentId, String courseId) throws Exception;
    List<AnnouncementTM> getAnnouncements(String courseId) throws Exception;
    StudentTM getStudent(String id) throws Exception;
    String getUserId(String studentId) throws Exception;
    String getStudentCount() throws Exception;
    String getStudentId(String userId) throws Exception;
    String getStudentIdUsingUsername(String username) throws Exception;

}

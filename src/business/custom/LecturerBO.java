package business.custom;

import business.SuperBO;
import util.AnnouncementTM;
import util.CourseTM;
import util.FacultyTM;
import util.LecturerTM;

import java.util.List;

public interface LecturerBO extends SuperBO {

    List<LecturerTM> getAllLecturers() throws Exception;

    boolean saveLecturer(String id, String courseId, String name, String address, String contact, String nic, String email, String userId) throws Exception;

    boolean deleteLecturer(String id)throws Exception;

    boolean updateLecturer(String courseId, String name, String address, String contact, String nic, String email, String id, String userId) throws Exception;

    String getNewLecturerId()throws Exception;

    List<FacultyTM> getLecturerFaculties(String lecturerId) throws Exception;

    List<CourseTM> getLecturerFacultyCourses(String lecturerId, String facultyId) throws Exception;

    List<AnnouncementTM> getAnnouncements(String courseId) throws Exception;

    LecturerTM getLecturer(String id)throws Exception;

    String getUserId(String lecturerId) throws Exception;

    String getLecturerCount() throws Exception;

    String getLecturerId(String userId) throws Exception;
}

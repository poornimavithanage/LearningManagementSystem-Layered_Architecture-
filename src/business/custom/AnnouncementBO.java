package business.custom;

import business.SuperBO;
import util.AnnouncementTM;
import util.CourseTM;

import java.sql.Date;
import java.util.List;

public interface AnnouncementBO extends SuperBO {
    List<AnnouncementTM> getAllAnnouncements() throws Exception;
    boolean saveAnnouncement(String courseId, String lecturerId, Date date, String announcement) throws Exception;
    boolean deleteAnnouncement(String courseId) throws Exception;
    boolean updateAnnouncement(String lecturerId, Date date, String announcement, String courseId) throws Exception;
}

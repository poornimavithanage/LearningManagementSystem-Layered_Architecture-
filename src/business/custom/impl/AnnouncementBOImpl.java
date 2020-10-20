package business.custom.impl;

import business.custom.AnnouncementBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.AnnouncementDAO;
import entity.Announcement;
import util.AnnouncementTM;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementBOImpl implements AnnouncementBO {
    @Override
    public List<AnnouncementTM> getAllAnnouncements() throws Exception {
        try {
            AnnouncementDAO announcementDAO = DAOFactory.getInstance().getDAO(DAOType.ANNOUNCEMENT);
            List<Announcement> allAnnouncements = announcementDAO.findAll();
            List<AnnouncementTM> announcements = new ArrayList<>();
            for (Announcement ann : allAnnouncements) {
                announcements.add(new AnnouncementTM(ann.getAnnouncementPK().getCourseId(),ann.getAnnouncementPK().getLecturerId(),ann.getDate(),ann.getAnnouncement()));
            }
            return announcements;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveAnnouncement(String courseId, String lecturerId, Date date, String announcement) throws Exception {
        try {
            AnnouncementDAO announcementDAO = DAOFactory.getInstance().getDAO(DAOType.ANNOUNCEMENT);
            return announcementDAO.save(new Announcement(courseId,lecturerId,date,announcement));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAnnouncement(String courseId) throws Exception {
        try {
            AnnouncementDAO announcementDAO = DAOFactory.getInstance().getDAO(DAOType.ANNOUNCEMENT);
            return announcementDAO.delete(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAnnouncement(String lecturerId, Date date, String announcement, String courseId) throws Exception {
        try {
            AnnouncementDAO announcementDAO = DAOFactory.getInstance().getDAO(DAOType.ANNOUNCEMENT);
            return announcementDAO.update(new Announcement(courseId, lecturerId, date,announcement));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

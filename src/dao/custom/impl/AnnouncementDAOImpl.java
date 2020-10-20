package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.AnnouncementDAO;
import entity.Announcement;
import entity.Course;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAOImpl implements AnnouncementDAO {
    @Override
    public List<Announcement> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Announcement");
        ArrayList<Announcement> announcements = new ArrayList<>();
        while(rst.next()){
            announcements.add(new Announcement(rst.getString(1),rst.getString(2),rst.getDate(3),rst.getString(4)));
        }
        return announcements;
    }

    @Override
    public Announcement find(String pk) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Announcement WHERE courseId = ?",pk);
        while(rst.next()){
            return new Announcement(rst.getString(1),rst.getString(2),rst.getDate(3),rst.getString(4));
        }
        return null;

    }

    @Override
    public boolean save(Announcement entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Announcement VALUES (?,?,?,?)", entity.getAnnouncementPK().getCourseId(),
                entity.getAnnouncementPK().getLecturerId(),entity.getDate(),entity.getAnnouncement());

    }

    @Override
    public boolean update(Announcement entity) throws Exception {
        return CrudUtil.execute("UPDATE Announcement SET date=?, announcement=? WHERE courseId=? and lecturerId=?",
                entity.getDate(),entity.getAnnouncement(),entity.getAnnouncementPK().getCourseId(),entity.getAnnouncementPK().getLecturerId());
    }

    @Override
    public boolean delete(String pk) throws Exception {
        return CrudUtil.execute("DELETE FROM Announcement WHERE courseId=?",pk);
    }
}

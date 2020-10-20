package business.custom.impl;

import business.custom.LecturerBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.*;
import entity.*;
import util.*;

import java.util.ArrayList;
import java.util.List;

public class LecturerBOImpl implements LecturerBO {
    // Field Injection
    private LecturerDAO lecturerDAO = DAOFactory.getInstance().getDAO(DAOType.LECTURER);

    @Override
    public List<LecturerTM> getAllLecturers() throws Exception {

        List<Lecturer>allLecturers = lecturerDAO.findAll();
        List<LecturerTM>lectures = new ArrayList<>();
        for (Lecturer lecturer:allLecturers) {
            lectures.add(new LecturerTM(lecturer.getId(),
                    lecturer.getCourseId(),lecturer.getName(),
                    lecturer.getAddress(),lecturer.getContact(),
                    lecturer.getNic(),lecturer.getEmail(),lecturer.getUserId()));
        }
        return lectures;
    }

    @Override
    public boolean saveLecturer(String id, String courseId, String name, String address, String contact, String nic, String email, String userId) throws Exception {
        return lecturerDAO.save(new Lecturer(id,courseId,name,address,contact,nic,email,userId));

    }

    @Override
    public boolean deleteLecturer(String id) throws Exception {
        return lecturerDAO.delete(id);
    }

    @Override
    public boolean updateLecturer(String courseId, String name, String address, String contact, String nic, String email, String id, String userId) throws Exception {
        return lecturerDAO.update(new Lecturer(id,courseId,name, address,contact,nic,email,userId));
    }

    @Override
    public String getNewLecturerId() throws Exception {
        try {
            String lastLecturerId = lecturerDAO.getLastLecturerId();
            if (lastLecturerId == null) {
                return "L001";
            } else {
                int maxId = Integer.parseInt(lastLecturerId.replace("L", ""));
                maxId = maxId + 1;
                String id = "";
                if (maxId < 10) {
                    id = "L00" + maxId;
                } else if (maxId < 100) {
                    id = "L0" + maxId;
                } else {
                    id = "L" + maxId;
                }
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FacultyTM> getLecturerFaculties(String lecturerId) throws Exception {
        QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
        List<Faculty> lecturerFaculties = queryDAO.findLecturerFaculties(lecturerId);
        ArrayList<FacultyTM> facultyTMS = new ArrayList<>();

        for (Faculty faculty : lecturerFaculties) {
            facultyTMS.add(new FacultyTM(faculty.getId(),faculty.getName(),faculty.getAddress()));
        }
        return facultyTMS;
    }

    public List<CourseTM> getLecturerFacultyCourses(String lecturerId, String facultyId) throws Exception {
        QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
        List<Course> lecturerFacultyCourses = queryDAO.findLecturerFacultyCourses(lecturerId,facultyId);
        ArrayList<CourseTM> courseTMS = new ArrayList<>();

        for (Course course : lecturerFacultyCourses) {
            courseTMS.add(new CourseTM(course.getId(),course.getTitle(),course.getType(),course.getDuration()));
        }
        return courseTMS;
    }

    public List<ModuleTM> getCourseModules(String courseId) throws Exception {
        ModuleDAO moduleDAO = DAOFactory.getInstance().getDAO(DAOType.MODULE);
        List<Module> courseModules = moduleDAO.getCourseModules(courseId);
        ArrayList<ModuleTM> moduleTMS = new ArrayList<>();

        for (Module module : courseModules) {
            moduleTMS.add(new ModuleTM(module.getId(),module.getTitle(),module.getDuration(),module.getCredits(),module.getCourseId()));
        }
        return moduleTMS;
    }

    public List<AnnouncementTM> getAnnouncements(String courseId) throws Exception {
        QueryDAO queryDAO= DAOFactory.getInstance().getDAO(DAOType.QUERY);
        List<CustomEntity> announcements = queryDAO.getAnnouncements(courseId);
        ArrayList<AnnouncementTM> announcement = new ArrayList<>();

        for (CustomEntity ann : announcements) {
            announcement.add(new AnnouncementTM(ann.getCourseId(),ann.getLecturerId(),ann.getDate(),ann.getAnnouncement()));
        }
        return announcement;
    }

    @Override
    public LecturerTM getLecturer(String id) throws Exception {
        LecturerDAO lecturerDAO = DAOFactory.getInstance().getDAO(DAOType.LECTURER);
        Lecturer lecturerDetails=lecturerDAO.find(id);
        return new LecturerTM(lecturerDetails.getId(),lecturerDetails.getCourseId(),lecturerDetails.getName(),lecturerDetails.getAddress(),lecturerDetails.getContact(),lecturerDetails.getNic(),lecturerDetails.getEmail(),lecturerDetails.getUserId());
    }

    public String getUserId(String lecturerId) throws Exception {
        LecturerDAO lecturerDAO = DAOFactory.getInstance().getDAO(DAOType.LECTURER);
        String userId = lecturerDAO.getUserId(lecturerId);
        return userId;
    }

    public String getLecturerCount() throws Exception {
        LecturerDAO lecturerDAO = DAOFactory.getInstance().getDAO(DAOType.LECTURER);
        String lecturerCount = lecturerDAO.getLecturerCount();
        return lecturerCount;
    }

    public String getLecturerId(String userId) throws Exception {
        LecturerDAO lecturerDAO = DAOFactory.getInstance().getDAO(DAOType.LECTURER);
        String lecturerId = lecturerDAO.getLecturerId(userId);
        return lecturerId;
    }
}

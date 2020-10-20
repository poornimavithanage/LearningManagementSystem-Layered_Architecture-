package business.custom.impl;

import business.custom.CourseBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.ContentDAO;
import dao.custom.CourseDAO;
import dao.custom.FacultyDAO;
import dao.custom.LecturerDAO;
import entity.Course;
import util.CourseTM;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {
    private CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
    @Override
    public List<CourseTM> getAllCourses() throws Exception {
        try {
            CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
            List<Course> allCourses = courseDAO.findAll();
            List<CourseTM> courses = new ArrayList<>();
            for (Course course : allCourses) {
                courses.add(new CourseTM(course.getId(),course.getTitle(),course.getType(),course.getDuration()));
            }
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveCourseDetails(String id, String title, String type, String duration) throws Exception {
        try {
            CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
            return courseDAO.save(new Course(id,title,type,duration));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCourse(String id) throws Exception {
        try {
            CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
            return courseDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCourse(String title, String type, String duration, String id) throws Exception {
        try {
            CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
            return courseDAO.update(new Course(id, title, type,duration));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getNewCourseId() throws Exception {
        try {
            String lastCoursesId = courseDAO.getLasCoursesId();
            if (lastCoursesId == null) {
                return "C001";
            } else {
                int maxId = Integer.parseInt(lastCoursesId.replace("C", ""));
                maxId = maxId + 1;
                String id = "";
                if (maxId < 10) {
                    id = "C00" + maxId;
                } else if (maxId < 100) {
                    id = "C0" + maxId;
                } else {
                    id = "C" + maxId;
                }
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCoursesCount() throws Exception {
        CourseDAO courseDAO = DAOFactory.getInstance().getDAO(DAOType.COURSE);
        String courseCount = courseDAO.getCoursesCount();
        return courseCount;
    }
}

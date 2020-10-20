package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOType daoType) {
        switch (daoType) {
            case ADMIN:
                return (T) new AdminDAOImpl();
            case ANNOUNCEMENT:
                return (T) new AnnouncementDAOImpl();
            case CONTENT:
                return (T) new ContentDAOImpl();
            case COURSE:
                return (T) new CourseDAOImpl();
            case FACULTY:
                return (T) new FacultyDAOImpl();
            case FACULTYCONTACT:
                return (T) new FacultyContactDAOImpl();
            case FACULTYLECTURER:
                return (T) new FacultyLecturerDAOImpl();
            case LECTURER:
                return (T) new LecturerDAOImpl();
            case MODULE:
                return (T) new ModuleDAOImpl();
            case LECTURERMODULE:
                return (T) new LecturerModuleDAOImpl();
            case STUDENT:
                return (T) new StudentDAOImpl();
            case STUDENTCOURSE:
                return (T) new StudentCourseDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            default:
                return null;
        }
    }
}

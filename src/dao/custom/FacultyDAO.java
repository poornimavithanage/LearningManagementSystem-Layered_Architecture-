package dao.custom;

import dao.CrudDAO;
import entity.Faculty;

public interface FacultyDAO extends CrudDAO<Faculty,String> {
    String getFacultyCount() throws Exception;
    String getLastFacultyId() throws Exception;
}

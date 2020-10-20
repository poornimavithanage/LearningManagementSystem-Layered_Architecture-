package dao.custom;

import dao.CrudDAO;
import entity.Student;

public interface StudentDAO extends CrudDAO<Student,String> {
    String getLastStudentId() throws Exception;
    String getUserId(String pk) throws Exception;
    String getStudentCount() throws Exception;
    String getStudentId(String userId) throws Exception;
}

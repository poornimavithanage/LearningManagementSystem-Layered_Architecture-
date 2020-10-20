package dao.custom;


import dao.CrudDAO;
import entity.Lecturer;

public interface LecturerDAO extends CrudDAO<Lecturer,String> {
    String getLastLecturerId() throws Exception;
    String getLecturerCount() throws Exception;
    String getUserId(String pk) throws Exception;
    String getLecturerId(String fk) throws Exception;
}

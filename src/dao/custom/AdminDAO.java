package dao.custom;

import dao.CrudDAO;
import entity.Admin;

public interface AdminDAO extends CrudDAO<Admin,String> {
    String getLastAdminId() throws Exception;
    String getUserId(String pk) throws Exception;
    String getAdminId(String fk) throws Exception;

}

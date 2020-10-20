package dao.custom;

import dao.CrudDAO;
import entity.Content;

import java.sql.SQLException;
import java.util.List;

public interface ContentDAO extends CrudDAO<Content,String> {
    String findModuleContentCount(String moduleId) throws Exception;
    String getLastContentId() throws Exception;
}

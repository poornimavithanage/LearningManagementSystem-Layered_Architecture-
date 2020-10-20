package dao.custom;

import dao.CrudDAO;
import entity.Module;

import java.sql.SQLException;
import java.util.List;

public interface ModuleDAO extends CrudDAO<Module,String> {
    String getLastModuleId() throws Exception;
    List<Module> getCourseModules(String courseId) throws Exception;
}

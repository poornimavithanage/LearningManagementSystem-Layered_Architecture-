package business.custom;

import business.SuperBO;
import util.ModuleTM;

import java.util.List;

public interface ModuleBO extends SuperBO {
    String getNewModuleId() throws Exception;
    List<ModuleTM> getAllModules() throws Exception;
    boolean saveModule(String id,String title,String duration, String credits,String courseId) throws Exception;
    boolean deleteModule(String id)throws Exception;
    boolean updateModule(String title,String duration, String credits,String courseId,String id)throws Exception;
    List<ModuleTM> getCourseModules(String courseId) throws Exception;
    ModuleTM getModule(String moduleId) throws Exception;
}

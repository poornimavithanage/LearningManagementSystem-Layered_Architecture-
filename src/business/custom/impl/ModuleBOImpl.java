package business.custom.impl;

import business.custom.ModuleBO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ModuleDAO;
import entity.Module;
import util.CourseTM;
import util.ModuleTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleBOImpl implements ModuleBO {
    private ModuleDAO moduleDAO = DAOFactory.getInstance().getDAO(DAOType.MODULE);
    @Override
    public String getNewModuleId() throws Exception {
        String lastModuleId = moduleDAO.getLastModuleId();
        if (lastModuleId == null){
            return "M001";
        }else{
            int maxId=  Integer.parseInt(lastModuleId.replace("M",""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "M00" + maxId;
            } else if (maxId < 100) {
                id = "M0" + maxId;
            } else {
                id = "M" + maxId;
            }
            return id;
        }
    }

    @Override
    public List<ModuleTM> getAllModules() throws Exception {
        List<Module> allModules = moduleDAO.findAll();
        ArrayList<ModuleTM> modules = new ArrayList<>();
        for (Module module : allModules) {
            modules.add(new ModuleTM(module.getId(),module.getTitle(),module.getDuration(),module.getCredits(),module.getCourseId()));
        }
        return modules;
    }

    @Override
    public boolean saveModule(String id, String title, String duration, String credits, String courseId) throws Exception {
        return moduleDAO.save(new Module(id,title,duration,credits,courseId));
    }

    @Override
    public boolean deleteModule(String id) throws Exception {
        return moduleDAO.delete(id);
    }

    @Override
    public boolean updateModule(String title, String duration, String credits, String courseId, String id) throws Exception {
        return moduleDAO.update(new Module(id,title,duration,credits,courseId));
    }

    public List<ModuleTM> getCourseModules(String courseId) throws Exception {
        List<Module> courseModules = moduleDAO.getCourseModules(courseId);
        ArrayList<ModuleTM> modules = new ArrayList<>();

        for (Module module :courseModules) {
            modules.add(new ModuleTM(module.getId(),module.getTitle(),module.getDuration(),module.getCredits(),module.getCourseId()));
        }
        return modules;
    }
    public ModuleTM getModule(String moduleId) throws Exception {
        ModuleDAO moduleDAO = DAOFactory.getInstance().getDAO(DAOType.MODULE);
        Module module= moduleDAO.find(moduleId);
        return new ModuleTM(module.getId(),module.getTitle(),module.getDuration(),module.getCredits(),module.getCourseId());
    }
}

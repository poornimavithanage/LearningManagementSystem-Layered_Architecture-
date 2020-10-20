package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ModuleDAO;
import entity.Module;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ModuleDAOImpl implements ModuleDAO {
    @Override
    public List<Module> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Module");
        ArrayList<Module> modules = new ArrayList<>();
        while (rst.next()){
            modules.add( new Module(rst.getString(1), rst.getString(2),
                    rst.getString(3),rst.getString(4),rst.getString(5)));
        }
        return modules;
    }

    @Override
    public Module find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Module WHERE id =?",key);
        if (rst.next()){
            return new Module(rst.getString(1), rst.getString(2),
                    rst.getString(3),rst.getString(4),rst.getString(5));
        }
        return null;
    }

    @Override
    public boolean save(Module module) throws Exception {
        return CrudUtil.execute("INSERT INTO Module VALUES (?,?,?,?,?)",
                module.getId(),module.getTitle(),module.getDuration(),module.getCredits(),module.getCourseId());
    }

    @Override
    public boolean update(Module module) throws Exception {
        return CrudUtil.execute("UPDATE Module SET title=?, duration=?,credits=?,courseId=? WHERE id=?",
                module.getTitle(),module.getDuration(),module.getCredits(),module.getCourseId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM Module WHERE id=?", key);
    }

    @Override
    public String getLastModuleId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Module ORDER BY id DESC  LIMIT 1");
        if (rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }
    }

    public List<Module> getCourseModules(String courseId) throws Exception {
        ResultSet resultSet= CrudUtil.execute("SELECT * FROM Module WHERE courseId=?", courseId);
        ArrayList<Module> courseModules = new ArrayList<>();

        while(resultSet.next()){
            courseModules.add(new Module(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
        }
        return courseModules;
    }


}

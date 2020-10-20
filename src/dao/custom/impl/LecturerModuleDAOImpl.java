package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.LecturerModuleDAO;
import entity.LecturerModule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LecturerModuleDAOImpl implements LecturerModuleDAO {
    public List<LecturerModule> findAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM LecturerModule");
        ArrayList<LecturerModule> lecturerModules = new ArrayList<>();
        while(resultSet.next()){
            lecturerModules.add(new LecturerModule(resultSet.getString(1),resultSet.getString(2)));
        }
        return lecturerModules;
    }

    public LecturerModule find(String pk) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM LecturerModule WHERE moduleId=?",pk);
        if(resultSet.next()){
            return new LecturerModule(resultSet.getString(1),resultSet.getString(2));
        }
        return null;
    }

    public boolean save(LecturerModule entity) throws SQLException {
        Boolean result = CrudUtil.execute("INSERT INTO LecturerModule VALUES(?,?)",entity.getLecturerModulePK().getModuleId(),entity.getLecturerModulePK().getLecturerId());
        if(!result){
            return false;
        }
        return true;
    }

    public boolean update(LecturerModule entity) throws SQLException {
        Boolean result = CrudUtil.execute("UPDATE LecturerModule SET lectureId=? WHERE moduleId=?",entity.getLecturerModulePK().getLecturerId(),entity.getLecturerModulePK().getModuleId());
        if(!result){
            return false;
        }
        return true;
    }

    public boolean delete(String pk) throws SQLException {
        Boolean result = CrudUtil.execute("DELETE FROM LecturerModule WHERE moduleId=?",pk);
        if(!result){
            return false;
        }
        return true;
    }
}

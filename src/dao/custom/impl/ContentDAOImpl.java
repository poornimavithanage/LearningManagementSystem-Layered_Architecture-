package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ContentDAO;
import entity.Content;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContentDAOImpl implements ContentDAO {
    public List<Content> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Content");
        ArrayList<Content> contents = new ArrayList<>();
        while (resultSet.next()){
            contents.add(new Content(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3),resultSet.getString(4),resultSet.getString(5)));
        }
        return contents;
    }

    public Content find(String pk) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Content WHERE id=?",pk);
        if(resultSet.next()){
            return new Content(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3),resultSet.getString(4),resultSet.getString(5));
        }
        return null;
    }

    public boolean save(Content entity) throws Exception {
        boolean result= CrudUtil.execute("INSERT INTO Content VALUES(?,?,?,?,?)", entity.getId(), entity.getTitle(), entity.getDate(), entity.getLecturerID(), entity.getModuleId());
        if(!result){
            return false;
        }
        return true;
    }

    public boolean update(Content entity) throws Exception {
        boolean result = CrudUtil.execute("UPDATE CONTENT SET title=?,date=?,lecturerID=?,moduleId=? WHERE id=?", entity.getTitle(), entity.getDate(), entity.getLecturerID(), entity.getModuleId(), entity.getId());
        if(!result){
            return false;
        }
        return true;
    }

    public boolean delete(String pk) throws Exception {
        boolean result = CrudUtil.execute("DELETE FROM Content WHERE id=?", pk);
        if(!result){
            return false;
        }
        return true;
    }

    public String findModuleContentCount(String moduleId) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT(distinct id) FROM Content WHERE moduleId=?",moduleId);
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public String getLastContentId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM Content ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}

package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.UserDAO;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM `User`");
        ArrayList<User> allUsers = new ArrayList<>();
        while(resultSet.next()){
            allUsers.add(new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }
        return allUsers;
    }

    @Override
    public User find(String pk) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM `User` WHERE id=?",pk);
        if(resultSet.next()){
            return new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
        }
        return null;
    }

    @Override
    public boolean save(User entity) throws Exception {
        boolean result = CrudUtil.execute("INSERT INTO `User` VALUES (?,?,?,?)",entity.getId(),entity.getUsername(),entity.getPassword(),entity.getPassword());
        if(!result){
            return false;
        }
        return true;
    }

    @Override
    public boolean update(User entity) throws Exception {
        boolean result = CrudUtil.execute("UPDATE `User` SET username?,password?,userRole? WHERE id=?",entity.getUsername(),entity.getPassword(),entity.getPassword(),entity.getId());
        if(!result){
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String pk) throws Exception {
        boolean result =  CrudUtil.execute("DELETE  FROM `User` WHERE id=?",pk);
        if(!result){
            return false;
        }
        return true;
    }

    public String findLastUserId() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM User ORDER BY id DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public boolean isValidUser(String username, String password) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT COUNT FROM User WHERE username=? AND password=?", username, password);
        if (resultSet.next()) {
            return true;
        }
        return false;
    }
}

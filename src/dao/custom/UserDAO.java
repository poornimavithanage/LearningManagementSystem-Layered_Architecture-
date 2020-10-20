package dao.custom;

import dao.CrudDAO;

import entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User,String> {
    String findLastUserId() throws Exception;
    boolean isValidUser(String username, String password) throws Exception;
}

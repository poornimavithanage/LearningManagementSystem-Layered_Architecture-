package business.custom.impl;

import business.custom.UserBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.UserDAO;
import entity.User;
import util.UserTM;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    public List<UserTM> getAllUsers() throws Exception {
        UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
        List<User> all = userDAO.findAll();
        ArrayList<UserTM> allUsers = new ArrayList<>();
        for (User user : all) {
            allUsers.add(new UserTM(user.getId(),user.getUsername(),user.getPassword(),user.getUserRole()));
        }
        return allUsers;
    }

    public UserTM getUser(String userId) throws Exception {
        UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
        User user = userDAO.find(userId);
        return new UserTM(user.getId(),user.getUsername(),user.getPassword(),user.getUserRole());
    }

    public boolean update(String userId, String username, String password, String userRole) throws Exception {
        UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
        boolean update = userDAO.update(new User(userId, username, password, userRole));
        return update;
    }

    public boolean save(String userId, String userName, String password, String userRole) throws Exception {
        UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
        boolean save = userDAO.save(new User(userId, userName, password, userRole));
        return save;
    }

    public boolean delete(String userId) throws Exception {
        UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
        boolean delete = userDAO.delete(userId);
        return delete;
    }

    public String generateNewId() throws Exception {
        UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
        String lastUserId = userDAO.findLastUserId();
        int num = Integer.parseInt(lastUserId.substring(1,4));
        String newId;
        if(num==0){
            newId = "U001";
        }
        else if(num<10){
            num++;
            newId = "U00"+num;
        }
        else if(num<100){
            num++;
            newId = "U0"+num;
        }
        else{
            num++;
            newId = "U" + num;
        }
        return newId;
    }

    public boolean isValidUser(String username, String password) throws Exception {
        UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOType.USER);
        boolean validUser = userDAO.isValidUser(username, password);
        return validUser;
    }


}

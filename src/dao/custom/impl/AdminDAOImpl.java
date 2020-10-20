package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.AdminDAO;
import entity.Admin;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public List<Admin> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Admin");
        ArrayList<Admin> admins = new ArrayList<>();
        while (rst.next()){
            admins.add( new Admin(rst.getString(1), rst.getString(2),
                    rst.getString(3),rst.getString(4)));
        }
        return admins;
    }

    @Override
    public Admin find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Admin WHERE id =?",key);
        if (rst.next()){
            return new Admin(rst.getString(1), rst.getString(2),
                    rst.getString(3),rst.getString(4));
        }
        return null;
    }

    @Override
    public boolean save(Admin admin) throws Exception {
        return CrudUtil.execute("INSERT INTO Admin VALUES (?,?,?,?,?)",
                admin.getId(),admin.getName(),admin.getContact(),admin.getUserId());
    }

    @Override
    public boolean update(Admin admin) throws Exception {
        return CrudUtil.execute("UPDATE Admin SET name=?, contact=?,userId=? WHERE id=?",
                admin.getName(),admin.getContact(),admin.getUserId(),admin.getId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM Admin WHERE id=?", key);
    }

    @Override
    public String getLastAdminId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Admin ORDER BY id DESC  LIMIT 1");
        if (rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }
    }

    public String getUserId(String pk) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT userId FROM LM_System.Admin WHERE id=?", pk);
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    public String getAdminId(String fk) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT id FROM LM_System.Admin WHERE id=?", fk);
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }
}

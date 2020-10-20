package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.FacultyDAO;
import entity.Faculty;
import entity.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAOImpl implements FacultyDAO {
    @Override
    public String getLastFacultyId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Faculty ORDER BY id DESC  LIMIT 1");
        if (rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }
    }

    @Override
    public List<Faculty> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Faculty");
        ArrayList<Faculty> faculties = new ArrayList<>();
        while (rst.next()){
            faculties.add( new Faculty(rst.getString(1), rst.getString(2),
                    rst.getString(3)));
        }
        return faculties;
    }

    @Override
    public Faculty find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Faculty WHERE id =?",key);
        if (rst.next()){
            return new Faculty(rst.getString(1), rst.getString(2),
                    rst.getString(3));
        }
        return null;
    }

    @Override
    public boolean save(Faculty faculty) throws Exception {
        return CrudUtil.execute("INSERT INTO Faculty VALUES (?,?,?)",
                faculty.getId(),faculty.getName(),faculty.getAddress()
                );
    }

    @Override
    public boolean update(Faculty faculty) throws Exception {
        return CrudUtil.execute("UPDATE Faculty SET name=?, address=? WHERE id=?",
                faculty.getName(),faculty.getAddress(),faculty.getId());
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM Faculty WHERE id=?",key);
    }

    public String getFacultyCount() throws Exception{
        ResultSet rst = CrudUtil.execute("SELECT COUNT(*) AS Total FROM Faculty");
        if (rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }
    }
}

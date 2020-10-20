package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.FacultyLecturerDAO;
import entity.FacultyLecturer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyLecturerDAOImpl implements FacultyLecturerDAO {
    public List<FacultyLecturer> findAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM FacultyLecturer");
        ArrayList<FacultyLecturer>  facultyLecturers = new ArrayList<>();
        while(resultSet.next()){
            facultyLecturers.add(new FacultyLecturer(resultSet.getString(1),resultSet.getString(2)));
        }
        return facultyLecturers;
    }

    public FacultyLecturer find(String pk) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM FacultyLecturer WHERE facultyId=?", pk);
        if(resultSet.next()){
            return new FacultyLecturer(resultSet.getString(1),resultSet.getString(2));
        }
        return null;
    }

    public boolean save(FacultyLecturer entity) throws SQLException {
        Boolean result = CrudUtil.execute("INSERT INTO FacultyLecturer VALUES(?,?)",entity.getFacultyLecturerPK().getFacultyId(),entity.getFacultyLecturerPK().getLectureId());
        if(!result){
            return false;
        }
        return true;
    }

    public boolean update(FacultyLecturer entity) throws SQLException {
        Boolean result = CrudUtil.execute("UPDATE FacultyLecturer SET lecturerId=? WHERE facultyId-?",entity.getFacultyLecturerPK().getLectureId(),entity.getFacultyLecturerPK().getFacultyId());
        if(!result){
            return false;
        }
        return true;
    }

    public boolean delete(String pk) throws SQLException {
        Boolean result = CrudUtil.execute("DELETE FROM FacultyLecturer WHERE facultyId-?",pk);
        if(!result){
            return false;
        }
        return true;
    }
}

package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CourseDAO;
import entity.Course;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    public String getLasCoursesId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT id FROM Course ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        } else {
            return null;
        }
    }
    public List<Course> findAll() throws Exception{
        ResultSet rst = CrudUtil.execute("SELECT * FROM Course");
        ArrayList<Course> courses = new ArrayList<>();
        while(rst.next()){
            courses.add(new Course(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return courses;

    }

    public Course find(String pk) throws Exception{
        ResultSet rst = CrudUtil.execute("SELECT * FROM Course WHERE id = ?",pk);
        while(rst.next()){
            return new Course(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4));
        }
        return null;

    }

    public boolean save(Course entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Course VALUES (?,?,?,?)", entity.getId(),
                entity.getTitle(),entity.getType(),entity.getDuration());
    }

    public boolean update(Course entity) throws Exception{
        return CrudUtil.execute("UPDATE Course SET title=?, type=?,duration=? WHERE id=?",entity.getTitle(),entity.getType(),entity.getDuration(),entity.getId());
    }


    public boolean delete(String pk) throws Exception{
        return CrudUtil.execute("DELETE FROM Course WHERE id=?",pk);

    }
    public String getCoursesCount() throws Exception{
        ResultSet rst = CrudUtil.execute("SELECT COUNT(*) AS Total FROM Course");
        if (rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }
    }
}

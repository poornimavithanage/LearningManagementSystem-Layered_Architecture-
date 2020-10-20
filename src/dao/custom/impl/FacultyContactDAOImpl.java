package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.FacultyContactDAO;
import entity.FacultyContact;
import entity.FacultyContactPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FacultyContactDAOImpl implements FacultyContactDAO {

    @Override
    public List<FacultyContact> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM FacultyContact");
        List<FacultyContact> orderDetails = new ArrayList<>();
        while (rst.next()) {
            orderDetails.add(new FacultyContact(rst.getString(1),
                    rst.getString(2)));
        }
        return orderDetails;
    }

    @Override
    public FacultyContact find(FacultyContactPK pk) throws Exception {
        FacultyContactPK facultyContactPK = new FacultyContactPK();
        ResultSet rst =CrudUtil.execute("SELECT * FROM FacultyContact WHERE id=? AND contact=?",
                facultyContactPK.getId(),facultyContactPK.getContact());
        if (rst.next()) {
            return new FacultyContact(rst.getString(1),
                    rst.getString(2));
        }
        return null;
    }

    @Override
    public boolean save(FacultyContact facultyContact) throws Exception {
        return CrudUtil.execute("INSERT INTO FacultyContact VALUES (?,?)",
                facultyContact.getFacultyContactPK().getId(),
                facultyContact.getFacultyContactPK().getContact());
    }

    @Override
    public boolean update(FacultyContact facultyContact) throws Exception {
        return CrudUtil.execute("UPDATE FacultyContact SET contact=? WHERE id=?",
               facultyContact.getFacultyContactPK().getId(),facultyContact.getFacultyContactPK().getContact());
    }

    @Override
    public boolean delete(FacultyContactPK pk) throws Exception {
       FacultyContactPK facultyContactPK = new FacultyContactPK();
       return CrudUtil.execute("DELETE FROM FacultyContact WHERE id=? AND contact=?",facultyContactPK.getId(),
               facultyContactPK.getContact());
    }
}

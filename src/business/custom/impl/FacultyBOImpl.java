package business.custom.impl;

import business.custom.FacultyBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.FacultyDAO;
import dao.custom.LecturerDAO;
import entity.Faculty;
import util.FacultyTM;


import java.util.ArrayList;
import java.util.List;

public class FacultyBOImpl implements FacultyBO {
    // Field Injection
    private FacultyDAO facultyDAO = DAOFactory.getInstance().getDAO(DAOType.FACULTY);

    @Override
    public List<FacultyTM> getAllFaculties() throws Exception {

        List<Faculty> allFaculties = facultyDAO.findAll();
        List<FacultyTM>faculties = new ArrayList<>();
        for (Faculty faculty: allFaculties) {
            faculties.add(new FacultyTM(faculty.getId(),
                    faculty.getName(),faculty.getAddress()));


        }
        return faculties;
    }

    @Override
    public boolean saveFacultyDetails(String id, String name, String address) throws Exception {
        return facultyDAO.save(new Faculty(id,name,address));

    }

    @Override
    public boolean deleteFaculty(String id) throws Exception {
        return facultyDAO.delete(id);
    }

    @Override
    public boolean updateFaculty(String id, String name, String address) throws Exception {
        return facultyDAO.update(new Faculty(id,name,address));

    }

    @Override
    public String getNewFacultyId() throws Exception {
        try {
            String lastFacultyId = facultyDAO.getLastFacultyId();
            if (lastFacultyId == null) {
                return "F001";
            } else {
                int maxId = Integer.parseInt(lastFacultyId.replace("F", ""));
                maxId = maxId + 1;
                String id = "";
                if (maxId < 10) {
                    id = "F00" + maxId;
                } else if (maxId < 100) {
                    id = "F0" + maxId;
                } else {
                    id = "F" + maxId;
                }
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getFacultyCount() throws Exception {
        FacultyDAO facultyDAO = DAOFactory.getInstance().getDAO(DAOType.FACULTY);
        String facultyCount = facultyDAO.getFacultyCount();
        return facultyCount;
    }
}

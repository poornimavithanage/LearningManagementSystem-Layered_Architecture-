package business.custom;

import business.SuperBO;
import util.FacultyTM;

import java.util.List;

public interface FacultyBO extends SuperBO {
    List<FacultyTM> getAllFaculties() throws Exception;
    boolean saveFacultyDetails(String id, String name, String address) throws Exception;
    boolean deleteFaculty(String id) throws Exception;
    boolean updateFaculty(String id, String name, String address) throws Exception;
    public String getNewFacultyId()throws Exception;
    public String getFacultyCount() throws Exception;

}

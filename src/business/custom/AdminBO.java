package business.custom;

import business.SuperBO;
import util.AdminTM;

import java.util.List;

public interface AdminBO extends SuperBO {
    public String getNewAdminId() throws Exception;
    public List<AdminTM> getAllAdmins() throws Exception;
    public boolean deleteAdmin(String id)throws Exception;
    public boolean saveAdmin(String id, String name, String contact, String userId) throws Exception;
    public boolean updateAdmin(String id,String name, String contact, String userId) throws Exception;
    AdminTM getAdmin(String id) throws Exception;
    String getUserId(String adminId) throws Exception;
    String getAdminId(String userId) throws Exception;
}

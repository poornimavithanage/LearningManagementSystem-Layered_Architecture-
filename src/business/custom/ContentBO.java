package business.custom;

import business.SuperBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ContentDAO;
import entity.Content;
import util.ContentTM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface ContentBO extends SuperBO {
    List<ContentTM> getAllContent() throws Exception;
    ContentTM getContent(String contentId) throws Exception;
    boolean saveContent(String id, String title, Date date, String lecturerId, String moduleId) throws Exception;
    boolean updateContent(String id, String title, Date date, String lecturerId, String moduleId) throws Exception;
    boolean deleteContent(String id) throws Exception;
    String findModuleContentCount(String moduleId) throws Exception;
    List<ContentTM> getModuleContent(String moduleId) throws Exception;
    String getNewContentId() throws Exception;
}

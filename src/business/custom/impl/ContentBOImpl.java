package business.custom.impl;

import business.custom.ContentBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.ContentDAO;
import dao.custom.QueryDAO;
import dao.custom.QueryDAO;
import entity.Content;
import util.ContentTM;
import util.ModuleTM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ContentBOImpl implements ContentBO {
    public List<ContentTM> getAllContent() throws Exception {
        ContentDAO contentDAO = DAOFactory.getInstance().getDAO(DAOType.CONTENT);
        List<Content> allContent = contentDAO.findAll();
        List<ContentTM> content = new ArrayList<>();
        for (Object O : allContent) {
            Content c = (Content) O;
            content.add(new ContentTM(c.getId(),c.getTitle(),c .getDate(),c.getLecturerID(),c.getModuleId()));
        }
        return content;
    }

    public ContentTM getContent(String contentId) throws Exception {
        ContentDAO contentDAO = DAOFactory.getInstance().getDAO(DAOType.CONTENT);
        Content content = contentDAO.find(contentId);
        return new ContentTM(content.getId(),content.getTitle(),content.getDate(),content.getLecturerID(),content.getModuleId());
    }

    public boolean saveContent(String id, String title, Date date, String lecturerId, String moduleId) throws Exception {
        ContentDAO contentDao = DAOFactory.getInstance().getDAO(DAOType.CONTENT);
        boolean save = contentDao.save(new Content(id, title, date, lecturerId, moduleId));
        return save;
    }

    public boolean updateContent(String id, String title, Date date, String lecturerId, String moduleId) throws Exception {
        ContentDAO contentDao = DAOFactory.getInstance().getDAO(DAOType.CONTENT);
        boolean update = contentDao.update(new Content(id, title, date, lecturerId, moduleId));
        return update;
    }

    public boolean deleteContent(String id) throws Exception {
        ContentDAO contentDao = DAOFactory.getInstance().getDAO(DAOType.CONTENT);
        boolean delete = contentDao.delete(id);
        return delete;
    }
    public String findModuleContentCount(String moduleId) throws Exception {
        ContentDAO contentDAO = DAOFactory.getInstance().getDAO(DAOType.CONTENT);
        String moduleContentCount = contentDAO.findModuleContentCount(moduleId);
        return moduleContentCount;
    }

    public List<ContentTM> getModuleContent(String moduleId) throws Exception {
        QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
        List<Content> moduleContent = queryDAO.findModuleContent(moduleId);
        ArrayList<ContentTM> contentTMS = new ArrayList<>();

        for (Content content : moduleContent) {
            contentTMS.add(new ContentTM(content.getId(),content.getTitle(),content.getDate(),content.getLecturerID(),content.getModuleId()));
        }
        return contentTMS;
    }

    public String getNewContentId() throws Exception {
        ContentDAO contentDAO = DAOFactory.getInstance().getDAO(DAOType.CONTENT);
        String lastContentId = contentDAO.getLastContentId();
        int num = Integer.parseInt(lastContentId.substring(2,5));
        System.out.println(num);
        String newId;
        if(num==0){
            newId = "CT001";
        } else if (num<10) {
            num++;
            newId = "CT00" + num;
        } else if (num<100) {
            num++;
            newId = "CT0" + num;
        }
        else{
            num++;
            newId = "CT" + num;
        }
        return newId;
    }
}

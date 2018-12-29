package managedBean.my_announcement_view;

import model.daoclasses.AnnouncementDAO;
import model.dbclasses.Announcement;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@ManagedBean
@ViewScoped
public class DataScrollerView {

    private List<Announcement> announcementList;
    private Announcement announcement;
    private SessionFactory sessionFactory;

    @ManagedProperty("#{announcementService}")
    private AnnouncementService service;

    @PostConstruct
    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();
        service.createListAnnouncement();
        announcementList = service.getAnnouncementList();
    }

    @PreDestroy
    public void destroy() {
        service = null;
        announcementList = null;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public void editAnnouncement() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("announcement", announcement);
            FacesContext.getCurrentInstance().getExternalContext().redirect("editMyAnnouncement.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAnnouncement() {
        AnnouncementDAO announcementDAO = new AnnouncementDAO(sessionFactory);
        announcementDAO.delete(announcement);
        announcementList.remove(announcement);
    }

    public List<Announcement> getAnnouncementList() {
        return announcementList;
    }

    public void setAnnouncementList(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }

    public AnnouncementService getService() {
        return service;
    }

    public void setService(AnnouncementService service) {
        this.service = service;
    }
}
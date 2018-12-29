package managedBean.filter_list_announcement_view;

import model.daoclasses.AnnouncementDAO;
import model.daoclasses.CarBrandDAO;
import model.daoclasses.CarDAO;
import model.dbclasses.Announcement;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "filterAnnouncementService")
@RequestScoped
public class FilterAnnouncementService {

    private SessionFactory sessionFactory;
    private List<Announcement> announcementList;

    public void createFilterListAnnouncement(String filterParameter, String filterValue) {
        sessionFactory = HibernateUtil.getSessionFactory();
        AnnouncementDAO announcementDAO = new AnnouncementDAO(sessionFactory);
        CarDAO carDAO = new CarDAO(sessionFactory);
        CarBrandDAO carBrandDAO = new CarBrandDAO(sessionFactory);
    }
}
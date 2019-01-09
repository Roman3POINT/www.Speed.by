package managedBean.filter_list_announcement_view;

import model.daoclasses.AnnouncementDAO;
import model.daoclasses.CarBrandDAO;
import model.daoclasses.CarDAO;
import model.dbclasses.Announcement;
import model.dbclasses.Car;
import model.dbclasses.CarBrand;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class FilterListAnnouncement {

    private SessionFactory sessionFactory;
    private AnnouncementDAO announcementDAO;
    private CarDAO carDAO;
    private CarBrandDAO carBrandDAO;
    private String filterParameter;
    private String filterValue;
    private List<Announcement> list;

    @PostConstruct
    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();

        announcementDAO = new AnnouncementDAO(sessionFactory);
        carDAO = new CarDAO(sessionFactory);
        carBrandDAO = new CarBrandDAO(sessionFactory);

        list = announcementDAO.createQuery("SELECT u FROM ANNOUNCEMENT u", Announcement.class).list();
    }

    public List<Announcement> getList() {
        return list;
    }

    public void setList(List<Announcement> list) {
        this.list = list;
    }

    public String getFilterParameter() {
        return filterParameter;
    }

    public void setFilterParameter(String filterParameter) {
        this.filterParameter = filterParameter;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }
}
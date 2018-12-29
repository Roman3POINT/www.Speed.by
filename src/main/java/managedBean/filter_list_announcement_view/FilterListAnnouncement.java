package managedBean.filter_list_announcement_view;

import model.daoclasses.AnnouncementDAO;
import model.daoclasses.CarBrandDAO;
import model.dbclasses.Announcement;
import model.dbclasses.CarBrand;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class FilterListAnnouncement {

    private SessionFactory sessionFactory;

    private String filterParameter;
    private String filterValue;
    private List<Announcement> list;
    private AnnouncementDAO announcementDAO;

    @ManagedProperty("#{filterAnnouncementService}")
    private FilterAnnouncementService service;

    @PostConstruct
    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();

        announcementDAO = new AnnouncementDAO(sessionFactory);
        list = announcementDAO.createQuery("SELECT u FROM ANNOUNCEMENT u", Announcement.class).list();
    }

    public void filterBrand() {
        CarBrandDAO carBrandDAO = new CarBrandDAO(sessionFactory);
        CarBrand carBrand = carBrandDAO.createQuery("SELECT u FROM CARBRAND u WHERE u.name = :brandName", CarBrand.class)
                .setParameter("brandName", filterValue).getSingleResult();
        list = announcementDAO.getAllBrand(carBrand);
    }

    public List<Announcement> getList() {
        return list;
    }

    public void setList(List<Announcement> list) {
        this.list = list;
    }

    public FilterAnnouncementService getService() {
        return service;
    }

    public void setService(FilterAnnouncementService service) {
        this.service = service;
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
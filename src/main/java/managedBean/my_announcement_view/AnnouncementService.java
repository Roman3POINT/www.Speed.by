package managedBean.my_announcement_view;

import model.daoclasses.AnnouncementDAO;
import model.daoclasses.UserDAO;
import model.dbclasses.Announcement;
import model.dbclasses.User;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "announcementService")
@ViewScoped
public class AnnouncementService {

    private SessionFactory sessionFactory;
    private List<Announcement> announcementList;

    public void createListAnnouncement() {
        sessionFactory = HibernateUtil.getSessionFactory();

        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        String login = (String) params.get("Login");

        AnnouncementDAO announcementDAO = new AnnouncementDAO(sessionFactory);
        UserDAO userDAO = new UserDAO(sessionFactory);

        User user = userDAO.createQuery("SELECT u FROM USERS u WHERE u.email = :login", User.class).setParameter("login", login).getSingleResult();
        announcementList = announcementDAO.createQuery("SELECT u FROM ANNOUNCEMENT u WHERE u.user = :userId ", Announcement.class)
                .setParameter("userId", user).list();
    }

    public List<Announcement> getAnnouncementList() {
        return announcementList;
    }
}
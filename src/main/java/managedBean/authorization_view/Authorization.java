package managedBean.authorization_view;

import model.daoclasses.UserDAO;
import model.dbclasses.User;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class Authorization implements Serializable {

    private String login;
    private String password;
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        dbInitialize();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void userAuthorization() {

        sessionFactory = HibernateUtil.getSessionFactory();
        UserDAO userDAO = new UserDAO(sessionFactory);
        User user = userDAO.createQuery("SELECT u FROM USERS u where u.email = :login", User.class).setParameter("login", login).getSingleResult();

        if (password.equals(user.getPassword())) {
            try {
                password = " ";
                FacesContext.getCurrentInstance().getExternalContext().redirect("profile.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        password = null;
    }

    public void dbInitialize() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
}
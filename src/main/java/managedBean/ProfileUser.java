package managedBean;

import model.daoclasses.UserDAO;
import model.dbclasses.User;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class ProfileUser {

    private String fullName;
    private int age;
    private String phoneNumber;
    private String login;
    private SessionFactory sessionFactory;

    public void attr(ActionEvent event) {
        sessionFactory = HibernateUtil.getSessionFactory();
        login = (String) event.getComponent().getAttributes().get("paramLogin");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Login", login);
        UserDAO userDAO = new UserDAO(sessionFactory);

        User user = userDAO.createQuery("SELECT u FROM USERS u where u.email = :login", User.class).setParameter("login", login).getSingleResult();

        fullName = user.getFullName();
        age = user.getAge();
        phoneNumber = user.getPhoneNumber();
    }

    public void toAddCar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("addCar.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toFilterListAnnouncement() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("filterListAnnouncement.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Exit() {

        try {
            login = null;
            FacesContext.getCurrentInstance().getExternalContext().redirect("authorization.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @PreDestroy
    public void destroy() {
        sessionFactory.close();
    }
}
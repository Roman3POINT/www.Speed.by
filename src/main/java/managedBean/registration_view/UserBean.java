package managedBean.registration_view;

import model.daoclasses.UserDAO;
import model.dbclasses.User;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class UserBean {

    private String fullName;
    private int age;
    private String phoneNumber;
    private String login;
    private String sourcePassword;
    private String repeatPassword;
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void registration() {

        UserDAO userDAO = new UserDAO(sessionFactory);

        User user = new User();

        user.setFullName(fullName);
        user.setAge(age);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(login);

        if(sourcePassword.equals(repeatPassword)) {
            user.setPassword(sourcePassword);
            user.setDescription("Стажер SoftClub");
            userDAO.save(user);
        }

        userDAO.close();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("authorization.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSourcePassword() {
        return sourcePassword;
    }

    public void setSourcePassword(String sourcePassword) {
        this.sourcePassword = sourcePassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
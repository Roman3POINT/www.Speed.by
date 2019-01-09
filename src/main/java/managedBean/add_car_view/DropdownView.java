package managedBean.add_car_view;

import managedBean.CarColorsEngineInitialize;
import model.daoclasses.AnnouncementDAO;
import model.daoclasses.CarBrandDAO;
import model.daoclasses.UserDAO;
import model.dbclasses.Announcement;
import model.dbclasses.CarBrand;
import model.dbclasses.User;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean
@ViewScoped
public class DropdownView implements Serializable {

    private Map<String, Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String car;
    private String brand;
    private String engine;
    private String color;
    private String date;
    private int price;
    private String mileage;
    private String login;
    private Map<String,String> cars = new HashMap<String, String>();
    private Map<String,String> brands = new HashMap<String, String>();
    private Map<String,String> engines = new HashMap<>();
    private Map<String,String> colors = new HashMap<>();
    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();

        CarColorsEngineInitialize.initEngine(engines);
        CarColorsEngineInitialize.initColors(colors);
        data = CarColorsEngineInitialize.initCars(cars, brands);
        Map<String, Object> params = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        login = (String) params.get("Login");
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void loginAttribute(ActionEvent event) {
        login = (String) event.getComponent().getAttributes().get("addLogin");
    }

    public void createAnnouncement() {
        Announcement announcement = new Announcement();
        AnnouncementDAO announcementDAO = new AnnouncementDAO(sessionFactory);

        announcement.setDateOfIssue(date);

        announcement.setColor(color);
        announcement.setEngine(engine);
        announcement.setPrice(price);
        announcement.setMileage(Integer.parseInt(mileage));

        UserDAO userDAO = new UserDAO(sessionFactory);

        User user = userDAO.createQuery("SELECT u FROM USERS u where u.email = :login", User.class).setParameter("login", login).getSingleResult();
        announcement.setUser(user);

        CarBrandDAO carBrandDAO = new CarBrandDAO(sessionFactory);
        CarBrand carBrand = carBrandDAO.createQuery("SELECT u FROM CARBRAND u WHERE u.name = :carBrandName", CarBrand.class)
                .setParameter("carBrandName", brand).getSingleResult();

        announcement.setCars(carBrand);
        announcement.setDescription("Срочное объявление");
        announcementDAO.save(announcement);

        try {
            getCurrentInstance().getExternalContext().redirect("profile.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Map<String, String> getCars() {
        return cars;
    }

    public Map<String, String> getBrands() {
        return brands;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Map<String, String> getEngines() {
        return engines;
    }

    public void setEngines(Map<String, String> engines) {
        this.engines = engines;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Map<String, String> getColors() {
        return colors;
    }

    public void setColors(Map<String, String> colors) {
        this.colors = colors;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void onCountryChange() {
        if(car != null && !car.equals(""))
            brands = data.get(car);
        else
            brands = new HashMap<String, String>();
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
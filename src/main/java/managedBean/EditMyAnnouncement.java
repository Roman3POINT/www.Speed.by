package managedBean;

import model.daoclasses.AnnouncementDAO;
import model.dbclasses.Announcement;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class EditMyAnnouncement {

    private SessionFactory sessionFactory;
    private Announcement announcement = (Announcement) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("announcement");
    private Map<String, String> colors = new HashMap<>();
    private Map<String, String> brands = new HashMap<>();
    private Map<String, String> engines = new HashMap<>();
    private Map<String, String> cars = new HashMap<>();
    private Map<String, Map<String, String>> data = new HashMap<>();
//    private String car;
//    private String brand;
//    private String engine;
//    private String mileage;
//    private String color;
//    private String date;
//    private String price;

    @PostConstruct
    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();
        CarColorsEngineInitialize.initColors(colors);
        CarColorsEngineInitialize.initEngine(engines);
        data = CarColorsEngineInitialize.initCars(cars, brands);

//        car = announcement.getCars().getCar().getName();
//        brand = announcement.getCars().getName();
//        engine = announcement.getEngine();
//        mileage = String.valueOf(announcement.getMileage());
//        color = announcement.getColor();
//        date = String.valueOf(announcement.getDateOfIssue());
//        price = String.valueOf(announcement.getPrice());
    }

    public void updateAnnouncement() {
            AnnouncementDAO announcementDAO = new AnnouncementDAO(sessionFactory);
            announcementDAO.update(announcement);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("profile.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCountryChange() {
        if(announcement.getCars().getCar() != null && !announcement.getCars().getCar().equals(""))
            brands = data.get(announcement.getCars().getCar());
        else
            brands = new HashMap<String, String>();
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public Map<String, String> getColors() {
        return colors;
    }

    public void setColors(Map<String, String> colors) {
        this.colors = colors;
    }

    public Map<String, String> getBrands() {
        return brands;
    }

    public void setBrands(Map<String, String> brands) {
        this.brands = brands;
    }

    public Map<String, String> getEngines() {
        return engines;
    }

    public void setEngines(Map<String, String> engines) {
        this.engines = engines;
    }

    public Map<String, String> getCars() {
        return cars;
    }

    public void setCars(Map<String, String> cars) {
        this.cars = cars;
    }

//    public String getCar() {
//        return car;
//    }
//
//    public void setCar(String car) {
//        this.car = car;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
//    public String getEngine() {
//        return engine;
//    }
//
//    public void setEngine(String engine) {
//        this.engine = engine;
//    }
//
//    public String getMileage() {
//        return mileage;
//    }
//
//    public void setMileage(String mileage) {
//        this.mileage = mileage;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
}
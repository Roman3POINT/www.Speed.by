package managedBean;

import model.daoclasses.AnnouncementDAO;
import model.daoclasses.CarBrandDAO;
import model.daoclasses.CarDAO;
import model.dbclasses.Announcement;
import model.dbclasses.Car;
import model.dbclasses.CarBrand;
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
    private Announcement announcement;
    private Map<String, String> colors = new HashMap<>();
    private Map<String, String> brands = new HashMap<>();
    private Map<String, String> engines = new HashMap<>();
    private Map<String, String> cars = new HashMap<>();
    private Map<String, Map<String, String>> data = new HashMap<>();
    private Map<String, Object> sessionParams;
    private CarDAO carDAO;
    private CarBrandDAO brandDAO;
    private AnnouncementDAO announcementDAO;

    private String car;
    private String brand;

    @PostConstruct
    public void init() {
        sessionFactory = HibernateUtil.getSessionFactory();
        CarColorsEngineInitialize.initColors(colors);
        CarColorsEngineInitialize.initEngine(engines);
        data = CarColorsEngineInitialize.initCars(cars, brands);

        sessionParams = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        announcement = (Announcement) sessionParams.get("myAnnouncement");

        carDAO = new CarDAO(sessionFactory);
        brandDAO = new CarBrandDAO(sessionFactory);
        announcementDAO = new AnnouncementDAO(sessionFactory);

        car = announcement.getCars().getCar().getName();
        brand = announcement.getCars().getName();
    }

    public void updateAnnouncement() {
        try {

            Car _car = carDAO.createQuery("SELECT u FROM CAR u WHERE u.name = :carName", Car.class)
                    .setParameter("carName", car).getSingleResult();
            CarBrand _carBrand = brandDAO.createQuery("SELECT u FROM CARBRAND u WHERE u.name = :carBrandName", CarBrand.class)
                    .setParameter("carBrandName", brand).getSingleResult();

            announcement.setCars(_carBrand);
            announcement.getCars().setCar(_car);
            announcementDAO.update(announcement);

            FacesContext.getCurrentInstance().getExternalContext().redirect("profile.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCountryChange() {
        if(car != null && !car.equals(""))
            brands = data.get(car);
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
}
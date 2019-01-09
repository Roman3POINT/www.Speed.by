package managedBean.filter_list_announcement_view;

import model.daoclasses.CarDAO;
import model.dbclasses.Car;
import model.dbclasses.CarBrand;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.*;

@ManagedBean
@ViewScoped
public class FilterAnnouncementService {

    private Car car;
    private CarBrand carBrand;
    private List<Car> cars = new ArrayList<>();
    private List<CarBrand> brands = new ArrayList<>();
    private Map<Car, List<CarBrand>> data = new HashMap<>();
    private int mileage;
    private int price;

    @PostConstruct
    public void init() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CarDAO carDAO = new CarDAO(sessionFactory);
        List<Car> carList;
        Query<Car> queryCar = carDAO.createQuery("SELECT u FROM CAR u", Car.class);
        carList = queryCar.list();

        for(Car _car: carList) {
            cars.add(_car);

            for (CarBrand carBrand : _car.getCarBrands()) {
                brands.add(carBrand);
                data.put(_car, brands);
            }
            brands = new ArrayList<>();
        }
    }

    public void filter() {
        System.out.println("asdas");
    }

    public void onCountryChange() {
        if(car != null && !car.equals(""))
            brands = data.get(car);
        else
            brands = new ArrayList<>();
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<CarBrand> getBrands() {
        return brands;
    }

    public void setBrands(List<CarBrand> brands) {
        this.brands = brands;
    }
}
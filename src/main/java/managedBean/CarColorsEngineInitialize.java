package managedBean;

import model.daoclasses.CarDAO;
import model.dbclasses.Car;
import model.dbclasses.CarBrand;
import model.hibernate_util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarColorsEngineInitialize {

    public static void initColors(Map<String, String> colors) {
        colors.put("Красный", "Красный");
        colors.put("Серый", "Серый");
        colors.put("Синий", "Синий");
        colors.put("Белый", "Белый");
        colors.put("Черный", "Черный");
        colors.put("Тёмно-зелёный", "Темно-зелёный");
        colors.put("Бардовый", "Бардовый");
        colors.put("Желтый", "Желтый");
        colors.put("Коричневый", "Коричневый");
        colors.put("Голубой", "Голубой");

    }

    public static void initEngine(Map<String, String> engines) {
        engines.put("Бензиновый", "Бензиновый");
        engines.put("Дизельный", "Дизельный");
        engines.put("Электрический", "Электрический");
    }

    public static Map<String, Map<String, String>> initCars(Map<String, String> cars, Map<String, String> brands) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CarDAO carDAO = new CarDAO(sessionFactory);
        Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
        List<Car> carList;
        Query<Car> queryCar = carDAO.createQuery("SELECT u FROM CAR u", Car.class);
        carList = queryCar.list();

        for(Car _car: carList) {
            cars.put(_car.getName(), _car.getName());

            for (CarBrand carBrand : _car.getCarBrands()) {
                brands.put(carBrand.getName(), carBrand.getName());
                data.put(_car.getName(), brands);
            }
            brands = new HashMap<>();
        }
        return data;
    }
}

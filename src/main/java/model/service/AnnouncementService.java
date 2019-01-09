package model.service;

import model.daoclasses.AnnouncementDAO;
import model.daoclasses.CarBrandDAO;
import model.daoclasses.CarDAO;
import model.dbclasses.Announcement;
import model.dbclasses.Car;
import model.dbclasses.CarBrand;
import org.hibernate.SessionFactory;

import java.util.List;

public class AnnouncementService {

    private List<Announcement> list;
    private AnnouncementDAO announcementDAO;
    private CarBrandDAO carBrandDAO;
    private CarDAO carDAO;

    public AnnouncementService(SessionFactory sessionFactory) {
        list = null;
        announcementDAO = new AnnouncementDAO(sessionFactory);
        carBrandDAO = new CarBrandDAO(sessionFactory);
        carDAO = new CarDAO(sessionFactory);
    }

    public void carBrandList(String filterValue) {
        CarBrand _carBrand = carBrandDAO.createQuery("SELECT u FROM CARBRAND u WHERE u.name = :carBrandName", CarBrand.class)
                .setParameter("carBrandName", filterValue).getSingleResult();

        list = announcementDAO.createQuery("SELECT u FROM ANNOUNCEMENT u WHERE u.cars = :carBrand", Announcement.class)
                .setParameter("carBrand", _carBrand).list();
    }

    public void carList(String filterValue) {

        Car _car = carDAO.createQuery("SELECT u FROM CAR u WHERE u.name = :carName", Car.class)
                .setParameter("carName", filterValue).getSingleResult();

        List<CarBrand> _carBrands = carBrandDAO.createQuery("SELECT u FROM CARBRAND u WHERE u.car = :carName", CarBrand.class)
                .setParameter("carName", _car).list();

        List<Announcement> announcements = null;

        for(CarBrand _carBrand: _carBrands) {
            announcements = announcementDAO.createQuery("SELECT u FROM ANNOUNCEMENT u WHERE u.cars = :carBrand", Announcement.class)
                    .setParameter("carBrand", _carBrand).list();
        }

        list = announcements;
    }

    public void priceList(String filterValue) {
        list = announcementDAO.createQuery("SELECT u FROM ANNOUNCEMENT u WHERE u.price <= :price", Announcement.class)
                .setParameter("price", Integer.parseInt(filterValue)).list();
    }

    public void mileageList(String filterValue) {
        list = announcementDAO.createQuery("SELECT u FROM ANNOUNCEMENT u WHERE u.mileage <= :mileage", Announcement.class)
                .setParameter("mileage", Integer.parseInt(filterValue)).list();
    }

    public List<Announcement> getList() {
        return list;
    }
}

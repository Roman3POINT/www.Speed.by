package model.daoclasses;

import model.dbclasses.Car;
import org.hibernate.SessionFactory;

public class CarDAO extends SpeedByDAO<Car> {
    public CarDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}

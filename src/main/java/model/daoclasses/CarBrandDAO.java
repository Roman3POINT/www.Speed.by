package model.daoclasses;

import model.dbclasses.CarBrand;
import org.hibernate.SessionFactory;

public class CarBrandDAO extends SpeedByDAO<CarBrand> {
    public CarBrandDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}

package model.daoclasses;

import model.dbclasses.User;
import org.hibernate.SessionFactory;

public class UserDAO extends SpeedByDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}

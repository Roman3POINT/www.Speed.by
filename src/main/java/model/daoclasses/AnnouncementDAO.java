package model.daoclasses;

import model.dbclasses.Announcement;
import model.dbclasses.CarBrand;
import model.dbclasses.SpeedBy;
import org.hibernate.SessionFactory;

import javax.faces.bean.SessionScoped;
import java.util.List;

public class AnnouncementDAO extends SpeedByDAO<Announcement> {

    private SessionFactory sessionFactory;

    public AnnouncementDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }
}
package model.daoclasses;

import model.dbclasses.SpeedBy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class SpeedByDAO<T extends SpeedBy> {

    private static Session session;

    public SpeedByDAO(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    public void save(T obj) {
        session.beginTransaction();
        session.save(obj);
        session.getTransaction().commit();
    }

    public void delete(T obj) {
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
    }

    public void update(T obj) {
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
    }

    public T get(Class<T> cls, Long id) {
        return session.get(cls, id);
    }

    public void close() {
        session.close();
    }

    public Query<T> createQuery(String query, Class<T> cls) {
        return session.createQuery(query, cls);
    }
}
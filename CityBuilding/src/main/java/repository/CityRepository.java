package repository;

import entities.City;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CityRepository {

    public void addNewCity(City city) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(city);
            session.getTransaction().commit();
        } catch (
                HibernateException e) {
            e.printStackTrace();
        }
    }


}

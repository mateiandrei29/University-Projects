package repository;

import entities.District;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DistrictRepository {

    public List<District> load() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "FROM District";
            Query<District> query = session.createQuery(HQL, District.class);
            List<District> districtList = query.getResultList();
            return districtList;
        } catch (
                HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}

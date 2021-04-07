package repository;

import entities.Amenity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AmenityRepository {
    public List<Amenity> getAmenitiesWithPlaceId(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "FROM Amenity WHERE idPlace=:id";
            Query<Amenity> query = session.createQuery(HQL, Amenity.class);
            query.setParameter("id", id);
            List<Amenity> amenities = query.getResultList();

            return amenities;
        } catch (
                HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}

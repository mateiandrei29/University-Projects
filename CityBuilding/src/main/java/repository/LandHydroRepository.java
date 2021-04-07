package repository;

import entities.LandHydro;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LandHydroRepository {

    public List<LandHydro> getComponentsWithIdDistrict(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "FROM LandHydro WHERE idDistrict=:id";
            Query<LandHydro> query = session.createQuery(HQL, LandHydro.class);
            query.setParameter("id", id);
            List<LandHydro> landHydros = query.getResultList();

            return landHydros;
        } catch (
                HibernateException e) {
            e.printStackTrace();
            return null;
        }

    }
}
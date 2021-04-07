package repository;

import entities.Bug;
import entities.District;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BugRepository {

    public List<Bug> getBugsForPlace(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "FROM Bug WHERE idPlace=:id";
            Query<Bug> query = session.createQuery(HQL, Bug.class);
            query.setParameter("id", id);
            List<Bug> bugs = query.getResultList();

            return bugs;
        } catch (
                HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertBug(Bug bug) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(bug);
            session.getTransaction().commit();

        } catch (
                HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<Bug> getAllBugs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "FROM Bug";
            Query<Bug> query = session.createQuery(HQL, Bug.class);
            List<Bug> bugsList = query.getResultList();
            return bugsList;
        } catch (
                HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}

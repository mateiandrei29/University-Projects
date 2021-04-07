package repository;

import entities.Contact;
import entities.Place;
import entities.PlaceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceRepository { //get place from xCoord and yCoord for when clicking on the map to show info

    private static final Logger logger = LogManager.getLogger();

    public void buildPlace(Place place) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(place);
            session.getTransaction().commit();
            logger.traceEntry("The following building is being constructed:  {}", place);


        } catch (
                HibernateException e) {
            e.printStackTrace();
        }
    }

    public void removePlace(Place place) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(place);
            session.getTransaction().commit();

        } catch (
                HibernateException e) {
            e.printStackTrace();
        }
    }

    public void updatePlace(Place place) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(place);
            session.getTransaction().commit();

        } catch (
                HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<Place> selectByCoords(Integer xCoord, Integer yCoord) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "FROM Place WHERE xCoord=:x AND yCoord=:y";
            Query<Place> query = session.createQuery(HQL, Place.class);
            query.setParameter("x", xCoord);
            query.setParameter("y", yCoord);
            List<Place> places = query.getResultList();

            return places;
        } catch (
                HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Place> getComponentsWithIdDistrict(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "FROM Place WHERE idDistrict=:id";
            Query<Place> query = session.createQuery(HQL, Place.class);
            query.setParameter("id", id);
            List<Place> places = query.getResultList();

            return places;
        } catch (
                HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Integer> getPlaceType() {
        Map<String, Integer> map = new HashMap<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "FROM PlaceType";
            Query<PlaceType> query = session.createQuery(HQL, PlaceType.class);
            List<PlaceType> places = query.getResultList();

            for (PlaceType place : places) {
                map.put(place.getPlaceName(), place.getIdPlaceType());
                System.out.println(place.getPlaceName());
            }
        } catch (
                HibernateException e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }


    public void insertContact(Contact contact) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(contact);
            session.getTransaction().commit();

        } catch (
                HibernateException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(Contact contact) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(contact);
            session.getTransaction().commit();

        } catch (
                HibernateException e) {
            e.printStackTrace();
        }
    }
    public void updateContact(Contact contact) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(contact);
            session.getTransaction().commit();

        } catch (
                HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> getContactByAddress(String address) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "FROM Contact where address=:add";
            Query<Contact> query = session.createQuery(HQL, Contact.class);
            query.setParameter("add", address);

            List<Contact> contacts = query.getResultList();
            return contacts;
        } catch (
                HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Contact> getContactById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String HQL = "FROM Contact where idContact=:id";
            Query<Contact> query = session.createQuery(HQL, Contact.class);
            query.setParameter("id", id);

            List<Contact> contacts = query.getResultList();
            return contacts;
        } catch (
                HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }


}

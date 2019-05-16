package model.da;

import model.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;

/**
 * Created by ehsan on 8/10/2018.
 */
public class DaoImpl implements Dao{

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public DaoImpl(){
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Object o) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(o);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void deleteTripRecord(long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Trip trip = entityManager.find(Trip.class,id);
        entityManager.remove(trip);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void deleteDriverRecord(long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Driver driver = entityManager.find(Driver.class,id);
        entityManager.remove(driver);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public Administrator getRecordOfAdministrator(long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Administrator administrator = entityManager.find(Administrator.class,id);
        entityManager.close();
        return administrator;
    }

    @Override
    public ArrayList getAll(Class aClass) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        ArrayList customer = (ArrayList) entityManager.createQuery("from "+aClass.getCanonicalName() ).getResultList();
        entityManager.close();
        return customer;
    }

    @Override
    public ArrayList getAllWithoutAuthentication() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        ArrayList data = (ArrayList) entityManager.createQuery("from model.entity.Trip").getResultList();
        entityManager.close();
        return data;
    }

    @Override
    public void updateTripRecord(long id , Trip trip) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Trip trip1= entityManager.find(Trip.class , id);
        trip1.setUsername(trip.getUsername());
        trip1.setPassword(trip.getPassword());
        trip1.setStart(trip.getStart());
        trip1.setDestination(trip.getDestination());
        trip1.setDate(trip.getDate());
        trip1.setCustomer_name(trip.getCustomer_name());
        trip1.setDriver_name(trip.getDriver_name());
        entityManager.merge(trip1);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void updateDriverRecord(long id, Driver driver) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Driver driver1= entityManager.find(Driver.class , id);
        driver1.setUsername(driver.getUsername());
        driver1.setPassword(driver.getPassword());
        driver1.setAddress(driver.getAddress());
        driver1.setEmail(driver.getEmail());
        entityManager.merge(driver1);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void updateCustomerRecord(long id, Customer customer) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Customer customer1= entityManager.find(Customer.class , id);
        customer1.setUsername(customer.getUsername());
        customer1.setPassword(customer.getPassword());
        customer1.setAddress(customer.getAddress());
        customer1.setEmail(customer.getEmail());
        entityManager.merge(customer1);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public Boolean findTripEntity(long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Trip trip1 = new Trip();
        trip1.setCustomer_name("");
        trip1 = entityManager.find(Trip.class , id);
        entityManager.close();

        try {
            if (trip1.getCustomer_name() == "");
        }catch (Exception e) {
            e.getMessage();
            return false;
        }
        return true;
    }

   /* public static void main(String[] args) {

        DaoImpl dao = (DaoImpl) SpringProvider.getApplicationContext().getBean("daoimpl");
        //dao.updateRecord(23l,new Trip("in dige shod","1234asdf","haminja"
             //   ,"hamonja",new Date(),"ehsan","vrtual"));


    }*/
}

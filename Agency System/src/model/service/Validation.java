package model.service;

import model.da.DaoImpl;
import model.entity.Customer;
import model.entity.Driver;
import model.util.Ciphering;
import model.util.SpringProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ehsan on 8/28/2018.
 */
public class Validation {

    private static DaoImpl dao = (DaoImpl) SpringProvider.getApplicationContext().getBean("daoimpl");

    public static Boolean isValidForCustomer(String password, String username)
    {
        try {
            if (getCustomerByPassword(password,username).getPassword().equals(new Ciphering().mD5(password))
                    && getCustomerByPassword(password,username).getUsername().equals(username))
                return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean isValidForDriver(String password, String username)
    {
        try {
            if (getDriverByPassword(password,username).getPassword().equals(new Ciphering().mD5(password))
                    && getDriverByPassword(password,username).getUsername().equals(username))
                return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }


    // return customer entity if exist customer with this password and username , else return temp

    public static Customer getCustomerByPassword(String pass , String username)
    {
        String password = "";

        try {

            password = new Ciphering().mD5(pass);

        } catch (NoSuchAlgorithmException e) {
            e.getMessage();
        }

        ArrayList<Customer> customers = dao.getAll(Customer.class);
        Iterator iterator = customers.iterator();
        Customer  customer = null;
        while (iterator.hasNext())
        {
            customer = (Customer) iterator.next();
            if (customer.getPassword().equals(password) && customer.getUsername().equals(username))
                return customer;
        }
           Customer temp = new Customer();
           temp.setPassword("");
           temp.setUsername("");
           return temp;
    }

    public static Driver getDriverByPassword(String pass , String username)
    {
        String password = "";

        try {

            password = new Ciphering().mD5(pass);

        } catch (NoSuchAlgorithmException e) {
            e.getMessage();
        }

        ArrayList<Driver> drivers = dao.getAll(Driver.class);
        Iterator iterator = drivers.iterator();
        Driver driver = null;
        while (iterator.hasNext())
        {
            driver = (Driver) iterator.next();
            if (driver.getPassword().equals(password) && driver.getUsername().equals(username))
                return driver;
        }
            Driver temp =  new Driver();
            temp.setPassword("");
            temp.setUsername("");
            return temp;
    }


   /* public static void main(String[] args) {

       *//* ArrayList<Customer> customers = dao.getAll(Customer.class);
        Iterator<Customer> customerIterator = customers.iterator();
        while (customerIterator.hasNext())
        {
            System.out.println(customerIterator.next().getUsername());
        }*//*
        //System.out.println(getCusromerByPassword("123456").getUsername());
    }*/
}

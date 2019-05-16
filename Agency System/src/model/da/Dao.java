package model.da;

import model.entity.*;

import java.util.ArrayList;

/**
 * Created by ehsan on 8/10/2018.
 */
public interface Dao {


    public void save(Object o);
    public void deleteTripRecord (long id);
    public void deleteDriverRecord (long id);
    public Administrator getRecordOfAdministrator (long id);
    public ArrayList getAll (Class aClass);
    public ArrayList getAllWithoutAuthentication();
    public void updateTripRecord(long id, Trip trip);
    public void updateDriverRecord(long id, Driver driver);
    public void updateCustomerRecord(long id, Customer customer);
    public Boolean findTripEntity(long id );
}

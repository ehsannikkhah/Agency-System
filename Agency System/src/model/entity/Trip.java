package model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ehsan on 8/28/2018.
 */
@Entity
@Table(name = "trip")
public class Trip implements Serializable {

    private long id;
    private String username;
    private String password;
    private String begin;
    private String destination;
    private Date date;
    private String customer_name;
    private String driver_name;


    public Trip() {
    }

    public Trip(String username, String password, String begin, String destination,
                Date date, String customer_name, String driver_name)
    {
        this.username = username;
        this.password = password;
        this.begin = begin;
        this.destination = destination;
        this.date = date;
        this.customer_name = customer_name;
        this.driver_name = driver_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "username" , columnDefinition = "NVARCHAR2(30)")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", columnDefinition = "NVARCHAR2(256)")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "beginof", columnDefinition = "NVARCHAR2(30)")
    public String getStart() {
        return begin;
    }

    public void setStart(String start) {
        this.begin = start;
    }
    @Column(name = "destination", columnDefinition = "NVARCHAR2(40)")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Column(name = "dateandtime")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "customer_name", columnDefinition = "NVARCHAR2(30)")
    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    @Column(name = "driver_name", columnDefinition = "NVARCHAR2(30)")
    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }
}

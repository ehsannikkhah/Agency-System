package model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ehsan on 8/28/2018.
 */
@Entity
@Table(name = "driver")
public class Driver implements Serializable {

    private long id;
    private String username;
    private String email;
    private String password;
    private String Address;

    @Column(name = "address" , columnDefinition = "NVARCHAR2(256)")
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Driver() {
    }

    public Driver(String username, String email, String password) {

        this.username = username;
        this.email = email;
        this.password = password;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
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

    @Column(name = "email" , columnDefinition = "NVARCHAR2(40)")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", columnDefinition = "NVARCHAR2(256)")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

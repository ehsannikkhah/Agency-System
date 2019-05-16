package model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ehsan on 8/28/2018.
 */
@Entity
@Table(name = "Administrator")
public class Administrator implements Serializable {

    private long id;
    private String username;
    private String password;

    public Administrator() {
    }

    public Administrator(String username, String password) {

        this.username = username;
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

    @Column(name = "password", columnDefinition = "NVARCHAR2(256)")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

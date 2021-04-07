package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name ;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthDate", nullable = false)
    private Date birthDate;

    @Column(name = "address", nullable = false)
    private String address;

//    @Column(name = "userType", nullable = false)
//    private int userType;

    @Temporal(TemporalType.DATE)
    @Column(name = "createDate")
    private Date createDate;

    public Account() {
    }

    public Account(String username, String password, String email, String name, String address, Date birthDate, Date calendar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
//        this.user_type = userType;
        this.createDate = calendar;
    }
    public Account(UUID id,String username, String password, String email, String name, String address, Date birthDate, Date calendar) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
//        this.user_type = userType;
        this.createDate = calendar;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

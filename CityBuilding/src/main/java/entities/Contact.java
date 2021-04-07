package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContact")
    private Integer idContact;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNo")
    private String phoneNo;

    @Column(name = "website")
    private String website;

    @Column(name = "email")
    private String email;

    public Contact(String address, String phoneNo, String website, String email) {
        this.address = address;
        this.phoneNo = phoneNo;
        this.website = website;
        this.email = email;
    }

    public Contact() {

    }

    public Integer getIdContact() {
        return idContact;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }
}

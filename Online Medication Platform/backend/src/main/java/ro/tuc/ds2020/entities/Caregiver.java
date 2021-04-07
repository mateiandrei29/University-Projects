package ro.tuc.ds2020.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Caregiver extends Account {

    private static final long serialVersionUID = 1L;


    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = false, mappedBy = "caregiver")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Patient> patients;

    public Caregiver() {
    }

    public Caregiver(UUID id, String username, String password, String email, String name, String address, Date birthDate, Date calendar) {
        super(id, username, password, email, name, address, birthDate, calendar);
    }

    public Caregiver(String username, String password, String email, String name, String address, Date birthDate, Date calendar) {
        super(username, password, email, name, address, birthDate, calendar);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}

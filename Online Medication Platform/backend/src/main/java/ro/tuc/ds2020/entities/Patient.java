package ro.tuc.ds2020.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Patient extends Account {

    private static final long serialVersionUID = 1L;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "caregiver_id") // inainte era account_id
    private Caregiver caregiver;


    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MedicationPlan> medicationPlans;



    public Patient() {
    }

    public Patient(UUID id, String username, String password, String email, String name, String address, Date birthDate, Date calendar) {
        super(id, username, password, email, name, address, birthDate, calendar);
    }

    public Patient(String username, String password, String email, String name, String address, Date birthDate, Date calendar) {
        super(username, password, email, name, address, birthDate, calendar);
    }



    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public List<MedicationPlan> getMedicationPlans() {
        return medicationPlans;
    }

    public void setMedicationPlans(List<MedicationPlan> medicationPlan) {
        this.medicationPlans = medicationPlans;
    }
}

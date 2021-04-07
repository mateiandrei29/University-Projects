package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Doctor extends Account {

    private static final long serialVersionUID = 1L;


    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "doctor")
    private List<MedicationPlan> medicationPlans;

    public Doctor() {
    }

    public Doctor(UUID id, String username, String password, String email, String name, String address, Date birthDate, Date calendar) {
        super(id, username, password, email, name, address, birthDate, calendar);
    }

    public Doctor(String username, String password, String email, String name, String address, Date birthDate, Date calendar) {
        super(username, password, email, name, address, birthDate, calendar);
    }


    public List<MedicationPlan> getMedicationPlans() {
        return medicationPlans;
    }

    public void setMedicationPlans(List<MedicationPlan> medicationPlans) {
        this.medicationPlans = medicationPlans;
    }
}

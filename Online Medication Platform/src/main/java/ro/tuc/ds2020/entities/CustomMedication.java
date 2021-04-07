package ro.tuc.ds2020.entities;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/*
 *
 * Medication plan with medication, specific for each patient, not generic
 * like the medication
 *
 * */
@Entity
public class CustomMedication implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicationPlan_id")
    private MedicationPlan medicationPlan;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "noOfDays")
    private int noOfDays;

    @Column(name = "timesPerDay")
    private int timesPerDay;


    public CustomMedication() {
    }

    public CustomMedication(String dosage, int noOfDays, int timesPerDay) {
        this.dosage = dosage;
        this.noOfDays = noOfDays;
        this.timesPerDay = timesPerDay;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public MedicationPlan getMedicationPlan() {
        return medicationPlan;
    }

    public void setMedicationPlan(MedicationPlan medicationPlan) {
        this.medicationPlan = medicationPlan;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public int getTimesPerDay() {
        return timesPerDay;
    }

    public void setTimesPerDay(int timesPerDay) {
        this.timesPerDay = timesPerDay;
    }
}

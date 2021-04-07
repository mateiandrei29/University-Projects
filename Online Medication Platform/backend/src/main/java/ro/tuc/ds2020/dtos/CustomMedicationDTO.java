package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;

import java.util.Objects;
import java.util.UUID;

public class CustomMedicationDTO extends RepresentationModel<CustomMedicationDTO> {
    private UUID id;
    private String dosage;
    private int noOfDays;
    private int timesPerDay;
    private boolean morning;
    private boolean afternoon;
    private boolean evening;


    private MedicationDTO medication;
    private MedicationPlanDTO medicationPlan;


    public CustomMedicationDTO() {
    }

    public CustomMedicationDTO(UUID id, String dosage, int noOfDays, int timesPerDay) {
        this.id = id;
        this.dosage = dosage;
        this.noOfDays = noOfDays;
        this.timesPerDay = timesPerDay;
    }



    public CustomMedicationDTO(UUID id, String dosage, int noOfDays, int timesPerDay, MedicationDTO medicationDTO, MedicationPlanDTO medicationPlanDTO) {
        this.id = id;
        this.dosage = dosage;
        this.noOfDays = noOfDays;
        this.timesPerDay = timesPerDay;
        this.medication=medicationDTO;
        this.medicationPlan=medicationPlanDTO;
    }

    public CustomMedicationDTO(UUID id, String dosage, int noOfDays, int timesPerDay, MedicationDTO medicationDTO) {
        this.id = id;
        this.dosage = dosage;
        this.noOfDays = noOfDays;
        this.timesPerDay = timesPerDay;
        this.medication=medicationDTO;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public MedicationDTO getMedication() {
        return medication;
    }

    public void setMedication(MedicationDTO medication) {
        this.medication = medication;
    }

    public MedicationPlanDTO getMedicationPlan() {
        return medicationPlan;
    }

    public void setMedicationPlan(MedicationPlanDTO medicationPlan) {
        this.medicationPlan = medicationPlan;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isAfternoon() {
        return afternoon;
    }

    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }

    public boolean isEvening() {
        return evening;
    }

    public void setEvening(boolean evening) {
        this.evening = evening;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomMedicationDTO customMedicationDTO = (CustomMedicationDTO) o;
        return dosage == customMedicationDTO.dosage &&
                Objects.equals(noOfDays, customMedicationDTO.noOfDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noOfDays, dosage);
    }
}

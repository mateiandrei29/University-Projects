package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.CustomMedication;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MedicationPlanDTO extends RepresentationModel<MedicationPlanDTO> {

    private UUID id;
    private String name;
    private Date startDate;

    private DoctorDTO doctor;
    private PatientDTO patient;
    private List<CustomMedicationDTO> customMedication;

    public MedicationPlanDTO() {
    }

    public MedicationPlanDTO(UUID id, String name, Date startDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }

    public MedicationPlanDTO(UUID id, String name, Date startDate, DoctorDTO doctor, List<CustomMedicationDTO> customMedicationDTOS) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.customMedication = customMedicationDTOS;
        this.doctor = doctor;
    }

    public MedicationPlanDTO(UUID id, String name, Date startDate, DoctorDTO doctor, PatientDTO patient, List<CustomMedicationDTO> customMedicationDTOS) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.customMedication = customMedicationDTOS;
        this.doctor = doctor;
        this.patient = patient;
    }

    public MedicationPlanDTO(UUID id, String name, Date startDate, DoctorDTO doctor, PatientDTO patient) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.patient = patient;
        this.doctor = doctor;

    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public List<CustomMedicationDTO> getCustomMedication() {
        return customMedication;
    }

    public void setCustomMedication(List<CustomMedicationDTO> customMedication) {
        this.customMedication = customMedication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationPlanDTO medicationPlanDTO = (MedicationPlanDTO) o;
        return name == medicationPlanDTO.name &&
                Objects.equals(startDate, medicationPlanDTO.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, name);
    }
}

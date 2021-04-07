package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Patient;

import java.util.stream.Collectors;

public class PatientBuilder {

    private PatientBuilder() {
    }

    public static PatientDTO toPatientDTO(Patient patient) {

        return new PatientDTO(patient.getId(),
                patient.getUsername(),
                patient.getPassword(),
                patient.getEmail(),
                patient.getName(),
                patient.getBirthDate(),
                patient.getAddress(),
                patient.getCreateDate(),
                CaregiverBuilder.toCaregiverDTONoPatients(patient.getCaregiver()),
                patient.getMedicationPlans().stream().map(MedicationPlanBuilder::toBasicMedicationPlanDTO).collect(Collectors.toList()));
    }


    public static PatientDTO toCaregiverPatientDTO(Patient patient) {

        return new PatientDTO(patient.getId(),
                patient.getUsername(),
                patient.getPassword(),
                patient.getEmail(),
                patient.getName(),
                patient.getBirthDate(),
                patient.getAddress(),
                patient.getCreateDate(),
                CaregiverBuilder.toCaregiverDTONoPatients(patient.getCaregiver()));
    }
    public static PatientDTO toBasicPatientDTO(Patient patient) {

        return new PatientDTO(patient.getId(),
                patient.getUsername(),
                patient.getPassword(),
                patient.getEmail(),
                patient.getName(),
                patient.getBirthDate(),
                patient.getAddress(),
                patient.getCreateDate()
        );
    }

    public static Patient toEntity(PatientDTO patientDTO) {
        return new Patient(patientDTO.getUsername(),
                patientDTO.getPassword(),
                patientDTO.getEmail(),
                patientDTO.getName(),
                patientDTO.getAddress(),
                patientDTO.getBirthDate(),
                patientDTO.getCreateDate());
    }

    public static Patient toEntityUpdate(PatientDTO patientDTO) {
        return new Patient(patientDTO.getId(),
                patientDTO.getUsername(),
                patientDTO.getPassword(),
                patientDTO.getEmail(),
                patientDTO.getName(),
                patientDTO.getAddress(),
                patientDTO.getBirthDate(),
                patientDTO.getCreateDate());
    }
}

package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;

import java.util.stream.Collectors;

public class MedicationPlanBuilder {

    public MedicationPlanBuilder() {
    }

    public static MedicationPlanDTO toMedicationPlanDTO(MedicationPlan medicationPlan) {
        return new MedicationPlanDTO(medicationPlan.getId(),
                medicationPlan.getName(),
                medicationPlan.getStartDate(),
                DoctorBuilder.toBasicDoctorDTO(medicationPlan.getDoctor()),
                PatientBuilder.toBasicPatientDTO(medicationPlan.getPatient()),
                medicationPlan.getCustomMedication().stream().map(CustomMedicationBuilder::toBasicCustomMedicationDTO).collect(Collectors.toList()));

    }

    public static MedicationPlanDTO toForPatientMedicationPlanDTO(MedicationPlan medicationPlan) {
        return new MedicationPlanDTO(medicationPlan.getId(),
                medicationPlan.getName(),
                medicationPlan.getStartDate(),
                DoctorBuilder.toBasicDoctorDTO(medicationPlan.getDoctor()),
                medicationPlan.getCustomMedication().stream().map(CustomMedicationBuilder::toForPatientCustomMedicationDTO).collect(Collectors.toList()));

    }



    public static MedicationPlanDTO toForDoctorMedicationPlanDTO(MedicationPlan medicationPlan) {
        return new MedicationPlanDTO(medicationPlan.getId(),
                medicationPlan.getName(),
                medicationPlan.getStartDate(),
                DoctorBuilder.toBasicDoctorDTO(medicationPlan.getDoctor()),
                PatientBuilder.toBasicPatientDTO(medicationPlan.getPatient()));

    }

    public static MedicationPlanDTO toBasicMedicationPlanDTO(MedicationPlan medicationPlan) {
        return new MedicationPlanDTO(medicationPlan.getId(),
                medicationPlan.getName(),
                medicationPlan.getStartDate());

    }
    public static MedicationPlan toEntity(MedicationPlanDTO medicationPlanDTO){
        return new MedicationPlan(medicationPlanDTO.getName(),
                medicationPlanDTO.getStartDate());
    }
}

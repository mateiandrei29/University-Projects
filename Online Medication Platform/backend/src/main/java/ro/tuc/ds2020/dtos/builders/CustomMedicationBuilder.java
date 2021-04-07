package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CustomMedicationDTO;
import ro.tuc.ds2020.entities.CustomMedication;

public class CustomMedicationBuilder {

    public CustomMedicationBuilder() {
    }

    public static CustomMedicationDTO toCustomMedicationDTO(CustomMedication customMedication) {
        return new CustomMedicationDTO(customMedication.getId(),
                customMedication.getDosage(),
                customMedication.getNoOfDays(),
                customMedication.getTimesPerDay(),
                MedicationBuilder.toBasicMedicationDTO(customMedication.getMedication()),
                MedicationPlanBuilder.toBasicMedicationPlanDTO(customMedication.getMedicationPlan()));
    }

    public static CustomMedicationDTO toForPatientCustomMedicationDTO(CustomMedication customMedication) {
        return new CustomMedicationDTO(customMedication.getId(),
                customMedication.getDosage(),
                customMedication.getNoOfDays(),
                customMedication.getTimesPerDay(),
                MedicationBuilder.toBasicMedicationDTO(customMedication.getMedication()));

    }

    public static CustomMedicationDTO toBasicCustomMedicationDTO(CustomMedication customMedication) {
        return new CustomMedicationDTO(customMedication.getId(),
                customMedication.getDosage(),
                customMedication.getNoOfDays(),
                customMedication.getTimesPerDay());
    }

    public static CustomMedication toEntity(CustomMedicationDTO customMedicationDTO) {
        return new CustomMedication(customMedicationDTO.getDosage(),
                customMedicationDTO.getNoOfDays(),
                customMedicationDTO.getTimesPerDay());
    }
}

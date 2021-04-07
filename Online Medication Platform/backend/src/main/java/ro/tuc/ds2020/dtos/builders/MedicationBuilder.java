package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.entities.Medication;

import java.util.stream.Collectors;

public class MedicationBuilder {

    public MedicationBuilder() {
    }

    public static MedicationDTO toMedicationDTO(Medication medication) {
        return new MedicationDTO(medication.getId(),
                medication.getName(),
                medication.getMg(),
                medication.getSideEffects(),
                medication.getCustomMedication().stream().map(CustomMedicationBuilder::toBasicCustomMedicationDTO).collect(Collectors.toList()));
    }

    public static MedicationDTO toBasicMedicationDTO(Medication medication) {
        return new MedicationDTO(medication.getId(),
                medication.getName(),
                medication.getMg(),
                medication.getSideEffects());
    }

    public static Medication toEntity(MedicationDTO medicationDTO) {
        return new Medication(medicationDTO.getName(),
                medicationDTO.getMg(),
                medicationDTO.getSideEffects());
    }

    public static Medication toEntityUpdate(MedicationDTO medicationDTO) {
        return new Medication(medicationDTO.getId(),
                medicationDTO.getName(),
                medicationDTO.getMg(),
                medicationDTO.getSideEffects());
    }
}

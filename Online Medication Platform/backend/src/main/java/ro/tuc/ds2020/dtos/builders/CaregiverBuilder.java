package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.entities.Caregiver;

import java.util.Date;
import java.util.stream.Collectors;

public class CaregiverBuilder {

    private CaregiverBuilder() {
    }

    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver) {
        return new CaregiverDTO(caregiver.getId(),
                caregiver.getUsername(),
                caregiver.getPassword(),
                caregiver.getEmail(),
                caregiver.getName(),
                caregiver.getBirthDate(),
                caregiver.getAddress(),
                new Date(),
                caregiver.getPatients().stream().map(PatientBuilder::toPatientDTO).collect(Collectors.toList()));
    }

    public static CaregiverDTO toCaregiverDTONoPatients(Caregiver caregiver) {
        return new CaregiverDTO(caregiver.getId(),
                caregiver.getUsername(),
                caregiver.getPassword(),
                caregiver.getEmail(),
                caregiver.getName(),
                caregiver.getBirthDate(),
                caregiver.getAddress(),
                new Date()
                );
    }

    public static Caregiver toEntity(CaregiverDTO caregiverDTO) {
        return new Caregiver(caregiverDTO.getUsername(),
                caregiverDTO.getPassword(),
                caregiverDTO.getEmail(),
                caregiverDTO.getName(),
                caregiverDTO.getAddress(),
                caregiverDTO.getBirthDate(),
                caregiverDTO.getCreateDate());
    }

    public static Caregiver toEntityUpdate(CaregiverDTO caregiverDTO) {
        return new Caregiver(caregiverDTO.getId(),
                caregiverDTO.getUsername(),
                caregiverDTO.getPassword(),
                caregiverDTO.getEmail(),
                caregiverDTO.getName(),
                caregiverDTO.getAddress(),
                caregiverDTO.getBirthDate(),
                caregiverDTO.getCreateDate());
    }
}

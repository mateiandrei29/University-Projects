package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.entities.Doctor;

import java.util.Date;
import java.util.stream.Collectors;

public class DoctorBuilder {

    private DoctorBuilder() {
    }

    public static DoctorDTO toDoctorDTO(Doctor doctor) {
        return new DoctorDTO(doctor.getId(),
                doctor.getUsername(),
                doctor.getPassword(),
                doctor.getEmail(),
                doctor.getName(),
                doctor.getBirthDate(),
                doctor.getAddress(),
                doctor.getCreateDate(),
                doctor.getMedicationPlans().stream().map(MedicationPlanBuilder::toForDoctorMedicationPlanDTO).collect(Collectors.toList()));
    }

    public static DoctorDTO toBasicDoctorDTO(Doctor doctor) {
        return new DoctorDTO(doctor.getId(),
                doctor.getUsername(),
                doctor.getPassword(),
                doctor.getEmail(),
                doctor.getName(),
                doctor.getBirthDate(),
                doctor.getAddress(),
                doctor.getCreateDate());
    }

    public static Doctor toEntity(DoctorDTO doctorDTO) {
        return new Doctor(doctorDTO.getUsername(),
                doctorDTO.getPassword(),
                doctorDTO.getEmail(),
                doctorDTO.getName(),
                doctorDTO.getAddress(),
                doctorDTO.getBirthDate(),
                new Date());
    }
    public static Doctor toEntityUpdate(DoctorDTO doctorDTO) {
        return new Doctor(doctorDTO.getId(),
                doctorDTO.getUsername(),
                doctorDTO.getPassword(),
                doctorDTO.getEmail(),
                doctorDTO.getName(),
                doctorDTO.getAddress(),
                doctorDTO.getBirthDate(),
                new Date());
    }
}

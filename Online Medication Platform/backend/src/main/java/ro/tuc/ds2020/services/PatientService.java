package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final CaregiverRepository caregiverRepository;


    @Autowired
    public PatientService(PatientRepository patientRepository, CaregiverRepository caregiverRepository) {
        this.patientRepository = patientRepository;
        this.caregiverRepository = caregiverRepository;
    }

    public List<PatientDTO> findPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream()
                .map(PatientBuilder::toCaregiverPatientDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO findPatientById(UUID id) {

        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (!patientOptional.isPresent()) {
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toBasicPatientDTO(patientOptional.get());
    }

    public List<PatientDTO> findPatientsByCaregiverId(UUID caregiverId) {
        List<Patient> patients = patientRepository.findPatientByCaregiverId(caregiverId);

        return patients.stream()
                .map(PatientBuilder::toBasicPatientDTO)
                .collect(Collectors.toList());

    }

    public Optional<PatientDTO> findPatientByUsername(String username) {

        Optional<Patient> patientOptional = patientRepository.findPatientByUsername(username);
        if (!patientOptional.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(PatientBuilder.toBasicPatientDTO(patientOptional.get()));
    }

    public UUID insert(PatientDTO patientDTO) {
        Patient patient = PatientBuilder.toEntity(patientDTO);

//        patient.setCaregiver(caregiverRepository.findById(patientDTO.getCaregiver().getId()).get());
        patient.setCaregiver(caregiverRepository.findByName(patientDTO.getCaregiver().getName()).get(0));
        patient = patientRepository.save(patient);
        return patient.getId();

    }

    public UUID update(PatientDTO patientDTO) {
        Patient patient = PatientBuilder.toEntityUpdate(patientDTO);

        Optional<Patient> patient2=patientRepository.findById(patientDTO.getId());
        patient.setMedicationPlans(patient2.get().getMedicationPlans());

        patient.setCaregiver(caregiverRepository.findByName(patientDTO.getCaregiver().getName()).get(0));
        patient = patientRepository.save(patient);
        return patient.getId();
    }

    public UUID delete(UUID id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (!patientOptional.isPresent()) {
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);

        }
        patientRepository.delete(patientOptional.get());

        return id;
    }


}

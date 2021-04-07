package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.builders.MedicationBuilder;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.MedicationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;


    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<MedicationDTO> findMedications(){
        List<Medication> medicationList=medicationRepository.findAll();
        return medicationList.stream()
                .map(MedicationBuilder::toBasicMedicationDTO)
                .collect(Collectors.toList());
    }

    public MedicationDTO findMedicationById(UUID id){

        Optional<Medication> medicationOptional = medicationRepository.findById(id);
        if (!medicationOptional.isPresent()) {
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        return MedicationBuilder.toMedicationDTO(medicationOptional.get());
    }

    public UUID insert(MedicationDTO medicationDTO){
        Medication medication=MedicationBuilder.toEntity(medicationDTO);
        medication=medicationRepository.save(medication);
        return medication.getId();
    }

    public UUID update(MedicationDTO medicationDTO){
        Medication medication=MedicationBuilder.toEntityUpdate(medicationDTO);

        Optional<Medication> medication2=medicationRepository.findById(medicationDTO.getId());
        medication.setCustomMedication(medication2.get().getCustomMedication());

        medication=medicationRepository.save(medication);
        return medication.getId();
    }

    public UUID delete(UUID id){
        Optional<Medication> medicationOptional=medicationRepository.findById(id);

        if (!medicationOptional.isPresent()) {
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);

        }
        medicationRepository.delete(medicationOptional.get());

        return id;
    }


}

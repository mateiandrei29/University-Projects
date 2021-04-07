package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CustomMedicationDTO;
import ro.tuc.ds2020.dtos.builders.CustomMedicationBuilder;
import ro.tuc.ds2020.entities.CustomMedication;
import ro.tuc.ds2020.repositories.CustomMedicationRepository;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.MedicationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomMedicationService {
    private final CustomMedicationRepository customMedicationRepository;
    private final MedicationRepository medicationRepository;
    private final MedicationPlanRepository medicationPlanRepository;


    @Autowired
    public CustomMedicationService(CustomMedicationRepository customMedicationRepository, MedicationRepository medicationRepository, MedicationPlanRepository medicationPlanRepository) {
        this.customMedicationRepository = customMedicationRepository;
        this.medicationRepository = medicationRepository;
        this.medicationPlanRepository = medicationPlanRepository;
    }

    public List<CustomMedicationDTO> findCustomMedications(){
        List<CustomMedication> customMedicationList=customMedicationRepository.findAll();
        return customMedicationList.stream()
                .map(CustomMedicationBuilder::toCustomMedicationDTO)
                .collect(Collectors.toList());
    }

    public List<CustomMedicationDTO> findCustomMedicationsForPatient(){
        List<CustomMedication> customMedicationList=customMedicationRepository.findAll();
        return customMedicationList.stream()
                .map(CustomMedicationBuilder::toForPatientCustomMedicationDTO)
                .collect(Collectors.toList());
    }

    public CustomMedicationDTO findCustomMedicationById(UUID id){

        Optional<CustomMedication> customMedicationOptional = customMedicationRepository.findById(id);
        if (!customMedicationOptional.isPresent()) {
            throw new ResourceNotFoundException(CustomMedication.class.getSimpleName() + " with id: " + id);
        }
        return CustomMedicationBuilder.toCustomMedicationDTO(customMedicationOptional.get());
    }

    public UUID insert(CustomMedicationDTO customMedicationDTO){
        CustomMedication customMedication=CustomMedicationBuilder.toEntity(customMedicationDTO);

        customMedication.setMedication(medicationRepository.findByName(customMedicationDTO.getMedication().getName()).get(0));
        customMedication.setMedicationPlan(medicationPlanRepository.findByName(customMedicationDTO.getMedicationPlan().getName()).get(0));

        customMedication=customMedicationRepository.save(customMedication);
        return customMedication.getId();
    }


}

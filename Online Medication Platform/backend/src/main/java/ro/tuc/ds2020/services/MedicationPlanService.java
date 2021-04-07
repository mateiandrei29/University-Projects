package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.builders.MedicationPlanBuilder;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.repositories.DoctorRepository;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationPlanService {
    private final MedicationPlanRepository medicationPlanRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<MedicationPlanDTO> findMedicationPlans(){
        List<MedicationPlan> medicationPlanList=medicationPlanRepository.findAll();
        return medicationPlanList.stream()
                .map(MedicationPlanBuilder::toForDoctorMedicationPlanDTO)
                .collect(Collectors.toList());
    }

    public List<MedicationPlanDTO> findMedicationPlansByPatientId(UUID patientId){
        List<MedicationPlan> medicationPlans=medicationPlanRepository.findMedicationPlansByPatient(patientId);

        return medicationPlans.stream()
                .map(MedicationPlanBuilder::toForPatientMedicationPlanDTO)
                .collect(Collectors.toList());
    }

    public MedicationPlanDTO findMedicationPlanById(UUID id){

        Optional<MedicationPlan> medicationPlanOptional = medicationPlanRepository.findById(id);
        if (!medicationPlanOptional.isPresent()) {
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + id);
        }
        return MedicationPlanBuilder.toMedicationPlanDTO(medicationPlanOptional.get());
    }

    public UUID insert(MedicationPlanDTO medicationPlanDTO){
        MedicationPlan medicationPlan=MedicationPlanBuilder.toEntity(medicationPlanDTO);

        medicationPlan.setDoctor(doctorRepository.findByName(medicationPlanDTO.getDoctor().getName()).get(0));
        medicationPlan.setPatient(patientRepository.findByName(medicationPlanDTO.getPatient().getName()).get(0));

        medicationPlan=medicationPlanRepository.save(medicationPlan);


        return medicationPlan.getId();
    }


}

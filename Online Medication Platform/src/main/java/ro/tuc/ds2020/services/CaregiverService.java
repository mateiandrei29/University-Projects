package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.CaregiverRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CaregiverService {
    private final CaregiverRepository caregiverRepository;


    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository) {
        this.caregiverRepository = caregiverRepository;
    }

    public List<CaregiverDTO> findCaregivers() {
        List<Caregiver> caregiverList = caregiverRepository.findAll();
        return caregiverList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }

    public CaregiverDTO findCaregiverById(UUID id) {

        Optional<Caregiver> caregiverOptional = caregiverRepository.findById(id);
        if (!caregiverOptional.isPresent()) {
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDTO(caregiverOptional.get());
    }

    public Optional<CaregiverDTO> findCaregiverByUsername(String username) {

        Optional<Caregiver> caregiverOptional = caregiverRepository.findCaregiverByUsername(username);
        if (!caregiverOptional.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(CaregiverBuilder.toCaregiverDTONoPatients(caregiverOptional.get()));
    }


    public UUID insert(CaregiverDTO caregiverDTO) {
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver = caregiverRepository.save(caregiver);
        return caregiver.getId();
    }


    public UUID update(CaregiverDTO caregiverDTO) {
        Caregiver caregiver = CaregiverBuilder.toEntityUpdate(caregiverDTO);

        Optional<Caregiver> caregiver2 = caregiverRepository.findById(caregiverDTO.getId());

        caregiver.setPatients(caregiver2.get().getPatients());

        caregiver = caregiverRepository.save(caregiver);
        return caregiver.getId();


    }

    public UUID delete(UUID id) {
        Optional<Caregiver> caregiverOptional = caregiverRepository.findById(id);
        if (!caregiverOptional.isPresent()) {
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);

        }
        caregiverRepository.delete(caregiverOptional.get());

        return id;
    }


}

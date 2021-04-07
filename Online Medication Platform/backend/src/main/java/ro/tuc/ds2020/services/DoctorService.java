package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.DoctorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;


    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorDTO> findDoctors(){
        List<Doctor> doctorList=doctorRepository.findAll();
        return doctorList.stream()
                .map(DoctorBuilder::toBasicDoctorDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO findDoctorById(UUID id){

        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (!doctorOptional.isPresent()) {
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        return DoctorBuilder.toDoctorDTO(doctorOptional.get());
    }

    public Optional<DoctorDTO> findDoctorByUsername(String username){

        Optional<Doctor> doctorOptional = doctorRepository.findDoctorByUsername(username);
        if (!doctorOptional.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(DoctorBuilder.toBasicDoctorDTO(doctorOptional.get()));
    }

    public UUID insert(DoctorDTO doctorDTO){
        Doctor doctor=DoctorBuilder.toEntity(doctorDTO);
        doctor=doctorRepository.save(doctor);
        return doctor.getId();
    }

    public UUID update(DoctorDTO doctorDTO){
        Doctor doctor=DoctorBuilder.toEntityUpdate(doctorDTO);

        Optional<Doctor> doctor2=doctorRepository.findById(doctorDTO.getId());
        doctor.setMedicationPlans(doctor2.get().getMedicationPlans());

        doctor=doctorRepository.save(doctor);
        return doctor.getId();
    }

    public UUID delete(UUID id){
        Optional<Doctor> doctorOptional=doctorRepository.findById(id);
        if (!doctorOptional.isPresent()) {
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        doctorRepository.delete(doctorOptional.get());

        return id;
    }


}

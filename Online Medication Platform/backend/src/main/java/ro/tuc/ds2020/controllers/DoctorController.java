package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.services.DoctorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        List<DoctorDTO> dtos = doctorService.findDoctors();
        for (DoctorDTO dto : dtos) {
            Link doctorLink = linkTo(methodOn(DoctorController.class)
                    .getDoctor(dto.getId())).withRel("personDetails");
            dto.add(doctorLink);

        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        UUID doctorID = doctorService.insert(doctorDTO);
        return new ResponseEntity<>(doctorID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable("id") UUID doctorId) {
        DoctorDTO dto = doctorService.findDoctorById(doctorId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{doctorId}")
    public ResponseEntity<UUID> deleteDoctor(@PathVariable UUID doctorId) {
        UUID id = doctorService.delete(doctorId);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<UUID> updateDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        UUID doctorId = doctorService.update(doctorDTO);

        return new ResponseEntity<>(doctorId, HttpStatus.OK);
    }
}

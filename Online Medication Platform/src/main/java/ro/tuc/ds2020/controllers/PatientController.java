package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.services.PatientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();
        for (PatientDTO dto : dtos) {
            Link patientLink = linkTo(methodOn(PatientController.class)
                    .getPatient(dto.getId())).withRel("personDetails");
            dto.add(patientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertPatient(@Valid @RequestBody PatientDTO patientDTO) {
        UUID patientID = patientService.insert(patientDTO);

        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{caregiverId}")
    public ResponseEntity<List<PatientDTO>> getPatientsByCaregiverId(@PathVariable UUID caregiverId) {
        List<PatientDTO> dtos = patientService.findPatientsByCaregiverId(caregiverId);
        for (PatientDTO dto : dtos) {
            Link patientLink = linkTo(methodOn(PatientController.class)
                    .getPatient(dto.getId())).withRel("personDetails");
            dto.add(patientLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") UUID patientId) {
        PatientDTO dto = patientService.findPatientById(patientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{patientId}")
    public ResponseEntity<UUID> deletePatient(@PathVariable UUID patientId) {
        UUID id = patientService.delete(patientId);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<UUID> updatePatient(@Valid @RequestBody PatientDTO patientDTO) {
        UUID patientID = patientService.update(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.OK);
    }
}

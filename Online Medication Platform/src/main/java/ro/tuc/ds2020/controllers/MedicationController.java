package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.services.MedicationService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {
    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping()
    public ResponseEntity<List<MedicationDTO>> getMedications() {
        List<MedicationDTO> dtos = medicationService.findMedications();
        for (MedicationDTO dto : dtos) {
            Link medicationLink = linkTo(methodOn(MedicationController.class)
                    .getMedication(dto.getId())).withRel("medicationDetails");
            dto.add(medicationLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertMedication(@Valid @RequestBody MedicationDTO medicationDTO) {
        UUID medicationID = medicationService.insert(medicationDTO);
        return new ResponseEntity<>(medicationID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable("id") UUID medicationId) {
        MedicationDTO dto = medicationService.findMedicationById(medicationId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{medicationId}")
    public ResponseEntity<UUID> deleteMedication(@PathVariable UUID medicationId) {
        UUID id = medicationService.delete(medicationId);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<UUID> updateMedication(@Valid @RequestBody MedicationDTO medicationDTO) {
        UUID medicationId = medicationService.update(medicationDTO);

        return new ResponseEntity<>(medicationId, HttpStatus.OK);
    }

}

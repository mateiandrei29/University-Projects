package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CustomMedicationDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.services.CustomMedicationService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/customMedication")
public class CustomMedicationController {
    private final CustomMedicationService customMedicationService;

    @Autowired
    public CustomMedicationController(CustomMedicationService customMedicationService) {
        this.customMedicationService = customMedicationService;
    }

    @GetMapping()
    public ResponseEntity<List<CustomMedicationDTO>> getCustomMedications() {
        List<CustomMedicationDTO> dtos = customMedicationService.findCustomMedications();
        for (CustomMedicationDTO dto : dtos) {
            Link customMedicationLink = linkTo(methodOn(CustomMedicationController.class)
                    .getCustomMedication(dto.getId())).withRel("personDetails");
            dto.add(customMedicationLink);

        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertCustomMedication(@Valid @RequestBody CustomMedicationDTO customMedicationDTO){
        UUID customMedicationID=customMedicationService.insert(customMedicationDTO);
        return new ResponseEntity<>(customMedicationID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomMedicationDTO> getCustomMedication(@PathVariable("id") UUID customMedicationId) {
        CustomMedicationDTO dto = customMedicationService.findCustomMedicationById(customMedicationId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

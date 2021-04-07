package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.services.CaregiverService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
public class CaregiverController {
    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @GetMapping()
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregivers();
        for (CaregiverDTO dto : dtos) {
            Link caregiverLink = linkTo(methodOn(CaregiverController.class)
                    .getCaregiver(dto.getId())).withRel("personDetails");
            dto.add(caregiverLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<UUID> insertCaregiver(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        UUID caregiverID = caregiverService.insert(caregiverDTO);
        return new ResponseEntity<>(caregiverID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CaregiverDTO> getCaregiver(@PathVariable("id") UUID caregiverId) {
        CaregiverDTO dto = caregiverService.findCaregiverById(caregiverId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{caregiverId}")
    public ResponseEntity<UUID> deleteCaregiver(@PathVariable UUID caregiverId) {
        UUID id = caregiverService.delete(caregiverId);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<UUID> updateCaregiver(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        UUID caregiverId = caregiverService.update(caregiverDTO);
        return new ResponseEntity<>(caregiverId, HttpStatus.OK);
    }
}

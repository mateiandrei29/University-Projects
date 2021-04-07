package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.services.MedicationPlanService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/medicationPlan")
public class MedicationPlanController {
    private final MedicationPlanService medicationPlanService;

    @Autowired
    public MedicationPlanController(MedicationPlanService medicationPlanService) {
        this.medicationPlanService = medicationPlanService;
    }

    @GetMapping()
    public ResponseEntity<List<MedicationPlanDTO>> getMedicationPlans() {
        List<MedicationPlanDTO> dtos = medicationPlanService.findMedicationPlans();
        for (MedicationPlanDTO dto : dtos) {
            Link medicationPlanLink = linkTo(methodOn(MedicationPlanController.class)
                    .getMedicationPlan(dto.getId())).withRel("personDetails");
            dto.add(medicationPlanLink);

        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertMedicationPlan(@Valid @RequestBody MedicationPlanDTO medicationPlanDTO) {
        UUID medicationPlanID = medicationPlanService.insert(medicationPlanDTO);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{patientId}")
    public ResponseEntity<List<MedicationPlanDTO>> getMedicationPlansbyPatientId(@PathVariable UUID patientId) {
        List<MedicationPlanDTO> dtos = medicationPlanService.findMedicationPlansByPatientId(patientId);
        for (MedicationPlanDTO dto : dtos) {
            Link medicationPlanLink = linkTo(methodOn(MedicationPlanController.class)
                    .getMedicationPlan(dto.getId())).withRel("mediPlanDetails");
            dto.add(medicationPlanLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    //    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationPlanDTO> getMedicationPlan(@PathVariable("id") UUID medicationPlanId) {
        MedicationPlanDTO dto = medicationPlanService.findMedicationPlanById(medicationPlanId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

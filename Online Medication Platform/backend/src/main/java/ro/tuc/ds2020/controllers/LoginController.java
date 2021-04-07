package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.services.CaregiverService;
import ro.tuc.ds2020.services.DoctorService;
import ro.tuc.ds2020.services.PatientService;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final CaregiverService caregiverService;

    @Autowired
    public LoginController(DoctorService doctorService, PatientService patientService, CaregiverService caregiverService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.caregiverService = caregiverService;
    }


    @PostMapping()
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {

        Optional<DoctorDTO> doctor = doctorService.findDoctorByUsername(loginDTO.getUsername());
        Optional<CaregiverDTO> caregiver = caregiverService.findCaregiverByUsername(loginDTO.getUsername());
        Optional<PatientDTO> patient = patientService.findPatientByUsername(loginDTO.getUsername());

        UUID id = null;
        LoginResponseDTO loginResponseDTO=new LoginResponseDTO();
        if (doctor.isPresent()) {
            if (doctor.get().getPassword().equals(loginDTO.getPassword())) {
                id = doctor.get().getId();
                loginResponseDTO.setRole("doctor");

            }
        }
        if (caregiver.isPresent()) {
            if (caregiver.get().getPassword().equals(loginDTO.getPassword())) {
                id = caregiver.get().getId();
                loginResponseDTO.setRole("caregiver");
            }
        }

        if (patient.isPresent()) {
            if (patient.get().getPassword().equals(loginDTO.getPassword())) {
                id = patient.get().getId();
                loginResponseDTO.setRole("patient");
            }
        }

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        loginResponseDTO.setId(id);
        loginResponseDTO.setUsername(loginDTO.getUsername());
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);

    }


}

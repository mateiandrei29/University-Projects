package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    @Query(value = "SELECT p " +
            "FROM Patient p " +
            "WHERE p.username = :username ")
    Optional<Patient> findPatientByUsername(@Param("username") String username);

    List<Patient> findByName(String name);


    @Query(value = "SELECT p " +
            "FROM Patient p " +
            "WHERE p.caregiver.id = :caregiver_id ")
    List<Patient> findPatientByCaregiverId(@Param("caregiver_id") UUID caregiver_id);

}
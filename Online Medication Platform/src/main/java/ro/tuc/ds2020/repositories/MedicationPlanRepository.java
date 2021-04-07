package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;

import java.util.List;
import java.util.UUID;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, UUID> {

    List<MedicationPlan> findByName(String name);

    @Query(value = "SELECT p " +
            "FROM MedicationPlan p " +
            "WHERE p.patient.id = :patient_id ")
    List<MedicationPlan> findMedicationPlansByPatient(@Param("patient_id") UUID patient_id);


}
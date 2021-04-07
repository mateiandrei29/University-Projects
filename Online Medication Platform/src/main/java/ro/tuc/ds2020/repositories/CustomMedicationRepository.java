package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.CustomMedication;
import ro.tuc.ds2020.entities.Medication;

import java.util.UUID;

public interface CustomMedicationRepository extends JpaRepository<CustomMedication, UUID> {

}

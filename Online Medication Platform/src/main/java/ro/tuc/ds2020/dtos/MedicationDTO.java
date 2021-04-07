package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.CustomMedication;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MedicationDTO extends RepresentationModel<MedicationDTO> {
    private UUID id;
    private String name;
    private int mg;
    private String sideEffects;
    private List<CustomMedicationDTO> customMedication;

    public MedicationDTO() {

    }

    public MedicationDTO(UUID id, String name, int mg, String sideEffects) {
        this.id = id;
        this.name = name;
        this.mg = mg;
        this.sideEffects = sideEffects;
    }

    public MedicationDTO(UUID id, String name, int mg, String sideEffects, List<CustomMedicationDTO> customMedicationDTOS) {
        this.id = id;
        this.name = name;
        this.mg = mg;
        this.sideEffects = sideEffects;
        this.customMedication = customMedicationDTOS;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMg() {
        return mg;
    }

    public void setMg(int mg) {
        this.mg = mg;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public List<CustomMedicationDTO> getCustomMedication() {
        return customMedication;
    }

    public void setCustomMedication(List<CustomMedicationDTO> customMedication) {
        this.customMedication = customMedication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationDTO medicationDTO = (MedicationDTO) o;
        return name == medicationDTO.name &&
                Objects.equals(sideEffects, medicationDTO.sideEffects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideEffects, name);
    }
}

package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.MedicationPlan;

import java.util.*;

public class DoctorDTO extends RepresentationModel<DoctorDTO> {

    private UUID id;
    private String username;
    private String password;
    private String email;
    private String name;
    private Date birthDate;
    private String address;
    private Date createDate;

    private List<MedicationPlanDTO> medicationPlans;

    public DoctorDTO() {
    }

    public DoctorDTO(UUID id, String username, String password, String email, String name, Date birthDate, String address, Date createDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.createDate = createDate;
    }

    public DoctorDTO(UUID id, String username, String password, String email, String name, Date birthDate, String address, Date createDate, List<MedicationPlanDTO> medicationPlans) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.createDate = createDate;
        this.medicationPlans = medicationPlans;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<MedicationPlanDTO> getMedicationPlans() {
        return medicationPlans;
    }

    public void setMedicationPlans(List<MedicationPlanDTO> medicationPlans) {
        this.medicationPlans = medicationPlans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDTO doctorDTO = (DoctorDTO) o;
        return name == doctorDTO.name &&
                Objects.equals(email, doctorDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name);
    }
}

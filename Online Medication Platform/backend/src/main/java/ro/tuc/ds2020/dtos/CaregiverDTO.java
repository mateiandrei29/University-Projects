package ro.tuc.ds2020.dtos;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Patient;

import java.util.*;

public class CaregiverDTO extends RepresentationModel<DoctorDTO> {

    private UUID id;
    private String username;
    private String password;
    private String email;
    private String name;
    private Date birthDate;
    private String address;
    private Date createDate;

    //@JsonIgnore
    private List<PatientDTO> patients;

    public CaregiverDTO() {
    }

    public CaregiverDTO(UUID id, String username, String password, String email, String name, Date birthDate, String address, Date createDate, List<PatientDTO> patients) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.createDate = createDate;
        this.patients=patients;
    }

    public CaregiverDTO(UUID id, String username, String password, String email, String name, Date birthDate, String address, Date createDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.createDate = createDate;
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

    public List<PatientDTO> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDTO> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverDTO caregiverDTO = (CaregiverDTO) o;
        return name == caregiverDTO.name &&
                Objects.equals(email, caregiverDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name);
    }
}



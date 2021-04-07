package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Medication implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "mg")
    private int mg;

    @Column(name = "sideEffects")
    private String sideEffects;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "medication")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CustomMedication> customMedication;

    public Medication() {
    }

    public Medication(UUID id,String name, int mg, String sideEffects) {
        this.id=id;
        this.name = name;
        this.mg = mg;
        this.sideEffects = sideEffects;
    }

    public Medication(String name, int mg, String sideEffects) {
        this.name = name;
        this.mg = mg;
        this.sideEffects = sideEffects;
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

    public List<CustomMedication> getCustomMedication() {
        return customMedication;
    }

    public void setCustomMedication(List<CustomMedication> customMedication) {
        this.customMedication = customMedication;
    }
}

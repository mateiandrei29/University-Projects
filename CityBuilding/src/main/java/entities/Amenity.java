package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "amenity")
public class Amenity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAmenity")
    private Integer idAmenity;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "idPlace")
    private Integer idPlace;


    public Amenity(String name, String description, Integer idPlace) {
        this.name = name;
        this.description = description;
        this.idPlace = idPlace;
    }

    public Amenity() {
        
    }
}


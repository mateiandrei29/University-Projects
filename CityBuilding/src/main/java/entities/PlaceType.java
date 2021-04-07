package entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "placetype")
public class PlaceType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlaceType")
    private Integer idPlaceType;

    @Column(name = "placeName")
    private String placeName;

    public Integer getIdPlaceType() {
        return idPlaceType;
    }

    public String getPlaceName() {
        return placeName;
    }
}

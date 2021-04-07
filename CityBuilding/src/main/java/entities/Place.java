package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "place")
public class Place implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlace")
    private Integer idPlace;

    @Column(name = "placeName")
    private String placeName;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "availability")
    private String availability;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "xCoord")
    private Integer xCoord;

    @Column(name = "yCoord")
    private Integer yCoord;

    @Column(name = "idContact")
    private Integer idContact;

    @Column(name = "idDistrict")
    private Integer idDistrict;

    @Column(name = "idPlaceType")
    private Integer idPlaceType;


    public Place(String placeName, int capacity, String availability, Double rating, Integer xCoord, Integer yCoord, Integer idContact, Integer idDistrict, Integer idPlaceType) {
        this.placeName = placeName;
        this.capacity = capacity;
        this.availability = availability;
        this.rating = rating;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.idContact = idContact;
        this.idDistrict = idDistrict;
        this.idPlaceType = idPlaceType;
    }

    public Place(String placeName, int capacity, String availability, Double rating, Integer xCoord, Integer yCoord, Integer idContact, Integer idDistrict) {
        this.placeName = placeName;
        this.capacity = capacity;
        this.availability = availability;
        this.rating = rating;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.idContact = idContact;
        this.idDistrict = idDistrict;
    }

    public Place() {

    }

    public Integer getIdPlace() {
        return idPlace;
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAvailability() {
        return availability;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getxCoord() {
        return xCoord;
    }

    public Integer getyCoord() {
        return yCoord;
    }

    public Integer getIdContact() {
        return idContact;
    }

    public Integer getIdDistrict() {
        return idDistrict;
    }

    public Integer getIdPlaceType() {
        return idPlaceType;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeName='" + placeName + '\'' +
                ", capacity=" + capacity +
                ", availability='" + availability + '\'' +
                ", rating=" + rating +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                '}';
    }
}

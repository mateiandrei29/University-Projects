package entities;


import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCity")
    private Integer idCity;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "latitude")
    private float latitude;

    public City(String name, Double rating, float longitude, float latitude) {
        this.name = name;
        this.rating = rating;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public City() {

    }
}

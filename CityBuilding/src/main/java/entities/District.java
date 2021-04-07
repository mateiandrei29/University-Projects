package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "district")
public class District implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDistrict")
    private Integer idDistrict;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private double rating;

    @Column(name = "xStartCoord")
    private Integer xStartCoord;

    @Column(name = "yStartCoord")
    private Integer yStartCoord;

    @Column(name = "xEndCoord")
    private Integer xEndCoord;

    @Column(name = "yEndCoord")
    private Integer yEndCoord;

    @Column(name = "idCity")
    private Integer idCity;


    public District(String name, double rating, Integer xStartCoord, Integer yStartCoord, Integer xEndCoord, Integer yEndCoord, Integer idCity) {
        this.name = name;
        this.rating = rating;
        this.xStartCoord = xStartCoord;
        this.yStartCoord = yStartCoord;
        this.xEndCoord = xEndCoord;
        this.yEndCoord = yEndCoord;
        this.idCity = idCity;
    }

    public District() {
    }

    public Integer getIdDistrict() {
        return idDistrict;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public Integer getxStartCoord() {
        return xStartCoord;
    }

    public Integer getyStartCoord() {
        return yStartCoord;
    }

    public Integer getxEndCoord() {
        return xEndCoord;
    }

    public Integer getyEndCoord() {
        return yEndCoord;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

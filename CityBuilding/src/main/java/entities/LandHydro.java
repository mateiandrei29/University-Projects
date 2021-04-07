package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "landhydro")
public class LandHydro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLandHydro")
    private Integer idLandHydro;

    @Column(name = "name")
    private String name;

    @Column(name = "xCoord")
    private Integer xCoord;

    @Column(name = "yCoord")
    private Integer yCoord;

    @Column(name = "idDistrict")
    private Integer idDistrict;

    @Column(name = "idLayerType")
    private Integer idLayerType;

    public LandHydro(String name, Integer xCoord, Integer yCoord, Integer idDistrict) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.idDistrict = idDistrict;
    }

    public LandHydro() {
    }

    public Integer getIdLandHydro() {
        return idLandHydro;
    }

    public void setIdLandHydro(Integer idLandHydro) {
        this.idLandHydro = idLandHydro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getxCoord() {
        return xCoord;
    }

    public void setxCoord(Integer xCoord) {
        this.xCoord = xCoord;
    }

    public Integer getyCoord() {
        return yCoord;
    }

    public void setyCoord(Integer yCoord) {
        this.yCoord = yCoord;
    }

    public Integer getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(Integer idDistrict) {
        this.idDistrict = idDistrict;
    }

    public Integer getIdLayerType() {
        return idLayerType;
    }

    public void setIdLayerType(Integer idLayerType) {
        this.idLayerType = idLayerType;
    }
}

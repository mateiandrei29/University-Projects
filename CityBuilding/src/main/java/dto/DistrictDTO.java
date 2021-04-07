package dto;

import entities.District;
import entities.LandHydro;

import java.util.List;

/*
 *  Contains the district with its layers.
 */
public class DistrictDTO extends District {

    private List<LandHydro> landHydroList;
    private List<PlaceDTO> placeList;


    public DistrictDTO(String name, double rating, Integer xStartCoord, Integer yStartCoord, Integer xEndCoord, Integer yEndCoord, Integer idCity) {
        super(name, rating, xStartCoord, yStartCoord, xEndCoord, yEndCoord, idCity);

    }

    public DistrictDTO(District d) {
        super(d.getName(), d.getRating(), d.getxStartCoord(), d.getyStartCoord(), d.getxEndCoord(), d.getyEndCoord(), d.getIdCity());
    }

    public List<LandHydro> getLandHydroList() {
        return landHydroList;
    }

    public void setLandHydroList(List<LandHydro> landHydroList) {
        this.landHydroList = landHydroList;
    }


    public List<PlaceDTO> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<PlaceDTO> placeList) {
        this.placeList = placeList;
    }

    public void setRating(Double rating) {
        super.setRating(rating);
    }
}

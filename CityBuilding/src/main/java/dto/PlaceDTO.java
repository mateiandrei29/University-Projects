package dto;

import entities.Amenity;
import entities.Place;

import java.util.List;

/**
 * Contains the place with its amenities
 */
public class PlaceDTO extends Place {

    private List<Amenity> amenities;


    public PlaceDTO(Place p) {
        super(p.getPlaceName(), p.getCapacity(), p.getAvailability(), p.getRating(), p.getxCoord(), p.getyCoord(), p.getIdContact(), p.getIdDistrict(), p.getIdPlaceType());
    }


    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }
}

package services;

import dto.DistrictDTO;
import entities.Bug;
import entities.City;
import entities.Contact;
import entities.Place;
import exception.ValidationException;

import java.util.List;

public interface Services {
    List<DistrictDTO> loadFullDistricts();

    List<Bug> showBugsForPlace(Integer idPlace);

    List<Bug> showAllBugs();

    void buildPlace(String placeName, String capacityText, String availability, String ratingText, Integer xCoord, Integer yCoord, Integer idContact, Integer idDistrict, String type) throws ValidationException;

    void removePlace(Integer xCoord, Integer yCoord);

    void updatePlace(Integer xCoord, Integer yCoord) throws ValidationException;

    void addContact(String address, String phoneNo, String website, String email) throws ValidationException;

    Integer getContactIdByAddress(String address);

    Place getPlaceByCoords(Integer xCoord, Integer yCoord);

    Contact getContactById(Integer id);

    boolean checkLogin(String username, String password, CityObserver cityObserver);

    void addRule();

    void addNewBug(String bugDescription, Integer idPlace);

}

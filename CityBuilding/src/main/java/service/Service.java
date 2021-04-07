package service;

import dto.DistrictDTO;
import dto.PlaceDTO;
import entities.*;
import exception.ValidationException;
import repository.*;
import services.CityObserver;
import services.Services;
import validator.PlaceValidator;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements Services {

    private LandHydroRepository landHydroRepo;
    private PlaceRepository placeRepo;
    private AmenityRepository amenityRepo;
    private DistrictRepository districtRepo;
    private BugRepository bugRepo;
    private Map<String, Integer> placeType;
    private PlaceValidator placeValidator;
    private Map<String, CityObserver> loggedClients;
    private static final Integer threadNumber = 5;

    public Service(LandHydroRepository landHydroRepo, PlaceRepository placeRepo, AmenityRepository amenityRepo, DistrictRepository districtRepo, BugRepository bugRepo, PlaceValidator placeValidator) {
        this.landHydroRepo = landHydroRepo;
        this.placeRepo = placeRepo;
        this.amenityRepo = amenityRepo;
        this.districtRepo = districtRepo;
        this.bugRepo = bugRepo;
        this.placeValidator = placeValidator;
        placeType = placeRepo.getPlaceType(); // Maps each place type with its id value
        loggedClients = new HashMap<>();
    }

    /**
     * Load all the districts with all the layers.
     * Compute rating for each district by averaging the rating of all places in that district.
     *
     * @return list of all the "full" districts
     */
    @Override
    public List<DistrictDTO> loadFullDistricts() {
        List<DistrictDTO> rez = new ArrayList<>();

        for (District d : districtRepo.load()) {
            DistrictDTO distr = new DistrictDTO(d);
            distr.setLandHydroList(landHydroRepo.getComponentsWithIdDistrict(d.getIdDistrict()));

            List<PlaceDTO> placeDTOS = new ArrayList<>();
            Double rating = 0.0D;
            for (Place p : placeRepo.getComponentsWithIdDistrict(d.getIdDistrict())) {
                PlaceDTO place = new PlaceDTO(p);

                List<Amenity> amenities = amenityRepo.getAmenitiesWithPlaceId(p.getIdPlace());
                place.setAmenities(amenities);
                rating += p.getRating();
                placeDTOS.add(place);
            }
            distr.setPlaceList(placeDTOS);
            rating /= placeDTOS.size();
            distr.setRating(rating);
            rez.add(distr);
        }
        return rez;
    }

    /**
     * Validates a place and then builds it.
     *
     * @param placeName
     * @param capacityText - String from interface, parsed into int
     * @param availability
     * @param ratingText   - String from interface, parsed into Double
     * @param xCoord
     * @param yCoord
     * @param idContact
     * @param idDistrict   - 1 by default
     * @param type         - Selected place type
     * @throws ValidationException - In case of NumberFormatException which happens when there is no value for capacity/rating.
     */
    @Override
    public void buildPlace(String placeName, String capacityText, String availability, String ratingText, Integer xCoord, Integer yCoord, Integer idContact, Integer idDistrict, String type) throws ValidationException {
        try {

            int capacity = Integer.parseInt(capacityText);
            Double rating = Double.parseDouble(ratingText);
            Place p = new Place(placeName, capacity, availability, rating, xCoord, yCoord, idContact, idDistrict, placeType.get(type));
            placeValidator.validate(p); // Validate the place that is about to be built.
            placeRepo.buildPlace(p);
            notifyBuilders();
        } catch (NumberFormatException e) {
            throw new ValidationException("Capacity/Rating must be set");
        }
    }

    private void notifyBuilders() {
        ExecutorService executor = Executors.newFixedThreadPool(threadNumber);
        loggedClients.values().forEach(x -> {
            executor.execute(() -> {
                try {
                    x.eventOccured();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    /**
     * Gets the place by its x and y coordinates and then removes it.
     *
     * @param xCoord
     * @param yCoord
     */
    @Override
    public void removePlace(Integer xCoord, Integer yCoord) {
        List<Place> places = placeRepo.selectByCoords(xCoord, yCoord);
        List<Contact> contact = placeRepo.getContactById(places.get(0).getIdContact());

        if (places.size() > 0 && contact.size() > 0) {
            placeRepo.removePlace(places.get(0));
            placeRepo.deleteContact(contact.get(0));
        }

    }

    /**
     * Updates a selected place after validating the new information.
     *
     * @param xCoord
     * @param yCoord
     * @throws ValidationException
     */
    @Override
    public void updatePlace(Integer xCoord, Integer yCoord) throws ValidationException {
        List<Place> places = placeRepo.selectByCoords(xCoord, yCoord);
        List<Contact> contacts = placeRepo.getContactById(places.get(0).getIdContact());

        if (places.size() > 0 && contacts.size() > 0) {
            placeValidator.validate(places.get(0));
            placeValidator.validate(contacts.get(0));
            placeRepo.updatePlace(places.get(0));
            placeRepo.updateContact(contacts.get(0));
        }

    }

    /**
     * Add a contact in the database
     *
     * @param address
     * @param phoneNo
     * @param website
     * @param email
     * @throws ValidationException
     */
    @Override
    public void addContact(String address, String phoneNo, String website, String email) throws ValidationException {
        Contact c = new Contact(address, phoneNo, website, email);
        placeValidator.validate(c); // Validate the contact attached to the place that is being built.
        placeRepo.insertContact(c);
    }

    @Override
    public Integer getContactIdByAddress(String address) {
        List<Contact> contactByAddress = placeRepo.getContactByAddress(address);
        if (contactByAddress.size() > 0) {
            return contactByAddress.get(0).getIdContact();
        }
        return null;
    }

    public Place getPlaceByCoords(Integer xCoord, Integer yCoord) {
        List<Place> place = placeRepo.selectByCoords(xCoord, yCoord);
        if (place.size() > 0) {
            return place.get(0);
        }
        return null;
    }

    /**
     * Gets contact associated with a place.
     *
     * @param id
     * @return the contact associated with a place if found.
     */
    public Contact getContactById(Integer id) {
        List<Contact> contact = placeRepo.getContactById(id);
        if (contact.size() > 0) {
            return contact.get(0);
        }
        return null;
    }


    /**
     * Checks if a login is valid.
     *
     * @param username
     * @param password
     * @return always true (will be changed in the next iteration)
     */
    public boolean checkLogin(String username, String password, CityObserver cityObserver) {
        loggedClients.put(username, cityObserver);
        return true;
    }

    @Override
    public void addNewBug(String bugDescription, Integer idPlace) {
        Bug bug = new Bug(bugDescription, idPlace, false);
        bugRepo.insertBug(bug);
    }

    @Override
    public List<Bug> showBugsForPlace(Integer idPlace) {

        return bugRepo.getBugsForPlace(idPlace);

    }

    public List<Bug> showAllBugs() {
        return bugRepo.getAllBugs();
    }

    @Override
    public void addRule() {

    }


}

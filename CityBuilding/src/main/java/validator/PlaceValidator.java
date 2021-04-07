package validator;

import entities.Contact;
import entities.Place;
import exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceValidator {
    private String errorsPlace;
    private String errorsContact;

    /**
     * Validates a place according to the following rules.
     *
     * @param p - the place that will be validated
     * @throws ValidationException - the message that will be shown in the interface.
     */
    public void validate(Place p) throws ValidationException {
        errorsPlace = "";
        if (p.getAvailability().equals("")) {
            errorsPlace += "Availability must be set.\n";
        }
        if (p.getPlaceName().equals("")) {
            errorsPlace += "Place name must be set.\n";
        }
        if (p.getCapacity() < 0) {
            errorsPlace += "Capacity cannot be negative.\n";
        }
        if (p.getRating() < 0.0D || p.getRating() > 5.0D) {
            errorsPlace += "Rating must be between 0 and 5.\n";
        }
        if (errorsPlace.length() > 0) {
            throw new ValidationException(errorsPlace);
        }
    }

    /**
     * Validates the contact fields of a place according to the following rules.
     *
     * @param c - the contact of a place that will be validated
     * @throws ValidationException - the message that will be shown in the interface.
     */
    public void validate(Contact c) throws ValidationException {
        errorsContact = "";
        if (c.getAddress().equals("")) {
            errorsContact += "Address must be set.\n";
        }
        if (c.getEmail().equals("")) {
            errorsContact += "Email must be set.\n";
        }
        //Check if email contains @ symbol
        String regexEmail = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(c.getEmail());

        if (!matcher.matches()) {
            errorsContact += "Email must be the following format: email@domain.\n";
        }
        if (c.getPhoneNo().matches("")) {
            errorsContact += "Phone number must be set.\n";
        }
        if (!c.getPhoneNo().matches("^[0-9]+$")) {
            errorsContact += "Phone number cannot contain characters.\n";
        }
        if (c.getWebsite().equals("")) {
            errorsContact += "Website must be set.\n";
        }

        if (errorsContact.length() > 0) {
            throw new ValidationException(errorsContact);
        }
    }

}

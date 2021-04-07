package controller;

import entities.Contact;
import entities.Place;
import exception.ValidationException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import service.Service;
import services.CityObserver;
import services.Services;

import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

/**
 * Controller for builders in the city.
 */
public class BuilderController extends UnicastRemoteObject implements Initializable, EventHandler<javafx.scene.input.MouseEvent>, CityObserver, Serializable {
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Button buttonInsert;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonUpdate;
    @FXML
    private TextField textPlaceName;
    @FXML
    private TextField textRating;
    @FXML
    private TextField textAddress;
    @FXML
    private TextField textPhotos;
    @FXML
    private TextField textCapacity;
    @FXML
    private TextField textAvailability;
    @FXML
    private TextField textPhoneNo;
    @FXML
    private TextField textWebsite;
    @FXML
    private TextField textEmail;
    @FXML
    private ComboBox comboBox;

    private Scene scene;


    private Integer xCoord;
    private Integer yCoord;
    private Services service;
    Rectangle[][] rec = new Rectangle[75][75];
    double width = Screen.getPrimary().getBounds().getWidth() / 75;

    public BuilderController() throws RemoteException {
    }

    /**
     * Initialize interface with the grid and the text boxes on the right.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                rec[i][j] = new Rectangle();
                rec[i][j].setX(i * width);
                rec[i][j].setY(j * width);
                rec[i][j].setWidth(width);
                rec[i][j].setHeight(width);
                rec[i][j].setFill(Color.LIGHTGREEN);
                rec[i][j].setStroke(Color.BLACK);
                pane1.getChildren().add(rec[i][j]);

            }
        }
        pane1.setOnMouseClicked(this);
        comboBox.getItems().addAll(
                "CHURCH",
                "HOSPITAL",
                "PARK",
                "SUPERMARKET",
                "FACTORY",
                "HOUSE",
                "BLOCKFLATS",
                "BANK",
                "PHARMACY"
        );
        buttonInsert.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Handles the Insert button. If clicked on a LightGreen(free) region, the builder fills in the text boxes and builds a place.
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == buttonInsert) {
                    if (xCoord != null && yCoord != null) {
                        if (rec[xCoord][yCoord].getFill() == Color.LIGHTGREEN) {
                            try {
                                service.addContact(textAddress.getText(), textPhoneNo.getText(), textWebsite.getText(), textEmail.getText());
                                Integer idContact = service.getContactIdByAddress(textAddress.getText());
                                service.buildPlace(textPlaceName.getText(), textCapacity.getText(), textAvailability.getText(), textRating.getText(), xCoord, yCoord, idContact, 1, (String) comboBox.getValue());
//                                if (((String) comboBox.getValue()).equalsIgnoreCase("Hospital")) {
//                                    rec[xCoord][yCoord].setFill(Color.RED);
//                                }
                                setComponents();
                            } catch (ValidationException e) {
                                AlertBox.display("Broken Rules", e.getMessage());
                            }

                        }
                    }
                }
            }
        });
        buttonDelete.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Handles the delete button. If pressed, the last clicked place will be deleted.
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == buttonDelete) {
                    if (xCoord != null && yCoord != null) {
                        if (rec[xCoord][yCoord].getFill() != Color.LIGHTGREEN) {
                            service.removePlace(xCoord, yCoord);
                            rec[xCoord][yCoord].setFill(Color.LIGHTGREEN);
                        }
                    }
                }
            }
        });
        buttonUpdate.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Handles the update button. Updates the last place clicked.
             * @param event
             */
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == buttonUpdate) {
                    if (xCoord != null && yCoord != null) {
                        if (rec[xCoord][yCoord].getFill() != Color.LIGHTGREEN) {
                            try {
                                service.updatePlace(xCoord, yCoord);
                            } catch (ValidationException e) {
                                AlertBox.display("Broken Rules", e.getMessage());
                            }
                        }
                    }
                }
            }
        });

    }

    /**
     * Handles the mouse click event. Prints the coordinates for a square in the interface.
     * If clicked on a place it will show all its information on the right part of the interface.
     *
     * @param event
     */
    @Override
    public void handle(javafx.scene.input.MouseEvent event) {
        if (event.getSource() == pane1) {
            double posX = event.getX();
            double posY = event.getY();
            int colX = (int) (posX / width);
            int colY = (int) (posY / width);
            Paint recColor = rec[colX][colY].getFill();
            if (recColor == Color.LIGHTGREEN || recColor == Color.BROWN || recColor == Color.BLUE) {
                textAddress.setText("");
                textAvailability.setText("");
                textCapacity.setText("");
                textEmail.setText("");
                textPhoneNo.setText("");
                textPhotos.setText("");
                textPlaceName.setText("");
                textRating.setText("");
                textWebsite.setText("");
            } else {
                Place place = service.getPlaceByCoords(colX, colY);
                Contact placeContact = service.getContactById(place.getIdContact());

                textAddress.setText(placeContact.getAddress());
                textAvailability.setText(place.getAvailability());
                textCapacity.setText(String.valueOf(place.getCapacity()));
                textEmail.setText(placeContact.getEmail());
                textPhoneNo.setText(placeContact.getPhoneNo());
                textPhotos.setText("");
                textPlaceName.setText(place.getPlaceName());
                textRating.setText(String.valueOf(place.getRating()));
                textWebsite.setText(placeContact.getWebsite());
            }
            this.xCoord = colX;
            this.yCoord = colY;
            System.out.println(colX + " " + colY);
        }


    }

    /**
     * Loads the service and loads all the layers on the interface.
     *
     * @param service
     */
    public void setService(Services service) {
        this.service = service;
        setComponents();


    }

    public void setComponents() {

        service.loadFullDistricts().stream().forEach(x ->
        {
            x.getLandHydroList().stream().forEach(y -> {
                if (y.getIdLayerType() == 1) {
                    rec[y.getxCoord()][y.getyCoord()].setFill(Color.BLUE);
                } else {
                    rec[y.getxCoord()][y.getyCoord()].setFill(Color.BROWN);
                }
            });
            x.getPlaceList().stream().forEach(y -> {
                switch ((y.getIdPlaceType())) {
                    case 1:
                        rec[y.getxCoord()][y.getyCoord()].setFill(Color.GRAY);
                        break;
                    case 2:
                        rec[y.getxCoord()][y.getyCoord()].setFill(Color.RED);
                        break;
                    case 3:
                        rec[y.getxCoord()][y.getyCoord()].setFill(Color.FORESTGREEN);
                        break;
                    case 4:
                        rec[y.getxCoord()][y.getyCoord()].setFill(Color.YELLOW);
                        break;
                    case 5:
                        rec[y.getxCoord()][y.getyCoord()].setFill(Color.BLACK);
                        break;
                    case 6:
                        rec[y.getxCoord()][y.getyCoord()].setFill(Color.PEACHPUFF);
                        break;
                    case 7:
                        rec[y.getxCoord()][y.getyCoord()].setFill(Color.DARKGREY);
                        break;
                    case 8:
                        rec[y.getxCoord()][y.getyCoord()].setFill(Color.WHITESMOKE);
                        break;
                    case 9:
                        rec[y.getxCoord()][y.getyCoord()].setFill(Color.YELLOWGREEN);
                        break;
                }
            });
        });
    }


    @Override
    public void eventOccured() throws RemoteException {
        //refresh
        System.out.println("event occured");
        setComponents();
    }


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return this.scene;
    }
}

package controller;

import api.WeatherAPI;
import entities.Contact;
import entities.Place;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import services.CityObserver;
import services.Services;

import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CitizenController extends UnicastRemoteObject implements Initializable, EventHandler<MouseEvent>, CityObserver, Serializable {
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Button buttonBug;
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
    private TextArea bugDescription;
    @FXML
    private Button buttonSend;
    @FXML
    private TextField search;
    @FXML
    private ListView list;
    @FXML
    private TextField weather;

    private Integer xCoord;
    private Integer yCoord;

    Rectangle[][] rec = new Rectangle[75][75];
    double width = Screen.getPrimary().getBounds().getWidth() / 75;


    private Scene scene;
    private Services service;

    public CitizenController() throws RemoteException {
    }

    @Override
    public void handle(MouseEvent event) {
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
                textPlaceName.setText(place.getPlaceName());
                textRating.setText(String.valueOf(place.getRating()));
                textWebsite.setText(placeContact.getWebsite());
            }
            this.xCoord = colX;
            this.yCoord = colY;
            System.out.println(colX + " " + colY);

        }
    }

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
        bugDescription.setVisible(false);
        buttonSend.setVisible(false);
        buttonBug.setOnAction(event -> {
            if (event.getSource() == buttonBug) {
                bugDescription.setVisible(true);
                buttonSend.setVisible(true);
            }
        });
        buttonSend.setOnAction(event -> {
            if (!bugDescription.getText().isEmpty()) {
                Place place = service.getPlaceByCoords(xCoord, yCoord);
                service.addNewBug(bugDescription.getText(), place.getIdPlace());
            }

        });
        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                handleFilter();
            }
        });

        weather.setText(WeatherAPI.getTemperature());
    }


    /**
     * Filters the places by the search text
     */
    private void handleFilter() {
        service.loadFullDistricts().stream().forEach(x -> {
            List<Place> place = x.getPlaceList().stream().filter(y -> y.getPlaceName().contains(search.getText())).collect(Collectors.toList());

            if (place.size() > 0) {
                Contact placeContact = service.getContactById(place.get(0).getIdContact());

                textPlaceName.setText(place.get(0).getPlaceName());
                textRating.setText(String.valueOf(place.get(0).getRating()));
                textAvailability.setText(place.get(0).getAvailability());
                textCapacity.setText(String.valueOf(place.get(0).getCapacity()));
                textEmail.setText(placeContact.getEmail());
                textPhoneNo.setText(placeContact.getPhoneNo());
                textWebsite.setText(placeContact.getWebsite());
            }
        });
    }

    @Override
    public void eventOccured() throws RemoteException {
        setComponents();
    }

    public Scene getScene() {
        return this.scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setService(Services server) {
        this.service = server;
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


}

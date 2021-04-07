package controller;


import entities.Bug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import service.Service;
import services.CityObserver;
import services.Services;

/**
 * Controller for the city council(a.k.a admin). New rules can be added here (they are not saved in this version of the project)
 */
public class AdminController extends UnicastRemoteObject implements Initializable, EventHandler, CityObserver {
    @FXML
    private Button addRule;
    @FXML
    private Button seeBugs;
    @FXML
    private TextField rule;

    @FXML
    private AnchorPane pane1;
    @FXML
    private ListView<String> listView=new ListView<>();

    private Scene scene;

    private Services service;
    Rectangle[][] rec = new Rectangle[75][75];
    double width = Screen.getPrimary().getBounds().getWidth() / 75;

    public AdminController() throws RemoteException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRule.setOnAction(this);
        seeBugs.setOnAction(this);
    }

    /**
     * Handles the add rule button. Adds a new rule for the builders.
     *
     * @param event
     */
    @Override
    public void handle(Event event) {
        if (event.getSource() == addRule) {
            AlertBox.display("Rule added", "Rule added successfully. Notified all builders.");
            service.addRule();
        }
        if (event.getSource() == seeBugs) {
            List<String> itm = new ArrayList<>();
            service.showAllBugs().stream().forEach(x->{
                itm.add(x.getDescription());
                System.out.println(x.getDescription());
            });

            ObservableList<String> items = FXCollections.observableArrayList(itm);
            listView.setItems(items);
        }
    }

    /**
     * Sets service and initializes the admin interface.
     *
     * @param service
     */
    public void setService(Services service) {
        this.service = service;
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

    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }
}

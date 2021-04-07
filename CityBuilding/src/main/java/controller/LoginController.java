package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.Service;
import services.Services;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, EventHandler {
    public Services service;
    @FXML
    public Button loginButton;
    @FXML
    public Button citizenButton;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;

    private AdminController adminController;
    private BuilderController builderController;
    private CitizenController citizenController;

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

    public void setBuilderController(BuilderController builderController) {
        this.builderController = builderController;
    }
    public void setCitizenController(CitizenController citizenController){
        this.citizenController=citizenController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setOnAction(this);
        citizenButton.setOnAction(this);
    }

    /**
     * Checks if credentials are valid and then checks the username. If the username is "admin" it opens the City Council interface
     * where the "admin" can add a new rule. Otherwise it opens the regular interface, the one for the builders where they can build
     * places.
     *
     * @param event
     */
    @Override
    public void handle(Event event) {
        if (event.getSource() == loginButton) {
            if (username.getText().equals("admin")) {
                Stage stage = (Stage) ((Node) loginButton).getScene().getWindow();
                stage.hide();
                if (service.checkLogin(username.getText(), password.getText(), this.adminController)) {
                    Stage stageAdmin = new Stage();

                    stageAdmin.setTitle("City Building - City Council");
                    stageAdmin.setScene(this.adminController.getScene());
                    stageAdmin.show();
                }
            } else {
                Stage stage = (Stage) ((Node) loginButton).getScene().getWindow();
                stage.hide();
                if (service.checkLogin(username.getText(), password.getText(), this.builderController)) {

                    Stage stageBuilder = new Stage();

                    stageBuilder.setTitle("City Building - Builder");
                    stageBuilder.setScene(this.builderController.getScene());
                    stageBuilder.show();
                }
            }
//        } else {
//            AlertBox.display("Login failed", "Wrong username/password");
//        }
        }
        if(event.getSource()==citizenButton){
            Stage stage = (Stage) ((Node) citizenButton).getScene().getWindow();
            stage.hide();
            Stage stageAdmin = new Stage();

            stageAdmin.setTitle("City Building - Citizen");
            stageAdmin.setScene(this.citizenController.getScene());
            stageAdmin.show();
        }

    }


    public void setService(Services service) {
        this.service = service;
    }
}

import controller.AdminController;
import controller.BuilderController;
import controller.CitizenController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import controller.LoginController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.AmenityRepository;
import repository.DistrictRepository;
import repository.LandHydroRepository;
import repository.PlaceRepository;
import service.Service;
import services.Services;
import validator.PlaceValidator;

public class ClientStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:client-config.xml");
        Services server = (Services) app.getBean("CityBuildService");


        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        LoginController loginController = fxmlLoader.getController();

        FXMLLoader fxmlLoader2 = new FXMLLoader();
        fxmlLoader2.setLocation(getClass().getResource("/fxml/admin.fxml"));
        Parent root2 = fxmlLoader2.load();
        Scene scene2 = new Scene(root2);
        AdminController adminController = fxmlLoader2.getController();
        adminController.setScene(scene2);
        adminController.setService(server);

        FXMLLoader fxmlLoader3 = new FXMLLoader();
        fxmlLoader3.setLocation(getClass().getResource("/fxml/interface.fxml"));
        Parent root3 = fxmlLoader3.load();
        Scene scene3 = new Scene(root3);
        BuilderController builderController = fxmlLoader3.getController();
        builderController.setScene(scene3);
        builderController.setService(server);

        FXMLLoader fxmlLoader4 = new FXMLLoader();
        fxmlLoader4.setLocation(getClass().getResource("/fxml/citizen.fxml"));
        Parent root4 = fxmlLoader4.load();
        Scene scene4 = new Scene(root4);
        CitizenController citizenController = fxmlLoader4.getController();
        citizenController.setScene(scene4);
        citizenController.setService(server);

        loginController.setAdminController(adminController);
        loginController.setBuilderController(builderController);
        loginController.setCitizenController(citizenController);
        loginController.setService(server);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}

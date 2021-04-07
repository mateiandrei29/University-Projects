import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import controller.LoginController;
import repository.AmenityRepository;
import repository.DistrictRepository;
import repository.LandHydroRepository;
import repository.PlaceRepository;
import service.Service;
import validator.PlaceValidator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Service service = new Service(new LandHydroRepository(), new PlaceRepository(), new AmenityRepository(), new DistrictRepository(), new PlaceValidator());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        LoginController loginController= fxmlLoader.getController();
        loginController.setService(service);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

        Stage secondaryStage=new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        fxmlLoader2.setLocation(getClass().getResource("/fxml/login.fxml"));
        Parent root2 = fxmlLoader2.load();
        Scene scene2 = new Scene(root2);
        LoginController loginController2= fxmlLoader2.getController();
        loginController2.setService(service);
        secondaryStage.setTitle("Login");
        secondaryStage.setScene(scene2);
        secondaryStage.show();


    }

}

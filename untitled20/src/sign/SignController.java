package sign;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ControllerSecond;

public class SignController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField markCarsSg;

    @FXML
    private TextField modelCarsSg;

    @FXML
    private Button MakeDo;

    @FXML
    private TextField EngineSg;

    @FXML
    private TextField HorseSg;

    @FXML
    private TextField VolumeSg;

    @FXML
    private TextField DriveSg;

    @FXML
    private TextField SpeedSg;

    @FXML
    void initialize() {
        MakeDo.setOnAction(actionEvent -> {
            String markText = markCarsSg.getText();
            String modelText = modelCarsSg.getText();
            String engineText = EngineSg.getText();
            String horseText = HorseSg.getText();
            String volumeText = VolumeSg.getText();
            String driveText = DriveSg.getText();
            String speedText = SpeedSg.getText();
            DB.signupCar(markText,modelText,engineText,horseText,volumeText,driveText,speedText);

            String way = "/congratulations/congratulations.fxml";
            MakeDo.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(way));

            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("ошибка");
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
    });
    }
}


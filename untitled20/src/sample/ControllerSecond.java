package sample;
//Code for the main first window
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sign.DB;
public class ControllerSecond {

    public static String engineText;
    public static String textTransmission;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField markCars;

    @FXML
    private TextField modelCars;

    @FXML
    private Button goToWtch;

    @FXML
    private Button create;

    @FXML
    void initialize() {
        //Go to subsequent windows depending on the data entered
        goToWtch.setOnAction(actionEvent -> {
            String markText = markCars.getText();
            String modelText = modelCars.getText();

            if(!markText.equals("")&&!modelText.equals("")){
                go(markText, modelText);
            }
            else {
                goToWtch.getScene().getWindow().hide();
                String way = "/error/error.fxml";
                error(way);

            }
        });
            //Go to the window with creating and then saving the machine in sql
        create.setOnAction(actionEvent -> {
            create.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sign/sign.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

    }
    /*A method that takes from the database tables the value of the row that corresponds
                    to the brand and model from 1 table.
    The connection between the tables is done in MySQL Workbench*/
    private void go(String markText, String modelText) {
        String way = null;
        DB db = new DB();
        Car car = new Car(markText, modelText);
        ResultSet rs = db.getCars(car);
        int c = 0;
        while (true){
            try {
                if (!rs.next()) break;
            } catch (SQLException throwables) {
                System.out.println("Что-то не то");
            }
            c++;
        }
        if(c>=1){
            String query = "SELECT * FROM " + Constant.CARS_MODEL;
            int id = 0;
            try {
                Statement statement =  db.getDbConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()){
                    if(resultSet.getString(3).equals(modelText)) {
                        car.setMark(resultSet.getString(1));
                        id = Integer.parseInt(car.getMark());
                    }
                }
                Transmission transmission = new Transmission("","");
                String query1 = "SELECT * FROM " + Constant.CARS_TRANSMISSION + " WHERE " + Constant.CARS_TRANSMISSION_MODEL_ID + " = " + id;
                ResultSet resultSet1 = statement.executeQuery(query1);
                while (resultSet1.next()){
                    transmission.setDrive(resultSet1.getString(2));
                    transmission.setSpeed(resultSet1.getString(3));
                     textTransmission = String.valueOf(transmission);
                    
                }

                Engine engine = new Engine("","","");
                String query2 = "SELECT * FROM " + Constant.CARS_ENGINE + " WHERE " + Constant.CARS_ENGINE_MARK_ID + " = " + id;
                ResultSet resultSet2 = statement.executeQuery(query2);
                while (resultSet2.next()){
                    engine.setNameEngine(resultSet2.getString(2));
                    engine.setHorsePower(resultSet2.getString(3));
                    engine.setVolume(resultSet2.getString(4));
                    engineText = String.valueOf(engine);
                }
                way = "/out/out.fxml";

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        else{
            way = "/error/error.fxml";

        }
        goToWtch.getScene().getWindow().hide();
        error(way);
    }
    //Method that calls a new window with the address way
    public void error(String way){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(way));

        try {
            loader.load();
        } catch (IOException e) {
           e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}

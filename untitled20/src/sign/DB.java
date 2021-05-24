package sign;
import sample.Car;
import sample.Config;
import sample.Constant;

import java.sql.*;

public class DB extends Config {
    //Connecting with DB
    static Connection dbConnection;
    public static Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connection = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connection, dbUser, dbPass);
        return dbConnection;
    }
    static int id = 29;//???features
    //Method for entering and creating a new machine in the database
    public static void signupCar(String mark, String model, String nameEngine, String power, String volume, String drive, String speed){
        id++;
        String insert = "INSERT INTO " + Constant.CARS_MODEL + "(" + Constant.CARS_MODEL_MARK + "," + Constant.CARS_MODEL_MODEL + ")" + "VALUES (?,?)";
        String insert1 = "INSERT INTO " + Constant.CARS_ENGINE + "(" + Constant.CARS_ENGINE_NAME + "," + Constant.CARS_ENGINE_POWER + "," + Constant.CARS_ENGINE_VOLUME +"," +  Constant.CARS_ENGINE_MARK_ID + ")" + "VALUES (?,?,?,?)";
        String insert2 = "INSERT INTO " + Constant.CARS_TRANSMISSION + "(" + Constant.CARS_TRANSMISSION_DRIVE + "," + Constant.CARS_TRANSMISSION_SPEED + "," + Constant.CARS_TRANSMISSION_MODEL_ID +  ")" + "VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,mark);
            preparedStatement.setString(2,model);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(insert1);
            preparedStatement1.setString(1,nameEngine);
            preparedStatement1.setString(2,power);
            preparedStatement1.setString(3,volume);
            preparedStatement1.setString(4, String.valueOf(id));
            preparedStatement1.executeUpdate();
            PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert2);
            preparedStatement2.setString(1,drive);
            preparedStatement2.setString(2,speed);
            preparedStatement2.setString(3, String.valueOf(id));
            preparedStatement2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error2");
        }
    }

        //Method for taking a car from the database
    public ResultSet getCars(Car car){
        ResultSet rs = null;
        String select = "SELECT * FROM " + Constant.CARS_MODEL + " WHERE " +  Constant.CARS_MODEL_MARK  + "=? AND " + Constant.CARS_MODEL_MODEL + "=?";

        try {
            PreparedStatement pr = getDbConnection().prepareStatement(select);
            pr.setString(1, car.getMark());
            pr.setString(2,car.getModel());
            rs = pr.executeQuery();
        } catch (SQLException throwables) {
            System.out.println("error1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rs;

    }

}

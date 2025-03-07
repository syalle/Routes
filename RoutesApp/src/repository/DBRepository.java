package repository;

import domain.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBRepository extends Repository {
    public String databaseName;

    public DBRepository(String databaseName) {
        this.databaseName = databaseName;
        try (Connection dBConnection = DriverManager.getConnection("jdbc:sqlite:" + this.databaseName);) {
            PreparedStatement preparedStatement = dBConnection.prepareStatement("SELECT * FROM Route");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String source = resultSet.getString(2);
                String destination = resultSet.getString(3);
                Integer depTime = resultSet.getInt(4);
                Integer arrivalTime = resultSet.getInt(5);
                Integer nrSeats = resultSet.getInt(6);
                Integer price = resultSet.getInt(7);


                Route route = new Route(id, source, destination, depTime, arrivalTime, nrSeats, price);
                super.add(id, route);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
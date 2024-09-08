package com.example.project.filehandling;

import com.example.project.DatabaseManager;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Concrete implementation of CruiseData for handling all destinations cruise data.
 */
public class AllDestinationsCruiseData extends CruiseData {

    /**
     * Constructor for initializing AllDestinationsCruiseData with a DatabaseManager.
     *
     * @param db The DatabaseManager instance for database operations.
     */
    protected AllDestinationsCruiseData(DatabaseManager db) {
        super(db);
    }

    @Override
    protected String getDestination() {
        return "All Destinations"; // Not used in this implementation
    }

    @Override
    public void fetchData() {
        try {
            Statement statement = getDatabase().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT country_from, duration, place, cruise_ship, route, price, date FROM cruise_destination");

            while (resultSet.next()) {
                setCountryFrom(resultSet.getString("country_from"));
                setDuration(resultSet.getString("duration"));
                setPlace(resultSet.getString("place"));
                setCruiseShip(resultSet.getString("cruise_ship"));
                setRoute(resultSet.getString("route"));
                setPrice(resultSet.getInt("price"));
                setDate(resultSet.getString("date"));

                formatData();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void writeDataToFile() {
        try (PrintWriter writer = new PrintWriter("allDestination.txt")) {
            writer.println(getDataList().toString());
            System.out.println("All Destination data separated and written to file.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

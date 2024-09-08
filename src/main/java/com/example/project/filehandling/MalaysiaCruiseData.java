package com.example.project.filehandling;

import com.example.project.DatabaseManager;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Concrete implementation of CruiseData for handling Malaysia cruise data.
 */
public class MalaysiaCruiseData extends CruiseData {

    /**
     * Constructor for initializing MalaysiaCruiseData with a DatabaseManager.
     *
     * @param db The DatabaseManager instance for database operations.
     */
    public MalaysiaCruiseData(DatabaseManager db) {
        super(db);
    }

    @Override
    protected String getDestination() {
        return "Malaysia";
    }

    @Override
    public void fetchData() {
        try {
            Statement malaysiaStatement = getDatabase().getConnection().createStatement();
            ResultSet malaysiaSet = malaysiaStatement.executeQuery("SELECT country_from, duration, place, cruise_ship, route, price, date FROM cruise_destination WHERE country_from = 'Malaysia';");

            while (malaysiaSet.next()) {
                setCountryFrom(malaysiaSet.getString("country_from"));
                setDuration(malaysiaSet.getString("duration"));
                setPlace(malaysiaSet.getString("place"));
                setCruiseShip(malaysiaSet.getString("cruise_ship"));
                setRoute(malaysiaSet.getString("route"));
                setPrice(malaysiaSet.getInt("price"));
                setDate(malaysiaSet.getString("date"));

                formatData();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void writeDataToFile() {
        try (PrintWriter writer = new PrintWriter("malaysiaDestination.txt")) {
            writer.println(getDataList().toString());
            System.out.println("Malaysia data separated and written to file.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

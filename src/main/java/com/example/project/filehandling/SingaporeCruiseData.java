package com.example.project.filehandling;

import com.example.project.DatabaseManager;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Concrete implementation of CruiseData for handling Singapore cruise data.
 */
public class SingaporeCruiseData extends CruiseData {

    /**
     * Constructor for initializing SingaporeCruiseData with a DatabaseManager.
     *
     * @param db The DatabaseManager instance for database operations.
     */
    public SingaporeCruiseData(DatabaseManager db) {
        super(db);
    }

    @Override
    protected String getDestination() {
        return "Singapore";
    }

    @Override
    public void fetchData() {
        try {
            Statement sgStatement = getDatabase().getConnection().createStatement();
            ResultSet sgSet = sgStatement.executeQuery("SELECT country_from, duration, place, cruise_ship, route, price, date FROM cruise_destination WHERE country_from = 'Singapore';");

            while (sgSet.next()) {
                setCountryFrom(sgSet.getString("country_from"));
                setDuration(sgSet.getString("duration"));
                setPlace(sgSet.getString("place"));
                setCruiseShip(sgSet.getString("cruise_ship"));
                setRoute(sgSet.getString("route"));
                setPrice(sgSet.getInt("price"));
                setDate(sgSet.getString("date"));

                formatData();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void writeDataToFile() {
        try (PrintWriter writer = new PrintWriter("singaporeDestination.txt")) {
            writer.println(getDataList().toString());
            System.out.println("Singapore data separated and written to file.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

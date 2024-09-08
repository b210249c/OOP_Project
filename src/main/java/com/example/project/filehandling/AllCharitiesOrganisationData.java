package com.example.project.filehandling;

import com.example.project.DatabaseManager;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * The AllCharitiesOrganisationData class extends the CharityData class
 * and provides functionality to fetch and write data for all charity organizations
 * from a database. This class is used to extract charity data and store it in a file.
 */
public class AllCharitiesOrganisationData extends CharityData{

    /**
     * Constructor to initialize AllCharitiesOrganisationData with a DatabaseManager.
     *
     * @param db The database manager instance to manage charity data operations.
     */
    protected AllCharitiesOrganisationData(DatabaseManager db) {
        super(db);
    }

    /**
     * Fetches charity data from the database and formats it.
     * The data is extracted from the "charity_organisation" table and includes
     * fields such as name, type, country, about, website, email, and location.
     * <p>
     * This method overrides the abstract fetchData method from CharityData.
     */
    @Override
    public void fetchData() {
        try {
            Statement statement = getDatabase().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name, type, country, about, website, email, location FROM charity_organisation");

            while (resultSet.next()) {
                setName(resultSet.getString("name"));
                setType(resultSet.getString("type"));
                setCountry(resultSet.getString("country"));
                setAbout(resultSet.getString("about"));
                setWebsite(resultSet.getString("website"));
                setEmail(resultSet.getString("email"));
                setLocation(resultSet.getString("location"));

                formatData();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Writes the formatted charity data to a file named "allCharities.txt".
     * The data is written from the StringBuilder containing formatted data.
     * <p>
     * This method overrides the abstract writeDataToFile method from CharityData.
     */
    @Override
    public void writeDataToFile() {
        try (PrintWriter writer = new PrintWriter("allCharities.txt")) {
            writer.println(getDataList().toString());
            System.out.println("All Charity data separated and written to file.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}

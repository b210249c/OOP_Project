package com.example.project.filehandling;

import com.example.project.*;

/**
 * Main class to handle the processing of cruise data and payment data.
 * This class creates instances of different cruise data handlers,
 * fetches data from the database, and writes it to files.
 */
public class FileHandling {
    /**
     * Default constructor for FileHandling.
     * <p>
     * This constructor is provided by default and does not perform any specific actions.
     * </p>
     */
    protected FileHandling() {}

    /**
     * The main method that serves as the entry point for the application.
     * <p>
     * This method initializes the {@link DatabaseManager} singleton instance, then processes cruise data
     * for different regions by creating instances of {@link CruiseDataHandler} subclasses. It fetches and writes
     * the data to files for Singapore, Malaysia, and all destinations.
     * </p>
     *
     * @param args command-line arguments for the application
     */
    public static void main(String[] args) {
        // Get the singleton instance of DatabaseManager
        DatabaseManager database = DatabaseManager.getInstance();

        // Handle Singapore cruise data
        CruiseDataHandler singaporeData = new SingaporeCruiseData(database);
        singaporeData.fetchData();
        singaporeData.writeDataToFile();

        // Handle Malaysia cruise data
        CruiseDataHandler malaysiaData = new MalaysiaCruiseData(database);
        malaysiaData.fetchData();
        malaysiaData.writeDataToFile();

        // Handle all destinations cruise data
        CruiseDataHandler allDestinationsData = new AllDestinationsCruiseData(database);
        allDestinationsData.fetchData();
        allDestinationsData.writeDataToFile();

        // Handle all charity organisations data
        CruiseDataHandler allCharitiesData = new AllCharitiesOrganisationData(database);
        allCharitiesData.fetchData();
        allCharitiesData.writeDataToFile();
    }
}

package com.example.project.filehandling;

/**
 * Interface defining the behavior for handling cruise data.
 * Implementing classes are expected to fetch cruise data from a database
 * and write the formatted data to a file.
 */
public interface CruiseDataHandler {

    /**
     * Fetches the cruise data from the database.
     * The specific implementation should retrieve the relevant cruise data
     * based on the type of cruise data being handled (e.g., Singapore, Malaysia).
     */
    void fetchData();

    /**
     * Writes the fetched and formatted cruise data to a file.
     * The specific implementation should handle writing data to the appropriate file
     * for the type of cruise data being processed.
     */
    void writeDataToFile();
}

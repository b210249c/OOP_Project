package com.example.project;

/**
 * Interface for defining database operations related to managing destinations.
 */
public interface DatabaseOperations {
    /**
     * Adds a new destination/ charity to the database.
     */
    void add();

    /**
     * Deletes an existing destination/ charity from the database.
     *
     * @param deleteBtnDetail A string representing the details of the destination to delete.
     */
    void delete(String deleteBtnDetail);

    /**
     * Updates an existing destination/ charity in the database.
     *
     * @param updateBtnDetails A string representing the details of the destination to update.
     */
    void update(String updateBtnDetails);
}

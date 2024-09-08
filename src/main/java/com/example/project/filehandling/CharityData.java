package com.example.project.filehandling;

import com.example.project.DatabaseManager;

/**
 * The abstract class CharityData serves as a blueprint for handling charity-related data
 * for integration into the cruise booking system. This class implements the
 * CruiseDataHandler interface and utilizes a DatabaseManager for database operations.
 *
 * It stores charity details such as name, type, country, description, website, email,
 * and location. It provides methods to get and set these fields, and includes a
 * method to format the charity data into a readable format.
 */
public abstract class CharityData implements CruiseDataHandler{
    private DatabaseManager database;
    private String name;
    private String type;
    private String country;
    private String about;
    private String website;
    private String email;
    private String location;
    private StringBuilder dataList = new StringBuilder();

    /**
     * Constructor to initialize CharityData with a DatabaseManager.
     *
     * @param db The database manager instance to manage charity data.
     */
    protected CharityData(DatabaseManager db) {
        this.database = db;
    }

    /**
     * Gets the DatabaseManager associated with the charity.
     *
     * @return The current DatabaseManager instance.
     */
    public DatabaseManager getDatabase() {
        return database;
    }

    /**
     * Sets the DatabaseManager for the charity data.
     *
     * @param database The DatabaseManager to be associated.
     */
    public void setDatabase(DatabaseManager database) {
        this.database = database;
    }

    /**
     * Gets the name of the charity.
     *
     * @return The name of the charity.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the charity.
     *
     * @param name The name to set for the charity.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of charity (e.g., health, education, etc.).
     *
     * @return The type of the charity.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the charity.
     *
     * @param type The type to set for the charity.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the country where the charity operates.
     *
     * @return The country of the charity.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country where the charity operates.
     *
     * @param country The country to set for the charity.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the description or information about the charity.
     *
     * @return A string describing the charity.
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the description or information about the charity.
     *
     * @param about A string describing the charity.
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * Gets the website of the charity.
     *
     * @return The website URL of the charity.
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the website URL of the charity.
     *
     * @param website The website URL to set for the charity.
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Gets the contact email of the charity.
     *
     * @return The charity's contact email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the contact email for the charity.
     *
     * @param email The email to set for the charity.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the location of the charity.
     *
     * @return The location of the charity.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the charity.
     *
     * @param location The location to set for the charity.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the formatted charity data in a list format.
     *
     * @return A StringBuilder containing the formatted charity data.
     */
    public StringBuilder getDataList() {
        return dataList;
    }

    /**
     * Formats the charity data into a readable format and stores it in the dataList.
     * The data is structured as: "Name, Type, Country, About, Website, Email, Location".
     */
    protected void formatData() {
        dataList.append("Name , Type , Country , About , Website , Email , Location\n")
                .append(name).append(" , ")
                .append(type).append(" , ")
                .append(country).append(" , ")
                .append(about).append(" , ")
                .append(website).append(" , ")
                .append(email).append(" , ")
                .append(location).append("\n\n");
    }
}

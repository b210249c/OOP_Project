package com.example.project.filehandling;

import com.example.project.DatabaseManager;

/**
 * Abstract class that represents cruise data.
 * This class provides methods for fetching cruise data from the database
 * and formatting it for output.
 */
public abstract class CruiseData implements CruiseDataHandler {
    private DatabaseManager database;
    private String countryFrom;
    private String duration;
    private String place;
    private String cruiseShip;
    private String route;
    private int price;
    private String date;
    private StringBuilder dataList = new StringBuilder();

    /**
     * Constructor for initializing CruiseData with a DatabaseManager.
     *
     * @param db The DatabaseManager instance for database operations.
     */
    protected CruiseData(DatabaseManager db) {
        this.database = db;
    }

    /**
     * Gets the DatabaseManager instance.
     *
     * @return The DatabaseManager instance.
     */
    public DatabaseManager getDatabase() {
        return database;
    }

    /**
     * Sets the DatabaseManager instance.
     *
     * @param database The DatabaseManager instance to set.
     */
    public void setDatabase(DatabaseManager database) {
        this.database = database;
    }

    /**
     * Gets the country from which the cruise originates.
     *
     * @return The country from which the cruise originates.
     */
    public String getCountryFrom() {
        return countryFrom;
    }

    /**
     * Sets the country from which the cruise originates.
     *
     * @param countryFrom The country from which the cruise originates.
     */
    public void setCountryFrom(String countryFrom) {
        this.countryFrom = countryFrom;
    }

    /**
     * Gets the duration of the cruise.
     *
     * @return The duration of the cruise.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the cruise.
     *
     * @param duration The duration of the cruise.
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Gets the place of the cruise.
     *
     * @return The place of the cruise.
     */
    public String getPlace() {
        return place;
    }

    /**
     * Sets the place of the cruise.
     *
     * @param place The place of the cruise.
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Gets the name of the cruise ship.
     *
     * @return The name of the cruise ship.
     */
    public String getCruiseShip() {
        return cruiseShip;
    }

    /**
     * Sets the name of the cruise ship.
     *
     * @param cruiseShip The name of the cruise ship.
     */
    public void setCruiseShip(String cruiseShip) {
        this.cruiseShip = cruiseShip;
    }

    /**
     * Gets the route of the cruise.
     *
     * @return The route of the cruise.
     */
    public String getRoute() {
        return route;
    }

    /**
     * Sets the route of the cruise.
     *
     * @param route The route of the cruise.
     */
    public void setRoute(String route) {
        this.route = route;
    }

    /**
     * Gets the price of the cruise.
     *
     * @return The price of the cruise.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the cruise.
     *
     * @param price The price of the cruise.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets the date of the cruise.
     *
     * @return The date of the cruise.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the cruise.
     *
     * @param date The date of the cruise.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the formatted data list.
     *
     * @return The formatted data list.
     */
    public StringBuilder getDataList() {
        return dataList;
    }

    /**
     * Formats the cruise data and appends it to the data list.
     */
    protected void formatData() {
        dataList.append("Country from , Duration , Place , Cruise ship , Route , Price , Date\n")
                .append(countryFrom).append(" , ")
                .append(duration).append(" , ")
                .append(place).append(" , ")
                .append(cruiseShip).append(" , ")
                .append(route).append(" , ")
                .append(price).append(" , ")
                .append(date).append("\n\n");
    }

    /**
     * Abstract method to get the destination for the cruise data.
     * This method must be implemented by subclasses.
     *
     * @return The destination for the cruise data.
     */
    protected abstract String getDestination();
}

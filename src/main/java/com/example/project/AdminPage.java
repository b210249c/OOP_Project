package com.example.project;

import javafx.scene.Scene;
import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The AdminPage class represents the administrative interface for managing cruise destinations in the Cruise Booking System.
 * It provides methods for adding, updating, and deleting cruise details in the database.
 * <p>
 * This class also integrates with the user interface components through the {@link AdminPageUI} class and
 * interacts with the database using {@link DatabaseManager}.
 * </p>
 */
public class AdminPage extends Component implements DatabaseOperations {
    /**
     * The user interface for the admin page.
     * <p>
     * This field holds an instance of {@link AdminPageUI}, which provides the graphical user interface for
     * administrative tasks and management of cruise destination details within the application.
     * </p>
     */
    private AdminPageUI adminPageUI;

    /**
     * The database manager instance used for database operations.
     * <p>
     * This field holds an instance of {@link DatabaseManager}, which is responsible for managing connections
     * to the database and performing CRUD (Create, Read, Update, Delete) operations on the database.
     * </p>
     */
    private DatabaseManager database;

    /**
     * Constructs an AdminPage instance.
     *
     * @param cbs the CruiseBookingSystem instance used to switch scenes
     * @param login the Login instance for user authentication
     */
    protected AdminPage(CruiseBookingSystem cbs, Login login) {
        this.adminPageUI = new AdminPageUI(this, cbs, login);
        this.database = DatabaseManager.getInstance();
    }

    /**
     * Returns the scene for the administrative page.
     *
     * @return the Scene for the Admin Page
     */
    public Scene adminPageScene() {
        return adminPageUI.adminPageScene();
    }

    /**
     * Adds a new cruise destination to the database.
     * <p>
     * This method prompts the user for details of the new destination and
     * inserts the provided information into the database.
     * </p>
     */
    @Override
    public void add(){
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        JTextField txtCountry_from = new JTextField();
        JTextField txtDuration = new JTextField();
        JTextField txtPlace = new JTextField();
        JTextField txtCruise_ship = new JTextField();
        JTextField txtRoute = new JTextField();
        JTextField txtPrice = new JTextField();
        JTextField txtDate = new JTextField();

        panel.add(new JLabel("Country from: "));
        panel.add(txtCountry_from);
        panel.add(new JLabel("Duration: "));
        panel.add(txtDuration);
        panel.add(new JLabel("Place: "));
        panel.add(txtPlace);
        panel.add(new JLabel("Cruise Ship: "));
        panel.add(txtCruise_ship);
        panel.add(new JLabel("Route: "));
        panel.add(txtRoute);
        panel.add(new JLabel("Price: "));
        panel.add(txtPrice);
        panel.add(new JLabel("Date: "));
        panel.add(txtDate);

        JOptionPane.showMessageDialog(this, panel, "Add New Destination", JOptionPane.PLAIN_MESSAGE);

        String country_from = txtCountry_from.getText();
        String duration = txtDuration.getText();
        String place = txtPlace.getText();
        String cruise_ship = txtCruise_ship.getText();
        String route = txtRoute.getText();
        String date = txtDate.getText();
        int price = Integer.parseInt(txtPrice.getText());

        try {

            PreparedStatement preparedStatement = database.getConnection().prepareStatement("INSERT INTO cruise_destination (country_from,duration,place,cruise_ship,route,price,date) VALUES (?, ?, ?, ?, ?, ?, ?);");

            preparedStatement.setString(1, country_from);
            preparedStatement.setString(2, duration);
            preparedStatement.setString(3, place);
            preparedStatement.setString(4, cruise_ship);
            preparedStatement.setString(5, route);
            preparedStatement.setInt(6, price);
            preparedStatement.setString(7, date);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Deletes a specific cruise destination from the database.
     * <p>
     * This method prompts the user to confirm the deletion of a selected destination
     * and removes it from the database if confirmed.
     * </p>
     *
     * @param deleteBtnDetail the place identifier of the cruise destination to be deleted
     */
    @Override
    public void delete(String deleteBtnDetail){
        try {
            // Show Message
            JOptionPane.showMessageDialog(this, deleteBtnDetail, "Choose place", JOptionPane.PLAIN_MESSAGE);

            int result = JOptionPane.showConfirmDialog(this,"Are you confirm to delete "+deleteBtnDetail+"?", "Delete Place", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if(result == JOptionPane.YES_OPTION) {
                // Prepare an SQL statement to delete the cruise details from the table 'cruise_destination' in the database
                PreparedStatement preparedStatement = database.getConnection().prepareStatement("DELETE FROM cruise_destination WHERE place = ?");

                preparedStatement.setString(1, deleteBtnDetail);

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the details of an existing cruise destination in the database.
     * <p>
     * This method allows the user to modify the details of a selected destination
     * and saves the changes to the database.
     * </p>
     *
     * @param updateBtnDetails the place identifier of the cruise destination to be updated
     */
    @Override
    public void update(String updateBtnDetails){
        try {
            // Prepare the SELECT statement to fetch the details from the database based on the place
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(
                    "SELECT country_from, duration, place, cruise_ship, route, price, date FROM cruise_destination WHERE place = ?");
            preparedStatement.setString(1, updateBtnDetails);

            // Execute the query and fetch the result
            ResultSet resultSet = preparedStatement.executeQuery();

            // Initialize variables to hold the fetched data
            String country_from = "";
            String duration = "";
            String place = "";
            String cruise_ship = "";
            String route = "";
            String date = "";
            int price = 0;

            // If the record is found, populate the variables
            if (resultSet.next()) {
                country_from = resultSet.getString("country_from");
                duration = resultSet.getString("duration");
                place = resultSet.getString("place");
                cruise_ship = resultSet.getString("cruise_ship");
                route = resultSet.getString("route");
                price = resultSet.getInt("price");
                date = resultSet.getString("date");
            }

            // Create the panel and set preferred size for layout
            JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
            panel.setPreferredSize(new Dimension(500, 300));

            // Pre-fill the text fields with the fetched data
            JTextField txtCountry_from = new JTextField(country_from);
            JTextField txtDuration = new JTextField(duration);
            JTextField txtPlace = new JTextField(place);
            JTextField txtCruise_ship = new JTextField(cruise_ship);
            JTextField txtRoute = new JTextField(route);
            JTextField txtPrice = new JTextField(String.valueOf(price)); // Convert price to String
            JTextField txtDate = new JTextField(date);

            // Add labels and text fields to the panel
            panel.add(new JLabel("Country from: "));
            panel.add(txtCountry_from);
            panel.add(new JLabel("Duration: "));
            panel.add(txtDuration);
            panel.add(new JLabel("Place: "));
            panel.add(txtPlace);
            panel.add(new JLabel("Cruise Ship: "));
            panel.add(txtCruise_ship);
            panel.add(new JLabel("Route: "));
            panel.add(txtRoute);
            panel.add(new JLabel("Price: "));
            panel.add(txtPrice);
            panel.add(new JLabel("Date: "));
            panel.add(txtDate);

            // Show the input dialog with pre-filled data
            int option = JOptionPane.showConfirmDialog(null, panel, "Update Cruise Destination", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            // If the user presses OK, update the record in the database with new values
            if (option == JOptionPane.OK_OPTION) {
                // Extract user input from text fields
                country_from = txtCountry_from.getText();
                duration = txtDuration.getText();
                place = txtPlace.getText();
                cruise_ship = txtCruise_ship.getText();
                route = txtRoute.getText();
                date = txtDate.getText();
                String pricetext = txtPrice.getText();
                price = Integer.parseInt(pricetext); // Parse the price as an integer

                // Prepare an SQL statement to update the cruise destination in the database
                preparedStatement = database.getConnection().prepareStatement(
                        "UPDATE cruise_destination SET country_from = ?, duration = ?, place = ?, cruise_ship = ?, route = ?, price = ?, date = ? WHERE place = ?");
                preparedStatement.setString(1, country_from);
                preparedStatement.setString(2, duration);
                preparedStatement.setString(3, place);
                preparedStatement.setString(4, cruise_ship);
                preparedStatement.setString(5, route);
                preparedStatement.setInt(6, price);
                preparedStatement.setString(7, date);
                preparedStatement.setString(8, updateBtnDetails);

                // Execute the update query
                preparedStatement.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

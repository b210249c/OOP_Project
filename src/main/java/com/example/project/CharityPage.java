package com.example.project;

import javafx.scene.Scene;
import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The CharityPage class represents the functionality for managing charity organizations
 * within the cruise booking system. It allows the addition, deletion, and update of charity organizations,
 * interacting with the database and providing UI elements for the same.
 */
public class CharityPage extends Component implements DatabaseOperations {
    /**
     * The user interface for the admin page.
     * <p>
     * This field holds an instance of {@link CharityPageUI}, which provides the graphical user interface for
     * administrative tasks and management of charity organisation details within the application.
     * </p>
     */
    private CharityPageUI charityPageUI;

    /**
     * The database manager instance used for database operations.
     * <p>
     * This field holds an instance of {@link DatabaseManager}, which is responsible for managing connections
     * to the database and performing CRUD (Create, Read, Update, Delete) operations on the database.
     * </p>
     */
    private DatabaseManager database;

    /**
     * Constructs a CharityPage instance with the necessary references to other system components.
     *
     * @param cbs   the CruiseBookingSystem instance that handles scene transitions and logic.
     * @param login the Login instance containing information about the currently logged-in user.
     */
    protected CharityPage(CruiseBookingSystem cbs, Login login) {
        this.charityPageUI = new CharityPageUI(this, cbs, login);
        this.database = DatabaseManager.getInstance();
    }

    /**
     * Returns the charity page scene which contains the UI for managing charity organizations.
     *
     * @return the Scene object representing the charity page.
     */
    public Scene charityPageScene() {
        return charityPageUI.charityPageScene();
    }

    /**
     * Adds a new charity organization to the database by collecting user input for various fields
     * (name, type, country, about, website, email, and location) and executing an INSERT SQL statement.
     */
    @Override
    public void add(){
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        JTextField txtName = new JTextField();
        JTextField txtType = new JTextField();
        JTextField txtCountry = new JTextField();
        JTextField txtAbout = new JTextField();
        JTextField txtWebsite = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtLocation = new JTextField();

        panel.add(new JLabel("Name: "));
        panel.add(txtName);
        panel.add(new JLabel("Type: "));
        panel.add(txtType);
        panel.add(new JLabel("Country: "));
        panel.add(txtCountry);
        panel.add(new JLabel("About: "));
        panel.add(txtAbout);
        panel.add(new JLabel("Website: "));
        panel.add(txtWebsite);
        panel.add(new JLabel("Email: "));
        panel.add(txtEmail);
        panel.add(new JLabel("Location: "));
        panel.add(txtLocation);

        JOptionPane.showMessageDialog(this, panel, "Add New Charities", JOptionPane.PLAIN_MESSAGE);

        String name = txtName.getText();
        String type = txtType.getText();
        String country = txtCountry.getText();
        String about = txtAbout.getText();
        String website = txtWebsite.getText();
        String email = txtEmail.getText();
        String location = txtLocation.getText();

        try {

            PreparedStatement preparedStatement = database.getConnection().prepareStatement("INSERT INTO charity_organisation (name,type,country,about,website,email,location) VALUES (?, ?, ?, ?, ?, ?, ?);");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, country);
            preparedStatement.setString(4, about);
            preparedStatement.setString(5, website);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, location);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Deletes a charity organization from the database based on the name.
     *
     * @param deleteBtnDetail the name of the charity organization to be deleted.
     */
    @Override
    public void delete(String deleteBtnDetail){
        try {
            // Show Message
            JOptionPane.showMessageDialog(this, deleteBtnDetail, "Choose charity", JOptionPane.PLAIN_MESSAGE);


            int result = JOptionPane.showConfirmDialog(this,"Are you confirm to delete "+deleteBtnDetail+"?", "Delete Charity", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if(result == JOptionPane.YES_OPTION) {
                // Prepare an SQL statement to delete the charity details from the table 'charity_organisation' in the database
                PreparedStatement preparedStatement = database.getConnection().prepareStatement("DELETE FROM charity_organisation WHERE name = ?");

                preparedStatement.setString(1, deleteBtnDetail);

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates an existing charity organization in the database by allowing the user to modify
     * fields like name, type, country, about, website, email, and location.
     *
     * @param updateBtnDetails the name of the charity organization to be updated.
     */
    @Override
    public void update(String updateBtnDetails) {
        try {
            // Fetch the record from the database based on the name
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(
                    "SELECT name, type, country, about, website, email, location FROM charity_organisation WHERE name = ?");
            preparedStatement.setString(1, updateBtnDetails);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Initialize the variables to hold the fetched data
            String name = "";
            String type = "";
            String country = "";
            String about = "";
            String website = "";
            String email = "";
            String location = "";

            // Check if the result set contains the data, then set variables
            if (resultSet.next()) {
                name = resultSet.getString("name");
                type = resultSet.getString("type");
                country = resultSet.getString("country");
                about = resultSet.getString("about");
                website = resultSet.getString("website");
                email = resultSet.getString("email");
                location = resultSet.getString("location");
            }

            // Now that data is fetched, pre-fill the input fields
            JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
            panel.setPreferredSize(new Dimension(500, 300));

            // Text Fields (Pre-filled with the fetched data)
            JTextField txtName = new JTextField(name);
            JTextField txtType = new JTextField(type);
            JTextField txtCountry = new JTextField(country);
            JTextField txtAbout = new JTextField(about);
            JTextField txtWebsite = new JTextField(website);
            JTextField txtEmail = new JTextField(email);
            JTextField txtLocation = new JTextField(location);

            // Label and text fields added to the panel
            panel.add(new JLabel("Name: "));
            panel.add(txtName);
            panel.add(new JLabel("Type: "));
            panel.add(txtType);
            panel.add(new JLabel("Country: "));
            panel.add(txtCountry);
            panel.add(new JLabel("About: "));
            panel.add(txtAbout);
            panel.add(new JLabel("Website: "));
            panel.add(txtWebsite);
            panel.add(new JLabel("Email: "));
            panel.add(txtEmail);
            panel.add(new JLabel("Location: "));
            panel.add(txtLocation);

            // Show the input dialog with pre-filled information
            int option = JOptionPane.showConfirmDialog(null, panel, "Update Charity", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                // Extract user input from the text fields
                name = txtName.getText();
                type = txtType.getText();
                country = txtCountry.getText();
                about = txtAbout.getText();
                website = txtWebsite.getText();
                email = txtEmail.getText();
                location = txtLocation.getText();

                // Prepare an SQL statement to update the charity details in the database
                preparedStatement = database.getConnection().prepareStatement(
                        "UPDATE charity_organisation SET name = ?, type = ?, country = ?, about = ?, website = ?, email = ?, location = ? WHERE name = ?");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, type);
                preparedStatement.setString(3, country);
                preparedStatement.setString(4, about);
                preparedStatement.setString(5, website);
                preparedStatement.setString(6, email);
                preparedStatement.setString(7, location);
                preparedStatement.setString(8, updateBtnDetails);

                // Execute the update
                preparedStatement.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

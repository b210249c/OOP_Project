package com.example.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The CruiseBookingSystem class represents the main application for the cruise booking system.
 * It extends the JavaFX Application class and manages scene transitions within the application.
 * <p>
 * The class sets up and displays the primary stages and scenes, including the main scene, login scene,
 * registration scene, and admin page. It also initializes necessary components for handling user interactions.
 * </p>
 */
public class CruiseBookingSystem extends Application {
    private Stage stage;
    private Main main;
    private LoginHandler loginHandler;
    private Home home;
    private Register register;
    private AdminPage adminPage;
    private CharityPage charityPage;

    /**
     * Default constructor for CruiseBookingSystem.
     * <p>
     * This constructor is provided by default and does not perform any specific actions.
     * </p>
     */
    public CruiseBookingSystem(){}

    /**
     * The entry point of the JavaFX application.
     * <p>
     * Initializes the main components of the application, sets the initial scene to the main scene, and shows the stage.
     * </p>
     *
     * @param stage the primary stage for this application, onto which the application scene is set
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;
        main = new Main(this);
        loginHandler = new LoginHandler(this);
        home = new Home(this, loginHandler.getLogin());
        register = new Register(this);
        adminPage = new AdminPage(this, loginHandler.getLogin());
        charityPage = new CharityPage(this, loginHandler.getLogin());
        stage.setTitle("CruiseBooker");
        switchToMainScene();
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Switches the current scene to the main scene.
     * <p>
     * This method sets the scene of the stage to the main scene, which is displayed when the application starts.
     * </p>
     */
    public void switchToMainScene() {
        Scene scene = main.mainScene();
        stage.setScene(scene);
    }

    /**
     * Switches the current scene to the login scene.
     * <p>
     * This method sets the scene of the stage to the login scene, which is used for user authentication.
     * </p>
     */
    public void switchToLoginScene() {
        stage.setScene(loginHandler.getLoginScene()); // Use LoginHandler to switch scenes
    }

    /**
     * Switches the current scene to the registration scene.
     * <p>
     * This method sets the scene of the stage to the registration scene, where users can create a new account.
     * </p>
     */
    public void switchToRegisterScene() {
        Scene scene = register.registerScene();
        stage.setScene(scene);
    }

    /**
     * Switches the current scene to the admin page scene.
     * <p>
     * This method sets the scene of the stage to the admin page scene, where administrators can manage cruise details.
     * </p>
     */
    public void switchToAdminPageScene() {
        Scene scene = adminPage.adminPageScene();
        stage.setScene(scene);
    }

    /**
     * Switches the current scene to the Charity Page scene.
     * <p>
     * This method sets the scene of the primary stage to the Charity Page scene, which displays information about various charity organizations.
     * </p>
     */
    public void switchToCharityScene() {
        Scene scene = charityPage.charityPageScene();
        stage.setScene(scene);
    }

    /**
     * Switches the current scene to the Home scene.
     * <p>
     * This method sets the scene of the primary stage to the Home scene, which provides an overview of the application and contain navigation options.
     * </p>
     */
    public void switchToHomeScene() {
        Scene scene = home.homeScene();
        stage.setScene(scene);
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args command-line arguments for the application
     */
    public static void main(String[] args) {
        launch();
    }
}

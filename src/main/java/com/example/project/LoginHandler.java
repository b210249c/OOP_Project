package com.example.project;

import javafx.scene.Scene;

/**
 * The {@code LoginHandler} class handles the login process and manages the login scene.
 * It holds a composition relationship with the {@link Login} class, which means it
 * creates and manages the {@code Login} object internally.
 * <p>
 * This class interacts with the {@link CruiseBookingSystem} to facilitate scene management
 * for login-related activities.
 * </p>
 */
public class LoginHandler {

    /**
     * An instance of the {@link Login} class, which is used to create and manage the
     * login user interface.
     */
    private Login login;

    /**
     * Constructs a {@code LoginHandler} and creates an instance of the {@link Login} class.
     * This establishes a composition relationship between {@code LoginHandler} and {@code Login}.
     *
     * @param cbs the instance of the cruise booking system used for managing scene transitions.
     */
    protected LoginHandler(CruiseBookingSystem cbs) {
        this.login = new Login(cbs); // Composition relationship
    }

    /**
     * Retrieves the login scene by calling the {@code createScene} method of the {@link Login} class.
     *
     * @return the JavaFX {@link Scene} object representing the login interface.
     */
    public Scene getLoginScene() {
        return login.createScene();
    }

    /**
     * Retrieves the {@link Login} instance.
     * <p>
     * This method can be used to access the {@code Login} object for further operations.
     * </p>
     *
     * @return the {@link Login} instance.
     */
    public Login getLogin() {
        return login;
    }
}

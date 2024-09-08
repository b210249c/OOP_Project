package com.example.project;

import javafx.scene.Scene;

/**
 * The {@code UserInterface} class serves as an abstract base class for all user interfaces
 * in the Cruise Booking System application. It provides the foundation for creating
 * different types of user interface screens by defining the {@code createScene} method.
 * <p>
 * This class utilizes the {@link CruiseBookingSystem} instance to switch between different
 * scenes in the application.
 * </p>
 */
public abstract class UserInterface {
    /**
     * A reference to the {@link CruiseBookingSystem} that allows interaction
     * between the user interface and the core system functionality.
     */
    protected CruiseBookingSystem cbs;

    /**
     * Constructs a new {@code UserInterface} with the specified {@link CruiseBookingSystem}.
     *
     * @param cbs the instance of the cruise booking system used for scene management.
     */
    public UserInterface(CruiseBookingSystem cbs) {
        this.cbs = cbs;
    }

    /**
     * An abstract method that must be implemented by subclasses to create and return
     * a JavaFX {@link Scene}. Each concrete subclass is responsible for creating its
     * specific user interface layout.
     *
     * @return the scene that represents this user interface.
     */
    public abstract Scene createScene();
}

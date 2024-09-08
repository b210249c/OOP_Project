/**
 * Module declaration for the example project.
 * <p>
 * This module defines the dependencies and visibility for the project's packages.
 * It requires JavaFX modules for UI controls and FXML, Java SQL for database interactions,
 * and MySQL connector for database connectivity.
 * <p>
 * It also opens specific packages to JavaFX for FXML loading and exports them for use
 * by other modules or applications.
 */
module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.java;

    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.filehandling;
    opens com.example.project.filehandling to javafx.fxml;
}
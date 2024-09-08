package com.example.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code Login} class is responsible for managing the login functionality in the cruise booking system.
 * This class extends the {@link UserInterface} and defines the logic for user authentication,
 * as well as the user interface for the login page.
 */
public class Login extends UserInterface{
    /** The username of the currently logged-in user. */
    private String username;

    /** The email of the currently logged-in user. */
    private String email;

    /** The database manager to interact with the database for authentication. */
    private DatabaseManager database;

    /**
     * Constructs a {@code Login} object with the given cruise booking system reference.
     * It also initializes the {@link DatabaseManager} to handle database operations.
     *
     * @param cbs the cruise booking system reference to manage scene transitions.
     */
    protected Login(CruiseBookingSystem cbs) {
        super(cbs);
        // Initialize database instance
        this.database = DatabaseManager.getInstance();
    }

    /**
     * Creates and returns the login scene, which includes the login form with fields for email,
     * password, and a login button. The interface also provides a sign-up link.
     *
     * @return a JavaFX {@link Scene} representing the login interface.
     */
    @Override
    public Scene createScene() {
        BorderPane borderPane = new BorderPane();
        // Background and layout setup as before
        borderPane.setBackground(new Background(new BackgroundImage(
                new Image(CruiseBookingSystem.class.getResource("bgforlogin.png").toString(), 1366, 768, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        borderPane.setCenter(CenterLogin());
        borderPane.setTop(TopBackButton());
        return new Scene(borderPane, 1039, 694);
    }

    /**
     * Creates the back button for the top of the login scene, which allows users to navigate
     * back to the main scene.
     *
     * @return an {@link HBox} containing the back button.
     */
    public HBox TopBackButton(){

        //Contruct button "<"
        Button btnBack = new Button("<");

        //The style of the button "<"
        btnBack.setFont(Font.font("Arial", 35));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKGRAY);
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        btnBack.setEffect(dropShadow);

        btnBack.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-radius: 15%;" +
                        "-fx-background-radius: 150%;" +
                        "-fx-padding: 8px 16px;"
        );

        //When hover button "<"
        btnBack.setOnMouseEntered(event -> btnBack.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: #1b2b36;" +
                        "-fx-border-radius: 50%;" +
                        "-fx-background-radius: 50%;" +
                        "-fx-padding: 8px 16px;"
        ));

        //When exit hover button "<"
        btnBack.setOnMouseExited(event -> btnBack.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-radius: 50%;" +
                        "-fx-background-radius: 50%;" +
                        "-fx-padding: 8px 16px;"
        ));
        //When button "<" pressed
        btnBack.setOnAction(event -> {
            cbs.switchToMainScene();
        });

        HBox hBox = new HBox(btnBack);
        hBox.setAlignment(Pos.BASELINE_LEFT);

        return hBox;
    }

    /**
     * Constructs the main login interface, including email and password fields, a login button,
     * and a sign-up hyperlink.
     *
     * @return a {@link BorderPane} containing the login interface.
     */
    public BorderPane CenterLogin(){

        //External Fonts
        Font customFont = Font.loadFont(getClass().getResourceAsStream("LilitaOne-Regular.ttf"), 18);
        Font customFont1 = Font.loadFont(getClass().getResourceAsStream("Lato-Regular.ttf"), 18);

        // Login Title
        Label signInLabel = new Label("Login");
        signInLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;-fx-font-family: '" + customFont.getName() + "';-fx-text-fill: #6699ff;");

        // Email
        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        //Password
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Sign In button
        Button signInButton = new Button("Login");

        //Style of the login button
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKGRAY);
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        signInButton.setEffect(dropShadow);

        signInButton.setStyle("-fx-background-color: #cfd7d9; -fx-background-radius: 25px; -fx-padding: 5px 10px;-fx-text-fill: #0e3641; -fx-font-size: 15px;-fx-font-family: '" + customFont1.getName() + "';-fx-font-weight: bold;");

        signInButton.setPrefSize(100, 40);

        signInButton.setOnMouseEntered(event -> {
            signInButton.setStyle("-fx-background-color: rgba(14,54,65, 0.8); -fx-border-color: transparent; -fx-background-radius: 25px;-fx-text-fill: #cfd7d9; -fx-padding: 5px 10px; -fx-font-size: 15px;-fx-font-family: '" + customFont1.getName() + "';-fx-font-weight: bold;");
        });

        signInButton.setOnMouseExited(event -> {
            signInButton.setStyle("-fx-background-color: #cfd7d9; -fx-border-color: transparent; -fx-background-radius: 25px;-fx-text-fill: #0e3641; -fx-padding: 5px 10px; -fx-font-size: 15px;-fx-font-family: '" + customFont1.getName() + "';-fx-font-weight: bold;");
        });

        //When login button clicked
        signInButton.setOnAction(event -> {

            //Construct database
            database = DatabaseManager.getInstance();
            email = emailField.getText();
            String password = passwordField.getText();

            //Authentication
            //Successful login
            if (validate(email, password, database)) {
                System.out.println(getUsername());
                System.out.println(getEmail());
                if (getEmail().endsWith("@admin.com")) {
                    emailField.clear();
                    passwordField.clear();
                    // Admin login, switch to admin page
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login Successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Redirect to Admin Page.");
                    alert.showAndWait();
                    cbs.switchToHomeScene();
                } else {
                    // Regular user login, switch to home page
//                    emailField.clear();
//                    passwordField.clear();
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Login Successful");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Redirect to Home Page.");
//                    alert.showAndWait();
//                    cbs.switchToHomeScene();
                }
            } else {
                // Show an error message for unsuccessful login
                emailField.clear();
                passwordField.clear();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid credentials. Please try again.");
                alert.showAndWait();
            }
        });

        // Sign Up text button
        Hyperlink signUpLink = new Hyperlink("Sign Up for an Account");

        // Define the styles for normal and hover states
        String normalStyle = "-fx-text-fill: #00ffca; -fx-underline: true;";
        String hoverStyle = "-fx-text-fill: #00ff05;-fx-underline: false;";

        signUpLink.setStyle(normalStyle);

        signUpLink.setOnMouseEntered(event -> signUpLink.setStyle(hoverStyle));
        signUpLink.setOnMouseExited(event -> signUpLink.setStyle(normalStyle));

        //When sign up hyperlink is clicked
        signUpLink.setOnAction(event -> {
            cbs.switchToRegisterScene();
        });


        //Style
        VBox vBox = new VBox(signInLabel, emailField, passwordField, signInButton, signUpLink);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        BorderPane borderPane = new BorderPane(vBox);
        BorderPane.setMargin(borderPane, new Insets(150, 200, 200, 200));
        borderPane.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.7), 10, 0, 0, 0);");
        BorderPane.setMargin(vBox, new Insets(50));

        return borderPane;
    }

    /**
     * Validates the email and password by checking them against the stored credentials in the database.
     *
     * @param email    the user's email address.
     * @param password the user's password.
     * @param database the database manager for querying stored credentials.
     * @return {@code true} if the email and password match the stored credentials, {@code false} otherwise.
     */
    private boolean validate(String email, String password, DatabaseManager database) {
        try {

            //Prepared Statement
            PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT username, password, email FROM REGISTRATION WHERE email = ?");
            preparedStatement.setString(1, email);

            //Get result from database
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storePassword = resultSet.getString("password");
                // You should use a proper password hashing library to compare hashes
                if (password.equals(storePassword)) {
                    username = resultSet.getString("username");
                    email = resultSet.getString("email");
                    return true; // Authentication successful
                }
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false; // Authentication failed
    }

    /**
     * Retrieves the username of the currently logged-in user.
     *
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for the user.
     *
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the email of the currently logged-in user.
     *
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email for the user.
     *
     * @param email the email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

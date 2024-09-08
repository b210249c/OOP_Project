package com.example.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Represents the home page of the Cruise Booking System.
 * This class provides the user interface elements for the home scene including
 * navigation and information display.
 */
public class Home {
    private CruiseBookingSystem cbs;
    private Login login;

    /**
     * Constructs a Home instance with the specified CruiseBookingSystem and Login.
     *
     * @param cbs the CruiseBookingSystem instance for scene management
     * @param login the Login instance for user information
     */
    protected Home(CruiseBookingSystem cbs,Login login) {
        this.cbs = cbs;
        this.login = login;
    }

    /**
     * Creates and returns the home scene for the Cruise Booking System.
     *
     * @return a Scene object representing the home page
     */
    public Scene homeScene() {

        BorderPane borderPane = new BorderPane();

        // Set background image
        Image image = new Image(CruiseBookingSystem.class.getResource("homebg.png").toString(), 1366,768,false,true);
        BackgroundImage bI = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        borderPane.setBackground(new Background(bI));

        // Set top navigation bar and center information
        borderPane.setTop(TopNavigationBar());
        borderPane.setCenter(CenterInformation());


        return new Scene(borderPane, 1039, 694);
    }

    /**
     * Creates and returns the top navigation bar for the home page.
     *
     * @return a BorderPane containing the top navigation bar
     */
    public BorderPane TopNavigationBar(){

        Button btnLogout = new Button("Logout");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKGRAY);
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        btnLogout.setEffect(dropShadow);

        btnLogout.setStyle("-fx-background-color: #cfd7d9; -fx-background-radius: 25px; -fx-padding: 5px 5px;-fx-text-fill: #0e3641; -fx-font-size: 15px;-fx-font-family: lato;-fx-font-weight: bold;");


        btnLogout.setPrefSize(80, 30);

        btnLogout.setOnMouseEntered(event -> {
            btnLogout.setStyle("-fx-background-color: rgba(0,85,255, 0.8); -fx-border-color: transparent; -fx-background-radius: 25px;-fx-text-fill: #cfd7d9; -fx-padding: 5px 5px; -fx-font-size: 15px;-fx-font-family: lato;-fx-font-weight: bold;");
        });

        btnLogout.setOnMouseExited(event -> {
            btnLogout.setStyle("-fx-background-color: #cfd7d9; -fx-border-color: transparent; -fx-background-radius: 25px;-fx-text-fill: #0e3641; -fx-padding: 5px 5px; -fx-font-size: 15px;-fx-font-family: lato;-fx-font-weight: bold;");
        });

        btnLogout.setOnAction(event -> {
            cbs.switchToMainScene();
        });

        Text welcome = new Text("Welcome, "+login.getUsername());
        welcome.setStyle("-fx-fill: white;");
        welcome.setFont(Font.font("Eras Demi ITC", 20));

        HBox hBox = new HBox(welcome, btnLogout);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(20));
        hBox.setAlignment(Pos.BASELINE_RIGHT);

        BorderPane borderPane = new BorderPane(hBox);
        borderPane.setStyle("-fx-background-color: rgba(0,0,0,0.3);");

        return borderPane;
    }

    /**
     * Creates and returns the center information pane for the home page.
     *
     * @return a BorderPane containing the center information and buttons
     */
    public BorderPane CenterInformation() {

        VBox textVB = new VBox();
        Font customFont = Font.loadFont(getClass().getResourceAsStream("YesevaOne-Regular.ttf"), 18);

        // Center text Home
        Text description = new Text("Cruise Management System");
        Text description1 = new Text("Admin Page");
        description.setWrappingWidth(1000);
        description.setTextAlignment(TextAlignment.CENTER);
        description.setStyle("-fx-fill: white;-fx-font-size: 45px;-fx-font-family: '" + customFont.getName() + "';");
        description1.setWrappingWidth(1000);
        description1.setTextAlignment(TextAlignment.CENTER);
        description1.setStyle("-fx-fill: white;-fx-font-size: 35px;");

        // Create Manage Destinations button
        Button btnManageDestinations = new Button();
        btnManageDestinations.setPrefSize(180, 60);
        btnManageDestinations.setStyle("-fx-background-color: transparent; -fx-border-color: #60442a; -fx-border-width: 1;");
        btnManageDestinations.setOnMouseEntered(e -> btnManageDestinations.setStyle("-fx-background-color: #60442a; -fx-border-color: #60442a; -fx-border-width: 1;"));
        btnManageDestinations.setOnMouseExited(e -> btnManageDestinations.setStyle("-fx-background-color: transparent; -fx-border-color: #60442a; -fx-border-width: 1;"));

        Text buttonTextManage = new Text("Manage Destinations");
        buttonTextManage.setFill(Color.WHITE);
        buttonTextManage.setFont(Font.font("Lato", FontWeight.BOLD, 18));

        btnManageDestinations.setGraphic(buttonTextManage);

        btnManageDestinations.setOnAction(event -> {
            cbs.switchToAdminPageScene();
        });

        // Create Additional button
        Button btnAdditional = new Button();
        btnAdditional.setPrefSize(180, 60);
        btnAdditional.setStyle("-fx-background-color: transparent; -fx-border-color: #60442a; -fx-border-width: 1;");
        btnAdditional.setOnMouseEntered(e -> btnAdditional.setStyle("-fx-background-color: #60442a; -fx-border-color: #60442a; -fx-border-width: 1;"));
        btnAdditional.setOnMouseExited(e -> btnAdditional.setStyle("-fx-background-color: transparent; -fx-border-color: #60442a; -fx-border-width: 1;"));

        Text buttonTextAdditional = new Text("Manage Charities");
        buttonTextAdditional.setFill(Color.WHITE);
        buttonTextAdditional.setFont(Font.font("Lato", FontWeight.BOLD, 18));

        btnAdditional.setGraphic(buttonTextAdditional);

        btnAdditional.setOnAction(event -> {
            cbs.switchToCharityScene();
        });

        // Create HBox for buttons
        HBox buttonHBox = new HBox(20, btnManageDestinations, btnAdditional);
        buttonHBox.setAlignment(Pos.CENTER);

        textVB.getChildren().addAll(description, description1);
        textVB.setAlignment(Pos.CENTER);

        VBox.setMargin(textVB, new Insets(60, 0, 295, 0));
        VBox vBox = new VBox(textVB, buttonHBox);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(20);

        BorderPane borderPane = new BorderPane(vBox);

        return borderPane;
    }

}

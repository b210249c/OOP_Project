package com.example.project;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.effect.DropShadow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This class is responsible for constructing the user interface for the admin page of the cruise booking system.
 * It handles the layout and presentation of cruise destinations, including filtering options, and provides functionalities
 * for updating and deleting destinations.
 */
public class AdminPageUI {
    private String selectedCountry = "All";
    private String selectedDuration = "All";
    private ComboBox<String> category;
    private ComboBox<String> duration;
    private VBox mainVBox;
    private DatabaseManager database;
    private CruiseBookingSystem cbs;
    private Login login;
    private AdminPage adminPage;
    private ResultSet resultSet;

    /**
     * Constructs an AdminPageUI instance.
     *
     * @param adminPage The instance of AdminPage that handles the business logic.
     * @param cbs The instance of CruiseBookingSystem that manages scene transitions.
     * @param login The instance of Login that provides user authentication details.
     */
    protected AdminPageUI(AdminPage adminPage, CruiseBookingSystem cbs, Login login) {
        this.adminPage = adminPage;
        this.cbs = cbs;
        this.login = login;
        this.database = DatabaseManager.getInstance();
        this.mainVBox = new VBox();
        this.category = new ComboBox<>();
        this.duration = new ComboBox<>();
    }

    /**
     * Creates and returns the scene for the admin page.
     *
     * @return The Scene object representing the admin page.
     */
    public Scene adminPageScene() {

        BorderPane intBorderPane = new BorderPane();
        intBorderPane.setTop(titleVBox());

        ScrollPane scrollPane = new ScrollPane(CruiseBorderPane());
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setFitToWidth(true);

        intBorderPane.setCenter(scrollPane);

        BorderPane mainBorderPane = new BorderPane();

        //Set background
        javafx.scene.image.Image image = new Image(CruiseBookingSystem.class.getResource("search1.png").toString(), 1366,768,false,true);
        BackgroundImage bI = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        mainBorderPane.setBackground(new Background(bI));

        mainBorderPane.setTop(TopNavigationBar());
        mainBorderPane.setCenter(intBorderPane);

        return new Scene(mainBorderPane, 1039, 694);
    }

    /**
     * Creates and returns the top navigation bar for the admin page.
     *
     * @return The BorderPane object representing the top navigation bar.
     */
    public BorderPane TopNavigationBar() {
        // Back button
        Button btnBack = new Button("<");

        // Styling back button
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DARKGRAY);
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        btnBack.setEffect(dropShadow);

        btnBack.setStyle("-fx-background-color: #cfd7d9; -fx-background-radius: 25px; -fx-padding: 5px 5px;-fx-text-fill: #0e3641; -fx-font-size: 15px;-fx-font-family: lato;-fx-font-weight: bold;");
        btnBack.setPrefSize(40, 30);

        // Hover, Exit, Click button
        btnBack.setOnMouseEntered(event -> {
            btnBack.setStyle("-fx-background-color: rgba(0,85,255, 0.8); -fx-border-color: transparent; -fx-background-radius: 25px;-fx-text-fill: #cfd7d9; -fx-padding: 5px 5px; -fx-font-size: 15px;-fx-font-family: lato;-fx-font-weight: bold;");
        });

        btnBack.setOnMouseExited(event -> {
            btnBack.setStyle("-fx-background-color: #cfd7d9; -fx-border-color: transparent; -fx-background-radius: 25px;-fx-text-fill: #0e3641; -fx-padding: 5px 5px; -fx-font-size: 15px;-fx-font-family: lato;-fx-font-weight: bold;");
        });

        btnBack.setOnAction(event -> {
            cbs.switchToHomeScene();
        });

        // Logout button
        Button btnLogout = new Button("Logout");

        // Styling logout button
        btnLogout.setEffect(dropShadow);

        btnLogout.setStyle("-fx-background-color: #cfd7d9; -fx-background-radius: 25px; -fx-padding: 5px 5px;-fx-text-fill: #0e3641; -fx-font-size: 15px;-fx-font-family: lato;-fx-font-weight: bold;");
        btnLogout.setPrefSize(80, 30);

        // Hover, Exit, Click button
        btnLogout.setOnMouseEntered(event -> {
            btnLogout.setStyle("-fx-background-color: rgba(0,85,255, 0.8); -fx-border-color: transparent; -fx-background-radius: 25px;-fx-text-fill: #cfd7d9; -fx-padding: 5px 5px; -fx-font-size: 15px;-fx-font-family: lato;-fx-font-weight: bold;");
        });

        btnLogout.setOnMouseExited(event -> {
            btnLogout.setStyle("-fx-background-color: #cfd7d9; -fx-border-color: transparent; -fx-background-radius: 25px;-fx-text-fill: #0e3641; -fx-padding: 5px 5px; -fx-font-size: 15px;-fx-font-family: lato;-fx-font-weight: bold;");
        });

        btnLogout.setOnAction(event -> {
            cbs.switchToMainScene();
        });

        // Welcome text
        Text welcome = new Text("Welcome, " + login.getUsername());
        welcome.setStyle("-fx-fill: white;");
        welcome.setFont(Font.font("Eras Demi ITC", 20));

        // Create HBox for the back button (left side)
        HBox leftBox = new HBox(btnBack);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setPadding(new Insets(10));

        // Create HBox for the welcome text and logout button (right side)
        HBox rightBox = new HBox(welcome, btnLogout);
        rightBox.setSpacing(20);
        rightBox.setPadding(new Insets(10));
        rightBox.setAlignment(Pos.CENTER_RIGHT);

        // Top Navigation Bar color
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(leftBox);
        borderPane.setRight(rightBox);
        borderPane.setStyle("-fx-background-color: rgba(0,0,0,0.3);");

        return borderPane;
    }


    /**
     * Constructs and returns the VBox containing the title and filter controls for the admin page.
     *
     * @return The VBox object representing the title and filter controls.
     */
    public VBox titleVBox(){
        Font customFont = Font.loadFont(getClass().getResourceAsStream("Righteous-Regular.ttf"), 18);

        //Title
        Text title = new Text("Manage Destinations");
        title.setStyle("-fx-font-family: '" + customFont.getName() + "';-fx-font-size: 38px;");
        title.setTextAlignment(TextAlignment.CENTER);

        //Add Button to add cruise details
        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(event -> {
            adminPage.add();
            mainVBox.getChildren().clear();
            CruiseBorderPane();
            System.out.println("Destination added");

        });
        btnAdd.setPrefSize(140, 40);
        btnAdd.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #91C9FF; -fx-border-width: 1;-fx-text-fill:#4F95DA;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:18px;");
        btnAdd.setOnMouseEntered(e -> btnAdd.setStyle("-fx-background-color: transparent; -fx-border-color: #FFFFFF; -fx-border-width: 1;-fx-text-fill:#FFFFFF;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:18px;"));
        btnAdd.setOnMouseExited(e -> btnAdd.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #91C9FF; -fx-border-width: 1;-fx-text-fill:#4F95DA;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:18px;"));

        //List of Country
        category = new ComboBox<>();
        category.getItems().addAll("All", "Malaysia", "Singapore");
        category.setPromptText("Select Departure Country");
        category.setPrefWidth(300);
        category.setOnAction(event -> {
            mainVBox.getChildren().clear();
            selectedCountry = category.getValue();
            category.setPromptText(selectedCountry);
            CruiseBorderPane();
        });

        //List for night
        duration = new ComboBox<>();
        duration.getItems().addAll("All", "1 Night", "2 Nights", "3 Nights", "4 Nights");
        duration.setPromptText("Select Duration");
        duration.setPrefWidth(300);
        duration.setOnAction(event -> {
            mainVBox.getChildren().clear();
            selectedDuration = duration.getValue();
            duration.setPromptText(selectedDuration);
            CruiseBorderPane();
        });

        //Keep combo box
        HBox filterHBox = new HBox(category, duration);
        filterHBox.setSpacing(30);
        filterHBox.setAlignment(Pos.CENTER);

        VBox titleVBox = new VBox(title, btnAdd, filterHBox);
        titleVBox.setSpacing(30);
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.setPadding(new Insets(30));

        return titleVBox;
    }

    /**
     * Constructs and returns the VBox containing the cruise destination information based on the current filters.
     *
     * @return The VBox object representing the cruise destinations.
     */
    public VBox CruiseBorderPane(){
        // Initialize database manager
        database = DatabaseManager.getInstance();

        mainVBox.getChildren().clear();

        try {
            // Check different filter conditions and execute corresponding queries
            if (selectedCountry.equals("All") && selectedDuration.equals("All")) {
                Statement statement = database.getConnection().createStatement();

                resultSet = statement.executeQuery("SELECT country_from, duration, place, cruise_ship, route, price, date FROM cruise_destination");

            } else if ((selectedCountry.equals("Malaysia") || selectedCountry.equals("Singapore")) && selectedDuration.equals("All")) {
                PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT country_from, duration, place, cruise_ship, route, price, date FROM cruise_destination WHERE country_from = ?");

                preparedStatement.setString(1, selectedCountry);

                resultSet = preparedStatement.executeQuery();

            } else if (selectedCountry.equals("All") && (selectedDuration.equals("1 Night") || selectedDuration.equals("2 Nights") || selectedDuration.equals("3 Nights") || selectedDuration.equals("4 Nights"))) {
                PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT country_from, duration, place, cruise_ship, route, price, date FROM cruise_destination WHERE duration = ?");

                preparedStatement.setString(1, selectedDuration);

                resultSet = preparedStatement.executeQuery();

            } else if ((selectedCountry.equals("Malaysia") || selectedCountry.equals("Singapore")) && (selectedDuration.equals("1 Night") || selectedDuration.equals("2 Nights") || selectedDuration.equals("3 Nights") || selectedDuration.equals("4 Nights"))) {
                PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT country_from, duration, place, cruise_ship, route, price, date FROM cruise_destination WHERE country_from = ? AND duration = ?");

                preparedStatement.setString(1, selectedCountry);
                preparedStatement.setString(2, selectedDuration);

                resultSet = preparedStatement.executeQuery();
            }

            // Loop through the result set and create UI elements for each cruise
            while (resultSet.next()){
                Label lblDuration = new Label("Duration: ");
                Label lblDate = new Label("Date: ");
                Label lblCountry = new Label("Departure From: ");
                Label lblCruise_ship = new Label("Cruise Ship: ");
                Label lblRoute = new Label("Route: ");
                Label lblPrice = new Label("Price : ");

                // Retrieve data from result set
                Text duration = new Text(resultSet.getString("duration"));
                Text place = new Text(resultSet.getString("place"));
                place.setFont(Font.font("Eras Demi ITC", 15));
                Text date = new Text(resultSet.getString("date"));
                Text country = new Text(resultSet.getString("country_from"));
                Text cruise_ship = new Text(resultSet.getString("cruise_ship"));
                Text route = new Text(resultSet.getString("route"));
                Text price = new Text("RM"+resultSet.getString("price"));


                // Create "Update" for updating cruise's details
                Button btnUpdate = new Button("Update");
                btnUpdate.setUserData(resultSet.getString("place"));
                Button btnDelete = new Button("Delete");
                btnDelete.setUserData(resultSet.getString("place"));

                // Create layout for displaying cruise details
                GridPane gridPane = new GridPane();
                gridPane.addColumn(0, lblDuration, lblDate, lblCountry, lblCruise_ship, lblRoute,lblPrice);
                gridPane.addColumn(1, duration, date, country, cruise_ship, route,price);
                gridPane.setHgap(10);
                gridPane.setVgap(5);

                // Create left and right VBox for UI layout
                VBox vBoxLeft = new VBox(place, gridPane);
                VBox vBoxRight = new VBox(btnDelete,btnUpdate);

                vBoxRight.setStyle("-fx-background-color: #3c362a;");
                vBoxRight.setSpacing(30);
                vBoxRight.setPadding(new Insets(0,60,0,60));
                vBoxRight.setAlignment(Pos.CENTER);

                btnUpdate.setStyle("-fx-background-color: transparent; -fx-border-color: #FFFFFF; -fx-border-width: 1;-fx-text-fill:#FFFFFF;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:13px;");
                btnUpdate.setOnMouseEntered(e -> btnUpdate.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #91C9FF; -fx-border-width: 1;-fx-text-fill:#4F95DA;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:13px;"));
                btnUpdate.setOnMouseExited(e -> btnUpdate.setStyle("-fx-background-color: transparent; -fx-border-color: #FFFFFF; -fx-border-width: 1;-fx-text-fill:#FFFFFF;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:13px;"));

                // Handle button click to update
                btnUpdate.setOnAction(event -> {
                    String updateBtnDetails = btnUpdate.getUserData().toString();
                    adminPage.update(updateBtnDetails);
                    mainVBox.getChildren().clear();
                    CruiseBorderPane();
                    System.out.println("Destination updated");
                });

                btnDelete.setStyle("-fx-background-color: transparent; -fx-border-color: #FFFFFF; -fx-border-width: 1;-fx-text-fill:#FFFFFF;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:13px;");
                btnDelete.setOnMouseEntered(e -> btnDelete.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #91C9FF; -fx-border-width: 1;-fx-text-fill:#4F95DA;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:13px;"));
                btnDelete.setOnMouseExited(e -> btnDelete.setStyle("-fx-background-color: transparent; -fx-border-color: #FFFFFF; -fx-border-width: 1;-fx-text-fill:#FFFFFF;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:13px;"));

                // Handle button click to delete
                btnDelete.setOnAction(event -> {
                    String deleteBtnDetails = btnDelete.getUserData().toString();
                    adminPage.delete(deleteBtnDetails);
                    mainVBox.getChildren().clear();
                    CruiseBorderPane();
                    System.out.println("Destination deleted");
                });

                vBoxRight.setAlignment(Pos.CENTER);
                vBoxLeft.setSpacing(20);
                BorderPane borderPane = new BorderPane();
                borderPane.setLeft(vBoxLeft);
                borderPane.setRight(vBoxRight);
                BorderPane.setMargin(vBoxLeft, new Insets(30));
                borderPane.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.7), 10, 0, 0, 0);");
                mainVBox.getChildren().add(borderPane);
                mainVBox.setSpacing(20);
                mainVBox.setAlignment(Pos.CENTER);
                mainVBox.setStyle("-fx-background-color: #E3CAB8;");
                mainVBox.setPadding(new Insets(30));
            }

            // Display message if no results found
            if (mainVBox.getChildren().isEmpty()) {
                BorderPane noResultBorderPane = new BorderPane();
                Text txtNoResult = new Text("No results found.");
                txtNoResult.setFont(Font.font("Eras Demi ITC", 60));
                txtNoResult.setFill(Color.RED);
                txtNoResult.setTextAlignment(TextAlignment.CENTER);
                noResultBorderPane.setCenter(txtNoResult);
                noResultBorderPane.setStyle("-fx-background-color: transparent;");

                mainVBox.getChildren().add(noResultBorderPane);
                mainVBox.setAlignment(Pos.CENTER);
                mainVBox.setStyle("-fx-background-color: #E3CAB8;");
                mainVBox.setPadding(new Insets(30));
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return mainVBox;
    }

    /**
     * Gets the selected country filter.
     *
     * @return the selected country
     */
    public String getSelectedCountry() {
        return selectedCountry;
    }

    /**
     * Gets the selected duration filter.
     *
     * @return the selected duration
     */
    public String getSelectedDuration() {
        return selectedDuration;
    }

    /**
     * Sets the selected country filter.
     *
     * @param selectedCountry the country to set as the filter
     */
    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    /**
     * Sets the selected duration filter.
     *
     * @param selectedDuration the duration to set as the filter
     */
    public void setSelectedDuration(String selectedDuration) {
        this.selectedDuration = selectedDuration;
    }

    /**
     * Retrieves the current ComboBox that holds the category filter.
     *
     * @return the ComboBox containing the list of categories such as countries (e.g., "All", "Malaysia", "Singapore").
     */
    public ComboBox<String> getCategory() {
        return category;
    }

    /**
     * Sets the category ComboBox to a new ComboBox.
     *
     * @param category the ComboBox to be set, which will replace the current ComboBox used for filtering by category.
     */
    public void setCategory(ComboBox<String> category) {
        this.category = category;
    }

    /**
     * Gets the ComboBox representing the duration.
     *
     * @return the ComboBox containing the list of categories such as durations (e.g., "1 Night", "2 Nights", etc.).
     */
    public ComboBox<String> getDuration() {
        return duration;
    }

    /**
     * Sets the duration ComboBox to a new ComboBox.
     *
     * @param duration the ComboBox to be set, which will replace the current ComboBox used for filtering by duration.
     */
    public void setDuration(ComboBox<String> duration) {
        this.duration = duration;
    }
}

package com.example.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * CharityPageUI class handles the user interface for managing charity organizations.
 * It displays a list of charity organizations based on the selected country,
 * allowing the user to add, update, or delete charity details.
 * This class uses JavaFX components to create a user-friendly UI.
 */
public class CharityPageUI {
    private String selectedCountry = "All";
    private ComboBox<String> category;
    private VBox mainVBox;
    private DatabaseManager database;
    private CruiseBookingSystem cbs;
    private Login login;
    private CharityPage charityPage;
    private ResultSet resultSet;

    /**
     * Constructor for CharityPageUI.
     * Initializes the charity page, CruiseBookingSystem, and login references,
     * and sets up the main UI layout.
     *
     * @param charityPage Reference to the CharityPage instance.
     * @param cbs Reference to the CruiseBookingSystem instance.
     * @param login Reference to the Login instance containing user details.
     */
    protected CharityPageUI(CharityPage charityPage, CruiseBookingSystem cbs, Login login) {
        this.charityPage = charityPage;
        this.cbs = cbs;
        this.login = login;
        this.database = DatabaseManager.getInstance();
        this.mainVBox = new VBox();
        this.category = new ComboBox<>();
    }

    /**
     * Generates the Scene for the charity page.
     * It sets up the main layout including the top navigation bar and charity list.
     *
     * @return The Scene object for the charity page.
     */
    public Scene charityPageScene() {

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
     * Generates the top navigation bar for the charity page.
     * It contains the back button, a welcome message, and a logout button.
     *
     * @return The BorderPane object for the top navigation bar.
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
     * Creates a VBox containing the title, an "Add" button for adding charities,
     * and a ComboBox for filtering charities by country.
     *
     * @return The VBox object with the title and controls for the charity page.
     */
    public VBox titleVBox(){
        Font customFont = Font.loadFont(getClass().getResourceAsStream("Righteous-Regular.ttf"), 18);

        //Title
        Text title = new Text("Manage Charities");
        title.setStyle("-fx-font-family: '" + customFont.getName() + "';-fx-font-size: 38px;");
        title.setTextAlignment(TextAlignment.CENTER);

        //Add Button to add cruise details
        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(event -> {
            charityPage.add();
            mainVBox.getChildren().clear();
            CruiseBorderPane();
            System.out.println("Charity added");

        });
        btnAdd.setPrefSize(140, 40);
        btnAdd.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #91C9FF; -fx-border-width: 1;-fx-text-fill:#4F95DA;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:18px;");
        btnAdd.setOnMouseEntered(e -> btnAdd.setStyle("-fx-background-color: transparent; -fx-border-color: #FFFFFF; -fx-border-width: 1;-fx-text-fill:#FFFFFF;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:18px;"));
        btnAdd.setOnMouseExited(e -> btnAdd.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #91C9FF; -fx-border-width: 1;-fx-text-fill:#4F95DA;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:18px;"));

        //List of Country
        category = new ComboBox<>();
        category.getItems().addAll("All", "Malaysia", "Singapore");
        category.setPromptText("Select Country");
        category.setPrefWidth(300);
        category.setOnAction(event -> {
            mainVBox.getChildren().clear();
            selectedCountry = category.getValue();
            category.setPromptText(selectedCountry);
            CruiseBorderPane();
        });

        //Keep combo box
        HBox filterHBox = new HBox(category);
        filterHBox.setSpacing(30);
        filterHBox.setAlignment(Pos.CENTER);

        VBox titleVBox = new VBox(title, btnAdd, filterHBox);
        titleVBox.setSpacing(30);
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.setPadding(new Insets(30));

        return titleVBox;
    }

    /**
     * Generates the main content layout for the charity page.
     * It displays the charity organizations based on the selected country filter.
     * Users can update or delete charity information.
     *
     * @return The VBox object containing the list of charities.
     */
    public VBox CruiseBorderPane(){
        // Initialize database manager
        database = DatabaseManager.getInstance();

        mainVBox.getChildren().clear();

        try {
            // Check different filter conditions and execute corresponding queries
            if (selectedCountry.equals("All")) {
                Statement statement = database.getConnection().createStatement();

                resultSet = statement.executeQuery("SELECT name, type, country, about, website, email, location FROM charity_organisation");

            } else if ((selectedCountry.equals("Malaysia") || selectedCountry.equals("Singapore"))) {
                PreparedStatement preparedStatement = database.getConnection().prepareStatement("SELECT name, type, country, about, website, email, location FROM charity_organisation WHERE country = ?");

                preparedStatement.setString(1, selectedCountry);

                resultSet = preparedStatement.executeQuery();

            }

            // Loop through the result set and create UI elements for each cruise
            while (resultSet.next()){

                Label lblType = new Label("Type: ");
                Label lblCountry = new Label("Country: ");
                Label lblAbout = new Label("About: ");
                Label lblWebsite = new Label("Website: ");
                Label lblEmail = new Label("Email : ");
                Label lblLocation = new Label("Location: ");

                // Retrieve data from result set
                Text type = new Text(resultSet.getString("type"));
                Text name = new Text(resultSet.getString("name"));
                name.setFont(Font.font("Eras Demi ITC", 15));
                Text country = new Text(resultSet.getString("country"));
                Text about = new Text(resultSet.getString("about"));
                about.setWrappingWidth(650);
                about.setTextAlignment(TextAlignment.JUSTIFY);
                Text website = new Text(resultSet.getString("website"));
                Text email = new Text(resultSet.getString("email"));
                Text location = new Text(resultSet.getString("location"));


                // Create "Update" for updating cruise's details
                Button btnUpdate = new Button("Update");
                btnUpdate.setUserData(resultSet.getString("name"));
                Button btnDelete = new Button("Delete");
                btnDelete.setUserData(resultSet.getString("name"));

                // Create layout for displaying cruise details
                GridPane gridPane = new GridPane();
                gridPane.addColumn(0, lblType, lblCountry, lblAbout, lblWebsite, lblEmail,lblLocation);
                gridPane.addColumn(1, type, country, about, website, email,location);
                gridPane.setHgap(10);
                gridPane.setVgap(5);

                // Create left and right VBox for UI layout
                VBox vBoxLeft = new VBox(name, gridPane);
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
                    charityPage.update(updateBtnDetails);
                    mainVBox.getChildren().clear();
                    CruiseBorderPane();
                    System.out.println("Charity updated");
                });

                btnDelete.setStyle("-fx-background-color: transparent; -fx-border-color: #FFFFFF; -fx-border-width: 1;-fx-text-fill:#FFFFFF;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:13px;");
                btnDelete.setOnMouseEntered(e -> btnDelete.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #91C9FF; -fx-border-width: 1;-fx-text-fill:#4F95DA;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:13px;"));
                btnDelete.setOnMouseExited(e -> btnDelete.setStyle("-fx-background-color: transparent; -fx-border-color: #FFFFFF; -fx-border-width: 1;-fx-text-fill:#FFFFFF;-fx-font-family: Lato; -fx-font-weight:bold;-fx-font-size:13px;"));

                // Handle button click to delete
                btnDelete.setOnAction(event -> {
                    String deleteBtnDetails = btnDelete.getUserData().toString();
                    charityPage.delete(deleteBtnDetails);
                    mainVBox.getChildren().clear();
                    CruiseBorderPane();
                    System.out.println("Charity deleted");
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
     * Sets the selected country filter.
     *
     * @param selectedCountry the country to set as the filter
     */
    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
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
}

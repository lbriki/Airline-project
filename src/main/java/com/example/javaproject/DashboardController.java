package com.example.javaproject;
import com.google.gson.JsonObject;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;


public  class DashboardController implements Initializable{
    public static int idVol;

    @FXML
    private Pane weather;
    @FXML
    private ImageView avatar;

    @FXML
    private AnchorPane container;

    @FXML
    private ImageView dashboard_icon;

    @FXML
    private TableView<Vol> dashboard_table;

    @FXML
    private Label dashboard_welcome;
    @FXML
    private Label dashboard_user_name;
    @FXML
    private ImageView deconnect_icon;
    @FXML
    private TableColumn<Vol,Integer> ID_vol;
    @FXML
    private TableColumn<Vol, String> depart;
    @FXML
    private TableColumn<Vol, String> arrive;
    @FXML
    private TableColumn<Vol,Integer> id_avion;
    @FXML
    private TableColumn<Vol,Integer> id_pilote;
    @FXML
    private TableColumn<Vol,String> jdep;
    @FXML
    private TableColumn<Vol,String> jarr;
    @FXML
    private TableColumn<Vol,String> detailsColumn;

    @FXML
    private AnchorPane navbar;

    @FXML
    private ImageView pilot_icon;

    @FXML
    private ImageView plane_icon;

    @FXML
    private ImageView team_icon;



    @FXML
    private ImageView vol_icon;


    @FXML
    private WebView map;
    @FXML
    private Label vols_number;

    @FXML
    private Label passengers_number;
    @FXML
    private Label price;
    @FXML
    private Label destinations_number;


    @FXML
    private ComboBox<String> combo_box;

    @FXML
    private ImageView place1;

    @FXML
    private ImageView place2;

    @FXML
    private ImageView place3;

    @FXML
    private Label nameplace1;

    @FXML
    private Label nameplace2;

    @FXML
    private Label nameplace3;


    @FXML
    private Pane places_container;
    @FXML
    private Button search_weather;
    @FXML
    private TextField country_weather;
    private static final String API_KEY = "a7e4a70f87abb3b3c1602586b43e20f4";

    // Set your desired city and country code here


    public void initialize (URL Location, ResourceBundle resources) {
        dashboard_table.refresh();
        dashboard_welcome.setText("Welcome  " + AdminDAO.user_name_login.toUpperCase());
        dashboard_user_name.setText(AdminDAO.user_fullname_login);
/*
        ArrayList<String> places=HomeDAO.get_top_places();
        Iterator<String> iterator = places.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            place1.setImage(new Image("C:\\JavaProject\\src\\main\\resources\\images\\" + element));
            place2.setImage(new Image("C:\\JavaProject\\src\\main\\resources\\images\\" + element));
            place3.setImage(new Image("C:\\JavaProject\\src\\main\\resources\\images\\" + places.get(3)));
        }


 */
        // vols_number.setText(String.valueOf(new VolDAO().get_vols_number(Integer.parseInt(AdminDAO.user_login))));
        ID_vol.setCellValueFactory(new PropertyValueFactory<>("ID_vol"));
        depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        arrive.setCellValueFactory(new PropertyValueFactory<>("arrive"));
        // id_avion.setCellValueFactory(new PropertyValueFactory<>("ID_avion"));
        // id_pilote.setCellValueFactory(new PropertyValueFactory<>("ID_pilote"));
        jdep.setCellValueFactory(new PropertyValueFactory<>("jdep"));
        jarr.setCellValueFactory(new PropertyValueFactory<>("jarr"));

        try {
            dashboard_table.setItems(new HomeDAO().getall(HomeDAO.week));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        combo_box.getItems().addAll("This Week", "This Month");
        combo_box.setValue("This Week");
        vols_number.setText(String.valueOf(HomeDAO.get_vols_number(HomeDAO.week)));
        passengers_number.setText(String.valueOf(HomeDAO.get_passengers_number(HomeDAO.week)));
        price.setText("$" + String.format("%.0f", HomeDAO.get_revenue(HomeDAO.week)));
        destinations_number.setText(String.valueOf(HomeDAO.get_destinations_number(HomeDAO.week)));
        combo_box.setOnAction(e -> {
            if (combo_box.getValue() == "This Week") {
                vols_number.setText(String.valueOf(HomeDAO.get_vols_number(HomeDAO.week)));
                passengers_number.setText(String.valueOf(HomeDAO.get_passengers_number(HomeDAO.week)));
                price.setText("$" + String.format("%.0f", HomeDAO.get_revenue(HomeDAO.week)));
                destinations_number.setText(String.valueOf(HomeDAO.get_destinations_number(HomeDAO.week)));
                try {
                    dashboard_table.setItems(new HomeDAO().getall(HomeDAO.week));
                    dashboard_table.refresh();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } else {
                vols_number.setText(String.valueOf(HomeDAO.get_vols_number(HomeDAO.month)));
                passengers_number.setText(String.valueOf(HomeDAO.get_passengers_number(HomeDAO.month)));
                price.setText("$" + String.format("%.0f", HomeDAO.get_revenue(HomeDAO.month)));
                destinations_number.setText(String.valueOf(HomeDAO.get_destinations_number(HomeDAO.month)));
                try {
                    dashboard_table.setItems(new HomeDAO().getall(HomeDAO.month));
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }

            }
        });

        //most visited places :
        ArrayList<String> places_list = HomeDAO.get_top_places();

        HBox hbox = new HBox();
        for (String place : places_list) {
            System.out.println(places_list);
            String path = "C:\\Users\\xfour\\Desktop\\AirlineProject\\src\\main\\resources\\images\\Paris.jpg" ;//+ place + ".jpg";
            // String path="C:\\Users\\xfour\\Desktop\\MYproject\\Airline-JavaProject-\\src\\main\\resources\\images\\Paris.jpg";

            System.out.println("this is " + place);
            VBox vbox = new VBox();
            Label place_name = new Label(place);
            ImageView image = new ImageView(new Image(path));
            image.setStyle("-fx-background-radius: 20px;");
            image.setFitWidth(120);
            image.setFitHeight(90);
            vbox.getChildren().addAll(image, place_name);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);
            hbox.getChildren().add(vbox);
        }
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        places_container.getChildren().add(hbox);


// Set the cell factory to create a cell with the value "details"
        detailsColumn.setCellFactory(new Callback<TableColumn<Vol, String>, TableCell<Vol, String>>() {
            @Override
            public TableCell<Vol, String> call(TableColumn<Vol, String> column) {
                return new TableCell<Vol, String>() {
                    private final Hyperlink detailsLink = new Hyperlink();

                    {
                        detailsLink.setOnMouseClicked(event -> {
                            // Handle the click event here
                            idVol = getTableView().getItems().get(getIndex()).getID_vol();
                            System.out.println("linked page to vol " + idVol);
                            LoadScene.load_pane(container, "details.fxml");
                            System.out.println("Details link clicked for row " + getIndex());
                        });
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            detailsLink.setText("details");
                            setGraphic(detailsLink);
                        }
                    }
                };
            }
        });









            // Set your OpenWeatherMap API key here


        final String CITY = "Tunis";
        final String COUNTRY_CODE = " ";

                ImageView imageView = new ImageView();

                try {
                    // Make API request to OpenWeatherMap to get current temperature and weather condition
                    URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "," + COUNTRY_CODE + "&appid=" + API_KEY + "&units=metric");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Parse the JSON response to get the current temperature and weather condition
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONObject main = jsonObject.getJSONObject("main");
                    double temperature = main.getDouble("temp");
                    JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
                    String weatherIcon = weather.getString("icon");

                    // Create HTML content to display temperature and weather condition
                    String content = "<html><body><h1>Current temperature in " + CITY + "</h1><h2>" + temperature + " &#8451;</h2></body></html>";
                    map.getEngine().loadContent(content);

                    // Load weather icon image
                    Image image = new Image("http://openweathermap.org/img/w/" + weatherIcon + ".png");
                    imageView.setImage(image);

                } catch (Exception e) {
                    String content = "<html><body><h1>Error retrieving temperature and weather condition</h1></body></html>";
                    map.getEngine().loadContent(content);
                    e.printStackTrace();
                }


                StackPane root = new StackPane(map, imageView);
                weather.getChildren().add(root);
                imageView.translateYProperty().set(40);
                imageView.translateXProperty().set(20);






    }








public void search_weather(ActionEvent event){
    final String CITY = country_weather.getText();
    System.out.println(CITY);
    final String COUNTRY_CODE = " ";
    ImageView imageView = new ImageView();
    try {

        // Make API request to OpenWeatherMap to get current temperature and weather condition
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "," + COUNTRY_CODE + "&appid=" + API_KEY + "&units=metric");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse the JSON response to get the current temperature and weather condition
        JSONObject jsonObject = new JSONObject(response.toString());
        JSONObject main = jsonObject.getJSONObject("main");
        double temperature = main.getDouble("temp");
        JSONObject wind = jsonObject.getJSONObject("wind");
        double windSpeed = wind.getDouble("speed");

        JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
        String weatherIcon = weather.getString("icon");

        // Create HTML content to display temperature and weather condition
        String content = "<html><body><h1>Current temperature in " + CITY + "</h1><h2>" + temperature + " &#8451;</h2></body></html>";
        map.getEngine().loadContent(content);

        // Load weather icon image
        Image image = new Image("http://openweathermap.org/img/w/" + weatherIcon + ".png");
        imageView.setImage(image);

    } catch (Exception e) {
        String content = "<html><body><h1>Error retrieving temperature and weather condition</h1></body></html>";
        map.getEngine().loadContent(content);
        e.printStackTrace();
    }

    StackPane root = new StackPane(map, imageView);
    weather.getChildren().add(root);
    imageView.translateYProperty().set(40);
    imageView.translateXProperty().set(20);


}






        public void load(MouseEvent event1)  {
                    LoadScene.load_pane(container,"signup.fxml");
        }


        public void load_dashboard(MouseEvent event){
        LoadScene.load_pane(container,"home.fxml");
        }



}

package com.example.javaproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomestructureController implements Initializable {

    @FXML
    private AnchorPane container;

    @FXML
    private ImageView dashboard_icon;

    @FXML
    private ImageView deconnect_icon;

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
    private ImageView sleep;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadScene.load_pane(container,"dashboard.fxml");
    }

    public  void dashboard_load(MouseEvent event){
        LoadScene.load_pane(container,"dashboard.fxml");
    }

    public void vols_load(MouseEvent event){LoadScene.load_pane(container,"vols.fxml");}
    public void staff_load(MouseEvent event){LoadScene.load_pane(container,"about.fxml");}

    //public void avion_load(MouseEvent event){LoadScene.load_pane(container,"dashboard.fxml");}


    public void disconnect(MouseEvent event){
        Myconnection.disconnect(AdminDAO.connection);
        LoadScene.load(deconnect_icon,"login.fxml","Travel",event);
    }
    public void chat(MouseEvent event){
        Client client = new Client();
        client.start(new Stage());
        //Client.start();
        // Call the main method of b.java using ProcessBuilder
           /*ProcessBuilder pb = new ProcessBuilder("java", "-classpath", ".", "com.example.javaproject.Client");
            pb.directory(new File("C:\\Users\\xfour\\Desktop\\MYproject\\Airline-JavaProject-\\"));
            Process p = pb.start();
            */
        System.out.println("chaaaaaaaaaaaaaaaaat");

    }
    public void sleep_load(MouseEvent event){
        LoadScene.load(sleep,"sleep.fxml","Travel",event);
    }
}

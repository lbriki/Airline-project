package com.example.javaproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;


public class HomeController  implements Initializable{

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


    public void initialize (URL Location, ResourceBundle resources) {
        dashboard_welcome.setText("Welcome  "+AdminDAO.user_name_login);
        ID_vol.setCellValueFactory(new PropertyValueFactory<>("ID_vol"));
        depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        arrive.setCellValueFactory(new PropertyValueFactory<>("arrive"));
        // id_avion.setCellValueFactory(new PropertyValueFactory<>("ID_avion"));
        // id_pilote.setCellValueFactory(new PropertyValueFactory<>("ID_pilote"));
        jdep.setCellValueFactory(new PropertyValueFactory<>("jdep"));
        jarr.setCellValueFactory(new PropertyValueFactory<>("jarr"));
        try {
            dashboard_table.setItems( new VolDAO().getall());
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }



}
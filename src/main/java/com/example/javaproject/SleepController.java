package com.example.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SleepController implements Initializable {

    @FXML
    private PasswordField login_password;

    @FXML
    private Label name_admin;

    @FXML
    private Button signin_button;

    @FXML
    void signin_action(ActionEvent event) throws SQLException {
        AdminDAO dao=new AdminDAO();
        boolean check= dao.authentification(String.valueOf(AdminDAO.user_id),login_password.getText());
        if (check) {
            LoadScene.load(signin_button, "home_structure.fxml","Home",event);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_admin.setText(AdminDAO.user_name_login.toUpperCase());
    }
}

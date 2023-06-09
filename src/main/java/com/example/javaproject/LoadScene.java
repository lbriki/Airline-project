package com.example.javaproject;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;


public class LoadScene {


    public static void load(Node element,String filefxml,String title, Event event){
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.4));
        fadeOut.setNode(element.getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished((ActionEvent event1) -> {
            FXMLLoader loader = new FXMLLoader(LoadScene.class.getResource(filefxml));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root, 1150, 650);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
            currentStage.setTitle(title);
            currentStage.show();
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.4), root);
            fadeIn.setFromValue(0.3);
            fadeIn.setToValue(1);
            fadeIn.play();
        });
        fadeOut.play();
    }

    public static void load_pane(AnchorPane container,String file)  {
        FXMLLoader loader = new FXMLLoader(LoadScene.class.getResource(file));
        Parent root ;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FadeTransition fadeOut = new FadeTransition(Duration.millis(400), container);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(400), container);
        fadeIn.setFromValue(0.3);
        fadeIn.setToValue(1.0);
        Parent finalRoot = root;
        fadeOut.setOnFinished(event -> {
            container.getChildren().clear();
            container.getChildren().add(finalRoot);
            fadeIn.play();
        });

        fadeOut.play();
    }
}

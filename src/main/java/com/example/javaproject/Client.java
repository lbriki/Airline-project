package com.example.javaproject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.io.*;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Client extends Application {

    private TextArea textArea;
    private TextField inputTextField;
    private Button sendButton;
    private Socket socket;
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    public void start(Stage primaryStage) {
        // Set up the UI
        Label title = new Label("Guide Window ");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        title.setAlignment(Pos.CENTER);

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setPrefHeight(300);

        Label inputLabel = new Label("Enter your choice (1, 2,3,4 or 5):");
        inputTextField = new TextField();
        inputTextField.setPrefWidth(200);
        inputTextField.setOnAction(e -> sendChoice());

        sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendChoice());

        Button quitButton = new Button("Quitter");
        HBox inputBox = new HBox(10, inputLabel, inputTextField, sendButton, quitButton);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10));
        quitButton.setOnAction(e -> {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            stage.close();

            try {
                toServer.writeInt(Integer.parseInt("5"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        VBox root = new VBox(10, title, textArea, inputBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        // Set up the socket connection
        try {
            socket = new Socket("localhost", 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            updateTextArea("Error: " + ex.getMessage());
        }

        // Start listening for server messages
        new Thread(() -> {
            try {
                while (true) {
                    String message = fromServer.readUTF();
                    Platform.runLater(() -> updateTextArea(message));

                    if (message.equals("Exiting...")) {
                        break;
                    }
                }
            } catch (IOException ex) {
                Platform.runLater(() -> updateTextArea("Error: " + ex.getMessage()));
            }
        }).start();

        // Set up the stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Our Guide");
        primaryStage.show();
    }

    private void sendChoice() {
        // Send the input to the server
        try {
            int choice = Integer.parseInt(inputTextField.getText());

            toServer.writeInt(choice);
            toServer.flush();
        } catch (NumberFormatException ex) {
            updateTextArea("Error: Please enter a valid number.");
        } catch (IOException ex) {
            updateTextArea("Error: Unable to send choice to server.");
        }

        inputTextField.setText("");
    }

    private void updateTextArea(String message) {
        textArea.appendText(message + "\n");
    }

}







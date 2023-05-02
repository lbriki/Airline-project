module com.example.javaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires gson;
    requires java.json;
    requires json.simple;
    requires jdk.jsobject;


    opens com.example.javaproject to javafx.fxml;
    exports com.example.javaproject;
}
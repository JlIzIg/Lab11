module com.example.lab11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires fastjson;
    requires poi;


    opens com.example.lab11 to javafx.fxml;
    exports com.example.lab11;
}
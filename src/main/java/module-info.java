module com.example.hotelproject2 {
    requires  javafx.graphics;
    requires  javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;


    opens com.example.hotelproject2 to javafx.fxml;
    exports com.example.hotelproject2;
}
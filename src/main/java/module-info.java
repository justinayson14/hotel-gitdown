module com.example.hotelproject2 {
    requires transitive javafx.graphics;
    requires transitive javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires java.desktop;


    opens com.example.hotelproject2 to javafx.fxml;
    exports com.example.hotelproject2;
    exports com.example.hotelproject2.models;
    opens com.example.hotelproject2.models to javafx.fxml;
    exports com.example.hotelproject2.guest;
    opens com.example.hotelproject2.guest to javafx.fxml;
    exports com.example.hotelproject2.admin;
    opens com.example.hotelproject2.admin to javafx.fxml;
}
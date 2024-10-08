module com.example.hotelproject2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hotelproject2 to javafx.fxml;
    exports com.example.hotelproject2;
}
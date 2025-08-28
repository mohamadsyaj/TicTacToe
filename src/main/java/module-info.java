module com.example.proj4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proj4 to javafx.fxml;
    exports com.example.proj4;
}
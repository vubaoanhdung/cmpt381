module com.example.a3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.a3 to javafx.fxml;
    exports com.example.a3;
}
module com.example.asn3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.asn3 to javafx.fxml;
    exports com.example.asn3;
}
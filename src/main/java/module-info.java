module org.example.CulturaMYTrip {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.json;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires java.net.http;

    opens org.example.CulturaMYTrip to javafx.fxml;
    exports org.example.CulturaMYTrip;
    exports org.example.CulturaMYTrip.Controller;
    exports org.example.CulturaMYTrip.api to com.myculture.api;
    opens org.example.CulturaMYTrip.Controller to javafx.fxml;
}
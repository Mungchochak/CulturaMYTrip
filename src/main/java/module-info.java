module org.example.jobby {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.json;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires java.net.http;

    opens org.example.jobby to javafx.fxml;
    exports org.example.jobby;
    exports org.example.jobby.Controller;
    opens org.example.jobby.Controller to javafx.fxml;
}
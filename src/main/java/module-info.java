module marieteam.marieteam_v2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires okhttp3;
    requires json.simple;
    requires org.apache.pdfbox;
    requires java.desktop;

    opens marieteam.marieteam_v2 to javafx.fxml;
    exports marieteam.marieteam_v2;
}
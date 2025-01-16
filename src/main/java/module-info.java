module jumper.jumper.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    exports jumper.jumper.app;

    opens jumper.jumper.app to javafx.fxml;
}

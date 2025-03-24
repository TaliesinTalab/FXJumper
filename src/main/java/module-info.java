module jumper.jumper.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;

    exports jumper.jumper.app;

    opens jumper.jumper.app to javafx.fxml;
    exports jumper.jumper.handlers;
    opens jumper.jumper.handlers to javafx.fxml;
}

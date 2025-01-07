module jumper.jumper {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports jumper.jumper;

    opens jumper.jumper to javafx.fxml;
}

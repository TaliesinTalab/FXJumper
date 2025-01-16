package jumper.jumper.app;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private static ScreenHandler screenHandler;

    /**
     * called by launch, sets scene and sets scene
     * @modifiedBy Jonathan Percht
     * @author Talisien
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        screenHandler = new ScreenHandler(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    //getters
    public static ScreenHandler getScreenhandler() {
        return screenHandler;
    }
}

package jumper.jumper.app;

import javafx.application.Application;
import javafx.stage.Stage;
import jumper.jumper.handlers.ScreenHandler;

public class App extends Application {
    private static ScreenHandler screenHandler;

    /**
     * called by launch, sets scene and sets scene
     * @author Talisien
     * @modifiedBy Jonathan Percht
     */
    @Override
    public void start(Stage primaryStage) {
        System.out.println(
                System.lineSeparator() +
                "App started"
        );
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

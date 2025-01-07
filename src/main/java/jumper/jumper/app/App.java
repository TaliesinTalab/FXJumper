package jumper.jumper.app;

import javafx.application.Application;
import javafx.scene.Scene;
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
        Scene scene = new Scene(screenHandler.getRoot(), 768, 576);

        primaryStage.setTitle("Card Jumper");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //getters
    public static ScreenHandler getTitleScreen() {
        return screenHandler;
    }
}

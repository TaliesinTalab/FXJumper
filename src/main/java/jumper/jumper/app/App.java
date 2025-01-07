package jumper.jumper.app;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    private static TitleScreen titleScreen;

    /** called by launch, sets titleScreen and sets scene
     * @modifiedBy Jonathan Percht
     * @author Talisien
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        titleScreen = new TitleScreen(primaryStage);
        Scene scene = new Scene(titleScreen.getRoot(), 768, 576);

        primaryStage.setTitle("Card Jumper");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //getters
    public static TitleScreen getTitleScreen() {
        return titleScreen;
    }
}

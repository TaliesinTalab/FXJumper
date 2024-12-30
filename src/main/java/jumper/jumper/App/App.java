package jumper.jumper.App;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Card Jumper");
        GamePanel gamePanel = new GamePanel();
        System.out.println("Game panelled");
        gamePanel.setupGame();
        System.out.println("Game sat up");


        Pane root = new Pane();
        root.getChildren().add(gamePanel);

        Scene scene = new Scene(root, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
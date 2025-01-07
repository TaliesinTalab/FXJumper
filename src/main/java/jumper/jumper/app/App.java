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

    private static Stage stage;
    private static Scene scene;
    private static VBox root;
    private static HBox box;
    private static GamePanel gamePanel;
    private static MenuBar inventoryMenu;
    private static Sound sound;

    @Override
    public void start(Stage primaryStage) {
        root = new VBox();
        root.setAlignment(Pos.CENTER);

        box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefHeight(204);

        Label title = new Label("Card Jumper");
        title.setStyle("-fx-font-weight: BOLD;" +
                "-fx-text-fill: WHITE;" +
                "-fx-font-size: 84;" +
                "-fx-font-style: ITALIC;");
        title.setTranslateY(-60);

        Button startButton = new Button("Start");
        startButton.setStyle("-fx-font-weight: bold;");
        startButton.setPrefHeight(50);
        startButton.setPrefWidth(230);
        startButton.setTranslateX(-10);
        startButton.setTranslateY(60);
        startButton.setOnAction(event -> {startGame();});

        Button exitButton = new Button("Exit Game");
        exitButton.setStyle("-fx-font-weight: bold;");
        exitButton.setPrefHeight(50);
        exitButton.setPrefWidth(230);
        exitButton.setTranslateX(10);
        exitButton.setTranslateY(60);
        exitButton.setOnAction(event -> {System.exit(0);});

        box.getChildren().addAll(startButton);
        box.getChildren().addAll(exitButton);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png"))),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        box.setBackground(new Background(backgroundImage));

        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen1.png"))),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        root.getChildren().add(title);
        root.getChildren().add(box);

        startMenuLoop();

        scene = new Scene(root, 768, 576);
        stage = primaryStage;

        primaryStage.setTitle("Card Jumper");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void startMenuLoop() {
        sound  = new Sound();

        sound.setFile(0);
        sound.play();
        sound.loop();

        new AnimationTimer() {
            private long lastTime = 0;
            int index = 1;
            final double FRAME_TIME = 1e9 / 60;
            @Override
            public void handle(long now) {
                if (lastTime == 0) {lastTime = now; return;}
                long elapsedTime = now - lastTime;
                if (elapsedTime >= FRAME_TIME) {
                    update(index);
                    lastTime = now;
                    index++;
                    if(index > 7) {index = 0;}
                }
            }
        }.start();
    }

    public void update(int index) {
        BackgroundImage backgroundImage;

        switch (index) {
                    case 0:
                        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen1.png"))),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        root.setBackground(new Background(backgroundImage));
                        break;
                    case 1:
                        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen2.png"))),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        root.setBackground(new Background(backgroundImage));
                        break;
                    case 2:
                        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen3.png"))),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        root.setBackground(new Background(backgroundImage));
                        break;
                    case 3:
                        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen4.png"))),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        root.setBackground(new Background(backgroundImage));
                        break;
                    case 4:
                        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen5.png"))),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        root.setBackground(new Background(backgroundImage));
                        break;
                    case 5:
                        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen6.png"))),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        root.setBackground(new Background(backgroundImage));
                        break;
                    case 6:
                        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen7.png"))),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        root.setBackground(new Background(backgroundImage));
                        break;
                    case 7:
                        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen8.png"))),
                                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                BackgroundSize.DEFAULT);
                        root.setBackground(new Background(backgroundImage));
                        break;
                }
                if(index > 3) {
                    backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/screen_up_1.png"))),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT);
                    box.setBackground(new Background(backgroundImage));
                } else {
                    backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/screen_up_2.png"))),
                            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT);
                    box.setBackground(new Background(backgroundImage));
                }
            }

    public static void startGame() {
        sound.stop();

        gamePanel = new GamePanel();
        System.out.println("Game panelled");
        gamePanel.setupGame();
        System.out.println("Game sat up");

        inventoryMenu = new MenuBar();
        inventoryMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");

        Pane root = new Pane();

        root.getChildren().add(gamePanel);
        root.getChildren().add(inventoryMenu);

        scene = new Scene(root, 768, 576);

        stage.setScene(scene);
        stage.show();
    }

    //getters
    public static GamePanel getGamePanel() {
        return gamePanel;
    }
    public static MenuBar getInventoryMenu() {
        return inventoryMenu;
    }
    public static Scene getScene() {
        return scene;
    }
}

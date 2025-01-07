package jumper.jumper.app;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * titlescreen class responsible for generating the titleScreen
 * @author Jonathan Percht
 */
public class ScreenHandler {
    private Stage stage;
    private VBox root;
    private HBox box;
    private GamePanel gamePanel;
    private MenuBar inventoryMenu;
    private Sound sound;

    public ScreenHandler(Stage stage) {
        this.stage = stage;
        this.root = new VBox();
        this.box = new HBox();
        this.gamePanel = new GamePanel();
        this.inventoryMenu = new MenuBar();
        this.sound = new Sound();

        root.setAlignment(Pos.CENTER);

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

        BackgroundImage backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/screen_up_1.png"))),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        box.setBackground(new Background(backgroundImage));

        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen1.png"))),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        root.getChildren().add(title);
        root.getChildren().add(box);

        startTitleScreenLoop();
    }

    /**
     * starts the titleScreen animation and soundLoop
     * @author Jonathan Percht
     */
    public void startTitleScreenLoop() {
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
                    updateTitleScreen(index);
                    lastTime = now;
                    index++;
                    if(index > 7) {index = 0;}
                }
            }
        }.start();
    }

    /**
     * is called by startMenuLoop().handle() to change the background of root and box
     * @author Jonathan Percht
     * @param index
     */
    public void updateTitleScreen(int index) {
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

    /**
     * triggered by startButton, loads gamePanel, inventory and sets scene for game
     * @author Jonathan Percht
     */
    public void startGame() {
        sound.stop();

        System.out.println("Game panelled");
        gamePanel.setupGame();
        System.out.println("Game sat up");

        inventoryMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");

        Pane root = new Pane();

        root.getChildren().add(gamePanel);
        root.getChildren().add(inventoryMenu);

        Scene scene = new Scene(root, 768, 576);

        stage.setScene(scene);
        stage.show();
    }

    //getters
    public VBox getRoot() {
        return root;
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public MenuBar getInventoryMenu() {
        return inventoryMenu;
    }
}

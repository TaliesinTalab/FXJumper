package jumper.jumper.app;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * titlescreen class responsible for generating the titleScreen
 * @author Jonathan Percht
 */
public class ScreenHandler {
    private Scene scene;
    private Stage stage;
    private VBox root;
    private HBox box;
    private HBox endBox;
    private GamePanel gamePanel;
    private MenuBar inventoryMenu;
    private Button closeButton;
    private Button exitButton;
    private Button startButton;
    private Label title;
    private Label end;
    private Label score;
    private int closeTimer = 1; //sets time to close after exitButton press
    private boolean isClosed = false; //was exitButton pressed?
    private int screen;
    private double timer = 0;

    public ScreenHandler(Stage stage) {
        this.stage = stage;
        this.root = new VBox();
        this.box = new HBox();
        this.gamePanel = new GamePanel();
        this.inventoryMenu = new MenuBar();
        this.closeButton = new Button("Menu");
        this.exitButton = new Button("Exit Game");
        this.startButton = new Button("Start Game");
        this.end = new Label("Game Over");
        this.title = new Label("Card Jumper");
        this.scene = new Scene(root, 768, 606);

        System.out.println("Game panelled");

        root.setAlignment(Pos.CENTER);

        box.setAlignment(Pos.CENTER);
        box.setPrefHeight(204);

        end.setStyle("-fx-font-weight: BOLD;" +
                "-fx-text-fill: WHITE;" +
                "-fx-font-size: 84;" +
                "-fx-font-style: ITALIC;");
        end.setTranslateY(-130);
        end.setTextAlignment(TextAlignment.CENTER);

        title.setStyle("-fx-font-weight: BOLD;" +
                "-fx-text-fill: WHITE;" +
                "-fx-font-size: 84;" +
                "-fx-font-style: ITALIC;");
        title.setTranslateY(-60);

        closeButton.setPrefHeight(22);
        closeButton.setPrefWidth(50);
        closeButton.setStyle("-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 10;" +
                "-fx-background-color: rgb(0, 0, 0)");
        closeButton.setOnAction(event -> {
            gamePanel.playSoundEffect(1);
            startTitleScreenLoop(); //comment out when testing endGame()
            //endGame(); //test endGame()
        });
        closeButton.setOnMouseEntered(event -> {
            closeButton.setStyle("-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 10;" +
                    "-fx-background-color: rgb(225, 55, 0)");
        });
        closeButton.setOnMouseExited(event -> {
            closeButton.setStyle("-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 10;" +
                    "-fx-background-color: rgb(0, 0, 0)");
        });

        startButton.setStyle("-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12;" +
                "-fx-background-color: rgb(55, 125, 15)");
        startButton.setPrefHeight(40);
        startButton.setPrefWidth(230);
        startButton.setTranslateX(-10);
        startButton.setTranslateY(20);
        startButton.setOnAction(event -> {
            gamePanel.playSoundEffect(1);
            startGame();
        });
        startButton.setOnMouseEntered(event -> {
            startButton.setStyle("-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 13;" +
                    "-fx-background-color: rgb(75, 175, 25)");
        });
        startButton.setOnMouseExited(event -> {
            startButton.setStyle("-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 12;" +
                    "-fx-background-color: rgb(55, 125, 15)");
        });

        exitButton.setStyle("-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12;" +
                "-fx-background-color: rgb(125, 55, 15)");
        exitButton.setPrefHeight(40);
        exitButton.setPrefWidth(230);
        exitButton.setTranslateX(10);
        exitButton.setTranslateY(20);
        exitButton.setOnAction(event -> {
            gamePanel.playSoundEffect(1);
            isClosed = true;
        });
        exitButton.setOnMouseEntered(event -> {
            exitButton.setStyle("-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 13;" +
                    "-fx-background-color: rgb(175, 75, 25);");
        });
        exitButton.setOnMouseExited(event -> {
            exitButton.setStyle("-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 12;" +
                    "-fx-background-color: rgb(125, 55, 15)");
        });

        box.getChildren().addAll(startButton);
        box.getChildren().addAll(exitButton);
        box.setPrefHeight(303);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/screen_up_1.png"))),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        box.setBackground(new Background(backgroundImage));

        backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen1.png"))),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        inventoryMenu.setStyle("-fx-background-color: white;");
        inventoryMenu.getMenus().add(new Menu("", closeButton));

        gamePanel.setupGame();
        System.out.println("Game sat up");

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        new AnimationTimer() {
            private long lastTime = 0;
            int index = 1;
            final double FRAME_TIME = 1e9 / 60;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {lastTime = now; return;}
                long elapsedTime = now - lastTime;
                if (elapsedTime >= FRAME_TIME) {
                    switch (screen) {
                        case 0:
                            updateTitleScreen(index);
                            break;
                        case 2:
                            updateEndScreen(index);
                            break;
                    }
                    lastTime = now;
                    index++;
                    if (isClosed) {
                        if (closeTimer <= 0) {
                            System.exit(0);
                        }
                        closeTimer--;
                    }
                    if(index > 7) {index = 0;}
                }
            }
        }.start();

        startTitleScreenLoop();
    }

    /**
     * starts the titleScreen animation and soundLoop
     * @author Jonathan Percht
     */
    public void startTitleScreenLoop() {
        screen = 0;

        root.getChildren().clear();

        inventoryMenu.getMenus().clear();

        exitButton.setTranslateX(10);

        root.getChildren().add(title);
        root.getChildren().add(box);
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(inventoryMenu);

        stage.setTitle("Card Jumper - Menu");
    }

    /**
     * is called by startMenuLoop().handle() to change the background of root and box
     * @author Jonathan Percht
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
     * is called by startMenuLoop().handle() to change the background of root and box
     * @author Jonathan Percht
     */
    public void updateEndScreen(int index) {
        BackgroundImage backgroundImage;

        switch (index) {
            case 0:
                backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen1.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                root.setBackground(new Background(backgroundImage));
                break;
            case 1:
                backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen2.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                root.setBackground(new Background(backgroundImage));
                break;
            case 2:
                backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen3.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                root.setBackground(new Background(backgroundImage));
                break;
            case 3:
                backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen4.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                root.setBackground(new Background(backgroundImage));
                break;
            case 4:
                backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen5.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                root.setBackground(new Background(backgroundImage));
                break;
            case 5:
                backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen6.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                root.setBackground(new Background(backgroundImage));
                break;
            case 6:
                backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen7.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                root.setBackground(new Background(backgroundImage));
                break;
            case 7:
                backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen8.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                root.setBackground(new Background(backgroundImage));
                break;
        }
        if(index > 3) {
            backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/screen_down_1.png"))),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);
            endBox.setBackground(new Background(backgroundImage));
        } else {
            backgroundImage = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/screen_down_2.png"))),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);
            endBox.setBackground(new Background(backgroundImage));
        }
    }

    /**
     * triggered by startButton, loads gamePanel, inventory and sets scene for game
     * @author Jonathan Percht
     */
    public void startGame() {
        screen = 1;

        root.getChildren().clear();

        gamePanel.getPlayer().renderInventory();

        startButton.setText("Resume Game");

        root.getChildren().add(gamePanel);
        root.getChildren().add(inventoryMenu);

        stage.setTitle("Card Jumper - Game");
    }

    /**
     * triggers endcreen and score
     * @author Jonathan Percht
     */
    public void endGame() {
        screen = 2;

        root.getChildren().clear();

        inventoryMenu.getMenus().clear();

        score = new Label("Score" + System.lineSeparator() + calculateScore());
        score.setStyle("-fx-text-fill: WHITE;" +
                "-fx-font-size: 28;");
        score.setTranslateY(-120);
        score.setTextAlignment(TextAlignment.CENTER);

        endBox = new HBox();
        endBox.setAlignment(Pos.CENTER);
        endBox.getChildren().add(exitButton);
        endBox.setTranslateY(-140);
        endBox.setPrefHeight(200);
        endBox.setAlignment(Pos.BOTTOM_CENTER);

        exitButton.setTranslateX(0);

        root.getChildren().add(end);
        root.getChildren().add(score);
        root.getChildren().add(endBox);
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(inventoryMenu);

        stage.setTitle("Card Jumper - Game Over");
    }

    public int calculateScore() {
        return 1000 - (int) timer;
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
    public Button getCloseButton() {
        return closeButton;
    }
    public void incrementTimer() {
        timer += 0.03;
    }
}

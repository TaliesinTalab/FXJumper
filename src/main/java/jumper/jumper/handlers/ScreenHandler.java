package jumper.jumper.handlers;

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
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * ScreenHandler class responsible for generating and loading screens
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
    private Button restartButton;
    private Button exitButton;
    private Button startButton;
    private Label title;
    private Label end;
    private Label score;
    private int points = 0;
    private int closeTimer = 1; //sets time to close after exitButton press
    private boolean isClosed = false; //was exitButton pressed?
    private int screen;
    private double timer = 0;
    private final BackgroundImage[] backgrounds;

    public ScreenHandler(Stage stage) {
        System.out.println(
                System.lineSeparator() +
                "--------------" +
                System.lineSeparator() +
                System.lineSeparator() +
                "ScreenHandler:" +
                System.lineSeparator()
        );
        this.stage = stage;
        this.root = new VBox();
        this.box = new HBox();
        this.endBox = new HBox();
        this.gamePanel = new GamePanel(this);
        System.out.println(
                "GamePanel set: " +
                this.gamePanel +
                ", " +
                this.gamePanel.getWidth() +
                ", " +
                this.gamePanel.getHeight()
        );
        this.inventoryMenu = new MenuBar();
        this.closeButton = new Button("Menu");
        this.exitButton = new Button("Exit Game");
        this.startButton = new Button("Start Game");
        this.restartButton = new Button("Restart Game");
        this.end = new Label("Game Over");
        this.title = new Label("Card Jumper");
        this.score = new Label(
                "Score" +
                System.lineSeparator() +
                calculateScore(0)
        );
        this.scene = new Scene(root, 768, 606);
        System.out.println(
                "Scene set: " +
                this.root +
                ", " +
                this.scene.getWidth() +
                ", " +
                this.scene.getHeight()
        );
        this.backgrounds = new BackgroundImage[] {
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen1.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen2.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen3.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen4.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen5.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen6.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen7.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/titleScreen8.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen1.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen2.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen3.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen4.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen5.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen6.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen7.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/endScreen8.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/screen_up_1.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/screen_up_2.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/screen_down_1.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT),
                new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/screens/screen_down_2.png"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT),
        };

        root.setAlignment(Pos.CENTER);

        box.setPrefHeight(204);
        box.setTranslateY(-40);
        box.setAlignment(Pos.CENTER);

        endBox.setTranslateY(-120);
        endBox.setPrefHeight(200);
        endBox.setAlignment(Pos.BOTTOM_CENTER);

        end.setStyle(
                "-fx-font-weight: BOLD;" +
                "-fx-text-fill: WHITE;" +
                "-fx-font-size: 84;" +
                "-fx-font-style: ITALIC;"
        );
        end.setTranslateY(-100);
        end.setTextAlignment(TextAlignment.CENTER);

        score.setStyle(
                "-fx-text-fill: WHITE;" +
                "-fx-font-size: 28;"
        );
        score.setTranslateY(-98);
        score.setTextAlignment(TextAlignment.CENTER);

        title.setStyle(
                "-fx-font-weight: BOLD;" +
                "-fx-text-fill: WHITE;" +
                "-fx-font-size: 84;" +
                "-fx-font-style: ITALIC;"
        );
        title.setTranslateY(-100);

        restartButton.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 12;" +
                        "-fx-background-color: rgb(155, 125, 15)"
        );
        restartButton.setPrefHeight(40);
        restartButton.setPrefWidth(230);
        restartButton.setOnAction(event -> {
            gamePanel.playSoundEffect(1);
            gamePanel = new GamePanel(this);
            startGame();
        });
        restartButton.setOnMouseEntered(event ->
                restartButton.setStyle(
                        "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-font-size: 13;" +
                                "-fx-background-color: rgb(195, 175, 25);"
                ));
        restartButton.setOnMouseExited(event ->
                restartButton.setStyle(
                        "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-font-size: 12;" +
                                "-fx-background-color: rgb(155, 125, 15)"
                ));

        closeButton.setPrefHeight(22);
        closeButton.setPrefWidth(50);
        closeButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 10;" +
                "-fx-background-color: rgb(0, 0, 0)"
        );
        closeButton.setOnAction(event -> {
            gamePanel.playSoundEffect(1);
            startMenu(); //comment out when testing endGame()
            //endGame(); //uncomment when testing endGame()
        });
        closeButton.setOnMouseEntered(event ->
                closeButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 10;" +
                "-fx-background-color: rgb(225, 55, 0)"
        ));
        closeButton.setOnMouseExited(event ->
                closeButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 10;" +
                "-fx-background-color: rgb(0, 0, 0)"
        ));

        startButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12;" +
                "-fx-background-color: rgb(55, 125, 15)"
        );
        startButton.setPrefHeight(40);
        startButton.setPrefWidth(230);
        startButton.setTranslateX(-10);
        startButton.setTranslateY(20);
        startButton.setOnAction(event -> {
            gamePanel.playSoundEffect(1);
            startGame();
        });
        startButton.setOnMouseEntered(event ->
                startButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 13;" +
                "-fx-background-color: rgb(75, 175, 25)"
        ));
        startButton.setOnMouseExited(event ->
                startButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12;" +
                "-fx-background-color: rgb(55, 125, 15)"
        ));

        exitButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12;" +
                "-fx-background-color: rgb(125, 55, 15)"
        );
        exitButton.setPrefHeight(40);
        exitButton.setPrefWidth(230);
        exitButton.setOnAction(event -> {
            gamePanel.playSoundEffect(1);
            isClosed = true;
        });
        exitButton.setOnMouseEntered(event ->
                exitButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 13;" +
                "-fx-background-color: rgb(175, 75, 25);"
        ));
        exitButton.setOnMouseExited(event ->
                exitButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12;" +
                "-fx-background-color: rgb(125, 55, 15)"
        ));

        box.getChildren().addAll(startButton);
        box.getChildren().addAll(exitButton);
        box.setPrefHeight(303);

        inventoryMenu.setStyle("-fx-background-color: white;");
        inventoryMenu.getMenus().add(new Menu("", closeButton));

        gamePanel.setupGame();
        System.out.println(
                System.lineSeparator() +
                "Game sat up" +
                System.lineSeparator() +
                System.lineSeparator() +
                "--------------" +
                System.lineSeparator()
        );

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
                            updateScreen(index);
                            break;
                        case 2:
                            updateScreen(index + 8);
                            break;
                    }
                    lastTime = now;
                    index++;
                    if (isClosed) {
                        if (closeTimer <= 0) {
                            System.out.println(
                                    "App closed" +
                                            System.lineSeparator()
                            );
                            System.exit(0);
                        }
                        closeTimer--;
                    }
                    if(index > 7) {index = 0;}
                }
            }
        }.start();

        startMenu();
    }

    /**
     * loads the titleScreen
     * @author Jonathan Percht
     */
    public void startMenu() {
        screen = 0;

        root.getChildren().clear();

        inventoryMenu.getMenus().clear();

        exitButton.setTranslateY(20);
        exitButton.setTranslateX(10);

        root.getChildren().add(title);
        root.getChildren().add(box);
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(inventoryMenu);

        stage.setTitle("Card Jumper - Menu");
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
     * loads the endScreen and calls calculateScore()
     * @author Jonathan Percht
     */
    public void endGame(int cuteness) {
        final int finalScore = calculateScore(cuteness);

        final int requiredScore = 100;

        screen = 2;

        root.getChildren().clear();
        inventoryMenu.getMenus().clear();

        if(finalScore >= requiredScore) {
            end.setText("Victory");
        }

        score.setText(
                "Score" +
                System.lineSeparator() +
                finalScore
        );

        exitButton.setTranslateY(-130);
        exitButton.setTranslateX(0);

        root.getChildren().add(end);
        root.getChildren().add(score);
        root.getChildren().add(endBox);
        root.getChildren().add(restartButton);
        root.getChildren().add(exitButton);
        root.getChildren().add(inventoryMenu);
        root.setAlignment(Pos.BOTTOM_CENTER);


        stage.setTitle("Card Jumper - End");
    }

    /**
     * is called by AnimationTimer to change the background of root, box and endBox
     * @author Jonathan Percht
     */
    public void updateScreen(int index) {
        root.setBackground(new Background(backgrounds[index]));
        if (index < 8) {
            if (index > 3) {
                box.setBackground(new Background(backgrounds[16]));
            } else {
                box.setBackground(new Background(backgrounds[17]));
            }
        } else {
            if (index > 11) {
                endBox.setBackground(new Background(backgrounds[18]));
            } else {
                endBox.setBackground(new Background(backgrounds[19]));
            }
        }
    }

    /**
     * returns score value
     * @author Jonathan Percht
     */
    public int calculateScore(int cuteness) {
        if (cuteness == -9999) return cuteness;
        return 200 - (int) timer + points + cuteness; //"100 - (int) timer" = time added if time less than n
    }

    /**
     * called by gamePanel.update(), only if game is not paused
     * @author Jonathan Percht
     */
    public void incrementTimer() {
        timer += 0.03;
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
    public void addPoints(int change) {
        points += change;
    }
}

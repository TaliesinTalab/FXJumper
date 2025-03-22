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
    private GamePanel gamePanel;
    private MenuBar inventoryMenu;
    private Button menuButton;
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
    private SoundHandler soundHandler;
    private boolean pausePressed = false;

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
        this.menuButton = new Button("Menu");
        this.exitButton = new Button("Exit Game");
        this.startButton = new Button("Start Game");
        this.restartButton = new Button("Restart Game");
        this.end = new Label("Game Over");
        this.title = new Label("Card Jumper");
        this.score = new Label("Score");
        this.scene = new Scene(root, 768, 606);
        System.out.println(
                "Scene set: " +
                this.root +
                ", " +
                this.scene.getWidth() +
                ", " +
                this.scene.getHeight() +
                System.lineSeparator()
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
        this.soundHandler = new SoundHandler();

        root.setAlignment(Pos.BOTTOM_CENTER);

        box.setPrefHeight(204);
        box.setTranslateY(-40);
        box.setAlignment(Pos.CENTER);

        end.setStyle(
                "-fx-font-weight: BOLD;" +
                "-fx-text-fill: WHITE;" +
                "-fx-font-size: 84;" +
                "-fx-font-style: ITALIC;"
        );
        end.setTranslateY(-100);

        score.setStyle(
                "-fx-font-weight: BOLD;" +
                "-fx-text-fill: WHITE;" +
                "-fx-font-size: 28;"
        );
        score.setTranslateY(-100);

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
        restartButton.setTranslateY(20);
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

        menuButton.setPrefHeight(22);
        menuButton.setPrefWidth(50);
        menuButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 10;" +
                "-fx-background-color: rgb(0, 0, 0)"
        );
        menuButton.setOnAction(event -> {
            gamePanel.playSoundEffect(1);
            startMenu(); //comment out when testing endGame()
            //endGame(); //uncomment when testing endGame()
        });
        menuButton.setOnMouseEntered(event ->
                menuButton.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 10;" +
                "-fx-background-color: rgb(225, 55, 0)"
        ));
        menuButton.setOnMouseExited(event ->
                menuButton.setStyle(
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
        startButton.setTranslateY(20);
        startButton.setTranslateX(-10);
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
        exitButton.setTranslateY(20);
        exitButton.setTranslateX(10);
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

        box.getChildren().add(startButton);
        box.getChildren().add(exitButton);
        box.setPrefHeight(303);

        inventoryMenu.setStyle("-fx-background-color: white;");
        inventoryMenu.getMenus().add(new Menu("", menuButton));

        gamePanel.setupGame();
        System.out.println(
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
                    pausePressed = gamePanel.getKeyHandler().getPausePressed();
                    switch (screen) {
                        case 0:
                            updateScreen(index);
                            break;
                        case 1:
                            if (pausePressed) {
                                gamePanel.playSoundEffect(1);
                                startMenu();
                                gamePanel.getKeyHandler().setPausePressed(false);
                            }
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

        playMusic(0);
        startMenu();
    }

    public void setDefaultValues() {
        timer = 0;
        points = 0;
    }

    /**
     * loads the titleScreen
     * @author Jonathan Percht
     */
    public void startMenu() {
        screen = 0;
        gamePanel.setGameState(2);
        System.out.println(
                "Game paused" +
                System.lineSeparator()
        );

        root.getChildren().clear();
        inventoryMenu.getMenus().clear();

        if (screen == 0) {
            if (!box.getChildren().contains(startButton)) {
                box.getChildren().add(startButton);
                restartButton.setTranslateX(0);
            }
        }

        root.getChildren().add(title);
        root.getChildren().add(box);
        root.getChildren().add(inventoryMenu);

        stage.setTitle("Card Jumper - Menu");
    }

    /**
     * triggered by startButton, loads gamePanel, inventory and sets scene for game
     * @author Jonathan Percht
     */
    public void startGame() {
        if (screen != 0 || !soundHandler.isRunning()) {
            playMusic(0);
        }

        screen = 1;
        gamePanel.setGameState(1);

        System.out.println("Game resumed" + System.lineSeparator());

        root.getChildren().clear();
        box.getChildren().clear();

        gamePanel.getPlayer().renderInventory();

        startButton.setText("Resume Game");
        box.getChildren().add(startButton);
        box.getChildren().add(restartButton);
        box.getChildren().add(exitButton);

        restartButton.setTranslateX(0);

        root.getChildren().add(gamePanel);
        root.getChildren().add(inventoryMenu);

        stage.setTitle("Card Jumper - Game");
    }

    /**
     * loads the endScreen and calls calculateScore()
     * @author Jonathan Percht
     */
    public void endGame() {
        final int finalScore = calculateScore();
        final int requiredScore = 1;

        screen = 2;
        gamePanel.setGameState(2);
        System.out.println("Game paused" + System.lineSeparator());

        playMusic(4);

        root.getChildren().clear();
        inventoryMenu.getMenus().clear();

        if (box.getChildren().contains(startButton)) {
            box.getChildren().remove(startButton);
            restartButton.setTranslateX(-10);
        }

        if(finalScore >= requiredScore) {
            end.setText("Victory");
            stage.setTitle("Card Jumper - Victory");
            score.setStyle(
                    "-fx-font-weight: BOLD;" +
                    "-fx-text-fill: rgb(155, 125, 15);" +
                    "-fx-font-size: 28;"
            );
        } else {
            end.setText("Game Over");
            stage.setTitle("Card Jumper - Game Over");
            score.setStyle(
                    "-fx-font-weight: BOLD;" +
                    "-fx-text-fill: rgb(125, 55, 15);" +
                    "-fx-font-size: 28;"
            );
        }

        if (finalScore == -9999) {
            score.setText("You were hate crimed :(");
            System.out.println("Hate Crime Ending" + System.lineSeparator());
        } else if (finalScore <= 0) {
            score.setText("You died from old age :(");
            System.out.println("Old Age Ending" + System.lineSeparator());
        } else {
            score.setText("- " + finalScore + " -");
            System.out.println("Regular Ending: " + finalScore + System.lineSeparator());
        }

        root.getChildren().add(score);
        root.getChildren().add(end);
        root.getChildren().add(box);
        root.getChildren().add(inventoryMenu);
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
                box.setBackground(new Background(backgrounds[18]));
            } else {
                box.setBackground(new Background(backgrounds[19]));
            }
        }
    }

    /**
     * returns score value
     * @author Jonathan Percht
     */
    public int calculateScore() {
        final int cuteness = gamePanel.getPlayer().getCuteness();
        if (cuteness == -9999) {
            return cuteness;
        }
        return 95 - (int) timer + points + cuteness; //"100 - (int) timer" = time added if time less than n
    }

    /**
     * called by gamePanel.update(), only if game is not paused
     * @author Jonathan Percht
     */
    public void incrementTimer() {
        timer += 0.03;
    }

    private void playMusic(int i) {
        soundHandler.setFile(i);
        soundHandler.loop();
    }

    private void stopMusic() {
        soundHandler.stop();
    }

    //getters
    public VBox getRoot() {
        return root;
    }
    public int getScreen() {
        return screen;
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public MenuBar getInventoryMenu() {
        return inventoryMenu;
    }
    public Button getMenuButton() {
        return menuButton;
    }

    //setter
    public void addPoints(int change) {
        points += change;
    }
}

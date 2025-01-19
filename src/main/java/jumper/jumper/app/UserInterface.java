package jumper.jumper.app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import jumper.jumper.object.ObjectHeart;
import jumper.jumper.object.SuperObject;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Displays the number of keys the player has collected
 * Tracks and shows the elapsed playtime
 * Displays custom messages on the screen temporarily
 * Shows a congratulatory endgame screen when the player finishes the game
 */
public class UserInterface {

    private GamePanel gamePanel;
    private final DecimalFormat dFormat = new DecimalFormat("#0.00");
    private Font arial_40 = Font.font("Arial", 40);
    private Font arial_80B = Font.font("Arial", 80);
    private Font arial_12 = Font.font("Arial",12);
    private String message = "";
    private Image heart_full, heart_half, heart_empty;
    private boolean messageOn = false;
    private boolean gameFinished = false;
    private int messageCounter = 0;
    private double playtime;
    private String currentDialogue = "";

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        //
        SuperObject heart = new ObjectHeart(gamePanel);
        heart_full = heart.getImage();
        heart_half = heart.getImage2();
        heart_empty = heart.getImage3();
    }

    // Getter
    public String getCurrentDialogue() {
        return currentDialogue;
    }

    // Setter
    public void setCurrentDialogue(String currectDialogue) {
        this.currentDialogue = currectDialogue;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void showMessage(String text) {
        this.message = text;
        this.messageOn = true;
    }

    public void draw(GraphicsContext gc) {
        //set default font

        gc.setFont(arial_40);
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        //check the current game state
        //Play state
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            drawPlayerLife(gc);
        }
        //Pause state
        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            drawPlayerLife(gc);
            drawPauseScreen(gc);
        }
        //Dialogue state
        if (gamePanel.getGameState() == gamePanel.getDialogueState()) {
            drawDialogueScreen();
        }
        if (messageOn) { //Handle Temporary Messages
            gc.setFont(arial_40);
            gc.fillText(message, gamePanel.getScreenWidth() / 2.0, gamePanel.getScreenHeight() - 50);
            messageCounter++;
            if (messageCounter > 120) {
                messageOn = false;
                messageCounter = 0;
            }
        }
    }

    /**
     * Method for drawing the current health of the player.
     * Note: each full heart equals 2 HP.
     *
     * @author Taliesin Talab
     */
    public void drawPlayerLife(GraphicsContext gc) {
        gamePanel.getPlayer().setHealth(5);
        // draws empty hearts
        int x = gamePanel.getTileSize() / 2;
        int y = gamePanel.getTileSize() / 2;
        int i = 0;
        while (i < gamePanel.getPlayer().getFullHealth() / 2) {
            gc.drawImage(heart_empty, x, y);
            i++;
            x += gamePanel.getTileSize() / 2;
        }
        //draw current health
        x = gamePanel.getTileSize() / 2;
        y = gamePanel.getTileSize() / 2;
        i = 0;
        while (i < gamePanel.getPlayer().getHealth()) {
            gc.drawImage(heart_half, x, y);
            i++;
            if (i < gamePanel.getPlayer().getHealth()) {
                gc.drawImage(heart_full, x, y);
            }
            i++;
            x += gamePanel.getTileSize() / 2;
        }
    }

    //this method set everything for the "pause" state of the game
    public void drawPauseScreen(GraphicsContext gc) {
        gc.setFont(Font.font("Arial", 8F));
        gc.setFill(Color.WHITE);
        String text = "PAUSED";
        gc.fillText(text, gamePanel.getScreenWidth() / 2.0, gamePanel.getScreenHeight() - 40);
    }

    /**
     * method for create the dialogue window
     * first is where to display the text(it should be inside the subWindow)
     *
     * @author Lu Wang
     */
    public void drawDialogueScreen() {
        //window
        GraphicsContext gc = gamePanel.getGraphicsContext2D();
        int x = gamePanel.getTileSize() * 2;
        int y = gamePanel.getTileSize() / 2;
        int width = gamePanel.getScreenWidth() - (gamePanel.getTileSize() * 4);
        int height = gamePanel.getTileSize() * 4;
        drawSubWindow(x, y, width, height);

        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();

        gc.strokeText(currentDialogue, x + 100, y + 20);

        gc.setFont(Font.font("Arial",12));



    }

    /**
     * Method drawSubWindow: since we will likely create this kind of window a lot in the future
     *
     * @author Lu Wang
     */
    public void drawSubWindow(int x, int y, int width, int height) {
        GraphicsContext gc = gamePanel.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRoundRect(x, y, width, height, 35, 35);
        gc.setGlobalAlpha(0.9); // Set opacity to 90%
        gc.setLineWidth(5);
        //to draw the frame
        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);


    }
}


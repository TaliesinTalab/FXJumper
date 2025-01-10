package jumper.jumper.app;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import jumper.jumper.object.ObjectHeart;
import jumper.jumper.object.SuperObject;

import java.text.DecimalFormat;

/**
 * Displays the number of keys the player has collected
 * Tracks and shows the elapsed playtime
 * Displays custom messages on the screen temporarily
 * Shows a congratulatory endgame screen when the player finishes the game
 */
public class UserInterface {

    private final GamePanel gamePanel;
    private final DecimalFormat dFormat = new DecimalFormat("#0.00");
    private Font arial_40 = Font.font("Arial", 40);
    private Font arial_80B = Font.font("Arial", 80);
    private String message = "";
    private Image heart_full, heart_half, heart_empty;
    private boolean messageOn = false;
    private boolean gameFinished = false;
    private int messageCounter = 0;
    private double playTime;

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        //
        SuperObject heart = new ObjectHeart(gamePanel);
        heart_full = heart.getImage();
        heart_half = heart.getImage2();
        heart_empty = heart.getImage3();
    }

    // Setter
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
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            drawPlayerLife(gc);
        }
        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            drawPlayerLife(gc);
            drawPauseScreen(gc);
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
     * @author Taliesin Talab
     */
    public void drawPlayerLife(GraphicsContext gc) {
        gamePanel.getPlayer().setHealth(5);
        // draws empty hearts
        int x = gamePanel.getTileSize() / 2;
        int y = gamePanel.getTileSize() / 2;
        int i = 0;
        while (i < gamePanel.getPlayer().getFullHealth()/2) {
            gc.drawImage(heart_empty, x, y);
            i++;
            x += gamePanel.getTileSize()/2;
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
            x += gamePanel.getTileSize()/2;
        }
    }

    //this method set everything for the "pause" state of the game
    public void drawPauseScreen(GraphicsContext gc) {
        gc.setFont(Font.font("Arial", 8F));
        gc.setFill(Color.WHITE);
        String text = "PAUSED";
        gc.fillText(text, gamePanel.getScreenWidth() / 2.0, gamePanel.getScreenHeight() - 40);
    }
}


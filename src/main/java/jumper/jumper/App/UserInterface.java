package jumper.jumper.App;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.text.DecimalFormat;

/**
 * Displays the number of keys the player has collected
 * Tracks and shows the elapsed playtime
 * Displays custom messages on the screen temporarily
 * Shows a congratulatory endgame screen when the player finishes the game
 */
public class UserInterface {

    private final GamePanel gamePanel;
    private Font arial_40 = Font.font("Arial", 40);
    private Font arial_80B = Font.font("Arial", 80);
    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;
    private boolean gameFinished = false;
    private double playTime;
    private final DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // Setter
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void showMassage(String text) {
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
            //Do playState stuff later
        }
        if (gamePanel.getGameState() == gamePanel.getPauseState()) {
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


    //this method set everything for the "pause" state of the game
    public void drawPauseScreen(GraphicsContext gc) {
        gc.setFont(Font.font("Arial", 8F));
        gc.setFill(Color.WHITE);
        String text = "PAUSED";
        gc.fillText(text, gamePanel.getScreenWidth() / 2.0, gamePanel.getScreenHeight() - 40);
    }
}


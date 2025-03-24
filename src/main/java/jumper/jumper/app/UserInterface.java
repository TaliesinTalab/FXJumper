package jumper.jumper.app;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import jumper.jumper.handlers.ScreenHandler;
import jumper.jumper.object.ObjectHeart;
import jumper.jumper.object.Object;

import java.text.DecimalFormat;

/**
 * Displays the number of keys the player has collected
 * Tracks and shows the elapsed playtime
 * Displays custom messages on the screen temporarily
 * Shows a congratulatory endgame screen when the player finishes the game
 * @author Abdullah Nazari
 * @modifiedBy: Taliesin Talab, Lu Wang, Jonathan Percht
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
    private boolean toggleCoordinateVisuals;
    private int messageCounter = 0;
    private double playtime;
    private String currentDialogue = "";

    public UserInterface(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        //
        Object heart = new ObjectHeart(gamePanel);
        heart_full = heart.getImage();
        heart_half = heart.getImage2();
        heart_empty = heart.getImage3();
    }

    // Getter
    public String getCurrentDialogue() {
        return currentDialogue;
    }
    public boolean getToggleCoordinateVisuals() {return toggleCoordinateVisuals;}

    // Setter
    public void setCurrentDialogue(String currectDialogue) {
        this.currentDialogue = currectDialogue;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void setToggleCoordinateVisuals(boolean toggleCoordinateVisuals) {this.toggleCoordinateVisuals = toggleCoordinateVisuals;}

    public void showMessage(String text) {
        this.message = text;
        this.messageOn = true;
    }

    public void toggleCoordinates(GraphicsContext gc, int x, int y) {
        x = x/48;
        y = y/48;
        int width_increment = 40;
        int centre_increment = 20;
        if (x >= 100 || y >= 100) {
            width_increment += 30;
            centre_increment += 15;
        }
        drawSubWindow(gamePanel.getScreenWidth() - 60 - 95 - centre_increment, 120, 120 + width_increment, 80);
        gc.setFont(arial_40);
        gc.setFill(Color.YELLOWGREEN);
        gc.fillText(x + " | " + y,gamePanel.getScreenWidth() - 95, 175);
    }

    /**
     *...
     * @author Abdullah Nazari
     * @modifiedBy Jonathan Percht
     */
    public void draw(GraphicsContext gc) {
        //set default font
        gc.setGlobalAlpha(1); // Set opacity to 100%

        gc.setFont(arial_40);
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.CENTER);
        //check the current game state
        //Play state
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            int score = gamePanel.getScreenHandler().calculateScore();
            int playerX = gamePanel.getPlayer().getWorldX();
            int playerY = gamePanel.getPlayer().getWorldY();
            if (score <= 0) {
                gamePanel.getScreenHandler().endGame();
            }
            drawPlayerLife(gc);
            drawSubWindow(gamePanel.getScreenWidth() - 60 - 95, 30, 120, 80);
            gc.setFont(arial_40);
            if (score > 0) {
                gc.setFill(Color.WHITE);
            } else {
                gc.setFill(Color.RED);
            }
            gc.fillText("" + score, gamePanel.getScreenWidth() - 95, 85);
            if (toggleCoordinateVisuals) toggleCoordinates(gc, playerX, playerY);
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
            drawSubWindow(gamePanel.getScreenWidth() / 2 - 300, gamePanel.getScreenHeight() - 125, 600, 100);
            gc.setFont(arial_40);
            gc.setFill(Color.WHITE);
            gc.fillText(message, gamePanel.getScreenWidth() / 2.0, gamePanel.getScreenHeight() - 60);
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

    /**
     * this method set everything for the "pause" state of the game
     * @author Lu Wang
     */
    public void drawPauseScreen(GraphicsContext gc) {
        drawSubWindow(gamePanel.getScreenWidth() / 2 - 300, gamePanel.getScreenHeight() - 125, 600, 100);
        gc.setFont(Font.font("Arial", 40F));
        gc.setFill(Color.WHITE);
        String text = "PAUSED";
        gc.setGlobalAlpha(0.75); // Set opacity to 90%
        gc.fillText(text, gamePanel.getScreenWidth() / 2.0, gamePanel.getScreenHeight() - 60);
    }

    /**
     * method for create the dialogue window
     * first is where to display the text(it should be inside the subWindow)
     * @author Lu Wang
     * @modifiedBy Jonathan Percht
     */
    public void drawDialogueScreen() {
        //window
        GraphicsContext gc = gamePanel.getGraphicsContext2D();
        int x = gamePanel.getTileSize() * 2;
        int y = gamePanel.getTileSize() / 2;
        int width = gamePanel.getScreenWidth() - (gamePanel.getTileSize() * 4);
        int height = gamePanel.getTileSize() * 4;
        drawSubWindow(x, y, width, height);

        gc.setFont(Font.font("Arial", 30)); // Adjust font size as needed
        gc.setFill(Color.WHITE);

        // Text wrapping variables
        int textPadding = gamePanel.getTileSize() / 2; // Padding inside the dialogue box
        double maxTextWidth = width - textPadding * 2; // Available width for text
        double lineHeight = 30; // Adjust line height based on font size

        // Wrap the text manually
        String[] words = currentDialogue.split(" ");
        StringBuilder wrappedText = new StringBuilder();
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            Text tempText = new Text(line + word + " ");
            tempText.setFont(gc.getFont());
            double textWidth = tempText.getLayoutBounds().getWidth();

            if (textWidth > maxTextWidth) {
                wrappedText.append(line).append("\n");
                line = new StringBuilder(word).append(" ");
            } else {
                line.append(word).append(" ");
            }
        }
        wrappedText.append(line); // Add the last line

        gc.setGlobalAlpha(0.75); // Set opacity to 90%

        // Draw wrapped text line by line
        String[] lines = wrappedText.toString().split("\n");
        double textX = x + textPadding + 72;
        double textY = y + textPadding + lineHeight; // Start slightly below the top of the box

        for (String textLine : lines) {
            gc.fillText(textLine, 2 * textX, textY);
            textY += lineHeight; // Move down for the next line
        }
        textX = x + textPadding + 200;
        textY = lineHeight*6.5;
        gc.setFill(Color.RED);
        gc.fillText("[E]", 2*textX, textY); // Show an [E] at the edge of the dialogue box to indicate exit button
    }

    /**
     * Method drawSubWindow: since we will likely create this kind of window a lot in the future
     * @author Lu Wang
     */
    public void drawSubWindow(int x, int y, int width, int height) {
        GraphicsContext gc = gamePanel.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRoundRect(x, y, width, height, 35, 35);
        gc.setLineWidth(5);
        //to draw the frame
        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}


package jumper.jumper.handlers;

import javafx.scene.input.KeyEvent;
import jumper.jumper.app.GamePanel;


/**
 * Handling multiple states in KeyHandler
 * @author Taliesin Talab
 * @modifiedBy Lu Wang, Jonathan Percht
 */
public class KeyHandler {
    GamePanel gamePanel;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean ePressed;
    private boolean checkDrawTime;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // Getter
    public boolean getUpPressed() {
        return upPressed;
    }

    public boolean getDownPressed() {
        return downPressed;
    }

    public boolean getLeftPressed() {
        return leftPressed;
    }

    public boolean getRightPressed() {
        return rightPressed;
    }

    public boolean getEPressed() {
        return ePressed;
    }


    // Other Methods

    /**
     * When a key is pressed (that has a function of course), this function is used to actually tell the game what was pressed.
     * @author Taliesin Talab
     */
    public void handleKeyPress(KeyEvent event) {
        String press = event.getCode().toString();
        switch (press) {
            case "W" -> upPressed = true;
            case "S" -> downPressed = true;
            case "A" -> leftPressed = true;
            case "D" -> rightPressed = true;
            case "P" -> {
                if (gamePanel.getGameState() == gamePanel.getPlayState()) {
                    gamePanel.setGameState(gamePanel.getPauseState());
                } else if (gamePanel.getGameState() == gamePanel.getPauseState()) {
                    gamePanel.setGameState(gamePanel.getPlayState());
                }
            }
            case "E" -> ePressed = true;
        }
    }

    /**
     * Basically the polar opposite of handleKeyPress(). If a key is released, it tells the game by modifying the according boolean.
     * @author Taliesin Talab
     */
    public void handleKeyRelease(KeyEvent event) {
        String release = event.getCode().toString();
        switch (release) {
            case "W" -> upPressed = false;
            case "S" -> downPressed = false;
            case "A" -> leftPressed = false;
            case "D" -> rightPressed = false;
            case "E" -> ePressed = false;
        }
    }
}








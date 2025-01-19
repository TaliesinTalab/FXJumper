package jumper.jumper.app;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.security.Key;


/**
 * Handling multiple states in KeyHandler
 *
 * @modifiedBy Lu Wang
 */
public class KeyHandler {
    GamePanel gamePanel;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean enterPressed;

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }

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

    public boolean isEnterPressed() {
        return enterPressed;
    }


    // Other Methods
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
                }
                else if (gamePanel.getGameState() == gamePanel.getPauseState()) {
                    gamePanel.setGameState(gamePanel.getPlayState());
                }
            case "Enter" -> enterPressed = false;
            }}

    public void handleKeyRelease(KeyEvent event) {
        String release = event.getCode().toString();
        switch (release) {
            case "W" -> upPressed = false;
            case "S" -> downPressed = false;
            case "A" -> leftPressed = false;
            case "D" -> rightPressed = false;
            case "Enter" -> enterPressed = false;
        }
    }
}








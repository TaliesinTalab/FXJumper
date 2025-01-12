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

    /**
     * @modifiedBy Lu Wang
     */
    public void keyPressed(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        //Play state
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {

            if (keyCode == KeyCode.W) {
                upPressed = true;

            }
            if (keyCode == KeyCode.S) {
                downPressed = true;
            }
            if (keyCode == KeyCode.A) {
                leftPressed = true;
            }
            if (keyCode == KeyCode.D) {
                rightPressed = true;

            }
            if(keyCode == KeyCode.ENTER){
                enterPressed=true;
            }
            if (keyCode == KeyCode.P) {
                gamePanel.setGameState(gamePanel.getPauseState());

            }
        } else if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            if (keyCode == KeyCode.P) {
                gamePanel.setGameState(gamePanel.getPlayState());
            }

        }else if(gamePanel.getGameState()==gamePanel.getDialogueState()){
            if (keyCode == KeyCode.ENTER){
                gamePanel.setGameState(gamePanel.getPlayState());
            }
        }


    }

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








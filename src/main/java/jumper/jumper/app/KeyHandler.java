package jumper.jumper.app;

import javafx.scene.input.KeyEvent;

public class KeyHandler {
    GamePanel gamePanel;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private boolean checkDrawTime;

    public KeyHandler(GamePanel gamePanel){
        this.gamePanel=gamePanel;
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
    public boolean isCheckDrawTime() {return checkDrawTime;}



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
            }
            case "T" -> checkDrawTime = !checkDrawTime; // Toggle debug tool
        }
    }

    public void handleKeyRelease(KeyEvent event) {
        String release = event.getCode().toString();
        switch (release) {
            case "W" -> upPressed = false;
            case "S" -> downPressed = false;
            case "A" -> leftPressed = false;
            case "D" -> rightPressed = false;
        }
    }

}

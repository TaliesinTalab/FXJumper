package jumper.jumper.App;

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
//
//    @Override
//    public void keyTyped(KeyEvent e) {} //we don't need this
//
//    /**
//     * If one of the following keys is pressed, it updates the corresponding variable
//     * @param e the event to be processed
//     */
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int code = e.getKeyCode(); //each key on a keyboard has an associated keycode (e.g. A = 65)
//        if (code == KeyEvent.VK_W) {upPressed = true;}
//        if (code == KeyEvent.VK_S) {downPressed = true;}
//        if (code == KeyEvent.VK_A) {leftPressed = true;}
//        if (code == KeyEvent.VK_D) {rightPressed = true;}
//        if (code == KeyEvent.VK_P) {
//            if(gamePanel.getGameState()==gamePanel.getPlayState()){
//                gamePanel.setGameState(gamePanel.getPauseState());
//            }else if(gamePanel.getGameState()==gamePanel.getPauseState()){
//                gamePanel.setGameState(gamePanel.getPlayState());
//            }
//        }
//
//
//        //DEBUG (since it`s a debug tool, it`s better to switch it on and off
//        if (code == KeyEvent.VK_T) {
//            checkDrawTime = !checkDrawTime;
//        }
//    }
//
//    /**
//     * If one of the following keys is pressed, it updates the corresponding variable
//     * @param e the event to be processed
//     */
//    @Override
//    public void keyReleased(KeyEvent e) {
//        int code = e.getKeyCode();
//        if (code == KeyEvent.VK_W) {upPressed = false;}
//        if (code == KeyEvent.VK_S) {downPressed = false;}
//        if (code == KeyEvent.VK_A) {leftPressed = false;}
//        if (code == KeyEvent.VK_D) {rightPressed = false;}
//    }
}

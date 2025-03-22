package jumper.jumper.entity.NPCs;

import jumper.jumper.app.GamePanel;
import jumper.jumper.entity.NPC;

public class NPCStickdude extends NPC {

    public NPCStickdude(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
    }

    public void getImage() {
        up1 = loadImage("/Stickdude/up_1");
        up2 = loadImage("/Stickdude/up_2");
        down1 = loadImage("/Stickdude/down_1");
        down2 = loadImage("/Stickdude/down_2");
        left1 = loadImage("/Stickdude/left_1");
        left2 = loadImage("/Stickdude/left_2");
        right1 = loadImage("/Stickdude/right_1");
        right2 = loadImage("/Stickdude/right_2");
        up1 = loadImage("/Stickdude/up_1");
    }


    @Override
    public void speak() {
        if (gamePanel.getPlayer().getCuteness() >= 60) {
            gamePanel.getScreenHandler().endGame();
            return;
        }
        gamePanel.getUserInterface().setCurrentDialogue("\"Bouncer\"\n  Sorry! Can't let ugly people in.\nGo dress up or something.");
        gamePanel.getPlayer().damage(1);
    }
}

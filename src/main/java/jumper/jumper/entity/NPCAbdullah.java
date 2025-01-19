package jumper.jumper.entity;

import jumper.jumper.app.GamePanel;
import jumper.jumper.object.ObjectMakeUp;
import jumper.jumper.object.ObjectMirror;
import jumper.jumper.object.SuperObject;

public class NPCAbdullah extends NPC {
    public NPCAbdullah(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
    }

    public void getImage() {
        up1 = loadImage("/Teamsprites/A_down_1");
        up2 = loadImage("/Teamsprites/A_down_1");
        down1 = loadImage("/Teamsprites/A_down_1");
        down2 = loadImage("/Teamsprites/A_down_1");
        left1 = loadImage("/Teamsprites/A_down_1");
        left2 = loadImage("/Teamsprites/A_down_1");
        right1 = loadImage("/Teamsprites/A_down_1");
        right2 = loadImage("/Teamsprites/A_down_1");
    }

    @Override
    public void speak() {
        SuperObject[] inventory = gamePanel.getPlayer().getInventory();
        for (SuperObject superObject : inventory) {
            if (superObject != null) {
                if (superObject.getClass().equals(ObjectMakeUp.class)) {
                    gamePanel.getUserInterface().setCurrentDialogue("\"Abdullah\"\n   Looks like the makeup helped!");
                    return;
                }
            }
        }
        gamePanel.getUserInterface().setCurrentDialogue("\"Abdullah\"\nTake this makeup I found!");
    }
}

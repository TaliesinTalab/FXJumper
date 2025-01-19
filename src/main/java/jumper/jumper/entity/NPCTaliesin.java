package jumper.jumper.entity;

import jumper.jumper.app.GamePanel;
import jumper.jumper.object.ObjectBribe;
import jumper.jumper.object.ObjectMirror;
import jumper.jumper.object.SuperObject;

public class NPCTaliesin extends NPC {
    public NPCTaliesin(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
    }

    public void getImage() {
        up1 = loadImage("/Teamsprites/T_down_1");
        up2 = loadImage("/Teamsprites/T_down_1");
        down1 = loadImage("/Teamsprites/T_down_1");
        down2 = loadImage("/Teamsprites/T_down_1");
        left1 = loadImage("/Teamsprites/T_down_1");
        left2 = loadImage("/Teamsprites/T_down_1");
        right1 = loadImage("/Teamsprites/T_down_1");
        right2 = loadImage("/Teamsprites/T_down_1");
    }


    @Override
    public void speak() {
        SuperObject[] inventory = gamePanel.getPlayer().getInventory();
        for (SuperObject superObject : inventory) {
            if (superObject != null) {
                if (superObject.getClass().equals(ObjectBribe.class)) {
                    gamePanel.getUserInterface().setCurrentDialogue("\"Taliesin\"\n              I'll take the money if you don't want it.");
                    return;
                }
            }
        }
        gamePanel.getUserInterface().setCurrentDialogue("\"Taliesin\"\nUse this to bribe the guard!");
    }
}

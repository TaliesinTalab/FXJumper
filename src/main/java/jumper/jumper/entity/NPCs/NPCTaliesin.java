package jumper.jumper.entity.NPCs;

import jumper.jumper.app.GamePanel;
import jumper.jumper.entity.NPC;
import jumper.jumper.object.ObjectBribe;
import jumper.jumper.object.Object;

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
        Object[] inventory = gamePanel.getPlayer().getInventory();
        for (Object object : inventory) {
            if (object != null) {
                if (object.getClass().equals(ObjectBribe.class)) {
                    gamePanel.getUserInterface().setCurrentDialogue("\"Taliesin\"\n              I'll take the money if you don't want it.");
                    return;
                }
            }
        }
        gamePanel.getUserInterface().setCurrentDialogue("\"Taliesin\"\nUse this to bribe the guard!");
    }
}

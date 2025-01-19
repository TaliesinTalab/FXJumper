package jumper.jumper.entity;

import jumper.jumper.app.GamePanel;
import jumper.jumper.object.ObjectMirror;
import jumper.jumper.object.ObjectPearl;
import jumper.jumper.object.SuperObject;

public class NPCLu extends NPC {
    public NPCLu(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
    }

    public void getImage() {
        up1 = loadImage("/Teamsprites/L_down_1");
        up2 = loadImage("/Teamsprites/L_down_1");
        down1 = loadImage("/Teamsprites/L_down_1");
        down2 = loadImage("/Teamsprites/L_down_1");
        left1 = loadImage("/Teamsprites/L_down_1");
        left2 = loadImage("/Teamsprites/L_down_1");
        right1 = loadImage("/Teamsprites/L_down_1");
        right2 = loadImage("/Teamsprites/L_down_1");
    }

    @Override
    public void speak() {
        SuperObject[] inventory = gamePanel.getPlayer().getInventory();
        for (SuperObject superObject : inventory) {
            if (superObject != null) {
                if (superObject.getClass().equals(ObjectPearl.class)) {
                    gamePanel.getUserInterface().setCurrentDialogue("\"Lu\"\nTime is ticking!");
                    return;
                }
            }
        }
        gamePanel.getUserInterface().setCurrentDialogue("\"Lu\"\nI found this pretty pearl.\nDo you want it?\nIt will make you cuter.");
    }
}

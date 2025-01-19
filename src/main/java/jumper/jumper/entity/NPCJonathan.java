package jumper.jumper.entity;

import jumper.jumper.app.GamePanel;
import jumper.jumper.object.ObjectKey;
import jumper.jumper.object.ObjectMirror;
import jumper.jumper.object.SuperObject;

public class NPCJonathan extends NPC {
    public NPCJonathan(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
    }

    public void getImage() {
        up1 = loadImage("/Teamsprites/J_down_1");
        up2 = loadImage("/Teamsprites/J_down_1");
        down1 = loadImage("/Teamsprites/J_down_1");
        down2 = loadImage("/Teamsprites/J_down_1");
        left1 = loadImage("/Teamsprites/J_down_1");
        left2 = loadImage("/Teamsprites/J_down_1");
        right1 = loadImage("/Teamsprites/J_down_1");
        right2 = loadImage("/Teamsprites/J_down_1");
    }

    @Override
    public void speak() {
        SuperObject[] inventory = gamePanel.getPlayer().getInventory();
        for (SuperObject superObject : inventory) {
            if (superObject != null) {
                if (superObject.getClass().equals(ObjectMirror.class)) {
                    gamePanel.getUserInterface().setCurrentDialogue("\"Jonathan\"\nGo find the rest!");
                    return;
                }
            }
        }
        gamePanel.getUserInterface().setCurrentDialogue("\"Jonathan\"\n      Sure is weird to find a mirror here.\nYou can have it, if you want.");
    }
}
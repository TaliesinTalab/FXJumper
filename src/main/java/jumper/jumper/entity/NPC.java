package jumper.jumper.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

//Author: Lu Wang

public class NPC extends Entity{

    public NPC(GamePanel gamePanel){
        super(gamePanel);

        setDirection("down");
        speed = 1;
        getImage();
    }

    public void getImage() {
        up1 = loadImage("/npc/up_1");
        up2 = loadImage("/npc/up_2");
        down1 = loadImage("/npc/down_1");
        down2 = loadImage("/npc/down_2");
        left1 = loadImage("/npc/left_1");
        left2 = loadImage("/npc/left_2");
        right1 = loadImage("/npc/right_1");
        right2 = loadImage("/npc/right_2");
        up1 = loadImage("/npc/up_1");
    }

}

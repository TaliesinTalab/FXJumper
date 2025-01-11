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
    public Image getUp1() {
        return up1;
    }

    public Image getUp2() {
        return up2;
    }

    public Image getDown1() {
        return down1;
    }

    public Image getDown2() {
        return down2;
    }

    public Image getLeft1() {
        return left1;
    }

    public Image getLeft2() {
        return left2;
    }

    public Image getRight1() {
        return right1;
    }

    public Image getRight2() {
        return right2;
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

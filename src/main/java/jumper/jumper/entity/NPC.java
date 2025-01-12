package jumper.jumper.entity;

import javafx.geometry.Rectangle2D;
import jumper.jumper.app.GamePanel;

import java.util.Random;

/**
 * Class for NPC, use for create NPC, upload png source for NPC and methods
 * @author Lu Wang
 */

public class NPC extends Entity {
    public NPC(GamePanel gamePanel) {
        super(gamePanel);
        setDirection("down");
        speed = 1;
        getImage();
        setDialogue();
    }

//loadImage is a method in its superclass(Entity)
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

    //here use for inputting character dialogues
    public void setDialogue(){

        getDialogues()[0]="Hello, Jumper! ";
        getDialogues()[1]="How are you?";
        getDialogues()[2]="Haha";
        getDialogues()[3]="Well... ";
    }

    //here the setting of this npc actions
    public void setAction() {
        setActionLockCounter(getActionLockCounter() + 1);
        //once it choose its direction, it won't change for 1.5 seconds( 90 frames)
        if (getActionLockCounter() == 90) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;  //+1. pick up a number from 1 to 100
            //here it is like a simple AI, 25% of the time it goes to each direction.
            if (i <= 25) {
                setDirection("up");
            }
            if (i > 25 && i <=50) {
                setDirection("down");
            }
            if (i > 50 && i <= 75) {
                setDirection("left");
            }
            if (i > 75 && i<= 100) {
                setDirection("right");
            }
            setActionLockCounter(0);
        }
    }

    //method speak will be both in Entity and here
    public void speak(){
        super.speak();
    }

}

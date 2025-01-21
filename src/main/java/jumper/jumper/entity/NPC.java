package jumper.jumper.entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;
import jumper.jumper.app.UtilityTool;

import java.util.Objects;
import java.util.Random;

/**
 * Class for NPC, use for create NPC, upload png source for NPC and methods
 * @author Lu Wang
 * @modifiedBy: Taliesin Talab
 */

public class NPC extends Entity {

    public NPC(GamePanel gamePanel) {
        super(gamePanel);
        setDirection("down");
        speed = 0;
        setSolidArea(new Rectangle2D(0, 0, gamePanel.getTileSize(), gamePanel.getTileSize()));
    }

    @Override
    public Image loadImage(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        Image image = null;
        try {
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/npcs" + imagePath + ".png")));
            image = uTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * here the setting of this npc actions
     * @author Lu Wang
     */
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
            if (i > 25 && i <= 50) {
                setDirection("down");
            }
            if (i > 50 && i <= 75) {
                setDirection("left");
            }
            if (i > 75 && i <= 100) {
                setDirection("right");
            }
            setActionLockCounter(0);
        }
    }

    /**
     * "super-function" so that NPCs can all use the same method to start a dialogue
     * @author Taliesin Talab
     */
    public void speak() {}
}

package jumper.jumper.entity;

import jumper.jumper.app.GamePanel;

public class NPC extends Entity{
    public NPC(GamePanel gamePanel){
        super(gamePanel);
        setDirection("down");
        speed = 0;
    }
}

package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * Pearl Object
 * @author Jonathan Percht (actual author)
 * @author Taliesin Talab (just copied existing code)
 */
public class ObjectPearl extends SuperObject{

    public ObjectPearl(GamePanel gamePanel){
        super(gamePanel);
        soundEffect = 2;

        setName("Pearl");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/pearl.png"))), 1);
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }

    public ObjectPearl(ObjectKey key){
        super(key.gamePanel);
        soundEffect = 2;

        setName("Pearl");

        setWorldX(key.getWorldX());
        setWorldY(key.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/pearl.png"))), 1);
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }
}

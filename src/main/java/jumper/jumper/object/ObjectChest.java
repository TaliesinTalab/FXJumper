package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * Chest Object
 * @author Jonathan Percht
 */
public class ObjectChest extends SuperObject {

    public ObjectChest(GamePanel gamePanel){
        super(gamePanel);
        soundEffect = 3;

        setName("Chest");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png"))), 1);
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }

    public ObjectChest(ObjectChest chest){
        super(chest.gamePanel);
        soundEffect = 3;

        setName("Chest");

        setWorldX(chest.getWorldX());
        setWorldY(chest.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png"))), 1);
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }
}

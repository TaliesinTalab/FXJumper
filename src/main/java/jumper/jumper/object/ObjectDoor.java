package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * Door Object
 * @author Jonathan Percht
 */
public class ObjectDoor extends SuperObject {
    private GamePanel gamePanel;

    public ObjectDoor(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setName("Door");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());

        setCollision(true);
    }

    public ObjectDoor(ObjectDoor door){
        gamePanel = door.gamePanel;
        setName("Door");

        setWorldX(door.getWorldX());
        setWorldY(door.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());

        setCollision(true);
    }
}

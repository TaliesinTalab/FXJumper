package jumper.jumper.Object;

import javafx.scene.image.Image;
import jumper.jumper.App.GamePanel;

import java.util.Objects;

public class ObjectDoor extends SuperObject {
    GamePanel gamePanel;

    public ObjectDoor(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setName("Door");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
        uTool.scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());

        setCollision(true);
    }

    public ObjectDoor(ObjectDoor door){
        setName("Door");

        setWorldX(door.getWorldX());
        setWorldY(door.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))));
        uTool.scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());

        setCollision(true);
    }
}

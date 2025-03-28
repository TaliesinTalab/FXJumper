package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * Door Object
 * @author Jonathan Percht
 */
public class ObjectDoor extends Object {

    public ObjectDoor(GamePanel gamePanel){
        super(gamePanel);
        soundEffect = 1;

        setName("Door");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))), 1);

        setCollision(true);
    }

    public ObjectDoor(ObjectDoor door){
        super(door.gamePanel);
        soundEffect = 1;

        setName("Door");

        setWorldX(door.getWorldX());
        setWorldY(door.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png"))), 1);

        setCollision(true);
    }
}

package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * Boots Object
 * @author Jonathan Percht
 */
public class ObjectBoots extends SuperObject {

    public ObjectBoots(GamePanel gamePanel){
        super(gamePanel);
        soundEffect = 3;

        setName("Boots");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))), 1);
    }

    public ObjectBoots(ObjectBoots boots){
        super(boots.gamePanel);
        soundEffect = 3;

        setName("Boots");

        setWorldX(boots.getWorldX());
        setWorldY(boots.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))), 1);
    }
}

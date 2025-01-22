package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * Makeup Object
 * @author Jonathan Percht (actual author)
 * @author Taliesin Talab (just copied existing code)
 */
public class ObjectMakeUp extends SuperObject{

    public ObjectMakeUp(GamePanel gamePanel){
        super(gamePanel);
        soundEffect = 2;

        setName("Makeup");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/makeup.png"))), 1);
    }

    public ObjectMakeUp(ObjectKey key){
        super(key.gamePanel);
        soundEffect = 2;

        setName("Makeup");

        setWorldX(key.getWorldX());
        setWorldY(key.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/makeup.png"))), 1);
    }
}

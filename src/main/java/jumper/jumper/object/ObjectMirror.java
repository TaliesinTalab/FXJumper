package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * Mirror Object
 * @author Jonathan Percht (actual author)
 * @author Taliesin Talab (just copied existing code)
 */
public class ObjectMirror extends Object {

    public ObjectMirror(GamePanel gamePanel){
        super(gamePanel);
        soundEffect = 2;

        setName("Mirror");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/mirror.png"))), 1);
    }

    public ObjectMirror(ObjectKey key){
        super(key.gamePanel);
        soundEffect = 2;

        setName("Mirror");

        setWorldX(key.getWorldX());
        setWorldY(key.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/mirror.png"))), 1);
    }
}

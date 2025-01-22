package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * Bribe Object
 * @author Jonathan Percht (actual author)
 * @author Taliesin Talab (just copied existing code)
 */
public class ObjectBribe extends SuperObject{

    public ObjectBribe(GamePanel gamePanel){
        super(gamePanel);
        soundEffect = 2;

        setName("Bribe");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bribe.png"))), 1);
    }

    public ObjectBribe(ObjectKey key){
        super(key.gamePanel);
        soundEffect = 2;

        setName("Bribe");

        setWorldX(key.getWorldX());
        setWorldY(key.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bribe.png"))), 1);
    }
}

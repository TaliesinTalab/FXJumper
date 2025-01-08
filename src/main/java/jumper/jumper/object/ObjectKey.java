package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * Key Object
 * @author Jonathan Percht
 */
public class ObjectKey extends SuperObject{

    public ObjectKey(GamePanel gamePanel){
        super(gamePanel);
        soundEffect = 2;

        setName("Key");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png"))));
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }

    public ObjectKey(ObjectKey key){
        super(key.gamePanel);
        soundEffect = 2;

        setName("Key");

        setWorldX(key.getWorldX());
        setWorldY(key.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png"))));
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }
}

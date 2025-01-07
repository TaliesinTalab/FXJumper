package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

/**
 * Boots Object
 * @author Jonathan Percht
 */
public class ObjectBoots extends SuperObject {
    private GamePanel gamePanel;

    public ObjectBoots(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setName("Boots");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))));
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }

    public ObjectBoots(ObjectBoots boots){
        gamePanel = boots.gamePanel;
        setName("Boots");

        setWorldX(boots.getWorldX());
        setWorldY(boots.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))));
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }
}

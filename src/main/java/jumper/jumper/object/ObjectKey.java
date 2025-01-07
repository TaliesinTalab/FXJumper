package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

public class ObjectKey extends SuperObject{
    private GamePanel gamePanel;

    public ObjectKey(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setName("Key");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png"))));
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }

    public ObjectKey(ObjectKey key){
        gamePanel = key.gamePanel;
        setName("Key");

        setWorldX(key.getWorldX());
        setWorldY(key.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png"))));
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }
}

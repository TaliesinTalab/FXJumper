package jumper.jumper.Object;

import javafx.scene.image.Image;
import jumper.jumper.App.GamePanel;

import java.util.Objects;

public class ObjectKey extends SuperObject{

    GamePanel gamePanel;

    public ObjectKey(GamePanel gamePanel){
        setName("Key");
        this.gamePanel = gamePanel;

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png"))));
        uTool.scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }

    public ObjectKey(ObjectKey key){
        setName("Key");

        setWorldX(key.getWorldX());
        setWorldY(key.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png"))));
    }
}

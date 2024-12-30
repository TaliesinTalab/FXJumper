package jumper.jumper.Object;

import javafx.scene.image.Image;
import jumper.jumper.App.GamePanel;

import java.util.Objects;

public class ObjectBoots extends SuperObject {
    GamePanel gamePanel;
    public ObjectBoots(GamePanel gamePanel){
        setName("Boots");
        this.gamePanel = gamePanel;
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))));
        uTool.scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }

    public ObjectBoots(ObjectBoots boots){
        setName("Boots");

        setWorldX(boots.getWorldX());
        setWorldY(boots.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png"))));
        uTool.scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }
}

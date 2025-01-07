package jumper.jumper.Object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

public class ObjectChest extends SuperObject {
    private GamePanel gamePanel;

    public ObjectChest(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setName("Chest");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png"))));
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }

    public ObjectChest(ObjectChest chest){
        gamePanel = chest.gamePanel;
        setName("Chest");

        setWorldX(chest.getWorldX());
        setWorldY(chest.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png"))));
        getUTool().scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());
    }
}

package jumper.jumper.Object;

import javafx.scene.image.Image;
import jumper.jumper.App.GamePanel;

import java.util.Objects;

public class ObjectAdventurersBackpack extends SuperObject {
    GamePanel gamePanel;
    public ObjectAdventurersBackpack(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setName("Adventurer's Backpack");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/adventurers_backpack.png"))));
        uTool.scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());

    }

    public ObjectAdventurersBackpack(ObjectAdventurersBackpack adventurersBackpack){
        setName("Adventurer's Backpack");

        setWorldX(adventurersBackpack.getWorldX());
        setWorldY(adventurersBackpack.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/adventurers_backpack.png"))));
        uTool.scaleImage(getImage(),gamePanel.getTileSize(),gamePanel.getTileSize());

    }
}

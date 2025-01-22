package jumper.jumper.object;

import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;

import java.util.Objects;

public class ObjectHeart extends SuperObject{


    public ObjectHeart(GamePanel gamePanel){
        super(gamePanel);
        soundEffect = 3;

        setName("Heart");

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart.png"))), 1);
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_half.png"))), 2);
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_empty.png"))), 3);
        setImage(getUTool().scaleImage(getImage(),gamePanel.getTileSize()/2,gamePanel.getTileSize()/2), 1);
        setImage(getUTool().scaleImage(getImage2(),gamePanel.getTileSize()/2,gamePanel.getTileSize()/2), 2);
        setImage(getUTool().scaleImage(getImage3(),gamePanel.getTileSize()/2,gamePanel.getTileSize()/2), 3);
    }

    public ObjectHeart(ObjectHeart heart){
        super(heart.gamePanel);
        soundEffect = 3;

        setName("Heart");

        setWorldX(heart.getWorldX());
        setWorldY(heart.getWorldY());

        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart.png"))), 1);
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_half.png"))), 2);
        setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_empty.png"))), 3);
        setImage(getUTool().scaleImage(getImage(),gamePanel.getTileSize()/2,gamePanel.getTileSize()/2), 1);
        setImage(getUTool().scaleImage(getImage2(),gamePanel.getTileSize()/2,gamePanel.getTileSize()/2), 2);
        setImage(getUTool().scaleImage(getImage3(),gamePanel.getTileSize()/2,gamePanel.getTileSize()/2), 3);
    }

    @Override
    public void setImage(Image image, int i) {
        if (i == 1)this.image = image;
        if (i == 2)this.image2 = image;
        if (i == 3)this.image3 = image;
    }
}

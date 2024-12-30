package jumper.jumper.Tiles;


import javafx.scene.image.Image;

public class Tile {
    private Image image;
    private boolean collision = false;

    // Setter & Getter

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public boolean getCollision() {
        return collision;
    }
    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}

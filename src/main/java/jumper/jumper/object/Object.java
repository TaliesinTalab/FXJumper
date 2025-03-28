package jumper.jumper.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;
import jumper.jumper.app.UtilityTool;

/**
 * SuperClass which is never instantiated (could be made abstract)
 * used to generate Objects
 * @author Jonathan Percht
 */
public abstract class Object {
    protected Image image, image2, image3, unscaledImage;
    private String name;
    private boolean collision = false;
    private int worldX, worldY;
    private Rectangle2D solidArea = new Rectangle2D(0, 0, 48, 48);
    private double solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    private UtilityTool uTool= new UtilityTool();
    protected GamePanel gamePanel;
    protected int soundEffect;
    protected int objectScale;

    public Object(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.objectScale = gamePanel.getTileSize();
    }

    /**
     * Draws objects on screen
     * @author Jonathan Percht
     */
    public void draw(GraphicsContext gc, GamePanel gamePanel) {
        double screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        double screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {

            gc.drawImage(image, screenX, screenY);
        }
    }

    //Getter
    public Image getImage() {
        return image;
    }
    public Image getImage2() {return image2;}
    public Image getImage3() {return image3;}
    public Image getUnscaledImage() {return unscaledImage;}
    public String getName() {
        return name;
    }
    public boolean getCollision() {
        return collision;
    }
    public int getWorldX() {
        return worldX;
    }
    public int getWorldY() {
        return worldY;
    }
    public Rectangle2D getSolidArea() {
        return solidArea;
    }
    public double getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public double getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public double getSolidAreaX() {return solidArea.getMinX(); }
    public double getSolidAreaY() {return solidArea.getMinY(); }
    public int getSoundEffect() {return soundEffect;}
    public GamePanel getGamePanel() {return gamePanel;}

    public UtilityTool getUTool() {
        return uTool;
    }

    //Setter

    /**
     * @modifiedBy Taliesin Talab
     * @param i was added to be able to choose, which image is being set
     */
    public void setImage(Image image, int i) {
        this.unscaledImage = image;
        image = uTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        if (i == 1)this.image = image;
        if (i == 2)this.image2 = image;
        if (i == 3)this.image3 = image;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    public void setSolidArea(double x, double y, double width, double height) {
        solidArea = new Rectangle2D(x, y, width, height);
    }
    public void setSolidArea(Rectangle2D rect) {
        this.solidArea = rect;
    }
    public void setSolidAreaX(double x) {
        this.solidArea = new Rectangle2D(x, solidArea.getMinY(), solidArea.getWidth(), solidArea.getHeight());
    }
    public void setSolidAreaY(double y) {
        this.solidArea = new Rectangle2D(solidArea.getMinX(), y, solidArea.getWidth(), solidArea.getHeight());
    }
}

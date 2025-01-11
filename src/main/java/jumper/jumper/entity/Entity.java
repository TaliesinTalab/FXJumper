package jumper.jumper.entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
import jumper.jumper.app.GamePanel;

public abstract class Entity {
    GamePanel gamePanel;
    private String name;
    protected int worldX, worldY, speed;
    private Rectangle2D solidArea = new Rectangle2D(0,0,48,48); //default for all entity
    private double solidAreaDefaultX, solidAreaDefaultY;
    private boolean collisionOn = false;
    private String direction;

    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    // Setters
    public void setName(String new_name) {this.name = new_name;}
    public void setSolidArea(Rectangle2D new_solidArea) {solidArea = new_solidArea;}
    public void setSolidAreaX(double x) {
        this.solidArea = new Rectangle2D(x, solidArea.getMinY(), solidArea.getWidth(), solidArea.getHeight());
    }
    public void setSolidAreaY(double y) {
        this.solidArea = new Rectangle2D(solidArea.getMinX(), y, solidArea.getWidth(), solidArea.getHeight());
    }
    public void setSolidAreaWidth(double w) {
        this.solidArea = new Rectangle2D(solidArea.getMinX(), solidArea.getMinY(), w, solidArea.getHeight());
    }
    public void setSolidAreaHeight(double h) {
        this.solidArea = new Rectangle2D(solidArea.getMinX(), solidArea.getMinY(), solidArea.getWidth(), h);
    }
    public void setCollisionOn(boolean new_collision) {this.collisionOn = new_collision;}
    public void setDirection(String new_direction) {this.direction = new_direction;}
    protected void setSolidAreaDefaultX(double new_solidAreaDefaultX) {
        this.solidAreaDefaultX = new_solidAreaDefaultX;
    }
    protected void setSolidAreaDefaultY(double new_solidAreaDefaultY) {
        this.solidAreaDefaultY = new_solidAreaDefaultY;
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getWorldY() {
        return worldY;
    }
    public int getWorldX() {
        return worldX;
    }
    public int getSpeed() {
        return speed;
    }
    public Rectangle2D getSolidArea() {
        return solidArea;
    }
    public boolean getCollisionOn() {
        return collisionOn;
    }
    public String getDirection() {
        return direction;
    }
    public double getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }
    public double getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }
    public double getSolidAreaWidth() {
        return solidArea.getWidth();
    }
    public double getSolidAreaHeight() {
        return solidArea.getHeight();
    }
    public double getSolidAreaX() {return solidArea.getMinX(); }
    public double getSolidAreaY() {return solidArea.getMinY(); }

}

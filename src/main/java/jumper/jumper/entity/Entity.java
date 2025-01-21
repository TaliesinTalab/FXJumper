package jumper.jumper.entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;
import jumper.jumper.app.UtilityTool;

import java.util.Objects;

public abstract class Entity {
    private NPC npc;
    protected GamePanel gamePanel;
    private String name;
    protected final int screenX, screenY;
    protected Image up1, up2, down1, down2, left1, left2, right1, right2;
    protected int spriteCounter = 0;
    protected int spriteNumber = 1;
    protected int worldX, worldY, speed;
    private Rectangle2D solidArea;
    private double solidAreaDefaultX, solidAreaDefaultY;
    private boolean collisionOn = false;
    private String direction;
    private int actionLockCounter = 0;
    private int dialogueIndex = 0;

    //we want to use the GamePanel inside Entity for npc
    public Entity(GamePanel gamePanel) {
        this.solidArea = new Rectangle2D(0, 0, 48, 48);
        this.gamePanel = gamePanel;
        screenX = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() / 2);
        screenY = gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize() / 2);
    }

    // Setters
    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }

    public void setName(String new_name) {
        this.name = new_name;
    }

    public void setSolidArea(Rectangle2D new_solidArea) {
        solidArea = new_solidArea;
    }

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

    public void setCollisionOn(boolean new_collision) {
        this.collisionOn = new_collision;
    }

    public void setDirection(String new_direction) {
        this.direction = new_direction;
    }

    protected void setSolidAreaDefaultX(double new_solidAreaDefaultX) {
        this.solidAreaDefaultX = new_solidAreaDefaultX;
    }

    protected void setSolidAreaDefaultY(double new_solidAreaDefaultY) {
        this.solidAreaDefaultY = new_solidAreaDefaultY;
    }

    public void setActionLockCounter(int actionLockCounter) {
        this.actionLockCounter = actionLockCounter;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    // Getters
    public int getDialogueIndex() {
        return dialogueIndex;
    }

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

    public double getSolidAreaX() {
        return solidArea.getMinX();
    }

    public double getSolidAreaY() {
        return solidArea.getMinY();
    }

    public int getActionLockCounter() {
        return actionLockCounter;
    }


    public Image loadImage(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        Image image = null;
        try {
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /*draw method for NPC, NPC is unlike object, NPC can move around and has several sprite variations,
   therefore we need to switch the sprite depending on its direction.
   And we draw it only if it is inside the camera frame.
    */
    public void draw(GraphicsContext gc) {
        Image image = null;
        double screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
        double screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

        if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {

            gc.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize());
            switch (getDirection()) {
                case "up":
                    if (spriteNumber == 1) {
                        image = up1;
                    }
                    if (spriteNumber == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNumber == 1) {
                        image = down1;
                    }
                    if (spriteNumber == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNumber == 1) {
                        image = left1;
                    }
                    if (spriteNumber == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNumber == 1) {
                        image = right1;
                    }
                    if (spriteNumber == 2) {
                        image = right2;
                    }
                    break;
            }
            gc.drawImage(image, screenX, screenY);
        }


        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNumber = spriteNumber == 1 ? 2 : 1;
            spriteCounter = 0;
        }


    }

    //the following two methods is for the movement of NPC
    //setAction is only the superClass here, so that the subclass can overwrite it.
    public void setAction() {
    }

    //since all NPCs will have the same movement, the method can be written here in superClass
    public void update() {
        // setAction(); I don't want NPCs to move :)
        collisionOn = false;
        gamePanel.getCollisionChecker().checkTile(this);
        gamePanel.getCollisionChecker().checkObject(this, false);
        gamePanel.getCollisionChecker().checkPlayer(this);
        //NPC can only move if collision is false
        if (!getCollisionOn()) {
            switch (getDirection()) {
                case "up" -> this.worldY -= this.speed;
                case "down" -> this.worldY += this.speed;
                case "left" -> this.worldX -= this.speed;
                case "right" -> this.worldX += this.speed;
            }
        }
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNumber = spriteNumber == 1 ? 2 : 1;
            spriteCounter = 0;
        }
    }

}

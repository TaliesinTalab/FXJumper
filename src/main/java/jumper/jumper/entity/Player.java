package jumper.jumper.entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jumper.jumper.app.*;
import jumper.jumper.handlers.KeyHandler;
import jumper.jumper.object.ObjectKey;
import jumper.jumper.object.SuperObject;

public class Player extends Entity {
    private int[] stats;
    private final KeyHandler keyHandler;
    private final int screenX;
    private final int screenY;
    private Image up1, up2, down1, down2, left1, left2, right1, right2;
    private int spriteCounter = 0;
    private int spriteNumber = 1;
    private SuperObject[] inventory = new SuperObject[10];

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;
        screenX = gamePanel.getScreenWidth()/2 - (gamePanel.getTileSize()/2);
        screenY = gamePanel.getScreenHeight()/2 - (gamePanel.getTileSize()/2);

        //It sets the exact area of the player which area is solid
        // I've adjusted it to the current player model, please do not touch, or I'll get you -Taliesin
        //setSolidArea(new Rectangle2D(16, 18, 21, 20));
        //I changed it :)
        // I'm gonna get you >:[
        setSolidArea(new Rectangle2D(14, 0, 22, 34));

        //preserves the default value of solidArea
        setSolidAreaDefaultX(getSolidArea().getMaxX());
        setSolidAreaDefaultY(getSolidArea().getMaxY());

        setDefaultStats();
        setDefaultValues();
        getPlayerImage();
    }

    // Setter
    public void setStrength(int strength) {
        this.stats[2] = strength;
    }
    public void setIntelligence(int intelligence) {
        this.stats[4] = intelligence;
    }
    public void setDexterity(int dexterity) {
        this.stats[3] = dexterity;
    }
    public void setCuteness(int cuteness) {
        this.stats[0] = cuteness;
    }
    public void setBody(int body) {
        this.stats[5] = body;
    }
    public void setHealth(int health) {
        this.stats[6] = health;
    }

    /**
     * This function initialises all the player's stats in an array (to save on space).
     * States are ordered as followed:
     * 0. Cuteness, 1. Level, 2. Strength, 3. Dexterity, 4. Intelligence, 5. Body,
     * 6. Health, 7. fullHealth, 8. healthModifier
     * Full health at the start of the game is 10.
     * @author Taliesin Talab
     */
    public void setDefaultStats() {
        this.stats = new int[9];
        this.stats[0] = 5;
        this.stats[1] = 1;
        this.stats[2] = 1;
        this.stats[3] = 1;
        this.stats[4] = 1;
        this.stats[5] = 1;
        this.stats[8] = 3;
        calculateFullHealth();
        this.stats[6] = this.stats[7];
    }

    public void setDefaultValues(){
        this.worldX = gamePanel.getTileSize() * 43; // starting position is 43, 24
        this.worldY = gamePanel.getTileSize() * 24;
        this.speed = 7;
        setDirection("up");
    }

    // Getter
    public int getStrength() {return this.stats[2];}
    public int getIntelligence() {return this.stats[4];}
    public int getDexterity() {return this.stats[3];}
    public int getCuteness() {return this.stats[0];}
    public int getBody() {return this.stats[5];}
    public int getLevel() {return this.stats[1];}
    public int getHealth() {return this.stats[6];}
    public int getFullHealth() {return this.stats[7];}
    public double getScreenX() { return this.screenX;}
    public double getScreenY() { return this.screenY;}
    public SuperObject[] getInventory() {return inventory;}

    /**
     * This function simply assigns the player character his sprites. If we change a sprite or add one, then
     * we must also implement that here. The variables below actually belong to the Entity super-class of Player
     * @author Taliesin Talab
     */
    public void getPlayerImage() {
        up1 = loadImage("/player/boy_up_1");
        up2 = loadImage("/player/boy_up_2");
        down1 = loadImage("/player/boy_down_1");
        down2 = loadImage("/player/boy_down_2");
        left1 = loadImage("/player/boy_left_1");
        left2 = loadImage("/player/boy_left_2");
        right1 = loadImage("/player/boy_right_1");
        right2 = loadImage("/player/boy_right_2");
        up1 = loadImage("/player/boy_up_1");
    }

    // Additional Functions

    /**
     * Levels up the player by one (1).
     * @author Taliesin Talab
     */
    public void levelUp() {
        this.stats[1]++;
        calculateFullHealth();
        healToFull();
        System.out.println("You have levelled up!\n" +
                "Your current level is: " + this.stats[1]);
    }

    /**
     * Increases cuteness stat by 1
     * @author Taliesin Talab
     */
    public void cutenessUp(int i) {
        this.stats[0] += i;
    }

    /**
     * Decreases cuteness stat by one
     * @author Taliesin Talab
     */
    public void cutenessDown() {
        this.stats[0]--;
        System.out.println("You feel less cute...");
    }

    /**
     * Calculates maximum health of player based on the body stat and level. Then the healthModifier is added.
     * @author Taliesin Talab
     */
    public void calculateFullHealth() {
        this.stats[7] = (this.stats[5]*2)+(this.stats[1]*5)+this.stats[8];
    }

    /**
     * Heals the player to their maximum health based on the fullHealth stat.
     * @author Taliesin Talab
     */
    public void healToFull() {
        this.stats[6] = this.stats[7];
    }

    /**
     * Restores the players health by a specified amount. If it exceeds the maximum health, healToFull() is used.
     * @param amount integer by 'how much the player is healed'
     * @author Taliesin Talab
     */
    public void heal(int amount) {
        if (this.stats[6] + amount < this.stats[7]) {
            this.stats[6] += amount;
            System.out.println("Healed to " + this.stats[6]);
        }
        else {
            healToFull();
            System.out.println("Healed to full health.");
        }
    }

    /**
     * Increases the healthModifier by a specific amount. This amount is added to the maximum health.
     * @param mod integer determining exact increase in health
     * @author Taliesin Talab
     */
    public void increaseHealthMod(int mod) {
        this.stats[8] += mod;
        calculateFullHealth();
        heal(mod);
    }

    /**
     * update() changes the player's position depending on which button has been pressed. It is also responsible
     *          for cycling through the player sprites via updating spriteCounter and spriteNumber which then play a role
     *          in draw()
     * @author Taliesin Talab
     * @modifiedBy Lu Wang
     * @modifiedBy Jonathan Percht
     */
    public void update() {

        if (keyHandler.getUpPressed() || keyHandler.getDownPressed()
                || keyHandler.getLeftPressed() || keyHandler.getRightPressed()) {
            if ((keyHandler.getUpPressed() && keyHandler.getLeftPressed()) ||
                    (keyHandler.getUpPressed() && keyHandler.getRightPressed()) ||
                    (keyHandler.getDownPressed() && keyHandler.getLeftPressed()) ||
                    (keyHandler.getDownPressed() && keyHandler.getRightPressed())) {
                if (keyHandler.getUpPressed() && keyHandler.getLeftPressed()) {
                    setDirection("upLeft");
                }
                if (keyHandler.getUpPressed() && keyHandler.getRightPressed()) {
                    setDirection("upRight");
                }
                if (keyHandler.getDownPressed() && keyHandler.getLeftPressed()) {
                    setDirection("downLeft");
                }
                if (keyHandler.getDownPressed() && keyHandler.getRightPressed()) {
                    setDirection("downRight");
                }
            } else {
                if (keyHandler.getUpPressed()) {
                    setDirection("up");
                }
                if (keyHandler.getDownPressed()) {
                    setDirection("down");
                }
                if (keyHandler.getLeftPressed()) {
                    setDirection("left");
                }
                if (keyHandler.getRightPressed()) {
                    setDirection("right");
                }
            }

            //check tile collision
            setCollisionOn(false);
            gamePanel.getCollisionChecker().checkTile(this);

            //check object collision
            int objectIndex = gamePanel.getCollisionChecker().checkObject(this, true);
            pickUpObject(objectIndex);

            //check NPC collision
            int npcIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNPCArray());
            interactNPC(npcIndex);

            //player can only move if collision is false
            if(!getCollisionOn()){
                switch (getDirection()){
                    case "upLeft" -> {
                        this.worldY -= (int) (this.speed * 0.7);
                        this.worldX -= (int) (this.speed * 0.7);
                    }
                    case "upRight" -> {
                        this.worldY -= (int) (this.speed * 0.7);
                        this.worldX += (int) (this.speed * 0.7);
                    }
                    case "downLeft" -> {
                        this.worldY += (int) (this.speed * 0.7);
                        this.worldX -= (int) (this.speed * 0.7);
                    }
                    case "downRight" -> {
                        this.worldY += (int) (this.speed * 0.7);
                        this.worldX += (int) (this.speed * 0.7);
                    }
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

    /**
     * Places item into the inventory and removes it from the map
     * @author Jonathan Percht (copy and pasted by Taliesin Talab)
     */
    public void placeIntoInventory(int index) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = gamePanel.getPlacedObjects()[index];
                gamePanel.getAssetHandler().placeObjectAtIndex(null, index);
            }
        }
    }

    /**
     * responsible for item pickup
     * @author Jonathan Percht
     */
    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gamePanel.getPlacedObjects()[index].getName();

            gamePanel.playSoundEffect(gamePanel.getPlacedObjects()[index].getSoundEffect());

            switch (objectName) {
                case "Door":
                    for(int i = 0; i < inventory.length; i++) {
                        if(inventory[i] != null) {
                            if (inventory[i].getClass().equals(ObjectKey.class)) {
                                gamePanel.getAssetHandler().placeObjectAtIndex(null, index);
                                inventory[i] = null;
                                gamePanel.getUserInterface().showMessage("Path unlocked");
                                break;
                            }
                        }
                    }
                    break;
                case "Boots":
                    for(int i = 0; i < inventory.length; i++) {
                        if (inventory[i] == null) {
                            inventory[i] = gamePanel.getPlacedObjects()[index];
                            gamePanel.getAssetHandler().placeObjectAtIndex(null, index);
                            speed += 4;
                            gamePanel.getUserInterface().showMessage("Speed up");
                            break;
                        }
                    }
                    break;
                case "Chest":
                    gamePanel.getAssetHandler().placeObjectAtIndex(null, index);
                    App.getScreenhandler().addPoints(50);
                    gamePanel.getUserInterface().showMessage("+50 Points");
                    break;
                case "Pearl":
                    placeIntoInventory(index);
                    cutenessUp(20);
                    gamePanel.getUserInterface().showMessage("Cuteness up (a lot)");
                    break;
                case "Makeup":
                    placeIntoInventory(index);
                    cutenessUp(10);
                    gamePanel.getUserInterface().showMessage("Cuteness up");
                    break;
                case "Mirror":
                    placeIntoInventory(index);
                    cutenessUp(5);
                    gamePanel.getUserInterface().showMessage("Cuteness up (very little)");
                    break;
                case "Bribe":
                    placeIntoInventory(index);
                    cutenessUp(30);
                    gamePanel.getUserInterface().showMessage("Cuteness up (amazing!)");
                    break;
                default:
                    placeIntoInventory(index);
            }
            //inventory management
            renderInventory();
        }
    }

    /**
     * displays the inventory
     * @author Jonathan Percht
     */
    public void renderInventory() {
        App.getScreenhandler().getInventoryMenu().getMenus().clear();
        App.getScreenhandler().getInventoryMenu().getMenus().add(new Menu("", App.getScreenhandler().getCloseButton()));

        for(SuperObject object : gamePanel.getPlayer().getInventory()) {
            if(object != null) {
                Menu item = new Menu(object.getName(), new ImageView(object.getImage()));
                App.getScreenhandler().getInventoryMenu().getMenus().add(item);
            }
        }
    }
    /**
    *This is for interation between NPC and Player, if the index is not 999, it means the
     * Player is touching NPC, so we can change the gameState here
     * check if Enterpressed is true, Key Enter turns on and off the Dialogues
     * @author Lu Wang
     */
    public void interactNPC(int i){
        if(i != 999){
            if(getSolidArea().intersects(gamePanel.getNPCArray()[i].getSolidArea())) {
                gamePanel.setGameState(gamePanel.getDialogueState());
                gamePanel.getNPCArray()[i].speak();
            }
        }
    }

    /**
     * This is responsible for the actual changing of sprites when the player does something. For example, it
     * sets the player's image to the corresponding sprite, depending on the current spriteNumber. spriteNumber is
     * continuously switched by update().
     * @author Taliesin Talab
     */
    public void draw(GraphicsContext gc) {
        Image image = null;
        switch (getDirection()) {
            case "left", "upLeft", "downLeft":
                if(spriteNumber == 1) {
                    image = left1;
                }
                if(spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right", "upRight", "downRight":
                if(spriteNumber == 1) {
                    image = right1;
                }
                if(spriteNumber == 2) {
                    image = right2;
                }
                break;
            case "up":
                if(spriteNumber == 1) {
                    image = up1;
                }
                if(spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNumber == 1) {
                    image = down1;
                }
                if(spriteNumber == 2) {
                    image = down2;
                }
                break;
            default:
        }
        gc.drawImage(image, screenX, screenY);
    }
}

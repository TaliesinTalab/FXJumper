package jumper.jumper.handlers;

import jumper.jumper.app.GamePanel;
import jumper.jumper.entity.Entity;
import jumper.jumper.entity.NPC;
import jumper.jumper.object.SuperObject;

public class CollisionHandler {
    private GamePanel gamePanel;

    public CollisionHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * ...
     * @author Lu Wang
     * @modifiedBy Jonathan Percht
     */
    public void checkTile(Entity entity){
        double entityLeftWorldX = entity.getWorldX() + entity.getSolidAreaX() - 24; //value at the end is a fix for a collision bug
        double entityRightWorldX = entity.getWorldX() + entity.getSolidAreaX() + entity.getSolidAreaWidth() - 24; //-~-
        double entityTopWorldY= entity.getWorldY() + entity.getSolidAreaY() - 24; //-~-
        double entityBottomWorldY= entity.getWorldY() + entity.getSolidAreaY() + entity.getSolidAreaHeight() - 24; //-~-

        int entityLeftCol =(int) entityLeftWorldX/gamePanel.getTileSize();
        int entityRightCol =(int) entityRightWorldX/gamePanel.getTileSize();
        int entityTopRow =(int) entityTopWorldY/gamePanel.getTileSize();
        int entityBottomRow =(int) entityBottomWorldY/gamePanel.getTileSize();
        int tileNum1, tileNum2, tileNum3;

        switch(entity.getDirection()) {
            //here is to check if left shoulder(col) and right shoulder(col) are hitting the tile which has set Collision
            case "up":
                entityTopRow = (int) ((entityTopWorldY - entity.getSpeed()) / gamePanel.getTileSize());
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
                if (gamePanel.getTileManager().getTiles()[tileNum1].getCollision() || gamePanel.getTileManager().getTiles()[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (int) ((entityBottomWorldY + entity.getSpeed())/gamePanel.getTileSize());
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
                if(gamePanel.getTileManager().getTiles()[tileNum1].getCollision() || gamePanel.getTileManager().getTiles()[tileNum2].getCollision())
                {
                    entity.setCollisionOn(true);

                }
                break;
            case "left":
                entityLeftCol = (int) ((entityLeftWorldX - entity.getSpeed())/gamePanel.getTileSize());
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
                if(gamePanel.getTileManager().getTiles()[tileNum1].getCollision() || gamePanel.getTileManager().getTiles()[tileNum2].getCollision())
                {
                    entity.setCollisionOn(true);

                }
                break;
            case "right":
                entityRightCol = (int) ((entityRightWorldX + entity.getSpeed())/gamePanel.getTileSize());
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
                if(gamePanel.getTileManager().getTiles()[tileNum1].getCollision() || gamePanel.getTileManager().getTiles()[tileNum2].getCollision())
                {
                    entity.setCollisionOn(true);

                }
                break;
            case "upLeft":
                entityTopRow = (int) ((entityTopWorldY - entity.getSpeed() * 0.7) / gamePanel.getTileSize());
                entityLeftCol = (int) ((entityLeftWorldX - entity.getSpeed() * 0.7) / gamePanel.getTileSize());
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
                tileNum3 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
                if (
                        gamePanel.getTileManager().getTiles()[tileNum1].getCollision() ||
                        gamePanel.getTileManager().getTiles()[tileNum2].getCollision() ||
                        gamePanel.getTileManager().getTiles()[tileNum3].getCollision()
                ) {
                    entity.setCollisionOn(true);
                }
                break;
            case "upRight":
                entityTopRow = (int) ((entityTopWorldY - entity.getSpeed() * 0.7) / gamePanel.getTileSize());
                entityRightCol = (int) ((entityRightWorldX + entity.getSpeed() * 0.7) / gamePanel.getTileSize());
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
                tileNum3 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
                if (
                        gamePanel.getTileManager().getTiles()[tileNum1].getCollision() ||
                        gamePanel.getTileManager().getTiles()[tileNum2].getCollision() ||
                        gamePanel.getTileManager().getTiles()[tileNum3].getCollision()
                ) {
                    entity.setCollisionOn(true);
                }
                break;
            case "downLeft":
                entityBottomRow = (int) ((entityBottomWorldY + entity.getSpeed() * 0.7) / gamePanel.getTileSize());
                entityLeftCol = (int) ((entityLeftWorldX - entity.getSpeed() * 0.7) / gamePanel.getTileSize());
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
                tileNum3 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
                if (
                        gamePanel.getTileManager().getTiles()[tileNum1].getCollision() ||
                                gamePanel.getTileManager().getTiles()[tileNum2].getCollision() ||
                                gamePanel.getTileManager().getTiles()[tileNum3].getCollision()
                ) {
                    entity.setCollisionOn(true);
                }
                break;
            case "downRight":
                entityBottomRow = (int) ((entityBottomWorldY + entity.getSpeed() * 0.7) / gamePanel.getTileSize());
                entityRightCol = (int) ((entityRightWorldX + entity.getSpeed() * 0.7) / gamePanel.getTileSize());
                tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
                tileNum3 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow];
                if (
                        gamePanel.getTileManager().getTiles()[tileNum1].getCollision() ||
                                gamePanel.getTileManager().getTiles()[tileNum2].getCollision() ||
                                gamePanel.getTileManager().getTiles()[tileNum3].getCollision()
                ) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    /**
     * checks collision between player and object
     * @author Taliesin Talab (actually just wrote two lines)
     * @modifiedBy Jonathan Percht
     */
    public int checkObject(Entity entity, boolean player) {
        int index = 999, objectIndex = 0;

        for(SuperObject object : gamePanel.getPlacedObjects()) {
            if(object != null) {
                //Entity's solidAreaPosition
                entity.setSolidAreaX(entity.getWorldX() + entity.getSolidAreaX() - 24); //value at the end is a fix for a collision bug
                entity.setSolidAreaY(entity.getWorldY() + entity.getSolidAreaY() - 30); //-~-

                //Object's SolidAreaPosition
                object.setSolidAreaX(object.getWorldX() + object.getSolidAreaX());
                object.setSolidAreaY(object.getWorldY() + object.getSolidAreaY());

                switch(entity.getDirection()) {
                    case "up":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(object.getSolidArea())) {
                            if(object.getCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) index = objectIndex;
                        }
                        break;
                    case "down":
                        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                        if(entity.getSolidArea().intersects(object.getSolidArea())) {
                            if (object.getCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if (player) index = objectIndex;
                        }
                        break;
                    case "left":
                        entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(object.getSolidArea())) {
                            if(object.getCollision()) {
                                entity.setCollisionOn(true);
                            }
                        if(player) index = objectIndex;
                        }
                        break;
                    case "right":
                        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                        if(entity.getSolidArea().intersects(object.getSolidArea())) {
                            if(object.getCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) index = objectIndex;
                        }
                        break;
                    case "upLeft":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(object.getSolidArea())) {
                            if(object.getCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) index = objectIndex;
                        }
                        break;
                    case "upRight":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                        if(entity.getSolidArea().intersects(object.getSolidArea())) {
                            if(object.getCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) index = objectIndex;
                        }
                        break;
                    case "downLeft":
                        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                        entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(object.getSolidArea())) {
                            if(object.getCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) index = objectIndex;
                        }
                        break;
                    case "downRight":
                        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                        if(entity.getSolidArea().intersects(object.getSolidArea())) {
                            if(object.getCollision()) {
                                entity.setCollisionOn(true);
                            }
                            if(player) index = objectIndex;
                        }
                        break;
                }
                entity.setSolidAreaX(entity.getSolidAreaDefaultX());
                entity.setSolidAreaY(entity.getSolidAreaDefaultY());

                object.setSolidAreaX(object.getSolidAreaDefaultX());
                object.setSolidAreaY(object.getSolidAreaDefaultY());
            }
            objectIndex++;
        }
        return index;
    }

    /**
    check NPC or Monster Collision
    Arthur: Lu Wang
     */
    public int checkEntity(Entity entity, NPC[] targets){

        int index = 999;  //Default value if no collision is detected
        int objectIndex = 0; //Tracks the index of the current target in the array

        for(int i = 0; targets[i]!=null; i++) {

            if(targets[i] != null) {
                //update the solid area postions of the entity and the target
                entity.setSolidAreaX(entity.getWorldX() + entity.getSolidAreaX() - 24); //value at the end is a fix for a collision bug
                entity.setSolidAreaY(entity.getWorldY() + entity.getSolidAreaY() - 30); //-~-

                targets[i].setSolidAreaX(targets[i].getWorldX() + targets[i].getSolidAreaX());
                targets[i].setSolidAreaY(targets[i].getWorldY() + targets[i].getSolidAreaY());

                //Handle collision detection based on the direction of the entity
                switch(entity.getDirection()) {
                    case "up":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;   //update index if collsion occurs
                        }
                        break;
                    case "down":
                        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())) {

                            entity.setCollisionOn(true);
                          index = i;
                        }
                        break;
                    case "left":
                        entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())) {

                                entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())) {

                                entity.setCollisionOn(true);

                            index = i;
                        }
                        break;
                    case "upLeft":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "upRight":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "downLeft":
                        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                        entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "downRight":
                        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                        if(entity.getSolidArea().intersects(targets[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                }

                //reset the solid area positions to their default values
                entity.setSolidAreaX(entity.getSolidAreaDefaultX());
                entity.setSolidAreaY(entity.getSolidAreaDefaultY());
                targets[i].setSolidAreaX(targets[i].getSolidAreaDefaultX());
                targets[i].setSolidAreaY(targets[i].getSolidAreaDefaultY());
            }
        }
        return index;   //return the index of the collided object , or 999 if no collision.
    }

    /**
     * to check the collision when NPC runs into the Player
     * @author Lu Wang
     */
    public void checkPlayer(Entity entity){
        //update the solid area postions of the entity and the target
        entity.setSolidAreaX(entity.getWorldX() + entity.getSolidAreaX() - 24); //value at the end is a fix for a collision bug
        entity.setSolidAreaY(entity.getWorldY() + entity.getSolidAreaY() - 30); //-~-

        gamePanel.getPlayer().setSolidAreaX(gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getSolidAreaX());
        gamePanel.getPlayer().setSolidAreaY(gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getSolidAreaY());

        //Handle collision detection based on the direction of the entity
        switch(entity.getDirection()) {
            case "up":
                entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                if(entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                if(entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {

                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                if(entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {

                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                if(entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {

                    entity.setCollisionOn(true);
                }
                break;

        }
        //reset the solid area positions to their default values
        entity.setSolidAreaX(entity.getSolidAreaDefaultX());
        entity.setSolidAreaY(entity.getSolidAreaDefaultY());
        gamePanel.getPlayer().setSolidAreaX(gamePanel.getPlayer().getSolidAreaDefaultX());
        gamePanel.getPlayer().setSolidAreaY(gamePanel.getPlayer().getSolidAreaDefaultY());

    }
}

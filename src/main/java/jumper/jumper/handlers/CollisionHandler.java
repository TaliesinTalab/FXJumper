package jumper.jumper.handlers;

import jumper.jumper.app.GamePanel;
import jumper.jumper.entity.Entity;
import jumper.jumper.entity.NPC;
import jumper.jumper.object.Object;

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
        final double entityLeftWorldX = entity.getWorldX() + entity.getSolidAreaX() - 24,
                entityRightWorldX = entity.getWorldX() + entity.getSolidAreaX() + entity.getSolidAreaWidth() - 24,
                entityTopWorldY= entity.getWorldY() + entity.getSolidAreaY() - 24,
                entityBottomWorldY= entity.getWorldY() + entity.getSolidAreaY() + entity.getSolidAreaHeight() - 24;
        final int entityTopRow = (int) ((entityTopWorldY - entity.getSpeed()) / gamePanel.getTileSize()),
                entityBottomRow = (int) ((entityBottomWorldY + entity.getSpeed()) / gamePanel.getTileSize()),
                entityLeftCol = (int) ((entityLeftWorldX - entity.getSpeed()) / gamePanel.getTileSize()),
                entityRightCol = (int) ((entityRightWorldX + entity.getSpeed()) / gamePanel.getTileSize());
        final int tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow],
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow],
                tileNum3 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow],
                tileNum4 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
        final boolean collTopLeft = gamePanel.getTileManager().getTiles()[tileNum1].getCollision(),
                collTopRight = gamePanel.getTileManager().getTiles()[tileNum2].getCollision(),
                collBottomLeft = gamePanel.getTileManager().getTiles()[tileNum3].getCollision(),
                collBottomRight = gamePanel.getTileManager().getTiles()[tileNum4].getCollision();

        switch(entity.getDirection()) {
            //here is to check if left shoulder(col) and right shoulder(col) are hitting the tile which has set Collision
            case "up":
                if (collTopLeft || collTopRight) {
                    entity.setCollisionOnTop(true);
                }
                break;
            case "down":
                if (collBottomLeft || collBottomRight) {
                    entity.setCollisionOnBottom(true);
                }
                break;
            case "left":
                if (collTopLeft || collBottomLeft) {
                    entity.setCollisionOnLeft(true);
                }
                break;
            case "right":
                if (collTopRight || collBottomRight) {
                    entity.setCollisionOnRight(true);
                }
                break;
            case "upLeft":
                if (collTopLeft && !(collTopRight || collBottomLeft)) {
                    entity.setCollisionOn(true);
                } else {
                    if (collTopRight) {
                        entity.setCollisionOnTop(true);
                    }
                    if (collBottomLeft) {
                        entity.setCollisionOnLeft(true);
                    }
                }
                break;
            case "upRight":
                if (collTopRight && !(collTopLeft || collBottomRight)) {
                    entity.setCollisionOn(true);
                } else {
                    if (collTopLeft) {
                        entity.setCollisionOnTop(true);
                    }
                    if (collBottomRight) {
                        entity.setCollisionOnRight(true);
                    }
                }
                break;
            case "downLeft":
                if (collBottomLeft && !(collBottomRight || collTopLeft)) {
                    entity.setCollisionOn(true);
                } else {
                    if (collBottomRight) {
                        entity.setCollisionOnBottom(true);
                    }
                    if (collTopLeft) {
                        entity.setCollisionOnLeft(true);
                    }
                }
                break;
            case "downRight":
                if (collBottomRight && !(collBottomLeft || collTopRight)) {
                    entity.setCollisionOn(true);
                } else {
                    if (collBottomLeft) {
                        entity.setCollisionOnBottom(true);
                    }
                    if (collTopRight) {
                        entity.setCollisionOnRight(true);
                    }
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

        for(Object object : gamePanel.getPlacedObjects()) {
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

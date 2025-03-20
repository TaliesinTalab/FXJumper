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
        final int tile = (int) (gamePanel.getTileSize() / 2);
        final double entityTopWorldY= entity.getWorldY() + entity.getSolidAreaY() - tile,
                entityBottomWorldY= entity.getWorldY() + entity.getSolidAreaY() + entity.getSolidAreaHeight() - tile,
                entityLeftWorldX = entity.getWorldX() + entity.getSolidAreaX() - tile,
                entityRightWorldX = entity.getWorldX() + entity.getSolidAreaX()+ entity.getSolidAreaWidth() - tile;
        final int entityTopRow = (int) ((entityTopWorldY - Math.abs(entity.getYSpeed())) / gamePanel.getTileSize()),
                entityBottomRow = (int) ((entityBottomWorldY + Math.abs(entity.getYSpeed())) / gamePanel.getTileSize()),
                entityLeftCol = (int) ((entityLeftWorldX - Math.abs(entity.getXSpeed())) / gamePanel.getTileSize()),
                entityRightCol = (int) ((entityRightWorldX + Math.abs(entity.getXSpeed())) / gamePanel.getTileSize());
        final int tileNum1 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityTopRow],
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow],
                tileNum3 = gamePanel.getTileManager().getMapTileNumber()[entityLeftCol][entityBottomRow],
                tileNum4 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityBottomRow];
        final boolean collTopLeft = gamePanel.getTileManager().getTiles()[tileNum1].getCollision(),
                collTopRight = gamePanel.getTileManager().getTiles()[tileNum2].getCollision(),
                collBottomLeft = gamePanel.getTileManager().getTiles()[tileNum3].getCollision(),
                collBottomRight = gamePanel.getTileManager().getTiles()[tileNum4].getCollision();

        //here is to check if left shoulder(col) and right shoulder(col) are hitting the tile which has set Collision
        entity.setCollisionOnTop(collTopLeft || collTopRight);
        entity.setCollisionOnBottom(collBottomLeft || collBottomRight);
        entity.setCollisionOnLeft(collTopLeft || collBottomLeft);
        entity.setCollisionOnRight(collTopRight || collBottomRight);
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

                entity.setSolidAreaY(entity.getSolidAreaY() + entity.getYSpeed());
                entity.setSolidAreaX(entity.getSolidAreaX() + entity.getXSpeed());
                if(entity.getSolidArea().intersects(object.getSolidArea())) {
                    if(object.getCollision()) {
                        entity.setCollisionOn(true);
                    }
                    if(player) index = objectIndex;
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
                entity.setSolidAreaY(entity.getSolidAreaY() + entity.getYSpeed());
                entity.setSolidAreaX(entity.getSolidAreaX() + entity.getXSpeed());
                if(entity.getSolidArea().intersects(targets[i].getSolidArea())) {
                    entity.setCollisionOn(true);
                    index = i;
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
        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getYSpeed());
        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getXSpeed());
        if(entity.getSolidArea().intersects(gamePanel.getPlayer().getSolidArea())) {
            entity.setCollisionOn(true);
        }

        //reset the solid area positions to their default values
        entity.setSolidAreaX(entity.getSolidAreaDefaultX());
        entity.setSolidAreaY(entity.getSolidAreaDefaultY());
        gamePanel.getPlayer().setSolidAreaX(gamePanel.getPlayer().getSolidAreaDefaultX());
        gamePanel.getPlayer().setSolidAreaY(gamePanel.getPlayer().getSolidAreaDefaultY());

    }
}

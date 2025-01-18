package jumper.jumper.app;

import jumper.jumper.entity.Entity;
import jumper.jumper.object.SuperObject;

public class CollisionChecker {
    private GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * ...
     * @modifiedBy Jonathan Percht
     */
    public void checkTile(Entity entity){
        double entityLeftWorldX = entity.getWorldX() + entity.getSolidAreaX() - 24; //value at the end is a fix for a collision bug
        double entityRightWorldX = entity.getWorldX() +entity.getSolidAreaX() + entity.getSolidAreaWidth() - 24; //-~-
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
}

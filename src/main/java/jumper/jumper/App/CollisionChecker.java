package jumper.jumper.App;

import jumper.jumper.Entity.Entity;
import jumper.jumper.Object.SuperObject;

public class CollisionChecker {
    private GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity){
        double entityLeftWorldX = entity.getWorldX() + entity.getSolidAreaX();
        double entityRightWorldX = entity.getWorldX() +entity.getSolidAreaX() + entity.getSolidAreaWidth();
        double entityTopWorldY= entity.getWorldY() + entity.getSolidAreaY();
        double entityBottomWorldY= entity.getWorldY() + entity.getSolidAreaY() + entity.getSolidAreaHeight();

        int entityLeftCol =(int) entityLeftWorldX/gamePanel.getTileSize();
        int entityRightCol =(int) entityRightWorldX/gamePanel.getTileSize();
        int entityTopRow =(int) entityTopWorldY/gamePanel.getTileSize();
        int entityBottomRow =(int) entityBottomWorldY/gamePanel.getTileSize();
        int tileNum1, tileNum2;

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
                tileNum2 = gamePanel.getTileManager().getMapTileNumber()[entityRightCol][entityTopRow];
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
        }
    }

    /**
     * checks collision between player and object
     */
    public int checkObject(Entity entity, boolean player) {
        int index = 999, objectIndex = 0;

        for(SuperObject object : gamePanel.getPlacedObjects()) {
            if(object != null) {
                //Entity's solidAreaPosition
                entity.setSolidAreaX(entity.getWorldX() + entity.getSolidAreaX());
                entity.setSolidAreaY(entity.getWorldY() + entity.getSolidAreaY());

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
                                if(object.getCollision()) {
                                    entity.setCollisionOn(true);
                                }
                                if(player) index = objectIndex;
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

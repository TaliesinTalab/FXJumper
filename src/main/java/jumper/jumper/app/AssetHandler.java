package jumper.jumper.app;

import jumper.jumper.object.*;

/**
 * responsible for handling onscreen objects
 * @Author Jonathan Perchty
 */
public class AssetHandler {
    private GamePanel gamePanel;

    public AssetHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /**
     * places provided object at provided index of placedObjects array
     * @author Jonathan Percht
     */
    public void placeObjectAtIndex(SuperObject object, int index) {
        if (index >= 0 && index < gamePanel.getPlacedObjects().length) {
            gamePanel.getPlacedObjects()[index] = object;
        }
    }

    /**
     * uses placeObjectAtIndex() to initiate the placedObjects array ( --> GamePanel.setupGame() )
     * @author Jonathan Percht
     */
    public void setObject() {
            int tileSize = gamePanel.getTileSize();

            //DEMO CODE START
            ObjectChest tmpChest = new ObjectChest(gamePanel);
            tmpChest.setWorldX(6 * tileSize);
            tmpChest.setWorldY(6 * tileSize);
            placeObjectAtIndex(tmpChest, 0);

            ObjectKey tmpObjectKey = new ObjectKey(gamePanel);
            tmpObjectKey.setWorldX(10 * tileSize);
            tmpObjectKey.setWorldY(10 * tileSize);
            placeObjectAtIndex(tmpObjectKey, 1);

            ObjectDoor tmpObjectDoor = new ObjectDoor(gamePanel);
            tmpObjectDoor.setWorldX(8 * tileSize);
            tmpObjectDoor.setWorldY(12 * tileSize);
            placeObjectAtIndex(tmpObjectDoor, 2);

            ObjectBoots tmpObjectBoots = new ObjectBoots(gamePanel);
            tmpObjectBoots.setWorldX(9 * tileSize);
            tmpObjectBoots.setWorldY(16 * tileSize);
            placeObjectAtIndex(tmpObjectBoots, 3);
            //DEMO CODE END
    }
}

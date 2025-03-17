package jumper.jumper.handlers;

import jumper.jumper.app.GamePanel;
import jumper.jumper.entity.NPCs.*;
import jumper.jumper.object.*;
import jumper.jumper.object.Object;

/**
 * responsible for handling onscreen objects
 * @Author Jonathan Percht
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
    public void placeObjectAtIndex(Object object, int index) {
        if (index >= 0 && index < gamePanel.getPlacedObjects().length) {
            gamePanel.getPlacedObjects()[index] = object;
        }
    }

    /**
     * uses placeObjectAtIndex() to initiate the placedObjects array ( --> GamePanel.setupGame() )
     * @author Jonathan Percht
     * @modifiedBy (heavily) Taliesin Talab
     */
    public void setObject() {
            gamePanel.setPlacedObjects(new Object[gamePanel.getPlacedObjects().length]);

            int tileSize = gamePanel.getTileSize();

            //DEMO CODE START
            ObjectChest chest = new ObjectChest(gamePanel);
            chest.setWorldX(35 * tileSize);
            chest.setWorldY(28 * tileSize);
            placeObjectAtIndex(chest, 0);

            ObjectKey clubKey = new ObjectKey(gamePanel);
            clubKey.setWorldX(77 * tileSize);
            clubKey.setWorldY(28 * tileSize);
            placeObjectAtIndex(clubKey, 1);

            ObjectKey forestKey = new ObjectKey(gamePanel);
            forestKey.setWorldX(24 * tileSize);
            forestKey.setWorldY(41 * tileSize);
            placeObjectAtIndex(forestKey, 2);

            ObjectKey lakeKey = new ObjectKey(gamePanel);
            lakeKey.setWorldX(30 * tileSize);
            lakeKey.setWorldY(83 * tileSize);
            placeObjectAtIndex(lakeKey, 3);

            ObjectKey mazeInsideKey = new ObjectKey(gamePanel);
            mazeInsideKey.setWorldX(77 * tileSize);
            mazeInsideKey.setWorldY(68 * tileSize);
            placeObjectAtIndex(mazeInsideKey, 4);

            ObjectKey mazeExitKey = new ObjectKey(gamePanel);
            mazeExitKey.setWorldX(74 * tileSize);
            mazeExitKey.setWorldY(60 * tileSize);
            placeObjectAtIndex(mazeExitKey, 5);

            ObjectDoor mazeExitDoor = new ObjectDoor(gamePanel);
            mazeExitDoor.setWorldX(73 * tileSize);
            mazeExitDoor.setWorldY(60 * tileSize);
            placeObjectAtIndex(mazeExitDoor, 6);

            ObjectDoor vaultDoor1 = new ObjectDoor(gamePanel);
            vaultDoor1.setWorldX(67 * tileSize);
            vaultDoor1.setWorldY(36 * tileSize);
            placeObjectAtIndex(vaultDoor1, 7);

            ObjectDoor vaultDoor2 = new ObjectDoor(gamePanel);
            vaultDoor2.setWorldX(67 * tileSize);
            vaultDoor2.setWorldY(38 * tileSize);
            placeObjectAtIndex(vaultDoor2, 8);

            ObjectDoor vaultDoor3 = new ObjectDoor(gamePanel);
            vaultDoor3.setWorldX(67 * tileSize);
            vaultDoor3.setWorldY(40 * tileSize);
            placeObjectAtIndex(vaultDoor3, 9);

            ObjectDoor vaultDoor4 = new ObjectDoor(gamePanel);
            vaultDoor4.setWorldX(67 * tileSize);
            vaultDoor4.setWorldY(43 * tileSize);
            placeObjectAtIndex(vaultDoor4, 10);

            ObjectBoots boots = new ObjectBoots(gamePanel);
            boots.setWorldX(36 * tileSize);
            boots.setWorldY(38 * tileSize);
            placeObjectAtIndex(boots, 11);

            ObjectMirror mirror = new ObjectMirror(gamePanel);
            mirror.setWorldX(25 * tileSize);
            mirror.setWorldY(41 * tileSize);
            placeObjectAtIndex(mirror, 12);

            ObjectBribe bribe = new ObjectBribe(gamePanel);
            bribe.setWorldX(67 * tileSize);
            bribe.setWorldY(49 * tileSize);
            placeObjectAtIndex(bribe, 13);

            ObjectMakeUp makeUp = new ObjectMakeUp(gamePanel);
            makeUp.setWorldX(76 * tileSize);
            makeUp.setWorldY(58 * tileSize);
            placeObjectAtIndex(makeUp, 14);

            ObjectPearl pearl = new ObjectPearl(gamePanel);
            pearl.setWorldX(30 * tileSize);
            pearl.setWorldY(84 * tileSize);
            placeObjectAtIndex(pearl, 15);
            //DEMO CODE END
    }
    /**
    here we instantiate the NPC
     */
    public void setNPC(){
        gamePanel.getNPCArray()[0] = new NPCLu(gamePanel);
        gamePanel.getNPCArray()[0].setWorldX(gamePanel.getTileSize()*29);
        gamePanel.getNPCArray()[0].setWorldY(gamePanel.getTileSize()*83);

        gamePanel.getNPCArray()[1] = new NPCTaliesin(gamePanel);
        gamePanel.getNPCArray()[1].setWorldX(gamePanel.getTileSize()*69);
        gamePanel.getNPCArray()[1].setWorldY(gamePanel.getTileSize()*49);

        gamePanel.getNPCArray()[2] = new NPCJonathan(gamePanel);
        gamePanel.getNPCArray()[2].setWorldX(gamePanel.getTileSize()*26);
        gamePanel.getNPCArray()[2].setWorldY(gamePanel.getTileSize()*39);

        gamePanel.getNPCArray()[3] = new NPCAbdullah(gamePanel);
        gamePanel.getNPCArray()[3].setWorldX(gamePanel.getTileSize()*78);
        gamePanel.getNPCArray()[3].setWorldY(gamePanel.getTileSize()*58);

        gamePanel.getNPCArray()[4] = new NPCStickdude(gamePanel);
        gamePanel.getNPCArray()[4].setWorldX(gamePanel.getTileSize()*43);
        gamePanel.getNPCArray()[4].setWorldY(gamePanel.getTileSize()*22);
    }
}

package jumper.jumper.tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import jumper.jumper.app.GamePanel;
import jumper.jumper.app.UtilityTool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    private GamePanel gamePanel;
    private Tile[] tiles;
    private int[][] mapTileNumber;

    /**
     * Constructor for TileManager, tiles is the actual tile-sprites that we have. We only need one grass-tile
     * so that we can populate the map entirely with them. Imagine it being an 'original tile' that the map
     * generation copies
     */
    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        tiles = new Tile[20]; //check later if we can use an ArrayList for this instead
        mapTileNumber = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        getTileImage();
        loadMap("/maps/JumperDemoMap"); //Map for Game Demo
    }

    // Setters & Getters
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public Tile[] getTiles() {
        return tiles;
    }
    public int[][] getMapTileNumber() {
        return mapTileNumber;
    }

    /**
     * Much like getPlayerImage(), this function is here to purely assign the TileManager object its sprites
     * Must be updated when new tile sprites are added
     * @author Taliesin Talab
     */
    public void getTileImage() {
        setup(0, "grass", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "sand", false);
        setup(4, "tree", true);
        setup(5, "dirt", false);
        setup(6, "club_wall", true);
        setup(7, "club_pink_window", true);
        setup(8, "club_red_window", true);
        setup(9, "club_blue_window", true);
        setup(10, "club_orange_window", true);
        setup(11, "fence", true);
        setup(12, "road", false);
        setup(13, "wood_floor", false);
        setup(14, "flower", false);
        setup(15, "water_stone", false);
        setup(16, "pedestal", false);
        setup(17, "club_neon", true);
        // item ideas: make-up kit, hand-mirror, bribe-money, lake-pearl
    }

    /**
     * Set up each tile with an image and collision properties
     */
    public void setup(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();
        tiles[index] = new Tile();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
        Image scaledImage = uTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());

        tiles[index].setImage(scaledImage);
        tiles[index].setCollision(collision);
    }

    // Other Methods

    /**
     * This function simply draws the tiles onto the screen. It uses four variables to determine the tile(col & row)
     * and location (x & y). The loop is based on (row < gamePanel.maxScreenRow) because col is reset within the
     * while loop whenever it reaches the maximum. This is done so that it prints all columns for each row indent.
     * @author Abdullah Nazari
     */
    public void draw(GraphicsContext gc) {
        int col = 0, row = 0;
        while (row < gamePanel.getMaxWorldRow()) {
            int tileNumber = mapTileNumber[col][row];

            int worldX = col * gamePanel.getTileSize();
            int worldY = row * gamePanel.getTileSize();
            double screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
            double screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

            if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
               worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
               worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
               worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()) {

                gc.drawImage(tiles[tileNumber].getImage(), screenX, screenY);
            }
            col++;

            if (col >= gamePanel.getMaxWorldCol()) {
                col = 0;
                row++;

            }
        }
    }

    /**
     * This function parses txt files and updates the TileManager's mapTileNumber 2d-array with the corresponding
     * tile number. Note that a tile number refers to the 'value' assigned to the sprite in getTileImage()
     * @author Taliesin Talab
     * @modifiedBy Abdullah Nazari
     */
    public void loadMap(String fileName) {

        try{
            InputStream is = getClass().getResourceAsStream(fileName);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {
                String line = br.readLine();
                while (col < gamePanel.getMaxWorldCol()) {
                    String[] numbers = line.split(" ");
                    int number = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = number;
                    col++;
                }
                if (col == gamePanel.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

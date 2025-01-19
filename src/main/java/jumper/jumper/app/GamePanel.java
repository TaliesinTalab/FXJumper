package jumper.jumper.app;

import jumper.jumper.entity.Entity;
import jumper.jumper.entity.NPC;
import jumper.jumper.entity.Player;
import jumper.jumper.object.SuperObject;
import jumper.jumper.tiles.TileManager;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class GamePanel extends Canvas {
    private KeyHandler keyHandler = new KeyHandler(this); // This is needed for us to read inputs
    private Player player = new Player(this, keyHandler);
    private TileManager tileManager = new TileManager(this); //responsible for the game-map being rendered
    private CollisionChecker collisionChecker = new CollisionChecker(this);
    private NPC NPCArray[] = new NPC[10];
    private SuperObject[] placedObjects = new SuperObject[20]; //Array of objects rendered in map
    private AssetHandler assetHandler = new AssetHandler(this); //handles objects in placedObjects array
    private Sound sound = new Sound(); // responsible for the background_music
    private Sound soundEffect = new Sound(); // to play two sounds at the same time
    private UserInterface userInterface = new UserInterface(this);
    private ScreenHandler screenHandler;

    // SCREEN SETTINGS
    private final int originalTileSize = 16; //16x16 pixel tile
    private final int scale = 3; //Tile-Size by three to make it look better on modern monitors
    private final int tileSize = originalTileSize * scale; // actual tile size
    private final int maxScreenCol = 16; // Biggest screen will show 16 tiles per column
    private final int maxScreenRow = 12; // Biggest screen will show 16 tiles per row
    private final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    private final int screenHeight = tileSize * maxScreenRow;// 576 pixels

    // WORLD SETTINGS
    private final int maxWorldCol = 100;
    private final int maxWorldRow = 100;

    // GAME SETTINGS
    private static final int FPS = 60;
    private static final double FRAME_TIME = 1e9 / FPS; //in nanoseconds because currentTime used them too

    //GAME STATE
    private int gameState;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int dialogueState = 3;

    public GamePanel(ScreenHandler screenHandler) {
        this.screenHandler = screenHandler;
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        this.setStyle("-fx-background-color: black");

        // Set up key handling
        this.setFocusTraversable(true);
        this.setOnKeyPressed(this::handleKeyPressed);
        this.setOnKeyReleased(this::handleKeyReleased);

        setupGame();
        startGameLoop();
    }

    // Getters
    public ScreenHandler getScreenHandler() {
        return screenHandler;
    }
    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
    public int getTileSize() {
        return tileSize;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public TileManager getTileManager() {
        return tileManager;
    }
    public int getMaxWorldCol() {
        return maxWorldCol;
    }
    public int getMaxWorldRow() {
        return maxWorldRow;
    }
    public Player getPlayer() {
        return player;
    }
    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }
    public SuperObject[] getPlacedObjects() {
        return placedObjects;
    }
    public AssetHandler getAssetHandler() {
        return assetHandler;
    }
    public UserInterface getUserInterface() { return userInterface; }
    public int getGameState() {return gameState;}
    public int getPlayState() {return playState;}
    public int getPauseState() {return pauseState;}
    public Sound getSound() {return sound;}
    public NPC[] getNPCArray() {
        return NPCArray;
    }
    public int getDialogueState() {
        return dialogueState;
    }

    //Setters
    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }
    public void setPlacedObjects(SuperObject[] placedObjects) {
        this.placedObjects = placedObjects;
    }
    public void setGameState(int gameState) {this.gameState = gameState;}

// Other Methods

    public void handleKeyPressed(KeyEvent event) {
        keyHandler.handleKeyPress(event);
    }

    public void handleKeyReleased(KeyEvent event) {
        keyHandler.handleKeyRelease(event);
    }

    /**
     * Loads objects into the placedObjects Array
     * @author Taliesin Talab
     * @modifiedBy Jonathan Percht
     */
    public void setupGame() {
        playMusic(0);
        gameState = playState;
        assetHandler.setObject();
        assetHandler.setNPC();
    }

    /**
     * Starts and handles the game loop at specific frames per second. It updates and renders visuals at each frame.
     * @author Taliesin Talab
     */
    private void startGameLoop() {
        new AnimationTimer() {
            private long lastTime = 0;
            @Override
            public void handle(long now) {
                if (lastTime == 0) {lastTime = now; return;}
                long elapsedTime = now - lastTime;
                if (elapsedTime >= FRAME_TIME) {
                    update();
                    render();
                    lastTime = now;
                }
            }
        }.start();
    }

    /**
     * Updates player sprites and other changes. Also checks if the game is paused, and pauses the music accordingly.
     * @author Taliesin Talab
     * @modifiedBy Abdullah Nazari
     */
    public void update() {
        if(gameState == playState){
            if (!sound.isRunning()) sound.continueLoop(); //replace later if needed, continues music if paused
            //update player
            player.update();
            App.getScreenhandler().incrementTimer();
            //update npc
            for (Entity object : NPCArray) {
                if (object != null) object.update();
            }
        }
        if (gameState == pauseState){
            if (sound.isRunning()) sound.pause(); //replace later if needed, pauses music if paused
        }
        if (gameState == dialogueState){
            if (keyHandler.getEnterPressed()) gameState = playState; //This is needed, otherwise you would be stuck in dialogue
        }
    }

    /**
     * Responsible for drawing everything onto the canvas. Draws in this
     * order: tiles, player, userInterface
     * @author Taliesin Talab
     * @modifiedBy Jonathan Percht
     */
    public void render() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, screenWidth, screenHeight);
        tileManager.draw(gc);
        for (SuperObject object : placedObjects) {
            if (object != null) object.draw(gc, this);
        }
        //for NPC
        for (Entity object : NPCArray) {
            if (object != null) object.draw(gc);
        }

        player.draw(gc);
        userInterface.draw(gc);
    }

    /**
     * Plays background music in a loop
     * @param i music file in index
     * @author Abdullah Nazari
     */
    public void playMusic(int i) {
        sound.setFile(i);
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }


}

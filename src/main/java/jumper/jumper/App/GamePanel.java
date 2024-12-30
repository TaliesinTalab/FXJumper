package jumper.jumper.App;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import jumper.jumper.Entity.Player;
import jumper.jumper.Object.SuperObject;
import jumper.jumper.Tiles.TileManager;

public class GamePanel extends Canvas {
    private Thread gameThread; // We are using threads so that the game continues even if the player is idle
    private KeyHandler keyHandler = new KeyHandler(this); // This is needed for us to read inputs
    private Player player = new Player(this, keyHandler);
    private TileManager tileManager = new TileManager(this); //responsible for the game-map being rendered
    private CollisionChecker collisionChecker = new CollisionChecker(this);
    private SuperObject[] placedObjects = new SuperObject[10]; //Array of objects rendered in map
    private AssetHandler assetHandler = new AssetHandler(this); //handles objects in placedObjects array
    private Sound sound = new Sound(); // responsible for the background_music
    private Sound soundEffect = new Sound(); // to play two sounds at the same time
    private UserInterface userInterface = new UserInterface(this);


    // SCREEN SETTINGS
    private final int originalTileSize = 16; //16x16 pixel tile
    private final int scale = 3; //Tile-Size by three to make it look better on modern monitors
    private final int tileSize = originalTileSize * scale; // actual tile size
    private final int maxScreenCol = 16; // Biggest screen will show 16 tiles per column
    private final int maxScreenRow = 12; // Biggest screen will show 16 tiles per row
    private final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    private final int screenHeight = tileSize * maxScreenRow;// 576 pixels

    // WORLD SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;


    // GAME SETTINGS
    private final int fps = 60;


    //GAME STATE
    private int gameState;
    private final int playState = 1;
    private final int pauseState = 2;

    public GamePanel() {
        this.setWidth(screenWidth);
        this.setHeight(screenHeight);
        this.setStyle("-fx-background-color: black");

        // Set up key handling
        this.setFocusTraversable(true);
        this.setOnKeyPressed(this::handleKeyPressed);
        this.setOnKeyReleased(this::handleKeyReleased);

        setupGame();
        startGameLoop();

//        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
//        this.setBackground(Color.BLACK);
//        this.setDoubleBuffered(true);
//        this.addKeyListener(keyHandler);
//        this.setFocusable(true);
    }

    // Getters
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
    public Thread getGameThread() {
        return this.gameThread;
    }
    public UserInterface getUserInterface() { return userInterface; }
    public int getGameState() {return gameState;}
    public int getPlayState() {return playState;}
    public int getPauseState() {return pauseState;}

    //Setters
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
     */
    public void setupGame() {
        assetHandler.setObject();
        playMusic(0);
        gameState = playState;
    }

    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        }.start();
    }


    public void update() {

        if(gameState == playState){
            if (!sound.isRunning()) sound.play(); //replace later if needed, continues music if paused
            player.update();

        }
        if (gameState == pauseState){
            if (sound.isRunning()) sound.stop(); //replace later if needed, pauses music if paused
        }
    }

    public void render() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, screenWidth, screenHeight);
        tileManager.draw(gc);
        for (SuperObject object : placedObjects) {
            if (object != null) object.draw(gc, this);
        }
        player.draw(gc);
        userInterface.draw(gc);
    }

//    /**
//     * Responsible for actually 'drawing' the visuals on screen
//     * @param g the <code>Graphics</code> object to protect
//     */
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//
//        //DEBUG to check how long our programm to draw stuffs
//
//        long drawStart = 0;
//        if(keyHandler.isCheckDrawTime()){
//            drawStart = System.nanoTime();
//        }
//
//
//        //DRAW TILES
//        tileManager.draw(g2d);
//
//        //DRAW OBJECTS
//        for (SuperObject object : placedObjects) {
//            if (object != null) object.draw(g2d, this);
//        }
//
//        //DRAW PLAYER
//        player.draw(g2d);
//
//        // DRAW Key
//        userInterface.draw(g2d);
//
//        //DEBUG
//        if(keyHandler.isCheckDrawTime()){
//            long drawEnd = System.nanoTime();
//            //this way we can check how long have passed and display it on the screen
//            long passed = drawEnd - drawStart;
//            g2d.setColor(Color.white);
//            g2d.drawString("Draw Time: " + passed, 10, 400);
//            System.out.println("Draw Time: "+passed);
//        }
//
//        g2d.dispose();
//    }
    public void playMusic(int i){

        sound.setFile(i);
        sound.play();
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

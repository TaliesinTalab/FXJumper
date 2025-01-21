All classes and how credit is divided
***
Note: technically every class has been modified by Taliesin, due to the conversion from Swing to JavaFX. This will not be included below unless large changes were made.
***

## Classes under package "app"
- App.java
  - Purpose: Serves as the entryway into the game and starts the game.
  - Author: Taliesin Talab
  - Modified by: Jonathan Percht
- GamePanel.java
  - Purpose: basically the "heart and brain" of the game. It ensures all components work together, contains settings like the refresh rate per second, and the actual function of the game-loop.
  - Author: Taliesin Talab
  - Modified by: Jonathan Percht, Abdullah Nazari, Lu Wang
- UserInterface.java
  - Purpose: displaying text on screen, such as "Cuteness up!" when an item that increases cuteness is collected
  - Author: Abdullah Nazari
  - Modified by: Taliesin Talab, Lu Wang, Jonathan Percht
- UtilityTool.java
  - Purpose: used to scale images
  - Author: Lu Wang
## Classes under package "entity"
- Entity.java
  - Purpose: Mother-class of the entire package. It is the base for both the player, npcs, and in future enemies as well.
  - Author: Taliesin Talab
  - Modified by: Lu Wang, Jonathan Percht, Abdullah Nazari
- Player.java
  - Purpose: handling basically everything related to the actual player in-game, such as drawing the actual player sprite depending on direction and handling the player's stats.
  - Author: Taliesin Talab
  - Modified by: Abdullah Nazari, Jonathan Percht, Lu Wang
- NPC.java
  - Purpose: works as super-class for all NPCs
  - Author: Lu Wang
  - Modified by: Taliesin Talab
- NPC____.java (all NPC daughter-classes)
  - Purpose: Each NPC has their own dialogue and sprites. Each of these classes does the necessary work so that NPC can be used in-game.
  - Author: Taliesin Talab
## Classes under package "handlers"
- KeyHandler.java
    - Purpose: as the name implies, it handles key-presses from the player.
    - Author: Taliesin Talab
    - Modified by: Lu Wang, Jonathan Percht
- ScreenHandler.java
    - Purpose: title and end screens, actual javafx and the window itself
    - Author: Jonathan Percht
- SoundHandler.java
    - Purpose: Handling sounds like sound-effects (e.g. item-pickup) and background music
    - Author: Abdullah Nazari
    - Modified by: Taliesin Talab
- AssetHandler.java
    - Purpose: responsible for handling onscreen objects
    - Author: Jonathan Percht
- CollisionHandler.java
    - Purpose: Checks and handles collision for entities and tiles.
    - Author: Lu Wang
    - Modified by: Jonathan Percht
## Classes under package "object"
- SuperObject.java
  - Purpose: As the name implies, mother class of all actual objects.
  - Author: Jonathan Percht
  - Modified by: Taliesin Talab, Lu Wang
- Object____.java (all Object daughter-classes)
  - Purpose: handles the initialising of a object (e.g. key). Handles the objects sprites
  - Authors: depending on object, Jonathan Percht or/and Taliesin Talab
## Classes under package "tiles"
- TileManager.java
  - Purpose: handles basically everything about tiles, including setting up sprites
  - Author: Taliesin Talab
  - Modified by: Abdullah Nazari, Lu Wang
- Tile.java
  - Purpose: very basic object class with only the necessary variables, getters and setters
  - Author: Taliesin Talab
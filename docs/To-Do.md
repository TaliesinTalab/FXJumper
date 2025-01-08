'Arbeitspakete' to be distributed to the Jumper team.
***
## Current Packets 

- Add 'creator signature' – High Importance 
	- to all methods. Since a new Github repo has been created, sadly all previous changes to the code are unviewable (as long as you don't view the last repo) so I would like everyone to go through the code and add 'written by x' to the methods they wrote. Whenever a method is edited, please add an 'Edited by x, y, z' if your name is not already there.
- Add NPCs – High Importance
	- Add interactable non-player entities to the game. This will be needed for future game development.
- In World Events – Medium Importance
	- Add 'events' that can influence the player in game (e.g. pitfall trap). For this, you might need to add HP-mechanics as well.
- Character Status – Low Importance
	- Add a visual for the player's current stats.
- Cuteness Functions – Low (High) Importance
	- This will come in clutch in the future. Cuteness is our game mechanic, we should add some interactions in-game to showcase that in the demo.

***
## Completed Packets

- Initial Demo – Taliesin
	- Game loop, drawing player sprites and tiles, movement
- World & Camera – Abdullah
	- Logic to work with a world map and a moving camera
- Collision - Lu
	- Creation of CollisionChecker.java, includes creation of rectangular 'hit-boxes' for everything with collision
- Objects and Object Interaction – Jonathan
	- Creation of an Object superclass, a couple of objects, and the ability to interact with them (collect keys to open doors). Objects can be placed and found on the map
- Sound integration – Abdullah
	- Inclusion of background music via creation of Sound.java, integration of sound in GamePanel.java, and groundwork for sound effects (e.g. door opening sound)
- Basic UI – Abdullah
	- Basic framework for text-based UI (displaying text on screen)
- Improved Rendering – Lu
	- Improved drawing and clean-up of rendering-related code
- Game State – Lu
	- Methods enabling pausing of the game. Includes adding a key dedicated to pausing the game (at the time of writing 'P').
- Conversion from Swing to JavaFX – Taliesin
	- Massive conversion of the project to use the JavaFX toolkit. This was caused by Taliesin's error in accidentally basing the project around Swing (not knowing the difference).
- Add Splash-screen – Jonathan
  - Add a splash screen that is shown when the game is started.
- Add basic item inventory – Jonathan 
  - Items will be displayed in miniature form at the top of the screen.

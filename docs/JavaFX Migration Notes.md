Details and Notes by Taliesin
***
## Original Context
The project 'Card-Jumper' was originally written using JavaSwing, a now outdated toolkit that has been replaced in most modern applications by JavaFX. 
The reason Swing was used was because we simply did not know it _existed_, By that I mean that we did not know that we were using JavaFX, causing our mistake to stagger into a massive problem by the point we noticed what had happened.
## Migration Process
The changes to JavaFX from Swing were not necessarily hard, but rather a problem of the program structure. The first step was to add a Gradle framework. This framework also had to be set up to support JavaFX.
The second step was editing the code to use the intended toolkit. 
In total these changes were so substantial, that the creation of a new Git-Repository was simply the most cost effective solution. The original repository has now been set to public and will be linked below.
https://github.com/TaliesinTalab/Card-Jumper
(_last accessed: 31.12.2024_)

### New Git Repo
This change may be the most controversial and it stands as a testament to my skill with Github, Gradle, and JavaFX. Somewhere in the process outlined above, (multiple) error(s) were made, causing the debugging process to be a constant nightmare. Many boot-layer problems occurred one after the other. In addition, the 'build.gradle' and 'module-info.java' had to be edited for the whole program to function. An example of this is the addition of javafx.media (used for sound). Some undetected relics of these changes might still be in one of these files.
The decision to switch to the new repository in the end was simply the most economical choice, given the time-restriction and the halt on progress due to the switch. If given more time in future, I might try to find a solution.

***
## Code changes

In reality, most of the code has remained either entire unchanged or with some minor modifications. Only some key functions needed to be altered for the conversion.
### Major Changes
- _App.java_ Switch from JFrame (Swing) to Canvas (JavaFX)
- _GamePanel.java_ JPanel has been replaced with a Pane. This will be used as a 'container' for our visual elements.
- _GamePanel.java_ Original use of a thread (for game loop) was replaced by JavaFX's AnimationTimer.
- _GamePanel.java_ paintComponent and Graphics2D was replaced by render() and GraphicsContext so that it can work with the new Canvas.
- _GraphicsContext_ Graphics2D was used almost in most classes (at the time of writing) (e.g. UserInterface.java, Player.java, SuperObject.java, etc.) This has been replaced by JavaFX's graphicsContext
- _GamePanel.java & KeyHandler.java_ KeyListener has been replaced by JavaFX's inbuilt event handlers.
- _Sound.java_ javax.sound.sampled package was replaced by JavaFX own audio classes (Media and Mediaplayer).
- **NOTE to Sound.java** javafx.media had to be added to both build.gradle and module-info.java
- _Player.java, TileManager.java, all Objects_ The way images are handled has been changed. BufferedImage has been entirely replaced by the 'Image' class
- _Collision_ Collision previously used 'Rectangle', but that has now been switched to Rectangle2D which behaves similarly but notably is immutable.  
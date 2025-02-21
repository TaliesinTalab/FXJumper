package jumper.jumper.handlers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

 /**
  * To handle sound playback, including sound effets and looping background music*
  * @author Abdullah Nazari
  */
public class SoundHandler {
    private MediaPlayer mediaplayer;
    private final List<Media> soundMediaList = new ArrayList<>();

    public SoundHandler() {
        try {
            soundMediaList.add(new Media(Objects.requireNonNull(getClass().getResource("/sound/background_music.wav")).toURI().toString()));
            soundMediaList.add(new Media(Objects.requireNonNull(getClass().getResource("/sound/door_open.wav")).toURI().toString()));
            soundMediaList.add(new Media(Objects.requireNonNull(getClass().getResource("/sound/key_sound.wav")).toURI().toString()));
            soundMediaList.add(new Media(Objects.requireNonNull(getClass().getResource("/sound/boots_sound.wav")).toURI().toString()));
            soundMediaList.add(new Media(Objects.requireNonNull(getClass().getResource("/sound/fanfare.wav")).toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the audio file for playback based on the index
     * @param i The index of the sound in the list.
     * @author Abdullah Nazari
     * @modifiedBy Taliesin Talab
     * changed it so that all sound is at half volume  because everything was too loud
     */
    public void setFile(int i) {
        if (i < 0 || i >= soundMediaList.size()) return;
        if (mediaplayer != null) mediaplayer.stop();
        mediaplayer = new MediaPlayer(soundMediaList.get(i));
        mediaplayer.setVolume(0.3); //set sound volume, 1 for full, 0 for mute
    }

    /**
     * Plays the selected sound
     * @author Abdullah Nazari
     */
    public void play() {
        if (mediaplayer != null) mediaplayer.play();
    }

    /**
     * Loops the selected sound
     * @author Abdullah Nazari
     */
    public void loop() {
        if (mediaplayer != null) {
            mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaplayer.play();
        }
    }

    /**
     * I mean, just read the damn name
     * @author Taliesin Talab
     */
    public void stop() {
        if (mediaplayer != null) mediaplayer.stop();
    }

    /**
     * Pauses current sound
     * @author Taliesin Talab
     */
    public void pause() {
        if (mediaplayer != null) mediaplayer.pause();
    }

    public void continueLoop() {
        if (mediaplayer != null) {
            mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaplayer.play();
        }
    }


    /**
     * Checks if the current sound is playing-
     * @return True if the sound is playing, otherwise false
     */
    public boolean isRunning() {
        return mediaplayer != null && mediaplayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    /**
     * Function to set volume of a specific file.
     * @param volume sets volume between 0 (mute) and 1 (normal)
     * @author Taliesin Talab
     */
    public void setVolume(double volume) {
        if (mediaplayer != null) mediaplayer.setVolume(volume);
    }
}


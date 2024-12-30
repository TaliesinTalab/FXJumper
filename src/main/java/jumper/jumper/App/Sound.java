package jumper.jumper.App;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
    * To handle sound playback, including sound effets and looping background music
    */
public class Sound {
    private MediaPlayer mediaplayer;
    private final List<Media> soundMediaList = new ArrayList<>();

    public Sound() {
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
         */
    public void setFile(int i) {
        if (i < 0 || i >= soundMediaList.size()) return;
        if (mediaplayer != null) mediaplayer.stop();
        mediaplayer = new MediaPlayer(soundMediaList.get(i));
    }

        /**
         * Plays the selected sound
         */
    public void play() {
        if (mediaplayer != null) mediaplayer.play();
    }

        /**
         * Loops the selected sound
         */
    public void loop() {
        if (mediaplayer != null) {
            mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaplayer.play();
        }
    }

        /**
         * I mean, just read the damn name
         */
    public void stop() {
        if (mediaplayer != null) mediaplayer.stop();
    }

        /**
         * Checks if the current sound is playing-
         * @return True if the sound is playing, otherwise false
         */
    public boolean isRunning() {
        return mediaplayer != null && mediaplayer.getStatus() == MediaPlayer.Status.PLAYING;
    }


}


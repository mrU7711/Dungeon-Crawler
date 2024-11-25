import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private static Clip clip; // Store the clip as a static variable

    // Method to play the sound
    public static void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath); // Load the sound file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip(); // Get the Clip instance
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Method to stop the sound
    public static void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Stop the sound if it's currently playing
        }
    }
}

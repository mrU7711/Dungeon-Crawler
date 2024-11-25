import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb implements Displayable {
    private double x, y;
    private final BufferedImage bombImage;
    private final BufferedImage explosionImage;
    private boolean exploded = false;
    private boolean active = true; // Tracks if the bomb is still active
    private long timerStart;
    private long explosionStartTime;
    private final long explosionDuration = 1000; // Duration of explosion in milliseconds
    private boolean soundPlayed = false; // Ensures the sound plays only once

    public Bomb(double x, double y, BufferedImage bombImage, BufferedImage explosionImage) {
        this.x = x;
        this.y = y;
        this.bombImage = bombImage;
        this.explosionImage = explosionImage;
        this.timerStart = System.currentTimeMillis();
    }

    public void update() {
        long currentTime = System.currentTimeMillis();

        // Trigger explosion after 3 seconds
        if (currentTime - timerStart >= 3000 && !exploded) {
            exploded = true;
            explosionStartTime = currentTime;

            // Play explosion sound when the bomb explodes
            if (!soundPlayed) {
                SoundPlayer.playSound("./resources/explosion.wav"); // Update path if necessary
                soundPlayed = true; // Mark sound as played
            }
        }

        // Deactivate bomb after the explosion duration
        if (exploded && currentTime - explosionStartTime >= explosionDuration) {
            active = false;
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!exploded) {
            // Draw the bomb image before explosion
            g.drawImage(bombImage, (int) x, (int) y, null);
        } else if (active) {
            // Draw the explosion image during the explosion
            g.drawImage(explosionImage, (int) x - explosionImage.getWidth() / 2, (int) y - explosionImage.getHeight() / 2, null);
        }
    }

    public boolean isActive() {
        return active;
    }
}

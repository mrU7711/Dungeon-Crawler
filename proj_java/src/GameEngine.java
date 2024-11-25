import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class GameEngine extends KeyAdapter {
    private DynamicSprite hero;
    private ArrayList<Bomb> bombs = new ArrayList<>();
    private RenderEngine renderEngine;
    private BufferedImage bombImage;
    private BufferedImage explosionImage;

    public GameEngine(DynamicSprite hero, RenderEngine renderEngine) {
        this.hero = hero;
        this.renderEngine = renderEngine;

        // Load bomb and explosion images
        bombImage = loadImage("./img/bomb.png");
        explosionImage = loadImage("./img/explosion.png");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Set the direction based on the key pressed
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.setDirection(Direction.WEST);  // Set direction to left
            hero.move(0, 0);  // Move left
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.setDirection(Direction.EAST);  // Set direction to right
            hero.move(0, 0);  // Move right
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.setDirection(Direction.NORTH);  // Set direction to up
            hero.move(0, 0);  // Move up
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.setDirection(Direction.SOUTH);  // Set direction to down
            hero.move(0, 0);  // Move down
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Place bomb at hero's position when space is pressed
            Bomb bomb = new Bomb(hero.getX(), hero.getY(), bombImage, explosionImage);
            bombs.add(bomb);  // Add to bombs list
            renderEngine.addToRenderList(bomb);  // Add the bomb to the render list for display
        }
    }

    public void update() {
        ArrayList<Bomb> bombsToRemove = new ArrayList<>(); // Track bombs to remove

        // Update all bombs
        for (Bomb bomb : bombs) {
            bomb.update();

            // Check if the bomb is inactive
            if (!bomb.isActive()) {
                bombsToRemove.add(bomb);
                renderEngine.removeFromRenderList(bomb); // Remove from render list
            }
        }

        // Remove all inactive bombs from the bombs list
        bombs.removeAll(bombsToRemove);
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class Main {

    private static JFrame displayZoneFrame;
    private static GameEngine gameEngine;
    private static RenderEngine renderEngine;
    private static PhysicEngine physicEngine;
    private static Timer gameTimer;
    private static Timer renderTimer;
    private static Timer physicTimer;
    private static Timer deathTimer;

    public Main() throws Exception {
        // Initialize the game window
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(800, 600);  // Adjust the size as needed
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load hero image
        BufferedImage heroImage = loadImage("./img/heroTileSheetLowRes.png");

        // Create the hero sprite (DynamicSprite)
        DynamicSprite hero = new DynamicSprite(200, 300, heroImage, 48, 50);

        // Initialize engines
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, renderEngine);

        // Set up the game timers for updating the game logic
        renderTimer = new Timer(50, (time) -> renderEngine.update());
        gameTimer = new Timer(50, (time) -> gameEngine.update());
        physicTimer = new Timer(50, (time) -> physicEngine.update());
        deathTimer = new Timer(100000, (time) -> gameOver());

        // Start timers
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
        deathTimer.setRepeats(false);  // Only run once after 10 seconds
        deathTimer.start();

        // Set up game level and environment
        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        // Add key listener for the game controls
        displayZoneFrame.addKeyListener(gameEngine);

        // Display the window
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);
    }

    // Game Over function to stop timers and dispose of the window
    private void gameOver() {
        renderTimer.stop();
        gameTimer.stop();
        physicTimer.stop();
        deathTimer.stop();
        displayZoneFrame.dispose();

        // Open the game over screen
        SwingUtilities.invokeLater(() -> new GameOverScreen());
    }

    // Helper method to load images
    private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Main function to launch the title screen and start the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Launch title screen and pass the function to start the game
            new TitleScreen(() -> {
                try {
                    new Main();  // Start the main game
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
    }
}

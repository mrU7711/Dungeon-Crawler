import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class TitleScreen {
    private JFrame frame;
    private JLabel titleLabel;
    private JButton startButton;
    private boolean soundPlayed = false;

    public TitleScreen(Runnable onStartGame) {
        frame = new JFrame("Ecran Titre");
        frame.setSize(645, 360);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center window

        // Create JLayeredPane to manage layers of components
        JLayeredPane layeredPane = new JLayeredPane();
        frame.setContentPane(layeredPane);
        layeredPane.setLayout(null); // Use null layout for manual positioning

        // Set background image
        ImageIcon background = new ImageIcon("./img/start.jpg");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // Fill the window with the background
        layeredPane.add(backgroundLabel, Integer.valueOf(0)); // Add background to the lowest layer

        // Title text
        titleLabel = new JLabel("Dungeon Crawler", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue color
        int titleWidth = titleLabel.getPreferredSize().width;
        int titleHeight = titleLabel.getPreferredSize().height;

        // Calculate the centered position for the title
        int titleX = (frame.getWidth() - titleWidth) / 2;
        int titleY = (frame.getHeight() - titleHeight) / 2; // Placed a bit higher, so it's not too close to the top

        titleLabel.setBounds(titleX, titleY, titleWidth, titleHeight); // Position title text
        layeredPane.add(titleLabel, Integer.valueOf(1)); // Add title above the background

        // Start Game Button with hover effect
        startButton = new JButton("Commencer");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.setBackground(new Color(0, 102, 204)); // Blue color
        startButton.setForeground(Color.BLACK); // Black font color inside the button
        startButton.setBorder(BorderFactory.createEmptyBorder());  // Remove button borders
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false); // Make button background transparent
        startButton.setOpaque(true);
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() - 100, 200, 50); // Position button at bottom center

        // Play sound only once
        if (!soundPlayed) {
            SoundPlayer.playSound("./resources/music.wav"); // Update path if necessary
            soundPlayed = true;
        }

        startButton.addActionListener((ActionEvent e) -> {
            frame.dispose(); // Close title screen
            SoundPlayer.stopSound(); // Stop the background music when the game starts
            onStartGame.run(); // Start the game
        });

        // Hover effects for the button
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(0, 128, 255)); // Lighter blue
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(0, 102, 204)); // Original blue
            }
        });

        // Add the start button to the layered pane
        layeredPane.add(startButton, Integer.valueOf(2)); // Add button above the title and background

        frame.setVisible(true);
    }

    // Rounded border for the button
    class RoundedBorder extends AbstractBorder {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius, this.radius, this.radius, this.radius);
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(new Color(255, 215, 0)); // Gold border
            g.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}

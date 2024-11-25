import javax.swing.*;
import java.awt.*;

public class GameOverScreen {

    public GameOverScreen() {
        JFrame frame = new JFrame("Game Over");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Message Game Over
        JLabel messageLabel = new JLabel("Game Over", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 32));
        frame.add(messageLabel, BorderLayout.CENTER);

        // Bouton pour quitter le jeu ou redémarrer
        JButton restartButton = new JButton("Rejouer");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 18));
        restartButton.addActionListener(e -> {
            frame.dispose(); // Ferme l'écran Game Over
            SwingUtilities.invokeLater(() -> {
                try {
                    new Main(); // Redémarre le jeu
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });
        frame.add(restartButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

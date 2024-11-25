public class FPSCounter {
    private int frameCount = 0;        // Nombre d'images rendues
    private long lastTime = System.currentTimeMillis(); // Dernière mise à jour
    private int currentFPS = 0;       // FPS calculé

    // Appelé à chaque frame pour mettre à jour le FPS
    public void frameRendered() {
        frameCount++;

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime >= 1000) {
            currentFPS = frameCount; // Met à jour le FPS
            frameCount = 0;          // Réinitialise le compteur
            lastTime = currentTime;  // Réinitialise le temps
        }
    }

    // Retourne le FPS actuel
    public int getFPS() {
        return currentFPS;
    }
}

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite {
    public SolidSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x, y, (double) width, (double) height);
    }

    public boolean intersect(Rectangle2D.Double hitBox){
        return this.getHitBox().intersects(hitBox);
    }
}

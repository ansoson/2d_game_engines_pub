package talc.objects;

import engine.GameObject;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

public class Tier3Flower extends AlcObject{
    public Tier3Flower(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.whatTier = 3;
    }

    public boolean onCollision(GameObject collidedWith, Vec2d NULL) {
        return false;
    }
}

package talc.objects;

import engine.GameObject;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

public class GarbageBin extends AlcObject{
    public GarbageBin(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.spriteCoords = new Vec2d(280.0, 0.0);
        this.spriteDims = new Vec2d(70.0, 70.0);
        hasBeenDraggedYet = true;
        whatTier = 4;
    }


    @Override
    public boolean onCollision(GameObject collidedWith, Vec2d MTV) {
        if(!collidedWith.beingDragged()){
            collidedWith.DEAD = true;
        }
        return false;
    }
}

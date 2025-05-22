package talc.objects;

import engine.GameObject;
import engine.Systems.GameSystem;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

public class Tier1Flower extends AlcObject{
    public Tier1Flower(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        whatTier = 1;
    }
    public Tier1Flower(Vec2d location, Vec2d dimensions, Vec2d offset, Color color, Vec2d spriteCoords, Vec2d spriteDims) {
        super(location, dimensions, offset, color);
        this.spriteCoords = spriteCoords;
        this.spriteDims = spriteDims;
        whatTier = 1;
    }

    public boolean onCollision(GameObject collidedWith, Vec2d NULL) {
        if (((AlcObject)collidedWith).whatTier == 1){
            return onCollision((Tier1Flower) collidedWith);
        }
        return false;
    }

    public boolean onCollision(Tier1Flower collidedWith) {
        if ((collidedWith.isPink && this.isPurple && this.hasBeenDraggedYet && collidedWith.hasBeenDraggedYet) ||
                (collidedWith.isBlue && this.isOrange && this.hasBeenDraggedYet && collidedWith.hasBeenDraggedYet) ||
                (collidedWith.isPurple && this.isPink && this.hasBeenDraggedYet && collidedWith.hasBeenDraggedYet) ||
                (collidedWith.isOrange && this.isBlue && this.hasBeenDraggedYet && collidedWith.hasBeenDraggedYet)) {
            if (!this.beingDragged && !collidedWith.beingDragged()) {
                System.out.println("AND IM OUTTA HERE");
                AlcObject menuReplacement = new Tier2Flower(this.getTransform().getLocation(),
                        this.getTransform().getDimensions(),
                        this.getTransform().getOffset(),
                        Color.BLACK);
                menuReplacement.myMama = this.myMama;
                menuReplacement.hasBeenDraggedYet = true;
                myMama.gameObjects.add(menuReplacement);
                for (GameSystem system : myMama.gameSystems) {
                    if (system.getTag().equals("draws")) {
                        system.addObject(menuReplacement, 3.0);
                    }
                    if (system.getTag().equals("mouse")) {
                        system.addObject(menuReplacement, 3.0);
                    }
                    if (system.getTag().equals("collision")) {
                        system.addObject(menuReplacement, 3.0);
                    }
                }
                if (this.isPurple || this.isPink) {
                    menuReplacement.spriteCoords = new Vec2d(140.0, 70.0);
                    menuReplacement.isPP = true;
                } else {
                    menuReplacement.spriteCoords = new Vec2d(210.0, 70.0);
                    menuReplacement.isOB = true;
                }
                return true;
            }
        }
        return false;
    }

}

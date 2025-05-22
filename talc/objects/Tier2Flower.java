package talc.objects;

import engine.GameObject;
import engine.Systems.GameSystem;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

public class Tier2Flower extends AlcObject{
    public Tier2Flower(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.whatTier = 2;
    }


    public boolean onCollision(GameObject collidedWith, Vec2d NULL) {
        if (((AlcObject)collidedWith).whatTier == 2){
            return onCollision((Tier2Flower) collidedWith);
        }
        return false;
    }

    public boolean onCollision(Tier2Flower collidedWith) {
        if ((collidedWith.isOB && this.isPP && this.hasBeenDraggedYet && collidedWith.hasBeenDraggedYet) ||
                (collidedWith.isPP && this.isOB && this.hasBeenDraggedYet && collidedWith.hasBeenDraggedYet)
        ) {
            if (!this.beingDragged && !collidedWith.beingDragged()) {
                System.out.println("AND IM OUTTA HERE");
                AlcObject menuReplacement = new Tier3Flower(this.getTransform().getLocation(),
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
                menuReplacement.spriteCoords = new Vec2d(0.0, 210.0);

                return true;
            }
        }
        return false;
    }


}

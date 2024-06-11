package engine.mapTools;

import engine.GameObject;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Tile extends GameObject {

    public ArrayList<Tile> eightSquare;
    public boolean beenSituationalized;
    public int tileType;

    public Tile(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        beenSituationalized = false;
        eightSquare = new ArrayList<>();
    }

    public void situationalize(){}
    @Override
    public boolean onCollision(GameObject collidedWith, Vec2d MTV) {
        if (this.collisionHitbox.movable) {
            if (collidedWith.collisionHitbox.movable) {

            }
        }
        return false;
    }
}

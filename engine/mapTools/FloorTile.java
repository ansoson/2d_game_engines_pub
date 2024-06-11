package engine.mapTools;

import engine.GameObject;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

public class FloorTile extends Tile {
    public FloorTile(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, Color.AQUA);
    }

}

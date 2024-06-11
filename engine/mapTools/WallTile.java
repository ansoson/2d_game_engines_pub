package engine.mapTools;

import engine.GameObject;
import engine.support.Vec2d;
import javafx.scene.paint.Color;

public class WallTile extends Tile {
    public WallTile(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, Color.BLACK);
    }
}

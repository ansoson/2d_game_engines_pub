package twiz.objects;

import engine.Systems.GameSystem;
import engine.Systems.SpriteSystem;
import engine.components.SpriteComponent;
import engine.mapTools.Tile;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class NextFloorTile extends Tile {
    public NextFloorTile(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.spriteComponent = new SpriteComponent(new Vec2d(0.0,640.0),new Vec2d(0.0,640.0),new Vec2d(0.0,640.0), new Vec2d(160.0, 160.0), 20, 4);
    }

    public void tick(long nanos){
        super.tick(nanos);
    }

    public void draw(GraphicsContext g) {
        spriteComponent.tick();
        for (GameSystem system : myMama.gameSystems) {
            if (system.getTag().equals("sprite")) {
                ((SpriteSystem) system).drawSprite(this.spriteComponent, g, this.getTransform().getLocation(), this.getTransform().getDimensions());
            }
        }
    }
}

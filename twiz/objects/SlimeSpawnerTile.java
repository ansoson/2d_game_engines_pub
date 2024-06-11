package twiz.objects;

import engine.Systems.GameSystem;
import engine.Systems.SpriteSystem;
import engine.mapTools.SpawnTile;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SlimeSpawnerTile extends SpawnTile {
    public SlimeSpawnerTile(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.spriteCoords = new Vec2d(0.0, 480.0);
        this.spriteDims = new Vec2d(160.0, 160.0);

    }

    public void draw(GraphicsContext g) {
        for (GameSystem system : myMama.gameSystems) {
            if (system.getTag().equals("sprite")) {
                ((SpriteSystem) system).drawSprite(g,spriteCoords.x, spriteCoords.y, spriteDims.x, spriteDims.y,
                        this.getTransform().getLocation().x,
                        this.getTransform().getLocation().y,
                        this.getTransform().getDimensions().x,
                        this.getTransform().getDimensions().y);
            }
        }
    }

}

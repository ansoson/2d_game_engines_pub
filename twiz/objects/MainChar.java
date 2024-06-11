package twiz.objects;

import engine.GameObject;
import engine.Systems.GameSystem;
import engine.Systems.SpriteSystem;
import engine.components.KeyMovementComponent;
import engine.components.SpriteComponent;
import engine.gamePieces.PlayerChar;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class MainChar extends PlayerChar {
    public MainChar(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.location = location;
        this.dimensions = dimensions;
        this.spriteComponent = new SpriteComponent(new Vec2d(0.0,0.0),new Vec2d(320.0,0.0),new Vec2d(640.0,0.0), new Vec2d(160.0, 160.0), 15, 2);
        this.spriteCoords = new Vec2d(0.0, 0.0);
        this.spriteDims = new Vec2d(160.0, 160.0);
        this.keyMovementComponent= new KeyMovementComponent(this);
        this.collisionHitbox.movable = true;
    }

    public void tick(long nanos){
        super.tick(nanos);
        keyMovementComponent.tick();
    }
    @Override
    public void draw(GraphicsContext g) {
        for (GameSystem system : myMama.gameSystems) {
            if (system.getTag().equals("sprite")) {
//                ((SpriteSystem) system).drawSprite(g, spriteCoords.x, spriteCoords.y, spriteDims.x, spriteDims.y,
//                        this.getTransform().getLocation().x,
//                        this.getTransform().getLocation().y,
//                        this.getTransform().getDimensions().x,
//                        this.getTransform().getDimensions().y);
                ((SpriteSystem) system).drawSprite(this.spriteComponent, g, this.getTransform().getLocation(), this.getTransform().getDimensions());
            }
        }
    }

    @Override
    public boolean onCollision(GameObject collidedWith, Vec2d MTV) {
        if (collidedWith.hasTrigger) {
            collidedWith.onTrigger();
        }
        return super.onCollision(collidedWith, MTV);
    }
}
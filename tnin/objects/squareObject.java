package tnin.objects;

import engine.GameObject;
import engine.collisionShapes.CircleShape;
import engine.components.CollisionComponentPhysics;
import engine.components.GravityComponent;
import engine.components.PhysicsComponent;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class squareObject extends GameObject {
    public squareObject(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.location = location;
        this.dimensions = dimensions;
        this.physicsComponent = new PhysicsComponent(true, 25.0, this, .1);
        this.collisionComponentPhysics = new CollisionComponentPhysics();
        this.gravityComponent = new GravityComponent();
        this.collisionHitbox.movable = true;
    }

    public void tick(long nanos){
        super.tick(nanos);
    }

    @Override
    public boolean onCollision(GameObject collidedWith, Vec2d MTV) {
        if (collidedWith.hasTrigger) {
            collidedWith.onTrigger();
        }
        if (collisionComponentPhysics != null && collidedWith.getCollisionComponentPhysics() != null) {
            collisionComponentPhysics.onCollision(collidedWith, this, MTV);
            return true;
        } else {
            return super.onCollision(collidedWith, MTV);
        }
    }
}

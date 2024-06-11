package tnin.objects;

import engine.GameObject;
import engine.collisionShapes.CircleShape;
import engine.components.*;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class circleObject extends GameObject {
    public circleObject(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.location = location;
        this.dimensions = dimensions;
        this.physicsComponent = new PhysicsComponent(true, 10.0, this, .8);
        this.collisionComponentPhysics = new CollisionComponentPhysics();
        this.gravityComponent = new GravityComponent();
        this.collisionHitbox = new CircleShape(this.location, this.dimensions);
        this.collisionHitbox.movable = true;
    }

    public void tick(long nanos){
        super.tick(nanos);
    }
    @Override
    public void draw(GraphicsContext g) {
        g.setFill(Color.BLACK);
        g.fillOval(this.getTransform().getLocation().x, this.getTransform().getLocation().y, this.getTransform().getDimensions().x, this.getTransform().getDimensions().y);
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

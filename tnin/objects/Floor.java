package tnin.objects;

import engine.GameObject;
import engine.components.CollisionComponentPhysics;
import engine.components.GravityComponent;
import engine.components.PhysicsComponent;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Floor extends GameObject {
    public Floor(Vec2d location, Vec2d dimensions, Vec2d offset, Color color) {
        super(location, dimensions, offset, color);
        this.physicsComponent = new PhysicsComponent(false, 5.0, this, 1);
        this.collisionComponentPhysics = new CollisionComponentPhysics();
        this.gravityComponent = new GravityComponent();
        this.collisionHitbox.movable = false;
    }

    @Override
    public void draw(GraphicsContext g) {
        super.draw(g);
    }
}

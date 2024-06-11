package tnin.objects;

import engine.GameObject;
import engine.collisionShapes.CircleShape;
import engine.collisionShapes.PolygonShape;
import engine.components.CollisionComponentPhysics;
import engine.components.GravityComponent;
import engine.components.PhysicsComponent;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PolygonObject extends GameObject {

    double[] fillPolygonPointsX;
    double[] fillPolygonPointsY;
    int numPoints;

    public PolygonObject(Vec2d location, Vec2d dimensions, Vec2d offset, Color color, Vec2d[] points) {
        super(location, dimensions, offset, color);
        this.location = location;
        this.dimensions = dimensions;
        this.physicsComponent = new PhysicsComponent(true, 50.0, this, .3);
        this.collisionComponentPhysics = new CollisionComponentPhysics();
        this.gravityComponent = new GravityComponent();
        this.collisionHitbox = new PolygonShape(this.location, this.dimensions, points);
        fillPolygonPointsX = new double[points.length];
        fillPolygonPointsY = new double[points.length];
        this.numPoints = points.length;
        this.collisionHitbox.movable = true;
    }

    public void tick(long nanos){
        super.tick(nanos);
    }
    @Override
    public void draw(GraphicsContext g) {
        int i = 0;
        for (Vec2d point: ((PolygonShape) this.collisionHitbox).getPoints()){
            fillPolygonPointsX[i] = point.x;
            fillPolygonPointsY[i] = point.y;
            i++;
        }
        g.setFill(Color.BLACK);
        g.fillPolygon(fillPolygonPointsX, fillPolygonPointsY, numPoints);
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
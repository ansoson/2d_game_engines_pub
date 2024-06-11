package engine.components;

import com.sun.source.doctree.SystemPropertyTree;
import engine.GameObject;
import engine.Systems.PhysicsSystem;
import engine.support.Vec2d;

import java.awt.*;

public class PhysicsComponent {

    public PhysicsComponent(boolean movable, Double mass, GameObject myObject, double cor){
        this.mass = mass;
        this.movable = movable;
        this.COR = cor;
        impulse = new Vec2d(0,0);
        force = new Vec2d(0,0);
        this.myObject = myObject;
        position = myObject.getTransform().getLocation();
        if (movable){
            velocity = new Vec2d(0,.5);
        } else {
            velocity = new Vec2d(0,0);
        }
    }
    double mass;
    boolean movable;
    double COR;
    Vec2d impulse;
    Vec2d force;
    public Vec2d position;
    Vec2d velocity;
    GameObject myObject;

    public void applyForce(Vec2d f){
        if (movable){
            force = new Vec2d(force.x +  f.x, force.y + f.y);
        }
    }
    public void applyImpulse(Vec2d p){
        if (movable){
            impulse = new Vec2d(impulse.x +  p.x, impulse.y + p.y);
            if  (myObject.getGravityComponent() != null && myObject.getGravityComponent().resting){
                myObject.getGravityComponent().resting = false;
                myObject.getGravityComponent().restDelay = 0;
            }
        }
    }

    public void onTick(long t) {
        double seconds = (double) t /100000000;
        velocity = new Vec2d(seconds * force.x/mass + impulse.x/mass + velocity.x, seconds * force.y/mass + impulse.y/mass + velocity.y);
        Vec2d oldPos = position;
        position = new Vec2d(seconds * velocity.x + position.x, seconds * velocity.y + position.y);
        force = new Vec2d(0);
        impulse = new Vec2d(0);
    }

    public boolean isMovable(){
        return movable;
    }
    public void movePos(Vec2d mtv){
        position = new Vec2d(position.x - mtv.x, position.y - mtv.y);
    }
    public double getCOR() {
        return COR;
    }
    public double getMass(){
        return mass;
    }
    public Vec2d getVelocity() {
        return velocity;
    }
}

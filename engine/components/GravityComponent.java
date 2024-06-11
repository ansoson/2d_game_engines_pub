package engine.components;

import engine.support.Vec2d;

public class GravityComponent {

    public GravityComponent(){
        gravityAccel = new Vec2d(0, 9);
        resting = false;
        restDelay = 0;
    }

    boolean resting;
    int restDelay;
    Vec2d gravityForce;
    Vec2d gravityAccel;

    public void tick(PhysicsComponent c){
//        System.out.println(c.getVelocity() + " PRINTING VELOCITY");
        if (restDelay > 3 && Math.abs(c.velocity.y) < 5){
            if(c.myObject.getKeyMovementComponentImpulse() != null){
                resting = true;
            }
        }
        if (Math.abs(c.velocity.y) < 5){
            restDelay++;
        } else {
            restDelay = 0;
        }
        gravityForce = new Vec2d(gravityAccel.x * c.mass, gravityAccel.y * c.mass);
        c.applyForce(gravityForce);
    }

}

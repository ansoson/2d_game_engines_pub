package engine.components;

import engine.GameObject;
import engine.support.Vec2d;
import tnin.objects.MainNin;

public class CollisionComponentPhysics {

    public CollisionComponentPhysics(){

    }

    public void onCollision(GameObject collidedWith, GameObject collided, Vec2d MTV){
        if (collidedWith.getPhysicsComponent().isMovable()){
            collided.getPhysicsComponent().movePos(new Vec2d(MTV.x/2, MTV.y/2));
            double impulse = collided.getPhysicsComponent().getMass() * collidedWith.getPhysicsComponent().getMass() *
                    (collidedWith.getPhysicsComponent().getVelocity().dot(MTV.normalize()) - collided.getPhysicsComponent().getVelocity().dot(MTV.normalize())) *
                    (1 + Math.sqrt(collided.getPhysicsComponent().getCOR() * collidedWith.getPhysicsComponent().getCOR()))/( collided.getPhysicsComponent().getMass() + collidedWith.getPhysicsComponent().getMass());
            if(MTV.normalize().y != 1){
                System.out.println(impulse + " COLLISION impulse");
            }
            collided.getPhysicsComponent().applyImpulse(new Vec2d((impulse * MTV.normalize().x), impulse * MTV.normalize().y));
        } else {
            collided.getPhysicsComponent().movePos(MTV);
            double impulse = collided.getPhysicsComponent().getMass()*
                    (collidedWith.getPhysicsComponent().getVelocity().dot(MTV.normalize()) - collided.getPhysicsComponent().getVelocity().dot(MTV.normalize())) *
                    (1 + Math.sqrt(collided.getPhysicsComponent().getCOR() * collidedWith.getPhysicsComponent().getCOR()));
            if(MTV.normalize().y != 1){
                System.out.println(impulse + " COLLISION impulse");
            }
            collided.getPhysicsComponent().applyImpulse(new Vec2d(impulse * MTV.normalize().x , impulse * MTV.normalize().y));
        }
    }

}

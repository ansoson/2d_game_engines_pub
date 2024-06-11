package engine.Systems;

import engine.GameManager;
import engine.GameObject;
import engine.collisionShapes.AxisAlignedBoxShape;
import engine.collisionShapes.CircleShape;
import engine.collisionShapes.PolygonShape;
import engine.support.Vec2d;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class CollisionSystem extends GameSystem{
    public CollisionSystem(GameManager mama) {
        super(mama);
        setTag("collision");
        keyList = new ArrayList<>();
        currentlyColliding = new ArrayList<>();
        collided = new ArrayList<>();
    }
    protected ArrayList<Double> keyList;
    protected ArrayList<GameObject> currentlyColliding;
    protected ArrayList<GameObject> collided;

    public void addObject(GameObject obj, Double additional){
        systemObjects.add(new Pair<>(additional, obj));
        if (!keyList.contains(additional)){
            keyList.add(additional);
            Collections.sort(keyList);
        }
    }

    public void runCollisions(){
        for (Double collisionLayer : keyList) {
            for (Pair<Double, GameObject> object : this.getSystemObjects()){
                if (Objects.equals(object.getKey(), collisionLayer)) {
                    currentlyColliding.add(object.getValue());
                }
            }
            int t = 0;
            for (GameObject obj : currentlyColliding) {
                t++;
                for(int i = t; i < currentlyColliding.size(); i++) {
                    GameObject objecto = currentlyColliding.get(i);
                    if (obj.collisionHitbox.isCircle && !obj.DEAD && !objecto.DEAD) {
                        Vec2d collisionMTV = objecto.collisionHitbox.isCollidingCircle((CircleShape) obj.collisionHitbox);
                        if (collisionMTV != null){
                            obj.onCollision(objecto, collisionMTV);
                            objecto.onCollision(obj, collisionMTV.reflect());
                        };
                    }
                    if (obj.collisionHitbox.isAAB && !obj.DEAD && !objecto.DEAD) {
                        Vec2d collisionMTV = objecto.collisionHitbox.isCollidingAAB((AxisAlignedBoxShape) obj.collisionHitbox);
                        if (collisionMTV != null){
                            obj.onCollision(objecto, collisionMTV);
                            objecto.onCollision(obj, collisionMTV.reflect());
                        };
                    }
                    if (obj.collisionHitbox.isPoly && !obj.DEAD && !objecto.DEAD) {
                        Vec2d collisionMTV = objecto.collisionHitbox.isCollidingPolygon((PolygonShape) obj.collisionHitbox);
                        if (collisionMTV != null){
                            obj.onCollision(objecto, collisionMTV);
                            objecto.onCollision(obj, collisionMTV.reflect());
                        };
                    }
                }
            }
            currentlyColliding = new ArrayList<>();
        }
    }
}

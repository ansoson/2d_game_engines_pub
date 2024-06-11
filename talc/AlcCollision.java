package talc;

import engine.GameManager;
import engine.GameObject;
import engine.Systems.CollisionSystem;
import engine.Systems.GameSystem;
import engine.collisionShapes.AxisAlignedBoxShape;
import engine.collisionShapes.CircleShape;
import engine.support.Vec2d;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class AlcCollision extends CollisionSystem {
    public AlcCollision(GameManager mama) {
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
                        if (objecto.collisionHitbox.isCollidingCircle((CircleShape) obj.collisionHitbox) != null){
                            System.out.println("CIRCLES BE COLLIDING OR WHATEVAH");
                        };
                    }
                    if (obj.collisionHitbox.isAAB && !obj.DEAD && !objecto.DEAD) {
                        if (objecto.collisionHitbox.isCollidingAAB((AxisAlignedBoxShape) obj.collisionHitbox) != null){
                            if (obj.onCollision(objecto, new Vec2d(0.0, 0.0))) {
                                System.out.println("BOXES BE COLLIDING OR WHATEVAH");
                                objecto.DEAD = true;
                                obj.DEAD = true;
                            };
                        };
                    }
                }
            }
            currentlyColliding = new ArrayList<>();
        }
    }
}
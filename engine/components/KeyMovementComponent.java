package engine.components;

import engine.GameObject;
import engine.support.Vec2d;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyMovementComponent {

    GameObject myObject;
    public ArrayList<String> currentDirections;
    boolean idle;

    public KeyMovementComponent(GameObject object) {
        currentDirections = new ArrayList<>();
        myObject = object;
        idle = true;
    }

    public void keyDown(KeyEvent e){
        switch (e.getCode()) {
            case W -> {if (!currentDirections.contains("Up")){currentDirections.add(0, "Up"); if (myObject.getSpriteComponent() != null && !currentDirections.contains("Right")) {myObject.getSpriteComponent().setCurrentSprite(2);}}}
            case A -> {if (!currentDirections.contains("Left")){currentDirections.add(0, "Left"); if (myObject.getSpriteComponent() != null && !currentDirections.contains("Right")) {myObject.getSpriteComponent().setCurrentSprite(2);}}}
            case S -> {if (!currentDirections.contains("Down")){currentDirections.add(0, "Down"); if (myObject.getSpriteComponent() != null && !currentDirections.contains("Left")) {myObject.getSpriteComponent().setCurrentSprite(1);}}}
            case D -> {if (!currentDirections.contains("Right")){currentDirections.add(0, "Right"); if (myObject.getSpriteComponent() != null && !currentDirections.contains("Left")) {myObject.getSpriteComponent().setCurrentSprite(1);}}}
        }
        idle = false;
    }
    public void keyUp(KeyEvent e) {
        switch (e.getCode()) {
            case W -> currentDirections.remove("Up");
            case A -> currentDirections.remove("Left");
            case S -> currentDirections.remove("Down");
            case D -> currentDirections.remove("Right");
        }
    }
    public void tick() {
            if (!this.currentDirections.isEmpty()) {
        switch (this.currentDirections.get(0)) {
            case "Up" -> {
                myObject.getTransform().setTransforms(new Vec2d(myObject.getTransform().getLocation().x, myObject.getTransform().getLocation().y - 7), myObject.getTransform().getDimensions(), myObject.getTransform().getOffset());
                myObject.collisionHitbox.setLocation(myObject.getTransform().getLocation());
            }
            case "Left" -> {
                myObject.getTransform().setTransforms(new Vec2d(myObject.getTransform().getLocation().x - 7, myObject.getTransform().getLocation().y), myObject.getTransform().getDimensions(), myObject.getTransform().getOffset());
                myObject.collisionHitbox.setLocation(myObject.getTransform().getLocation());
            }
            case "Down" -> {
                myObject.getTransform().setTransforms(new Vec2d(myObject.getTransform().getLocation().x, myObject.getTransform().getLocation().y + 7), myObject.getTransform().getDimensions(), myObject.getTransform().getOffset());
                myObject.collisionHitbox.setLocation(myObject.getTransform().getLocation());
            }
            case "Right" -> {
                myObject.getTransform().setTransforms(new Vec2d(myObject.getTransform().getLocation().x + 7, myObject.getTransform().getLocation().y), myObject.getTransform().getDimensions(), myObject.getTransform().getOffset());
                myObject.collisionHitbox.setLocation(myObject.getTransform().getLocation());
            }
        }
    } else {
                if (myObject.getSpriteComponent() != null) {
                    if(!idle) {
                        myObject.getSpriteComponent().setCurrentSprite(0);
                        idle = true;
                    }
                }
            }
    }
}

package engine;

import engine.Systems.GameSystem;
import engine.components.*;
import engine.collisionShapes.AxisAlignedBoxShape;
import engine.collisionShapes.Shape;
import engine.support.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GameObject {

    public GameObject(Vec2d location, Vec2d dimensions, Vec2d offset, Color color){
        systemList = new ArrayList<>();
        transformations = new TransformComponent(location, dimensions, offset, myMama);
        baseColor = color;
        DEAD = false;
        collisionHitbox = new AxisAlignedBoxShape(location, dimensions);
    }

protected ArrayList<GameSystem> systemList;
private TransformComponent transformations;
public Vec2d location;
public Vec2d dimensions;
public Vec2d offset;
private Color baseColor;
public GameManager myMama;
public Shape collisionHitbox;
public boolean DEAD;
public Vec2d spriteCoords;
public Vec2d spriteDims;
protected KeyMovementComponent keyMovementComponent;
protected SpriteComponent spriteComponent;
protected PhysicsComponent physicsComponent;
protected GravityComponent gravityComponent;
protected CollisionComponentPhysics collisionComponentPhysics;
protected KeyMovementComponentImpulse keyMovementComponentImpulse;
public boolean hasTrigger;
public void setMama(GameManager mama){
    myMama = mama;
}
public void addSystem(GameSystem system, Double additional) {
    if (!this.systemList.contains(system)) {
        this.systemList.add(system);
        system.addObject(this, additional);
    }
}
public void removeSystem(GameSystem system) {
    system.removeObject(this);
    systemList.remove(system);
}
public GameSystem getSystem(String tag) {
    for (GameSystem system : systemList) {
        if (system.getTag().equals(tag)) {
            return system;
        }
    }
    System.out.println("UH OH THIS SYSTEM ISN'T IN HERE BUCKAROO (GameObject getSystem)");
    return transformations;
}
public TransformComponent getTransform(){
    return transformations;
}
public void setTransform(Vec2d newLocation, Vec2d newDimensions, Vec2d newOffset){
    this.transformations.setTransforms(newLocation, newDimensions, newOffset);
}


public void tick(long t) {
    if(spriteComponent != null){
        spriteComponent.tick();
    }
    if(gravityComponent != null){
        gravityComponent.tick(physicsComponent);
    }
    if(physicsComponent != null){
        physicsComponent.onTick(t);
        this.transformations.setTransforms(physicsComponent.position, getTransform().getDimensions(), getTransform().getOffset());
        this.collisionHitbox.setLocation(getTransform().getLocation());
    }
}
public void lateTick() {
}
public void draw(GraphicsContext g){
    /**
     * test default draw
     */
    g.setFill(baseColor);
    g.fillRect(transformations.getLocation().x, transformations.getLocation().y, transformations.getDimensions().x, transformations.getDimensions().y);
}
public void mouseManage(boolean mousePressed, boolean mouseReleased, Vec2d mouseCoords){
}

public void onResize(Vec2d newDims, Vec2d newSize){
}

public boolean onCollision(GameObject collidedWith, Vec2d MTV){
    if (this.collisionHitbox.movable) {
        if (collidedWith.collisionHitbox.movable) {
            this.getTransform().setTransforms(new Vec2d(getTransform().getLocation().x - MTV.x/2, getTransform().getLocation().y - MTV.y/2), getTransform().getDimensions(), getTransform().getOffset());
            this.collisionHitbox.setLocation(this.getTransform().getLocation());
        } else {
            this.getTransform().setTransforms(new Vec2d(getTransform().getLocation().x - MTV.x, getTransform().getLocation().y - MTV.y), getTransform().getDimensions(), getTransform().getOffset());
            this.collisionHitbox.setLocation(this.getTransform().getLocation());
        }
    }
    return true;
}

public boolean beingDragged(){return false;
}
public void iCollided(){
}
    public void keyManage(KeyEvent key) {}
    public void keyPressed(KeyEvent key) {
        if (keyMovementComponent != null) {
            keyMovementComponent.keyDown(key);
        }
        if (keyMovementComponentImpulse != null) {
            keyMovementComponentImpulse.keyDown(key);
        }
    }
    public void keyLifted(KeyEvent key) {
        if (keyMovementComponent != null) {
            keyMovementComponent.keyUp(key);
        }
        if (keyMovementComponentImpulse != null) {
            keyMovementComponentImpulse.keyUp(key);
        }
    }
    public SpriteComponent getSpriteComponent() {
        return spriteComponent;
    }

    public void onTrigger(){}

    public CollisionComponentPhysics getCollisionComponentPhysics() {
        return collisionComponentPhysics;
    }

    public PhysicsComponent getPhysicsComponent() {
        return physicsComponent;
    }

    public GravityComponent getGravityComponent() {
        return gravityComponent;
    }
    public KeyMovementComponentImpulse getKeyMovementComponentImpulse() {
        return keyMovementComponentImpulse;
    }
}


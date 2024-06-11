package engine.collisionShapes;

import engine.support.Vec2d;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class Shape {

    protected Vec2d location;
    protected Vec2d dimensions;
    public boolean isAAB;
    public boolean isCircle;
    public boolean isPoly;
    public boolean movable;

    public Shape(Vec2d location, Vec2d dimensions) {
        this.location = location;
        this.dimensions = dimensions;
        this.isAAB = false;
        this.isCircle = false;
        this.isPoly = false;
        this.movable = true;
    }

    public Vec2d isCollidingAAB(AxisAlignedBoxShape s1) {
        return null;
    }

    public Vec2d isCollidingCircle(CircleShape s1) {
        return null;
    }

    public Vec2d isCollidingPoint(Vec2d s1) {
        return null;
    }

    public Vec2d isCollidingPolygon(PolygonShape s1){return null;}

    public Vec2d getLocation(){
        return this.location;
    }

    public Vec2d getDimensions(){
        return this.dimensions;
    }

    public void setLocation(Vec2d newLoc){
        this.location = newLoc;
    }

    public void makeUnmoving(){
        this.movable = false;
    }

}

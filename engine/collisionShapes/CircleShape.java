package engine.collisionShapes;

import debugger.collisions.AABShape;
import debugger.support.Interval;
import engine.support.Vec2d;

public class CircleShape extends Shape{

    protected Vec2d center;
    public CircleShape(Vec2d location, Vec2d dimensions) {
        super(location, dimensions);
        this.center = new Vec2d(location.x + dimensions.x/2, location.y + dimensions.y/2);
        this.isCircle = true;
        this.movable = true;
    }

    public Vec2d isCollidingAAB(AxisAlignedBoxShape box) {
        Vec2d f = box.isCollidingCircle(this);
        return f == null ? null : f.reflect();
    }

    @Override
    public Vec2d isCollidingCircle(CircleShape shape) {
        if((this.getRadius() + shape.getRadius()) * (shape.getRadius() + this.getRadius()) >= (shape.getCenter().x - this.getCenter().x) * (shape.getCenter().x - this.getCenter().x) + (shape.getCenter().y - this.getCenter().y) * (shape.getCenter().y - this.getCenter().y)) {
            double mag = this.getRadius() + shape.getRadius() - Math.sqrt((this.getCenter().x - shape.getCenter().x) * (this.getCenter().x - shape.getCenter().x) +
                    (this.getCenter().y - shape.getCenter().y) * (this.getCenter().y - shape.getCenter().y));
            double dir = (this.getCenter().y - shape.getCenter().y)/(this.getCenter().x - shape.getCenter().x);
            double moreRight = 1.0;
            double moreUp = 1.0;
            double isneg = 1.0;
            if (this.getCenter().x < shape.getCenter().x) {moreRight = -1.0;}
            if (this.getCenter().y < shape.getCenter().y) {moreUp = -1.0;}
            if (dir < 0) {isneg = -1.0;}
            if (this.getCenter().x == shape.getCenter().x) {
                return new Vec2d(0, moreUp * (this.getRadius() + shape.getRadius() - Math.sqrt((this.getCenter().x - shape.getCenter().x) * (this.getCenter().x - shape.getCenter().x) + (this.getCenter().y - shape.getCenter().y) * (this.getCenter().y - shape.getCenter().y))));
            }
            return new Vec2d(mag/(Math.sqrt(dir * dir + 1)) * moreRight, mag/(Math.sqrt(dir * dir + 1)) * dir * moreUp * isneg);
        }
        return null;
    }

    @Override
    public Vec2d isCollidingPoint(Vec2d s2) {
        if (this.getRadius() * this.getRadius() >= (s2.x - this.getCenter().x) * (s2.x - this.getCenter().x) + (s2.y - this.getCenter().y) * (s2.y - this.getCenter().y)) {
            double mag = this.getRadius() - Math.sqrt((s2.x - this.getCenter().x) * (s2.x - this.getCenter().x) +
                    (s2.y - this.getCenter().y) * (s2.y - this.getCenter().y));
            double dir = (this.getCenter().y - s2.y )/(this.getCenter().x - s2.x);
            return new Vec2d(mag/dir, mag * dir);
        }
        return null;
    }

    @Override
    public Vec2d isCollidingPolygon(PolygonShape s1) {
        Vec2d f = s1.isCollidingCircle(this);
        return f == null ? null : f.reflect();
    }

    public void setLocation(Vec2d newLoc){
        this.location = newLoc;
        this.center = new Vec2d(this.location.x + this.getRadius(), this.location.y + this.getRadius());
    }

    public Vec2d getCenter(){
        return this.center;
    }

    public Double getRadius(){
        return this.getDimensions().x/2;
    }

}

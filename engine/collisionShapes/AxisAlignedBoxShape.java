package engine.collisionShapes;

import engine.support.Vec2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AxisAlignedBoxShape extends Shape{

    public AxisAlignedBoxShape(engine.support.Vec2d location, engine.support.Vec2d dimensions) {
        super(location, dimensions);
        this.isAAB = true;
        this.movable = true;
    }

    public Vec2d isCollidingAAB(AxisAlignedBoxShape shape) {
        if (this.getLocation().x < shape.getLocation().x + shape.getDimensions().x && shape.getLocation().x < this.getLocation().x + this.getDimensions().x && this.getLocation().y + this.getDimensions().y > shape.getLocation().y && shape.getLocation().y + shape.getDimensions().y > this.getLocation().y){
            ArrayList<Double> minilist = new ArrayList<>();
            HashMap<Double, Vec2d> directlist = new HashMap<>();
            minilist.add(Math.abs(shape.getLocation().y + shape.getDimensions().y  - this.getLocation().y));
            directlist.put(Math.abs(shape.getLocation().y + shape.getDimensions().y  - this.getLocation().y), new Vec2d(0, shape.getLocation().y + shape.getDimensions().y  - this.getLocation().y));
            minilist.add(Math.abs(shape.getLocation().y - (this.getLocation().y + this.getDimensions().y)));
            directlist.put(Math.abs(shape.getLocation().y - (this.getLocation().y + this.getDimensions().y)), new Vec2d(0, shape.getLocation().y - (this.getLocation().y + this.getDimensions().y)));
            minilist.add(Math.abs(shape.getLocation().x + shape.getDimensions().x  - this.getLocation().x));
            directlist.put(Math.abs(shape.getLocation().x + shape.getDimensions().x  - this.getLocation().x), new Vec2d(shape.getLocation().x + shape.getDimensions().x  - this.getLocation().x, 0));
            minilist.add(Math.abs(shape.getLocation().x  - (this.getLocation().x + this.getDimensions().x)));
            directlist.put(Math.abs(shape.getLocation().x  - (this.getLocation().x + this.getDimensions().x)), new Vec2d(shape.getLocation().x  - (this.getLocation().x + this.getDimensions().x), 0));
            Collections.sort(minilist);
            return directlist.get(minilist.get(0));
        }
        return null;
    }


    public Vec2d isCollidingCircle(CircleShape shape) {
        Vec2d closestToCenter = isCollidingPoint(shape.getCenter());
        if (closestToCenter != null){
            debugger.support.Vec2d p = new debugger.support.Vec2d(shape.getCenter().x + closestToCenter.x, shape.getCenter().y + closestToCenter.y);
            double mag = shape.getRadius() + Math.sqrt((shape.getCenter().x - p.x) * (shape.getCenter().x - p.x) + (shape.getCenter().y - p.y) * (shape.getCenter().y - p.y));
            if  (p.x == this.getLocation().x) {
                return new Vec2d(mag, 0);
            } else if (p.x == this.getLocation().x + this.getDimensions().x) {
                return new Vec2d(-mag, 0);
            } else if (p.y == this.getLocation().y) {
                return new Vec2d(0, mag);
            } else {
                return new Vec2d(0, -mag);
            }
        }
        double pointX = Math.max(this.getLocation().x, Math.min(this.getLocation().x + this.getDimensions().x, shape.getCenter().x));
        double pointY = Math.max(this.getLocation().y, Math.min(this.getLocation().y + this.getDimensions().y, shape.getCenter().y));
        Vec2d clampedCenter = new Vec2d(pointX, pointY);
        if (shape.isCollidingPoint(clampedCenter) != null) {
            double mag = shape.getRadius() - Math.sqrt((shape.getCenter().x - clampedCenter.x) * (shape.getCenter().x - clampedCenter.x) + (shape.getCenter().y - clampedCenter.y) * (shape.getCenter().y - clampedCenter.y));
            double dir = (clampedCenter.y - shape.getCenter().y)/(clampedCenter.x - shape.getCenter().x);
            double moreRight = 1.0;
            double moreUp = 1.0;
            double isneg = 1.0;
            if (this.getLocation().x + this.getDimensions().x/2 < shape.getCenter().x) {moreRight = -1.0;}
            if (this.getLocation().y + this.getDimensions().y/2 < shape.getCenter().y) {moreUp = -1.0;}
            if (dir < 0) {isneg = -1.0;}
            if (this.getLocation().x <= shape.getCenter().x & this.getLocation().x + this.getDimensions().x >= shape.getCenter().x) {
                return new Vec2d(0, moreUp * mag);
            }
            return new Vec2d(mag/(Math.sqrt(dir * dir + 1)) * moreRight, mag/(Math.sqrt(dir * dir + 1)) * dir * moreUp * isneg);
        };
        return null;
    }

    public Vec2d isCollidingPoint(Vec2d s2) {
        double topPointX = this.getLocation().x;
        double topPointY = this.getLocation().y;
        if (topPointX <= s2.x && s2.x <= topPointX + this.getDimensions().x && topPointY <= s2.y && s2.y <= topPointY + this.getDimensions().y) {
            ArrayList<Double> minilist = new ArrayList<>();
            HashMap<Double, Vec2d> directlist = new HashMap<>();
            minilist.add(Math.abs(this.getLocation().y + this.getDimensions().y  - s2.y));
            directlist.put(Math.abs(this.getLocation().y + this.getDimensions().y  - s2.y), new Vec2d(0, this.getLocation().y + this.getDimensions().y  - s2.y));

            minilist.add(Math.abs(this.getLocation().y - (s2.y)));
            directlist.put(Math.abs(this.getLocation().y - (s2.y)), new Vec2d(0, this.getLocation().y - (s2.y)));

            minilist.add(Math.abs(this.getLocation().x + this.getDimensions().x  - s2.x));
            directlist.put(Math.abs(this.getLocation().x + this.getDimensions().x  - s2.x), new Vec2d(this.getLocation().x + this.getDimensions().x  - s2.x, 0));

            minilist.add(Math.abs(this.getLocation().x  - (s2.x)));
            directlist.put(Math.abs(this.getLocation().x  - (s2.x)), new Vec2d(this.getLocation().x  - (s2.x), 0));
            Collections.sort(minilist);
            return directlist.get(minilist.get(0));
        }
        return null;
    }

    @Override
    public Vec2d isCollidingPolygon(PolygonShape s1) {
        Vec2d f = s1.isCollidingAAB(this);
        return f == null ? null : f.reflect();
    }

    public Vec2d getTopLeft() {
        return this.location;
    }

    public Vec2d getSize() {
        return this.dimensions;
    }
}

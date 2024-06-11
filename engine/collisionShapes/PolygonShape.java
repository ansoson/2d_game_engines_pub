package engine.collisionShapes;

import debugger.support.Interval;
import engine.support.Vec2d;
import java.util.ArrayList;

public class PolygonShape extends Shape{
    public PolygonShape(Vec2d location, Vec2d dimensions, Vec2d[] points) {
        super(location, dimensions);
        this.points = points;
        this.isPoly = true;
    }

    @Override
    public Vec2d isCollidingCircle(CircleShape s1) {
        int index = 0;
        double min1DMTV = 50000;
        Vec2d minAxis = null;
        for (Vec2d point : this.points) {
            if (index != 0) {
                Vec2d v = new Vec2d(point.x - this.getPoint(index - 1).x, point.y - this.getPoint(index - 1).y);
                Vec2d Vaxis = v.normalize().perpendicular();
                Interval s1Interval = new Interval();
                Interval s2Interval = new Interval();
                s1Interval.consider(new Vec2d(s1.getCenter().x + s1.getRadius() * Vaxis.x, s1.getCenter().y + s1.getRadius() * Vaxis.y).dot(Vaxis));
                s1Interval.consider(new Vec2d(s1.getCenter().x - s1.getRadius() * Vaxis.x, s1.getCenter().y - s1.getRadius() * Vaxis.y).dot(Vaxis));
                for (Vec2d points2 : this.points){
                    s2Interval.consider(points2.dot(Vaxis));
                }
                Double thisAxisMTV = s1Interval.returnMTV(s2Interval);
                if (thisAxisMTV == null) {
                    return null;
                }
                if (Math.abs(min1DMTV) >= Math.abs(thisAxisMTV)) {
                    min1DMTV = thisAxisMTV;
                    minAxis = Vaxis;
                }
            } else {
                Vec2d v = new Vec2d(point.x - this.getPoint(this.getNumPoints() - 1).x, point.y - this.getPoint(this.getNumPoints() - 1).y);
                Vec2d Vaxis = v.normalize().perpendicular();
                Interval s1Interval = new Interval();
                Interval s2Interval = new Interval();
                s1Interval.consider(new Vec2d(s1.getCenter().x - s1.getRadius() * Vaxis.x, s1.getCenter().y - s1.getRadius() * Vaxis.y).dot(Vaxis));
                s1Interval.consider(new Vec2d(s1.getCenter().x + s1.getRadius() * Vaxis.x, s1.getCenter().y + s1.getRadius() * Vaxis.y).dot(Vaxis));
                for (Vec2d points2 : this.points) {
                    s2Interval.consider(points2.dot(Vaxis));
                }
                Double thisAxisMTV = s1Interval.returnMTV(s2Interval);
                if (thisAxisMTV == null) {
                    return null;
                }
                if (Math.abs(min1DMTV) >= Math.abs(thisAxisMTV)) {
                    min1DMTV = thisAxisMTV;
                    minAxis = Vaxis;
                }
            }
            Vec2d cv = new Vec2d(point.x - s1.getCenter().x, point.y - s1.getCenter().y);
            Vec2d cvAxis = cv.normalize();
            Interval s1Interval2 = new Interval();
            Interval s2Interval2 = new Interval();
            s1Interval2.consider(new Vec2d(s1.getCenter().x + s1.getRadius() * cvAxis.x, s1.getCenter().y + s1.getRadius() * cvAxis.y).dot(cvAxis));
            s1Interval2.consider(new Vec2d(s1.getCenter().x - s1.getRadius() * cvAxis.x, s1.getCenter().y - s1.getRadius() * cvAxis.y).dot(cvAxis));
            for (Vec2d points2 : this.points){
                s2Interval2.consider(points2.dot(cvAxis));
            }
            Double thisAxisMTV = s1Interval2.returnMTV(s2Interval2);
            if (thisAxisMTV == null) {
                return null;
            }
            if (Math.abs(min1DMTV) > Math.abs(thisAxisMTV)) {
                min1DMTV = thisAxisMTV;
                minAxis = cvAxis;
            }
            index++;
        }
        return new Vec2d(-min1DMTV * minAxis.x, -min1DMTV * minAxis.y);
    }

    @Override
    public Vec2d isCollidingAAB(AxisAlignedBoxShape s1) {
        int index = 0;
        double min1DMTV = 50000;
        Vec2d minAxis = null;
        for (Vec2d point : this.points) {
            if (index != 0) {
                Vec2d v = new Vec2d(point.x - this.getPoint(index - 1).x, point.y - this.getPoint(index - 1).y);
                Vec2d Vaxis = v.normalize().perpendicular();
                Interval s1Interval = new Interval();
                Interval s2Interval = new Interval();
                s1Interval.consider(s1.getTopLeft().dot(Vaxis));
                s1Interval.consider(new Vec2d(s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y + s1.getSize().y).dot(Vaxis));
                s1Interval.consider(new Vec2d(s1.getTopLeft().x, s1.getTopLeft().y + s1.getSize().y).dot(Vaxis));
                s1Interval.consider(new Vec2d(s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y).dot(Vaxis));
                for (Vec2d points2 : this.points){
                    s2Interval.consider(points2.dot(Vaxis));
                }
                Double thisAxisMTV = s1Interval.returnMTV(s2Interval);
                if (thisAxisMTV == null) {
                    return null;
                }
                if (Math.abs(min1DMTV) >= Math.abs(thisAxisMTV)) {
                    min1DMTV = thisAxisMTV;
                    minAxis = Vaxis;
                }
            } else {
                Vec2d v = new Vec2d(point.x - this.getPoint(this.getNumPoints() - 1).x, point.y - this.getPoint(this.getNumPoints() - 1).y);
                Vec2d Vaxis = v.normalize().perpendicular();
                Interval s1Interval = new Interval();
                Interval s2Interval = new Interval();
                s1Interval.consider(s1.getTopLeft().dot(Vaxis));
                s1Interval.consider(new Vec2d(s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y + s1.getSize().y).dot(Vaxis));
                s1Interval.consider(new Vec2d(s1.getTopLeft().x, s1.getTopLeft().y + s1.getSize().y).dot(Vaxis));
                s1Interval.consider(new Vec2d(s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y).dot(Vaxis));
                for (Vec2d points2 : this.points) {
                    s2Interval.consider(points2.dot(Vaxis));
                }
                Double thisAxisMTV = s1Interval.returnMTV(s2Interval);
                if (thisAxisMTV == null) {
                    return null;
                }
                if (Math.abs(min1DMTV) >= Math.abs(thisAxisMTV)) {
                    min1DMTV = thisAxisMTV;
                    minAxis = Vaxis;
                }
            }
            index++;
        }
        Vec2d x = new Vec2d(1, 0);
        Vec2d y = new Vec2d(0, 1);
        Interval s1Interval = new Interval();
        Interval s2Interval = new Interval();
        s1Interval.consider(s1.getTopLeft().dot(x));
        s1Interval.consider(new Vec2d(s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y).dot(x));
        for (Vec2d points2 : this.points) {
            s2Interval.consider(points2.dot(x));
        }
        Double thisAxisMTV = s1Interval.returnMTV(s2Interval);
        if (thisAxisMTV == null) {
            return null;
        }
        if (Math.abs(min1DMTV) >= Math.abs(thisAxisMTV)) {
            min1DMTV = thisAxisMTV;
            minAxis = x;
        }
        Interval s1Interval2 = new Interval();
        Interval s2Interval2 = new Interval();
        s1Interval2.consider(s1.getTopLeft().dot(y));
        s1Interval2.consider(new Vec2d(s1.getTopLeft().x, s1.getTopLeft().y + s1.getSize().y).dot(y));
        for (Vec2d points2 : this.points) {
            s2Interval2.consider(points2.dot(y));
        }
        thisAxisMTV = s1Interval2.returnMTV(s2Interval2);
        if (thisAxisMTV == null) {
            return null;
        }
        if (Math.abs(min1DMTV) >= Math.abs(thisAxisMTV)) {
            min1DMTV = thisAxisMTV;
            minAxis = y;
        }
        return new Vec2d(-min1DMTV * minAxis.x, -min1DMTV * minAxis.y);
    }

    protected Vec2d[] points;

    public int getNumPoints() {
        return points.length;
    }

    public Vec2d getPoint(int i) {
        return points[i];
    }

    public Vec2d[] getPoints() {
        return points;
    }

    @Override
    public void setLocation(Vec2d newLoc) {
        Vec2d offset = new Vec2d(newLoc.x - location.x, newLoc.y - location.y);
        location = newLoc;
        int index = 0;
        for (Vec2d point : points) {
            points[index] = new Vec2d(point.x + offset.x, point.y + offset.y);
            index++;
        }
    }
}

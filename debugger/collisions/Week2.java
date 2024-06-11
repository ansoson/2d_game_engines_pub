package debugger.collisions;

import debugger.support.Vec2d;
import debugger.support.interfaces.Week2Reqs;

/**
 * Fill this class in during Week 2.
 */
public final class Week2 extends Week2Reqs {

	// AXIS-ALIGNED BOXES
	
	@Override
	public boolean isColliding(AABShape s1, AABShape s2) {
//		double projectionTLx = s1.getTopLeft().dot(new Vec2d(1, 0));
//		double projectionBLx = new Vec2d( s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y + s1.getSize().y).dot(new Vec2d(1, 0));
//		Vec2d xintervalA = new Vec2d(projectionTLx, projectionBLx);
//		double projectionTLx2 = s2.getTopLeft().dot(new Vec2d(1, 0));
//		double projectionBLx2 = new Vec2d( s2.getTopLeft().x + s2.getSize().x, s2.getTopLeft().y + s2.getSize().y).dot(new Vec2d(1, 0));
//		Vec2d xintervalB = new Vec2d(projectionTLx2, projectionBLx2);
//
//		double projectionTLx3 = new Vec2d( s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y).dot(new Vec2d(0, 1));
//		double projectionBLx3 = new Vec2d( s1.getTopLeft().x, s1.getTopLeft().y - s1.getSize().y).dot(new Vec2d(0, 1));
//		Vec2d xintervalA2 = new Vec2d(projectionTLx3, projectionBLx3);
//		double projectionTLx4 = new Vec2d( s2.getTopLeft().x + s2.getSize().x, s2.getTopLeft().y).dot(new Vec2d(0, 1));
//		double projectionBLx4 = new Vec2d( s2.getTopLeft().x, s2.getTopLeft().y - s2.getSize().y).dot(new Vec2d(0, 1));
//		Vec2d xintervalB2 = new Vec2d(projectionTLx4, projectionBLx4);
//
//		return xintervalA.x <= xintervalB.y && xintervalB.y <= xintervalA.x && xintervalA2.x >= xintervalB2.y && xintervalB2.y >= xintervalA2.x;

        return
				s1.getTopLeft().x <= s2.getTopLeft().x + s2.getSize().x && s2.getTopLeft().x <= s1.getTopLeft().x + s1.getSize().x
				&&
				s1.getTopLeft().y + s1.getSize().y >= s2.getTopLeft().y && s2.getTopLeft().y + s2.getSize().y >= s1.getTopLeft().y;
    }

	@Override
	public boolean isColliding(AABShape s1, CircleShape s2) {
		Double pointX = Math.max(s1.getTopLeft().x, Math.min(s1.getTopLeft().x + s1.getSize().x, s2.getCenter().x));
		Double pointY = Math.max(s1.getTopLeft().y, Math.min(s1.getTopLeft().y + s1.getSize().y, s2.getCenter().y));
		return isColliding(s2, new Vec2d(pointX, pointY));
	}

	@Override
	public boolean isColliding(AABShape s1, Vec2d s2) {
		double topPointX = s1.getTopLeft().x;
		double topPointY = s1.getTopLeft().y;
        return topPointX <= s2.x && s2.x <= topPointX + s1.getSize().x && topPointY <= s2.y && s2.y <= topPointY + s1.getSize().y;
    }

	// CIRCLES
	
	@Override
	public boolean isColliding(CircleShape s1, AABShape s2) {
		return isColliding(s2, s1);
	}

	@Override
	public boolean isColliding(CircleShape s1, CircleShape s2) {
		return (s1.getRadius() + s2.getRadius()) * (s2.getRadius() + s1.getRadius())  >= (s2.getCenter().x - s1.getCenter().x) * (s2.getCenter().x - s1.getCenter().x) +
																					(s2.getCenter().y - s1.getCenter().y) * (s2.getCenter().y - s1.getCenter().y);
	}

	@Override
	public boolean isColliding(CircleShape s1, Vec2d s2) {
        return s1.getRadius() * s1.getRadius() >= (s2.x - s1.getCenter().x) * (s2.x - s1.getCenter().x) + (s2.y - s1.getCenter().y) * (s2.y - s1.getCenter().y);
    }

	
}

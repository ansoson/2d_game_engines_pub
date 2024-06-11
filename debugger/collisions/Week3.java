package debugger.collisions;

import com.sun.javafx.collections.VetoableListDecorator;
import debugger.support.Vec2d;
import debugger.support.interfaces.Week3Reqs;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Fill this class in during Week 3. Make sure to also change the week variable in Display.java.
 */
public final class Week3 extends Week3Reqs {

	// AXIS-ALIGNED BOXES
	@Override
	public Vec2d collision(AABShape s1, AABShape s2) {
		if (s1.getTopLeft().x <= s2.getTopLeft().x + s2.getSize().x && s2.getTopLeft().x <= s1.getTopLeft().x + s1.getSize().x && s1.getTopLeft().y + s1.getSize().y >= s2.getTopLeft().y && s2.getTopLeft().y + s2.getSize().y >= s1.getTopLeft().y){
			ArrayList<Double> minilist = new ArrayList<>();
			HashMap<Double, Vec2d> directlist = new HashMap<>();
			minilist.add(Math.abs(s2.getTopLeft().y + s2.getSize().y  - s1.getTopLeft().y));
			directlist.put(Math.abs(s2.getTopLeft().y + s2.getSize().y  - s1.getTopLeft().y), new Vec2d(0, s2.getTopLeft().y + s2.getSize().y  - s1.getTopLeft().y));
			minilist.add(Math.abs(s2.getTopLeft().y - (s1.getTopLeft().y + s1.getSize().y)));
			directlist.put(Math.abs(s2.getTopLeft().y - (s1.getTopLeft().y + s1.getSize().y)), new Vec2d(0, s2.getTopLeft().y - (s1.getTopLeft().y + s1.getSize().y)));
			minilist.add(Math.abs(s2.getTopLeft().x + s2.getSize().x  - s1.getTopLeft().x));
			directlist.put(Math.abs(s2.getTopLeft().x + s2.getSize().x  - s1.getTopLeft().x), new Vec2d(s2.getTopLeft().x + s2.getSize().x  - s1.getTopLeft().x, 0));
			minilist.add(Math.abs(s2.getTopLeft().x  - (s1.getTopLeft().x + s1.getSize().x)));
			directlist.put(Math.abs(s2.getTopLeft().x  - (s1.getTopLeft().x + s1.getSize().x)), new Vec2d(s2.getTopLeft().x  - (s1.getTopLeft().x + s1.getSize().x), 0));
			Collections.sort(minilist);
			return directlist.get(minilist.get(0));
		}
		return null;
	}

	@Override
	public Vec2d collision(AABShape s1, CircleShape s2) {
		Vec2d closestToCenter = collision(s1, s2.getCenter());
		if (closestToCenter != null){
			Vec2d p = new Vec2d(s2.getCenter().x + closestToCenter.x, s2.getCenter().y + closestToCenter.y);
			double mag = s2.getRadius() + Math.sqrt((s2.getCenter().x - p.x) * (s2.getCenter().x - p.x) + (s2.getCenter().y - p.y) * (s2.getCenter().y - p.y));
			if  (p.x == s1.topLeft.x) {
				return new Vec2d(mag, 0);
			} else if (p.x == s1.topLeft.x + s1.getSize().x) {
				return new Vec2d(-mag, 0);
			} else if (p.y == s1.topLeft.y) {
				return new Vec2d(0, mag);
			} else {
				return new Vec2d(0, -mag);
			}
		}
		double pointX = Math.max(s1.getTopLeft().x, Math.min(s1.getTopLeft().x + s1.getSize().x, s2.getCenter().x));
		double pointY = Math.max(s1.getTopLeft().y, Math.min(s1.getTopLeft().y + s1.getSize().y, s2.getCenter().y));
		Vec2d clampedCenter = new Vec2d(pointX, pointY);
		if (collision(s2,clampedCenter) != null) {
			double mag = s2.getRadius() - Math.sqrt((s2.getCenter().x - clampedCenter.x) * (s2.getCenter().x - clampedCenter.x) + (s2.getCenter().y - clampedCenter.y) * (s2.getCenter().y - clampedCenter.y));
			double dir = (clampedCenter.y - s2.getCenter().y)/(clampedCenter.x - s2.getCenter().x);
			double moreRight = 1.0;
			double moreUp = 1.0;
			double isneg = 1.0;
			if (s1.getCenter().x < s2.getCenter().x) {moreRight = -1.0;}
			if (s1.getCenter().y < s2.getCenter().y) {moreUp = -1.0;}
			if (dir < 0) {isneg = -1.0;}
			if (s1.getTopLeft().x <= s2.getCenter().x & s1.getTopLeft().x + s1.getSize().x >= s2.getCenter().x) {
				return new Vec2d(0, moreUp * mag);
			}
			return new Vec2d(mag/(Math.sqrt(dir * dir + 1)) * moreRight, mag/(Math.sqrt(dir * dir + 1)) * dir * moreUp * isneg);
		};
		return null;
	}

	@Override
	public Vec2d collision(AABShape s1, Vec2d s2) {
		double topPointX = s1.getTopLeft().x;
		double topPointY = s1.getTopLeft().y;
		if (topPointX <= s2.x && s2.x <= topPointX + s1.getSize().x && topPointY <= s2.y && s2.y <= topPointY + s1.getSize().y) {
			ArrayList<Double> minilist = new ArrayList<>();
			HashMap<Double, Vec2d> directlist = new HashMap<>();
			minilist.add(Math.abs(s1.getTopLeft().y + s1.getSize().y  - s2.y));
			directlist.put(Math.abs(s1.getTopLeft().y + s1.getSize().y  - s2.y), new Vec2d(0, s1.getTopLeft().y + s1.getSize().y  - s2.y));

			minilist.add(Math.abs(s1.getTopLeft().y - (s2.y)));
			directlist.put(Math.abs(s1.getTopLeft().y - (s2.y)), new Vec2d(0, s1.getTopLeft().y - (s2.y)));

			minilist.add(Math.abs(s1.getTopLeft().x + s1.getSize().x  - s2.x));
			directlist.put(Math.abs(s1.getTopLeft().x + s1.getSize().x  - s2.x), new Vec2d(s1.getTopLeft().x + s1.getSize().x  - s2.x, 0));

			minilist.add(Math.abs(s1.getTopLeft().x  - (s2.x)));
			directlist.put(Math.abs(s1.getTopLeft().x  - (s2.x)), new Vec2d(s1.getTopLeft().x  - (s2.x), 0));
			Collections.sort(minilist);
			return directlist.get(minilist.get(0));
		}
		return null;
	}
	
	// CIRCLES

	@Override
	public Vec2d collision(CircleShape s1, AABShape s2) {
		Vec2d f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}

	@Override
	public Vec2d collision(CircleShape s1, CircleShape s2) {
		if((s1.getRadius() + s2.getRadius()) * (s2.getRadius() + s1.getRadius()) >= (s2.getCenter().x - s1.getCenter().x) * (s2.getCenter().x - s1.getCenter().x) + (s2.getCenter().y - s1.getCenter().y) * (s2.getCenter().y - s1.getCenter().y)) {
			double mag = s1.getRadius() + s2.getRadius() - Math.sqrt((s1.getCenter().x - s2.getCenter().x) * (s1.getCenter().x - s2.getCenter().x) +
																	(s1.getCenter().y - s2.getCenter().y) * (s1.getCenter().y - s2.getCenter().y));
			double dir = (s1.getCenter().y - s2.getCenter().y)/(s1.getCenter().x - s2.getCenter().x);
			double moreRight = 1.0;
			double moreUp = 1.0;
			double isneg = 1.0;
			if (s1.getCenter().x < s2.getCenter().x) {moreRight = -1.0;}
			if (s1.getCenter().y < s2.getCenter().y) {moreUp = -1.0;}
			if (dir < 0) {isneg = -1.0;}
			if (s1.getCenter().x == s2.getCenter().x) {
				return new Vec2d(0, moreUp * (s1.getRadius() + s2.getRadius() - Math.sqrt((s1.getCenter().x - s2.getCenter().x) * (s1.getCenter().x - s2.getCenter().x) + (s1.getCenter().y - s2.getCenter().y) * (s1.getCenter().y - s2.getCenter().y))));
			}
			return new Vec2d(mag/(Math.sqrt(dir * dir + 1)) * moreRight, mag/(Math.sqrt(dir * dir + 1)) * dir * moreUp * isneg);
		}
		return null;
	}

	@Override
	public Vec2d collision(CircleShape s1, Vec2d s2) {
		if ( s1.getRadius() * s1.getRadius() >= (s2.x - s1.getCenter().x) * (s2.x - s1.getCenter().x) + (s2.y - s1.getCenter().y) * (s2.y - s1.getCenter().y)) {
			double mag = s1.getRadius() - Math.sqrt((s2.x - s1.getCenter().x) * (s2.x - s1.getCenter().x) +
					(s2.y - s1.getCenter().y) * (s2.y - s1.getCenter().y));
			double dir = (s1.getCenter().y - s2.y )/(s1.getCenter().x - s2.x);
			return new Vec2d(mag/dir, mag * dir);
		}
		return null;
	}
}

package debugger.collisions;

import debugger.support.Interval;
import debugger.support.Vec2d;
import debugger.support.interfaces.Week6Reqs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Fill this class in during Week 6. Make sure to also change the week variable in Display.java.
 */
public final class Week6 extends Week6Reqs {

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

	@Override
	public Vec2d collision(AABShape s1, PolygonShape s2) {
		int index = 0;
		double min1DMTV = 50000;
		Vec2d minAxis = null;
		for (Vec2d point : s2.points) {
			if (index != 0) {
				Vec2d v = new Vec2d(point.x - s2.getPoint(index - 1).x, point.y - s2.getPoint(index - 1).y);
				Vec2d Vaxis = v.normalize().perpendicular();
				Interval s1Interval = new Interval();
				Interval s2Interval = new Interval();
				s1Interval.consider(s1.getTopLeft().dot(Vaxis));
				s1Interval.consider(new Vec2d(s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y + s1.getSize().y).dot(Vaxis));
				s1Interval.consider(new Vec2d(s1.getTopLeft().x, s1.getTopLeft().y + s1.getSize().y).dot(Vaxis));
				s1Interval.consider(new Vec2d(s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y).dot(Vaxis));
				for (Vec2d points2 : s2.points){
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
				Vec2d v = new Vec2d(point.x - s2.getPoint(s2.getNumPoints() - 1).x, point.y - s2.getPoint(s2.getNumPoints() - 1).y);
				Vec2d Vaxis = v.normalize().perpendicular();
				Interval s1Interval = new Interval();
				Interval s2Interval = new Interval();
				s1Interval.consider(s1.getTopLeft().dot(Vaxis));
				s1Interval.consider(new Vec2d(s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y + s1.getSize().y).dot(Vaxis));
				s1Interval.consider(new Vec2d(s1.getTopLeft().x, s1.getTopLeft().y + s1.getSize().y).dot(Vaxis));
				s1Interval.consider(new Vec2d(s1.getTopLeft().x + s1.getSize().x, s1.getTopLeft().y).dot(Vaxis));
				for (Vec2d points2 : s2.points) {
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
		for (Vec2d points2 : s2.points) {
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
		for (Vec2d points2 : s2.points) {
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
		return new Vec2d(min1DMTV * minAxis.x, min1DMTV * minAxis.y);
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

	@Override
	public Vec2d collision(CircleShape s1, PolygonShape s2) {
		String whatsec = "first";
		int index = 0;
		double min1DMTV = 50000;
		Vec2d minAxis = null;
		for (Vec2d point : s2.points) {
			if (index != 0) {
				Vec2d v = new Vec2d(point.x - s2.getPoint(index - 1).x, point.y - s2.getPoint(index - 1).y);
				Vec2d Vaxis = v.normalize().perpendicular();
				Interval s1Interval = new Interval();
				Interval s2Interval = new Interval();
				s1Interval.consider(new Vec2d(s1.getCenter().x + s1.getRadius() * Vaxis.x, s1.getCenter().y + s1.getRadius() * Vaxis.y).dot(Vaxis));
				s1Interval.consider(new Vec2d(s1.getCenter().x - s1.getRadius() * Vaxis.x, s1.getCenter().y - s1.getRadius() * Vaxis.y).dot(Vaxis));
				for (Vec2d points2 : s2.points){
					s2Interval.consider(points2.dot(Vaxis));
				}
				Double thisAxisMTV = s1Interval.returnMTV(s2Interval);
				if (thisAxisMTV == null) {
					return null;
				}
				if (Math.abs(min1DMTV) >= Math.abs(thisAxisMTV)) {
					min1DMTV = thisAxisMTV;
					minAxis = Vaxis;
					whatsec = "second";
				}
			} else {
				Vec2d v = new Vec2d(point.x - s2.getPoint(s2.getNumPoints() - 1).x, point.y - s2.getPoint(s2.getNumPoints() - 1).y);
				Vec2d Vaxis = v.normalize().perpendicular();
				Interval s1Interval = new Interval();
				Interval s2Interval = new Interval();
				s1Interval.consider(new Vec2d(s1.getCenter().x - s1.getRadius() * Vaxis.x, s1.getCenter().y - s1.getRadius() * Vaxis.y).dot(Vaxis));
				s1Interval.consider(new Vec2d(s1.getCenter().x + s1.getRadius() * Vaxis.x, s1.getCenter().y + s1.getRadius() * Vaxis.y).dot(Vaxis));
				for (Vec2d points2 : s2.points) {
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
			for (Vec2d points2 : s2.points){
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
		return new Vec2d(min1DMTV * minAxis.x, min1DMTV * minAxis.y);
	}
	
	// POLYGONS

	@Override
	public Vec2d collision(PolygonShape s1, AABShape s2) {
		Vec2d f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}

	@Override
	public Vec2d collision(PolygonShape s1, CircleShape s2) {
		Vec2d f = collision(s2, s1);
		return f == null ? null : f.reflect();
	}

	@Override
	public Vec2d collision(PolygonShape s1, Vec2d s2) {
		int index = 0;
		double min1DMTV = 100;
		for (Vec2d point : s1.points) {
			if (index != s1.getNumPoints() - 1) {
				Vec2d v = new Vec2d(point.x - s1.getPoint(index + 1).x, point.y - s1.getPoint(index + 1).y);
				Vec2d p = new Vec2d(point.x - s2.x, point.y - s2.y);
				double ret = v.cross(p);
				if (ret > 0) {
					return null;
				}
			} else {
				Vec2d v = new Vec2d(point.x - s1.getPoint(0).x, point.y - s1.getPoint(0).y);
				Vec2d p = new Vec2d(point.x - s2.x, point.y - s2.y);
				double ret = v.cross(p);
				if (ret > 0) {
					return null;
				}
			}
			index++;
		}
		return new Vec2d(1, 1);
	}

	@Override
	public Vec2d collision(PolygonShape s1, PolygonShape s2) {
		int index = 0;
		double min1DMTV = 50000;
		Vec2d minAxis = null;
		for (Vec2d point : s1.points) {
			if (index != s1.getNumPoints() - 1) {
				Vec2d v = new Vec2d(point.x - s1.getPoint(index + 1).x, point.y - s1.getPoint(index + 1).y);
				Vec2d Vaxis = v.normalize().perpendicular();
				Interval s1Interval = new Interval();
				Interval s2Interval = new Interval();
				for (Vec2d points1 : s1.points){
					s1Interval.consider(points1.dot(Vaxis));
				}
				for (Vec2d points2 : s2.points){
					s2Interval.consider(points2.dot(Vaxis));
				}
				Double thisAxisMTV = s1Interval.returnMTV(s2Interval);
				if (thisAxisMTV == null) {
					return null;
				}
				if (Math.abs(min1DMTV) > Math.abs(thisAxisMTV)) {
					min1DMTV = thisAxisMTV;
					minAxis = Vaxis;
				}
			} else {
				Vec2d v = new Vec2d(point.x - s1.getPoint(0).x, point.y - s1.getPoint(0).y);
				Vec2d Vaxis = v.normalize().perpendicular();
				Interval s1Interval = new Interval();
				Interval s2Interval = new Interval();
				for (Vec2d points1 : s1.points) {
					s1Interval.consider(points1.dot(Vaxis));
				}
				for (Vec2d points2 : s2.points) {
					s2Interval.consider(points2.dot(Vaxis));
				}
				Double thisAxisMTV = s1Interval.returnMTV(s2Interval);
				if (thisAxisMTV == null) {
					return null;
				}
				if (Math.abs(min1DMTV) > Math.abs(thisAxisMTV)) {
					min1DMTV = thisAxisMTV;
					minAxis = Vaxis;
				}
			}
			index++;
		}
		index = 0;
		for (Vec2d point : s2.points) {
			if (index != s2.getNumPoints() - 1) {
				Vec2d v = new Vec2d(point.x - s2.getPoint(index + 1).x, point.y - s2.getPoint(index + 1).y);
				Vec2d Vaxis = v.normalize().perpendicular();
				Interval s1Interval = new Interval();
				Interval s2Interval = new Interval();
				for (Vec2d points1 : s1.points){
					s1Interval.consider(points1.dot(Vaxis));
				}
				for (Vec2d points2 : s2.points){
					s2Interval.consider(points2.dot(Vaxis));
				}
				Double thisAxisMTV = s1Interval.returnMTV(s2Interval);
				if (thisAxisMTV == null) {
					return null;
				}
				if (Math.abs(min1DMTV) > Math.abs(thisAxisMTV)) {
					min1DMTV = thisAxisMTV;
					minAxis = Vaxis;
				}
			} else {
				Vec2d v = new Vec2d(point.x - s2.getPoint(0).x, point.y - s2.getPoint(0).y);
				Vec2d Vaxis = v.normalize().perpendicular();
				Interval s1Interval = new Interval();
				Interval s2Interval = new Interval();
				for (Vec2d points1 : s1.points) {
					s1Interval.consider(points1.dot(Vaxis));
				}
				for (Vec2d points2 : s2.points) {
					s2Interval.consider(points2.dot(Vaxis));
				}
				Double thisAxisMTV = s2Interval.returnMTV(s1Interval);
				if (thisAxisMTV == null) {
					return null;
				}
				if (Math.abs(min1DMTV) > Math.abs(thisAxisMTV)) {
					min1DMTV = thisAxisMTV;
					minAxis = Vaxis;
				}
			}
			index++;
		}
		return new Vec2d(min1DMTV * minAxis.x, min1DMTV * minAxis.y);
	}

	
	// RAYCASTING
	
	@Override
	public float raycast(AABShape s1, Ray s2) {
		double minIntersect = 5000;
		Vec2d m = new Vec2d(1,0);
		Vec2d n = new Vec2d(0,1);
		Vec2d a = s1.getTopLeft();
		Vec2d b = new Vec2d(a.x + s1.getSize().x, a.y);
		Vec2d p = s2.src;
		Vec2d d = s2.dir;
		minIntersect = edgeIntersection(m, n, a, b, p, d, minIntersect);
		m = new Vec2d(1,0);
		n = new Vec2d(0,1);
		a = new Vec2d(s1.getTopLeft().x , s1.getTopLeft().y + s1.getSize().y);
		b = new Vec2d(a.x + s1.getSize().x, a.y);
		p = s2.src;
		d = s2.dir;
		minIntersect = edgeIntersection(m, n, a, b, p, d, minIntersect);
		m = new Vec2d(0,1);
		n = new Vec2d(1,0);
		a = new Vec2d(s1.getTopLeft().x , s1.getTopLeft().y);
		b = new Vec2d(a.x, a.y + s1.getSize().y);
		p = s2.src;
		d = s2.dir;
		minIntersect = edgeIntersection(m, n, a, b, p, d, minIntersect);
		m = new Vec2d(0,1);
		n = new Vec2d(1,0);
		a = new Vec2d(s1.getTopLeft().x + s1.getSize().x , s1.getTopLeft().y);
		b = new Vec2d(a.x, a.y + s1.getSize().y);
		p = s2.src;
		d = s2.dir;
		minIntersect = edgeIntersection(m, n, a, b, p, d, minIntersect);
		if (minIntersect < 5000) {
			return (float) minIntersect;
		} else {
			return -1;
		}
	}
	
	@Override
	public float raycast(CircleShape s1, Ray s2) {
		double projPointT = s1.getCenter().dot(s2.dir);
		Vec2d projPoint = s1.getCenter().projectOnto(s2.dir);
		if (collision(s1, projPoint) != null && projPointT >=0){
			double L = s2.src.dist(projPoint);
			Vec2d intersectPT = new Vec2d(s2.src.x + s2.dir.x * (L - Math.sqrt(s1.getRadius()*s1.getRadius() - projPoint.dist2(s1.getCenter()))),
											s2.src.y + s2.dir.y * (L - Math.sqrt(s1.getRadius()*s1.getRadius() - projPoint.dist2(s1.getCenter()))));
			return (float) s2.src.dist(intersectPT);
		}
		if (collision(s1, projPoint) != null && projPointT < 0){
			return 20;
		}
		return -1;
	}
	
	@Override
	public float raycast(PolygonShape s1, Ray s2) {
		int index = 0;
		double minIntersect = 5000;
		for (Vec2d point : s1.points) {
			if (index != s1.getNumPoints() - 1) {
				Vec2d m = new Vec2d(point.x - s1.getPoint(index + 1).x, point.y - s1.getPoint(index + 1).y).normalize();
				Vec2d n = m.perpendicular();
				Vec2d a = point;
				Vec2d b = s1.getPoint(index + 1);
				Vec2d p = s2.src;
				Vec2d d = s2.dir;
				minIntersect = edgeIntersection(m, n, a, b, p, d, minIntersect);
			} else {
				Vec2d m = new Vec2d(point.x - s1.getPoint(0).x, point.y - s1.getPoint(0).y).normalize();
				Vec2d n = m.perpendicular();
				Vec2d a = point;
				Vec2d b = s1.getPoint(0);
				Vec2d p = s2.src;
				Vec2d d = s2.dir;
				minIntersect = edgeIntersection(m, n, a, b, p, d, minIntersect);
			}
			index++;
		}
		if (minIntersect < 5000) {
			return (float) minIntersect;
		} else {
			return -1;
		}
	}

	public double edgeIntersection(Vec2d m, Vec2d n, Vec2d a, Vec2d b, Vec2d p, Vec2d d, double minIntersect){
		double val1 = a.minus(p).cross(d);
		double val2 = b.minus(p).cross(d);
		if (val1 * val2 < 0) {
			double t = b.minus(p).dot(n) / (d.dot(n));
			Vec2d q = p.plus(new Vec2d(d.x * t, d.y * t));
			if (t < minIntersect) {
				return t;
			}
		}
		return minIntersect;
	}
}

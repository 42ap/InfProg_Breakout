package model.objects;

import acm.graphics.GRect;
import model.Vector;

public abstract class RectangularObject implements GameObject {
	public Vector loc, size;
	private Vector center;

	public RectangularObject(double x, double y, double w, double h) {
		loc = new Vector(x, y);
		size = new Vector(w, h);
		
		center = loc.plus(size.times(0.5));
	}
	
	
	public double left() {
		return loc.x;
	}
	
	public double right() {
		return loc.x + size.x;
	}
	
	public double top() {
		return loc.y;
	}
	
	public double bottom() {
		return loc.y + size.y;
	}
	
	
	

	@Override
	public boolean contains(Vector pt) {
		return Math.abs(pt.x - center.x) < 0.5*size.x && Math.abs(pt.y - center.y) < 0.5*size.y;
	}
	
	public void onCollision(DeflectDirection d) { }

	@Override
	public GRect toGObject(double cvsWidth, double cvsHeight) {
		return new GRect(loc.x * cvsWidth, loc.y * cvsHeight, size.x * cvsWidth, size.y * cvsHeight);
	}
}

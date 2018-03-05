package model.object;

import acm.graphics.GRect;
import model.Vector;

public abstract class RectangularObject implements GameObject {
	public Vector loc, size;

	public RectangularObject(double x, double y, double w, double h) {
		loc = new Vector(x, y);
		size = new Vector(w, h);
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
	
	
	public double getCenterX() {
		return loc.x + 0.5 * size.x;
	}
	

	
	public void onCollision(DeflectDirection d) { }

	@Override
	public GRect toGObject(double cvsWidth, double cvsHeight) {
		double aspectRatio = cvsWidth / cvsHeight;
		return new GRect(loc.x * cvsWidth, loc.y * aspectRatio * cvsHeight, size.x * cvsWidth, size.y * aspectRatio * cvsHeight);
	}
}

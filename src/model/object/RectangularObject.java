package model.object;

import acm.graphics.GRect;
import model.Vector;

/**
 * Superclass for all rectangle-shaped game objects.
 */
public abstract class RectangularObject implements GameObject {
	
	// Default fields
	protected Vector loc, size;

	/**
	 * Constructor.
	 * @param x The x of the rectangle
	 * @param y The y of the rectangle
	 * @param w The width of the rectangle
	 * @param h The height of the rectangle
	 */
	public RectangularObject(double x, double y, double w, double h) {
		loc = new Vector(x, y);
		size = new Vector(w, h);
	}
	
	
	
	// ***** GETTER *****
	
	
	/**
	 * Returns the x of the left bound of this object.
	 */
	@Override
	public double left() {
		return loc.x;
	}
	
	/**
	 * Returns the x of the right bound of this object.
	 */
	@Override
	public double right() {
		return loc.x + size.x;
	}
	
	/**
	 * Returns the y of the top bound of this object.
	 */
	@Override
	public double top() {
		return loc.y;
	}
	
	/**
	 * Returns the y of the bottom bound of this object.
	 */
	@Override
	public double bottom() {
		return loc.y + size.y;
	}
	
	/**
	 * Returns the center of this object.
	 */
	@Override
	public Vector center() {
		return Vector.plus(loc, Vector.times(size, 0.5));
	}
	

	/**
	 * Default empty collision method
	 */
	public void onCollision(DeflectDirection d) { }

	
    /**
     * Turns this model into a displayable GObject.
     * @param cvsWidth The width of the canvas
     * @param cvsHeight The height of the canvas
     */
	@Override
	public GRect toGObject(double cvsWidth, double cvsHeight) {
		double aspectRatio = cvsWidth / cvsHeight;
		return new GRect(loc.x * cvsWidth, loc.y * aspectRatio * cvsHeight, size.x * cvsWidth, size.y * aspectRatio * cvsHeight);
	}
}

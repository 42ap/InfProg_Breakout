package model.object;

import acm.graphics.GObject;
import model.Vector;


/**
 * An interface all game objects have to implement.
 */
public interface GameObject {
	
	// An default center vector
	Vector center = new Vector(0,0);
	
	
	// Getter for the surrounding bounds
	public double left();
	
	public double right();
	
	public double top();
	
	public double bottom();
	
	public Vector center();
	
	
	// Collision	
	public void onCollision(DeflectDirection d);
	

	// Conversion to Gobject
	public GObject toGObject(double cvsWidth, double cvsHeight);
}

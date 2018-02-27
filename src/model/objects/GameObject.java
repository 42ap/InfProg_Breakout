package model.objects;

import acm.graphics.GObject;
import model.Vector;

public abstract class GameObject {
	protected Vector center;
	public abstract GObject toGObject();
	
	
	public Vector getCenter() { return center; }
}

package model.objects;

import acm.graphics.GObject;
import model.Vector;

public interface GameObject {
	Vector center = new Vector(0,0);
	
	public void onCollision(DeflectDirection d);
	
	public GObject toGObject(double cvsWidth, double cvsHeight, double aspectRatio);
}

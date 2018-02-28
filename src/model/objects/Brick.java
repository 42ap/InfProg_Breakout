package model.objects;

import java.awt.Color;

import acm.graphics.GRect;

public class Brick extends RectangularObject {

	public boolean isAlive;
	
	public Brick(double x, double y, double w, double h) {
		super(x, y, w, h);
		isAlive = true;
	}


	@Override
	public void onCollision(DeflectDirection d) {
		isAlive = false;
	}
	
	
	@Override
	public GRect toGObject(double cvsWidth, double cvsHeight) {
		GRect brick = new GRect(loc.x * cvsWidth, loc.y * cvsHeight, size.x * cvsWidth, size.y * cvsHeight);
		brick.setFilled(true);
		brick.setColor(Color.orange);
		return brick;
	}
}

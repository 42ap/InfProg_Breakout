package model.objects;

import java.awt.Color;

import acm.graphics.GRect;
import model.Helper;

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
	public GRect toGObject(double cvsWidth, double cvsHeight, double aspectRatio) {
		GRect brick = super.toGObject(cvsWidth, cvsHeight, aspectRatio);
		brick.setFilled(true);
		brick.setColor(Color.orange);
		return brick;
	}
}

package model.objects;

import java.awt.Color;

import acm.graphics.GRect;
import model.Vector;

public class Paddle extends RectangularObject implements Updateable {
	public double speed;

	public Paddle(double x, double y, double w, double h) {
		super(x, y, w, h);
		this.speed = 0.0;
	}

	@Override
	public void update(double frameTime) {
		this.loc.x += speed;
	}
	
	
	@Override
	public GRect toGObject(double cvsWidth, double cvsHeight) {
		GRect paddle = new GRect(loc.x * cvsWidth, loc.y * cvsHeight, size.x * cvsWidth, size.y * cvsHeight);
		paddle.setFilled(true);
		paddle.setColor(Color.blue);
		return paddle;
	}
}

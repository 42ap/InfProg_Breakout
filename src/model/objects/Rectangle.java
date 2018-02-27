package model.objects;

import acm.graphics.GRect;
import model.Vector;

public abstract class Rectangle extends Collidable {
	public double halfwidth;
	public double halfheight;

	public Rectangle(double xPos, double yPos, double width, double height) {
		halfwidth = width / 2;
		halfheight = height / 2;
		center = new Vector(xPos + halfwidth, yPos + halfheight);
	}

	@Override
	public boolean contains(Vector point) {
		return Math.abs(point.getX() - center.getX()) < halfwidth && Math.abs(point.getY() - super.center.getY()) < halfheight;
	}

	@Override
	public GRect toGObject() {
		return new GRect(super.center.getX() - halfwidth, super.center.getY() - halfheight, 2 * halfwidth, 2 * halfheight);
	}
	
	@Override
	public void deflect(Vector impactPosition, Ball ball) {
		Vector velocity = center.minus(impactPosition);
		if (Math.abs(velocity.getX()) * halfheight > Math.abs(velocity.getY()) * halfwidth)
			ball.velocity.setX(-ball.velocity.getX());
		else
			ball.velocity.setY(-ball.velocity.getY());
	}
}

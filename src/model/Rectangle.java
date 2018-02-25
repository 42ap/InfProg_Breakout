package model;

import acm.graphics.GRect;

abstract class Rectangle extends CollisionObject {
	double halfwidth;
	double halfheight;

	public Rectangle(double xPos, double yPos, double width, double height) {
		halfwidth = width / 2;
		halfheight = height / 2;
		super.center = new Vector(xPos + halfwidth, yPos + halfheight);
	}

	@Override
	public boolean contains(Vector point) {
		return Math.abs(point._1 - super.center._1) < halfwidth && Math.abs(point._2 - super.center._2) < halfheight;
	}

	@Override
	public GRect toGObject() {
		return new GRect(super.center._1 - halfwidth, super.center._2 - halfheight, 2 * halfwidth, 2 * halfheight);
	}
	
	@Override
	public void deflect(Vector impactPosition, Ball ball) {
		Vector velocity = center.minus(impactPosition);
		if (Math.abs(velocity._1) * halfheight > Math.abs(velocity._2) * halfwidth)
			ball.velocity._1 *= -1;
		else
			ball.velocity._2 *= -1;
	}
}

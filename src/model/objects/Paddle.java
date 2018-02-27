package model.objects;

import model.Vector;

public class Paddle extends Rectangle {
	
	double speed;
	int counter = 0;

	public Paddle(double xPos, double yPos, double width, double height, double speed) {
		super(xPos, yPos, width, height);
		this.speed = speed;
		super.collisionEvent = CollisionEvent.DEFLECT;
	}

	/**
	 * based on householder transformation.
	 */
	@Override
	public void deflect(Vector impactPosition, Ball ball) {
		if (counter == 0) {
			double rel = (center.getX() - impactPosition.getX()) / halfwidth;
			Vector v = new Vector(rel, 3 + rel * -ball.velocity.getX() / ball.velocity.getX());
			v.mult(1 / v.abs());
			ball.velocity = ball.velocity.minus(v.times(2 * (v.getX() * ball.velocity.getX() + v.getX() * ball.velocity.getX())));
			counter = 250;
		}
	}

	public void update(int mouseX) {
		if (mouseX > center.getX() + halfwidth)
			center.setX(center.getY() + speed);
		if (mouseX < center.getX() - halfwidth)
			center.setX(center.getY() - speed);
		
		counter = Math.max(0, counter--);
	}

}

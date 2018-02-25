package model;

class Paddle extends Rectangle {
	
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
			double rel = (center._1 - impactPosition._1) / halfwidth;
			Vector v = new Vector(rel,
					3 + rel * -ball.velocity._1 / ball.velocity._2);
			v.mult(1 / v.abs());
			ball.velocity = ball.velocity.minus(v.times(2 * (v._1 * ball.velocity._1 + v._2 * ball.velocity._2)));
			counter = 250;
		}
	}

	public void move(int mouseX) {
		if (mouseX > super.center._1 + halfwidth)
			super.center._1 += speed;
		if (mouseX < super.center._1 - halfwidth)
			super.center._1 -= speed;
		if (counter > 0)
			counter--;
	}

}

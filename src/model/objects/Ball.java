package model.objects;

import acm.graphics.GOval;
import model.Vector;

public class Ball implements GameObject, Updateable {
	public Vector center;
	public double radius;
	Vector velocity; // velocity per 100ms

	public Ball(Vector center, double radius, Vector velocity) {
		this.center = center;
		this.radius = radius;
		this.velocity = velocity;
	}



	@Override
	public boolean contains(Vector point) {
		return (Vector.distSqr(center, point) <= radius*radius);
	}

	@Override
	public void update(double frameTime) {
		center.add(velocity.times(frameTime / 100));
	}

	@Override
	public void onCollision(DeflectDirection d) {
		switch (d) {
		case UpDown:
			velocity.y *= -1;
			break;
		case LeftRight:
			velocity.x *= -1;
			break;
		default:
			break;
		}			
	}
	
	
	@Override
	public GOval toGObject(double cvsWidth, double cvsHeight) {
		double scalar = Math.min(cvsWidth, cvsHeight);
		GOval ball = new GOval((center.x - radius) * cvsWidth, (center.y - radius) * cvsHeight, 2 * radius * cvsWidth, 2 * radius * cvsHeight);
		ball.setFilled(true);
		return ball;
	}


}

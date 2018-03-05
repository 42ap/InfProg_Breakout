package model.object;

import java.awt.Color;

import acm.graphics.GOval;
import model.Vector;

public class Ball implements GameObject, Updateable {
	public Vector center;
	public double radius;
	public Vector velocity; // velocity per 100ms

	public Ball(Vector center, double radius, Vector velocity) {
		this.center = center;
		this.radius = radius;
		this.velocity = velocity;
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
	
	
	
    public void householderCollision(Vector d) {
        velocity = velocity.minus(d.times(2 * (d.x * velocity.x + d.y * velocity.y)));
    }
	
	
	@Override
	public GOval toGObject(double cvsWidth, double cvsHeight) {
		double aspectRatio = cvsWidth / cvsHeight;
		GOval ball = new GOval((center.x - radius) * cvsWidth, (center.y - radius) * cvsHeight * aspectRatio, 2 * radius * cvsWidth, 2 * radius * cvsHeight * aspectRatio);
		ball.setFilled(true);
		ball.setColor(new Color(245, 245, 245));
		return ball;
	}


}

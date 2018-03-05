package model;

import java.util.ArrayList;

import acm.graphics.*;
import model.object.*;


public class GameModel {
	
	public Ball ball;
	public Bounds bounds;
	public Paddle paddle;
	public ArrayList<Brick> bricks;
	
	public boolean isRunning;
	
	
	public GameModel(double aspectRatio) {		
		bounds = new Bounds(0, 0, 1, 1/aspectRatio);
		ball = new Ball(new Vector(0.9, 0.9 * bounds.size.y), 0.01, new Vector(0.03, -0.03));
		paddle = new Paddle(0.5, 0.95 * bounds.size.y, 0.2, 0.03);
		
		bricks = new ArrayList<Brick>();
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 1; j++)
				bricks.add(new Brick(0.075 + (0.15 * i), 0.05 + (0.08 * j), 0.1, 0.03));
	}
	
	
	
	public void start() {
		isRunning = true;
	}
	
	

	public void update(double frameTime, double mouseX) {	
		// ** Check game status
		isRunning = isRunning && !(isWon() || isLost());
		if (!isRunning)
			return;
		
		
		// ** Check mouse
        if (mouseX > 0 && mouseX < 1) {
        	double delta = (mouseX - paddle.getCenterX());
        	paddle.speed = 3 * ball.velocity.abs() * Math.signum(delta) * Math.sqrt(Math.abs(delta));  
        }
        
		
		// ** Check collision
		// Ball <-> Bounds
		if (ball.center.x - ball.radius <= bounds.loc.x || ball.center.x + ball.radius >= bounds.size.x) {
			ball.onCollision(DeflectDirection.LeftRight);
			ball.update(frameTime);
		}
		if (ball.center.y - ball.radius <= bounds.loc.y) {
			ball.onCollision(DeflectDirection.UpDown);
			ball.update(frameTime);
		}
		
		// Ball <-> Paddle
        if (ball.center.y + ball.radius >= paddle.top()) {
            if ( ball.center.x > paddle.loc.x && ball.center.x < paddle.right()) {
            	// Householder reflection
                double rel = (paddle.getCenterX() - ball.center.x) / (2*paddle.size.x);
                Vector v = new Vector(rel, 1 + rel * -ball.velocity.x / ball.velocity.y);
                v.mult(1 / v.abs());
                ball.update(-frameTime); // Basically updating back in time, so that the ball is outside the paddle for sure (as it was one frame ago), and the collision is not triggering every frame
                ball.householderCollision(v);
                ball.update(frameTime);
            }
        }
		
		// Ball <-> Bricks	
		for (int i = bricks.size()-1; i >= 0; i--) {
			Brick b = bricks.get(i);
			
			// Find the closest point to the circle within the rectangle
			Vector closestPt = new Vector(Helper.clamp(ball.center.x, b.left(), b.right()), Helper.clamp(ball.center.y, b.top(), b.bottom()));
			// Calculate the distance between the circle's center and this closest point
			double dist = Vector.distSqr(ball.center, closestPt);
			
			
			// If the distance is less than the circle's radius, an intersection occurs
			if (dist < (ball.radius * ball.radius)) {
				DeflectDirection dd = (Helper.isBetween(ball.center.x, b.left(), b.right()) ? DeflectDirection.UpDown : DeflectDirection.LeftRight);
				ball.onCollision(dd);
				ball.update(frameTime);
				b.onCollision(null);
				
				if (b.state == 0)
					bricks.remove(i);
			}
		}
		
		
		
		// Update objects
		paddle.update(frameTime);
		ball.update(frameTime);
	}


	
	
	
	private boolean isWon() {
		return bricks.isEmpty();
	}
	
	private boolean isLost() {
		return ball.center.y > paddle.bottom();
	}

}
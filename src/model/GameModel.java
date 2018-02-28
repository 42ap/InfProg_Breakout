package model;

import java.util.ArrayList;
import model.objects.*;
import acm.graphics.*;


public class GameModel {
	
	protected Ball ball;
	protected Bounds bounds;
	protected Paddle paddle;
	protected ArrayList<Brick> bricks;
	
	public boolean isRunning;
	
	
	public GameModel() {
		ball = new Ball(new Vector(0.9, 0.03), 0.02, new Vector(0.02, 0.02));
		bounds = new Bounds(0, 0, 1, 1);
		paddle = new Paddle(0.5, 0.95, 0.2, 0.03);
		
		bricks = new ArrayList<Brick>();
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 5; j++)
				bricks.add(new Brick(0.075 + (0.15 * i), 0.05 + (0.08 * j), 0.1, 0.05));
		
		isRunning = true;
	}
	
	
	

	public void update(double frameTime, double mouseX) {
		// ** Check game status
		isRunning = !(isWon() || isLost());
		
		// ** Check mouse
		if (mouseX > 0 && mouseX < 1) {
			paddle.loc.x = mouseX - 0.5 * paddle.size.x;
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
		if (ball.center.y + ball.radius >= paddle.loc.y) {
			if ( ball.center.x > paddle.loc.x && ball.center.x < paddle.loc.x+paddle.size.x) {
				ball.onCollision(DeflectDirection.UpDown);
				ball.update(frameTime);
			}
		}
		
		// Ball <-> Bricks
		double ballLeft = ball.center.x - ball.radius;
		double ballRight = ball.center.x + ball.radius;
		double ballTop = ball.center.y - ball.radius;
		double ballBottom = ball.center.y + ball.radius;
		
		for (int i = bricks.size()-1; i > 0; i--) {
			Brick b = bricks.get(i);
			
			// Find the closest point to the circle within the rectangle
			Vector closestPt = new Vector(clamp(ball.center.x, b.left(), b.right()), clamp(ball.center.y, b.top(), b.bottom()));
			// Calculate the distance between the circle's center and this closest point
			double dist = Vector.distSqr(ball.center, closestPt);
			
			
			// If the distance is less than the circle's radius, an intersection occurs
			if (dist < (ball.radius * ball.radius)) {
				DeflectDirection dd = (isBetween(ball.center.x, b.left(), b.right()) ? DeflectDirection.UpDown : DeflectDirection.LeftRight);
				ball.onCollision(dd);
				ball.update(frameTime);
				bricks.remove(i);
			}
		}
		
		
		
		// Update objects
		paddle.update(frameTime);
		ball.update(frameTime);
	}

	public ArrayList<GObject> toGObjects(double cvsWidth, double cvsHeight) {
		ArrayList<GObject> result = new ArrayList<GObject>();
		
		result.add(paddle.toGObject(cvsWidth, cvsHeight));
		result.add(ball.toGObject(cvsWidth, cvsHeight));
		
		for (Brick brick : bricks)
			result.add(brick.toGObject(cvsWidth, cvsHeight));
		
		
		GRect bounds = new GRect(1, 1, cvsWidth-2, cvsHeight-2);
		result.add(bounds);
		
		return result;
	}
	
	
	
	
	private double clamp(double val, double low, double high) {
		if (val < low)
			return low;
		else if (val > high)
			return high;
		
		return val;
	}
	
	private boolean isBetween(double val, double low, double high) {
		return val <= high && val >= low;
	}
	
	
	
	
	private boolean isWon() {
		return bricks.isEmpty();
	}
	
	private boolean isLost() {
		return ball.center.y > bounds.loc.y + bounds.size.y;
	}

}
